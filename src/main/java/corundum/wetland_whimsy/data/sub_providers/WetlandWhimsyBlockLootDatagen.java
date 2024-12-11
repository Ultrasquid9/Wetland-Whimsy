package corundum.wetland_whimsy.data.sub_providers;

import java.util.Set;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class WetlandWhimsyBlockLootDatagen extends BlockLootSubProvider {
	public WetlandWhimsyBlockLootDatagen(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return WetlandWhimsyBlocks.BLOCKS.getEntries()
			.stream()
			.map(block -> (Block) block.value())
			.toList();
	}

	@Override
	protected void generate() {
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get());
		this.dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());

		this.add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			this.createLeavesDrops(
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get(),
				NORMAL_LEAVES_SAPLING_CHANCES
			)
		);
		this.add(
			WetlandWhimsyBlocks.CORDGRASS.get(), 
			BlockLootSubProvider.createShearsOnlyDrop(WetlandWhimsyBlocks.CORDGRASS_ITEM)
		);
	}
}
