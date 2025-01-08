package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.misc.Config;

public class AncientBrazier extends BaseSpawner {
	private int spawnedEntityCount;

	public AncientBrazier() {
		this.spawnedEntityCount = 0;
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
		if (!serverLevel.getBlockState(pos).getValue(BlockStateProperties.LIT))
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
					.setValue(BlockStateProperties.LIT, false)
					.setValue(AncientBrazierBlock.SMOLDERING, true), 
				2
			);
			return;
		}
	}

	private void setRandomEntity(ServerLevel level, BlockPos pos) {
		var random = level.getRandom();
		var entity = Config.ancientBrazierEntities.get(random.nextInt(0, Config.ancientBrazierEntities.size()));

		this.setEntityId(BuiltInRegistries.ENTITY_TYPE.get(entity), level, random, pos);
	}
}
