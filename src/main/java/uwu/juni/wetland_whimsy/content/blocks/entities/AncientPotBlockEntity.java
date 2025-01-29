package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.datapacks.ScalableReward;

public class AncientPotBlockEntity extends BlockEntity {
	private int lootQuality;

	public AncientPotBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_POT.get(), pos, state);
		lootQuality = 1;
	}

	@Override
	protected void saveAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("loot_quality", lootQuality);
	}

	@Override
	protected void loadAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.loadAdditional(tag, registries);
		this.lootQuality = tag.getInt("loot_quality");
	}

	public void increaseLootQuality() { lootQuality++; }
	public int lootQuality() { return lootQuality; }

	/// Gambling
	public void dropLoot(ServerLevel level, BlockPos pos) {
		// In case the pot was from a version where lootQuality started at 0
		if (lootQuality < 1) {
			WetlandWhimsy.LOGGER.warn("lootQuality is " + lootQuality + "... increasing to 1");
			lootQuality = 1;
		}

		var loot = ScalableReward.Manager.getLoot(
			level,
			WetlandWhimsyItems.ANCIENT_COIN.get(),
			lootQuality
		);

		for (var item : loot) 
			Block.popResource(level, pos, item);
	}
}
