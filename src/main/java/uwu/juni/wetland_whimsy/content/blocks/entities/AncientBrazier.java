package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.data.sub_providers.WetlandWhimsyVaultLootDatagen;
import uwu.juni.wetland_whimsy.misc.Config;

public class AncientBrazier extends BaseSpawner {
	private int spawnedEntityCount;
	private SimpleWeightedRandomList<ResourceKey<LootTable>> lootTablesToEject;

	public AncientBrazier() {
		this.spawnedEntityCount = 0;

		this.lootTablesToEject = new SimpleWeightedRandomList.Builder<ResourceKey<LootTable>>()
			.add(WetlandWhimsyVaultLootDatagen.INTERMEDIATE_LOOT)
			.add(WetlandWhimsyVaultLootDatagen.ANCIENT_COIN)
			.build();
	}

	@SuppressWarnings("null")
	@Override
	public void broadcastEvent(Level level, BlockPos pos, int id) {
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
		
		if (this.spawnDelay > 3) 
			this.spawnDelay -= 3;

		if (this.spawnDelay == 1) {
			this.spawnedEntityCount++;

			this.setRandomEntity(serverLevel, pos);;
		}

		super.serverTick(serverLevel, pos);

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

	private void setRandomEntity(ServerLevel level, BlockPos pos) {
		var random = level.getRandom();
		var entity = Config.ancientBrazierEntities.get(random.nextInt(0, Config.ancientBrazierEntities.size()));

		this.setEntityId(BuiltInRegistries.ENTITY_TYPE.get(entity), level, random, pos);
	}

	private void ejectLoot(ServerLevel level, BlockPos pos, RandomSource random) {
		var x = lootTablesToEject.getRandom(random);
		if (x.isEmpty()) return;

		var params = new LootParams.Builder(level).create(LootContextParamSets.EMPTY);
		var table = level.getServer().reloadableRegistries().getLootTable(x.get().data());

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

		level.levelEvent(3014, pos, 0);
	}
}
