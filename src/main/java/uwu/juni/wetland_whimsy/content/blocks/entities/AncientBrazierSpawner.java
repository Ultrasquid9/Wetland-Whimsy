package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.neoforge.event.EventHooks;
import uwu.juni.wetland_whimsy.client.particles.coloredfire.ColoredFireParticleOptions;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.datagen.loot.WetlandWhimsyMiscLoot;
import uwu.juni.wetland_whimsy.datapacks.Incense;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class AncientBrazierSpawner extends BaseSpawner {
	private int spawnedEntityCount;
	private SimpleWeightedRandomList<ResourceKey<LootTable>> lootTablesToEject;

	public AncientBrazierSpawner() {
		spawnedEntityCount = 0;

		lootTablesToEject = new SimpleWeightedRandomList.Builder<ResourceKey<LootTable>>()
			.add(WetlandWhimsyMiscLoot.INTERMEDIATE_LOOT, 1)
			.add(WetlandWhimsyMiscLoot.EPIC_LOOT, 1)
			.add(WetlandWhimsyMiscLoot.ANCIENT_COIN, 2)
			.build();
	}

	@Override
	public void broadcastEvent(Level level, BlockPos pos, int id) {
		level.blockEvent(pos, WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(), id, 0);
	}

	@Override
	public void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData data) {
		super.setNextSpawnData(level, pos, data);

		if (level != null) {
			var blockstate = level.getBlockState(pos);
			level.sendBlockUpdated(pos, blockstate, blockstate, 4);
		}
	}

	@Override
	public void serverTick(ServerLevel level, BlockPos pos) {
		if (!level.getBlockState(pos).getValue(AncientBrazierBlock.FLAME).equals(AncientBrazierBlock.Flame.LIT)) {
			return;
		}

		var random = level.getRandom();
		
		if (spawnDelay > 3) {
			spawnDelay -= 3;
		} else if (spawnDelay > 0) {
			spawnDelay -= 1;
		}
		
		if (spawnDelay == 1) {
			spawnedEntityCount++;
			
			var entity = level.getBlockEntity(pos);
			if (entity != null) {
				entity.setChanged();
			}

			setRandomEntity(level, pos);
		}

		var uuid = spawnDelay <= 0 
			? spawnMob(level, pos) 
			: Optional.empty();

		if (uuid.isPresent()) {
			spawnDelay = random.nextInt(200, 400);

			for (var i = 0; i < random.nextInt(2, 4); i++) {
				spawnMob(level, pos);
			}
		}

		if (spawnedEntityCount >= 8) {
			spawnedEntityCount = 0;
			level.setBlock(
				pos, 
				level
					.getBlockState(pos)
					.setValue(AncientBrazierBlock.FLAME, AncientBrazierBlock.Flame.SMOLDERING), 
				2
			);
			ejectLoot(level, pos, random);

			var be = level.getBlockEntity(pos);
			if (be instanceof AncientBrazierBlockEntity ab) {
				ab.killIncense();
			}

			return;
		}
	}

	public void setRandomEntity(ServerLevel level, BlockPos pos) {
		var be = level.getBlockEntity(pos);
		var random = level.getRandom();

		EntityType<?> entity;

		Function<AncientBrazierBlockEntity, EntityType<?>> getEntity = ab -> {
			var entities = ab.getIncense(level).get().entities();
			var index = random.nextInt(0, entities.size());
			return entities.get(index);
		};

		entity = (be instanceof AncientBrazierBlockEntity ab && ab.hasIncense())
			? getEntity.apply(ab)
			: level
				.registryAccess()
				.lookupOrThrow(Registries.ENTITY_TYPE)
				.getOrThrow(WetlandWhimsyTags.Entities.SPAWNS_FROM_ANCIENT_BRAZIER)
				.getRandomElement(random)
				.get()
				.value();

		setEntityId(entity, level, random, pos);
	}

	// Copied from Vanilla's TrialSpawner class
	public Optional<UUID> spawnMob(ServerLevel level, BlockPos pos) {
		var randomsource = level.getRandom();
		var spawndata = getOrCreateNextSpawnData(level, level.getRandom(), pos);
		var compoundtag = spawndata.entityToSpawn();
		var listtag = compoundtag.getList("Pos", 6);
		var optional = EntityType.by(compoundtag);

		if (optional.isEmpty()) { 
			return Optional.empty();
		}

		int i = listtag.size();
		var d0 = i >= 1
			? listtag.getDouble(0)
			: pos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * 7 + 0.5;
		var d1 = i >= 2 ? listtag.getDouble(1) : pos.getY() + randomsource.nextInt(3) - 1;
		var d2 = i >= 3
			? listtag.getDouble(2)
			: pos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * 7 + 0.5;

		if (!level.noCollision(optional.get().getSpawnAABB(d0, d1, d2))) {
			return Optional.empty();
		}

		var vec3 = new Vec3(d0, d1, d2);
		if (!inLineOfSight(level, pos.getCenter(), vec3)) {
			return Optional.empty();
		}

		var blockpos = BlockPos.containing(vec3);
		if (!SpawnPlacements.checkSpawnRules(optional.get(), level, MobSpawnType.TRIAL_SPAWNER, blockpos, level.getRandom())) {
			return Optional.empty();
		}

		if (spawndata.getCustomSpawnRules().isPresent()) {
			SpawnData.CustomSpawnRules spawndata$customspawnrules = spawndata.getCustomSpawnRules().get();
			if (!spawndata$customspawnrules.isValidPosition(blockpos, level)) {
				return Optional.empty();
			}
		}

		var entity = EntityType.loadEntityRecursive(
			compoundtag, 
			level, 
			e -> {
				e.moveTo(d0, d1, d2, randomsource.nextFloat() * 360.0F, 0.0F);
				return e;
			}
		);
		if (entity == null) {
			return Optional.empty();
		}

		if (entity instanceof Mob mob) {
			if (!mob.checkSpawnObstruction(level)) {
				return Optional.empty();
			}

			var flag = spawndata.getEntityToSpawn().size() == 1 && spawndata.getEntityToSpawn().contains("id", 8);
			EventHooks.finalizeMobSpawnSpawner(
				mob, 
				level, 
				level.getCurrentDifficultyAt(mob.blockPosition()), 
				MobSpawnType.TRIAL_SPAWNER, 
				// SpawnGroupData is an interface that does nothing. Why? Who the fuck knows!
				// Passing in null is safe, but it creates a warning anyways so I pass a garbage value instead.
				new SpawnGroupData() {}, 
				this, 
				flag
			);

			mob.setPersistenceRequired();
			spawndata.getEquipment().ifPresent(mob::equip);
		}

		if (!level.tryAddFreshEntityWithPassengers(entity)) {
			return Optional.empty();
		}

		effects(pos, blockpos, level);
		level.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);

		return Optional.of(entity.getUUID());
	}

	private void effects(BlockPos pos, BlockPos mobSpawnPos, ServerLevel level) {
		var ab = (AncientBrazierBlockEntity)level.getBlockEntity(pos);
		var random = level.getRandom();

		BiConsumer<BlockPos, Incense> spawnParticles = (blockpos, incense) -> {
			for (var i = 0; i < random.nextInt(8, 10); i++) {
				level.sendParticles(
					new ColoredFireParticleOptions(incense.color(), (float)random.nextInt(18, 24) / 10),
					blockpos.getX() + ((float)random.nextInt(-2, 12)) / 10, 
					blockpos.getY() + ((float)random.nextInt(-2, 12)) / 10, 
					blockpos.getZ() + ((float)random.nextInt(-2, 12)) / 10, 
					1, 
					0, 
					0, 
					0, 
					0
				);
			}
		};

		if (ab != null && ab.hasIncense()) {
			var incense = ab.getIncense(level).get();

			spawnParticles.accept(pos, incense);
			spawnParticles.accept(mobSpawnPos, incense);

			level.playSound(
				null, 
				mobSpawnPos, 
				SoundEvents.TRIAL_SPAWNER_SPAWN_MOB, 
				SoundSource.BLOCKS
			);
		} else {
			level.levelEvent(3011, pos, 1);
			level.levelEvent(3012, mobSpawnPos, 1); 
		}
	}

	private static boolean inLineOfSight(Level level, Vec3 spawnerPos, Vec3 mobPos) {
		var blockhitresult = level.clip(
			new ClipContext(
				mobPos, 
				spawnerPos, 
				ClipContext.Block.VISUAL, 
				ClipContext.Fluid.NONE, 
				CollisionContext.empty()
			)
		);
		return blockhitresult.getBlockPos().equals(BlockPos.containing(spawnerPos)) || blockhitresult.getType() == HitResult.Type.MISS;
	}

	private void ejectLoot(ServerLevel level, BlockPos pos, RandomSource random) {
		var x = lootTablesToEject.getRandom(random);
		if (x.isEmpty()) {
			return;
		}

		var be = level.getBlockEntity(pos);
		var table = level.getServer()
			.reloadableRegistries()
			.getLootTable(
				(be != null && be instanceof AncientBrazierBlockEntity ab && ab.hasIncense())
					? ab.getIncense(level).get().getLootKey()
					: x.get().data()	
			);

		var params = new LootParams.Builder(level).create(LootContextParamSets.EMPTY);
		var loot = table.getRandomItems(params);
		if (loot.isEmpty()) {
			return;
		}

		for (var itemstack : loot) {
			DefaultDispenseItemBehavior.spawnItem(
				level, 
				itemstack, 
				2, 
				Direction.UP,
				Vec3.atBottomCenterOf(pos).relative(Direction.UP, 1.2)
			);
		}

		level.levelEvent(3014, pos, 0);
	}

	@Override
	public CompoundTag save(@Nonnull CompoundTag tag) {
		tag.putShort("SpawnedEntityCount", (short)this.spawnedEntityCount);
		return super.save(tag);
	}

	@Override
	public void load(@Nullable Level level, @Nonnull BlockPos pos, @Nonnull CompoundTag tag) {
		spawnedEntityCount = tag.getInt("SpawnedEntityCount");
		super.load(level, pos, tag);
	}
}
