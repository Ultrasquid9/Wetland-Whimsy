package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;

public class AncientPotBlockEntity extends BlockEntity {
	private int lootQuality;

	public AncientPotBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_POT.get(), pos, state);
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

	public void dropLoot(Level level, BlockPos pos) {
		Block.popResource(level, pos, new ItemStack(WetlandWhimsyBlocks.CORDGRASS));
	}
}
