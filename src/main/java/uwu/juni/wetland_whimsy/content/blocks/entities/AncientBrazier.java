package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.data.sub_providers.WetlandWhimsyStructureLootDatagen;
import uwu.juni.wetland_whimsy.mixins.BaseSpawnerAccessor;

public class AncientBrazier extends BaseSpawner {
	private int spawnedEntityCount;
	private SimpleWeightedRandomList<ResourceLocation> lootTablesToEject;

	public AncientBrazier() {
		this.spawnedEntityCount = 0;

		this.lootTablesToEject = new SimpleWeightedRandomList.Builder<ResourceLocation>()
			.add(WetlandWhimsyStructureLootDatagen.INTERMEDIATE_LOOT, 1)
			.add(WetlandWhimsyStructureLootDatagen.ANCIENT_COIN, 1)
			.build();
	}

	@Override
	public void broadcastEvent(@Nonnull Level level, @Nonnull BlockPos pos, int id) {
		level.blockEvent(pos, WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(), id, 0);
	}

	@SuppressWarnings("null")
	@Override
	public void setNextSpawnData(Level level, BlockPos pos, SpawnData data) {
		super.setNextSpawnData(level, pos, data);

		if (level != null) {
			var blockstate = level.getBlockState(pos);
			level.sendBlockUpdated(pos, blockstate, blockstate, 4);
		}
	}

	@Override
	public void serverTick(@Nonnull ServerLevel serverLevel, @Nonnull BlockPos pos) {
		if (!serverLevel.getBlockState(pos).getValue(AncientBrazierBlock.FLAME).equals(AncientBrazierBlock.Flame.LIT))
			return;

		var spawnDelay = ((BaseSpawnerAccessor) this).getSpawnDelay();
		
		if (spawnDelay > 3) 
			((BaseSpawnerAccessor) this).setSpawnDelay(spawnDelay - 3);
		else if (spawnDelay > 0)
			((BaseSpawnerAccessor) this).setSpawnDelay(spawnDelay - 1);

		if (spawnDelay == 1) {
			this.spawnedEntityCount++;
			
			var entity = serverLevel.getBlockEntity(pos);
			if (entity != null)
				entity.setChanged();

			this.setRandomEntity(serverLevel, pos);;
		}

		var uuid = spawnDelay <= 0 ? spawnMob(serverLevel, pos) : Optional.empty();

		if (uuid.isPresent()) {
			((BaseSpawnerAccessor) this).setSpawnDelay(serverLevel.getRandom().nextInt(200, 400));

			for (int i = 0; i < serverLevel.getRandom().nextInt(0, 4); i++)
				spawnMob(serverLevel, pos);
		}

		if (this.spawnedEntityCount >= 8) {
			this.spawnedEntityCount = 0;
			serverLevel.setBlock(
				pos, 
				serverLevel.getBlockState(pos)
					.setValue(AncientBrazierBlock.FLAME, AncientBrazierBlock.Flame.SMOLDERING), 
				2
			);
			ejectLoot(serverLevel, pos, serverLevel.getRandom());
			return;
		}
	}

	@SuppressWarnings("deprecation")
	private void setRandomEntity(ServerLevel level, BlockPos pos) {
		var random = level.getRandom();
		var entity = WetlandWhimsy.config.ancientBrazierEntities.get(
			random.nextInt(0, WetlandWhimsy.config.ancientBrazierEntities.size())
		);

		this.setEntityId(BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.tryParse(entity)), level, random, pos);
	}

	// Copied from Vanilla's TrialSpawner class
	@SuppressWarnings("deprecation")
	public Optional<UUID> spawnMob(ServerLevel level, BlockPos pos) {
		var randomsource = level.getRandom();
		var spawndata = ((BaseSpawnerAccessor) this).invokeGetOrCreateNextSpawnData(level, level.getRandom(), pos);
		var compoundtag = spawndata.entityToSpawn();
		var listtag = compoundtag.getList("Pos", 6);
		var optional = EntityType.by(compoundtag);

		if (optional.isEmpty()) 
			return Optional.empty();

		WetlandWhimsy.LOGGER.info("EntityType exists");

		int i = listtag.size();
		double d0 = i >= 1
			? listtag.getDouble(0)
			: (double)pos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * 7 + 0.5;
		double d1 = i >= 2 ? listtag.getDouble(1) : (double)(pos.getY() + randomsource.nextInt(3) - 1);
		double d2 = i >= 3
			? listtag.getDouble(2)
			: (double)pos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * 7 + 0.5;

		if (!level.noCollision(optional.get().getAABB(d0, d1, d2))) 
			return Optional.empty();

		WetlandWhimsy.LOGGER.info("Collision exists");

		Vec3 vec3 = new Vec3(d0, d1, d2);
		if (!inLineOfSight(level, pos.getCenter(), vec3))
			return Optional.empty();

		WetlandWhimsy.LOGGER.info("In line of sight");

		BlockPos blockpos = BlockPos.containing(vec3);

		Entity entity = EntityType.loadEntityRecursive(compoundtag, level, p_312375_ -> {
			p_312375_.moveTo(d0, d1, d2, randomsource.nextFloat() * 360.0F, 0.0F);
			return p_312375_;
		});
		if (entity == null)
			return Optional.empty();
		
		WetlandWhimsy.LOGGER.info("Entity loaded");

		if (entity instanceof Mob mob) {
			if (!mob.checkSpawnObstruction(level))
				return Optional.empty();

			var event = net.minecraftforge.event.ForgeEventFactory.onFinalizeSpawnSpawner(
				mob, 
				level, 
				level.getCurrentDifficultyAt(entity.blockPosition()), 
				null, 
				compoundtag, 
				this
			);
			if (
				event != null 
				&& spawndata.getEntityToSpawn().size() == 1 
				&& spawndata.getEntityToSpawn().contains("id", 8)
			) {
				((Mob)entity).finalizeSpawn(
					level, 
					event.getDifficulty(), 
					event.getSpawnType(), 
					event.getSpawnData(), 
					event.getSpawnTag()
				);
			}

			mob.setPersistenceRequired();
		}

		if (!level.tryAddFreshEntityWithPassengers(entity))
			return Optional.empty();

		level.levelEvent(2004, pos, 0);
		level.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);
		if (entity instanceof Mob) {
		   ((Mob)entity).spawnAnim();
		}

		return Optional.of(entity.getUUID());
	}

	private static boolean inLineOfSight(Level level, Vec3 spawnerPos, Vec3 mobPos) {
		@SuppressWarnings("null")
		var blockhitresult = level.clip(
			new ClipContext(
				mobPos, 
				spawnerPos, 
				Block.VISUAL, 
				Fluid.NONE, 
				null
			)
		);
		return blockhitresult.getBlockPos().equals(BlockPos.containing(spawnerPos)) || blockhitresult.getType() == HitResult.Type.MISS;
	}

	private void ejectLoot(ServerLevel level, BlockPos pos, RandomSource random) {
		var x = lootTablesToEject.getRandom(random);
		if (x.isEmpty()) return;

		var params = new LootParams.Builder(level).create(LootContextParamSets.EMPTY);
		var table = level.getServer().getLootData().getLootTable(x.get().getData());

		var loot = table.getRandomItems(params);
		if (loot.isEmpty()) return;

		for (var itemstack : loot) {
			DefaultDispenseItemBehavior.spawnItem(
				level, 
				itemstack, 
				2, 
				Direction.UP,
				Vec3.atBottomCenterOf(pos).relative(Direction.UP, 1.2)
			);
		}

		level.playSound(
			null, 
			pos, 
			SoundEvents.AMETHYST_BLOCK_BREAK, 
			SoundSource.BLOCKS, 
			1.0F, 
			1.0F
		);
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
