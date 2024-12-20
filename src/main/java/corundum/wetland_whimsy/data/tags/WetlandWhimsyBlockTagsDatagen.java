package corundum.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class WetlandWhimsyBlockTagsDatagen extends BlockTagsProvider {
	public WetlandWhimsyBlockTagsDatagen(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> lookupProvider, 
		ExistingFileHelper existingFileHelper
	) {
		super(output, lookupProvider, WetlandWhimsy.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		// Mining tags 
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get()
		);
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()
		);
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
			WetlandWhimsyBlocks.LIMESTONE.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(),
			WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(),
			WetlandWhimsyBlocks.LIMESTONE_SLAB.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(),
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(),
			WetlandWhimsyBlocks.LIMESTONE_PILLAR.get()
		);
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
			WetlandWhimsyBlocks.SUSSY_MUD.get()
		);

		// Wood and leaves and stuff
		this.tag(BlockTags.LEAVES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()
		);
		this.tag(BlockTags.LOGS_THAT_BURN).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()
		);
		this.tag(BlockTags.LOGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()
		);
		this.tag(BlockTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()
		);

		// Woodset tags
		this.tag(BlockTags.WOODEN_FENCES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get()
		);
		this.tag(WetlandWhimsyTags.Blocks.BALD_CYPRESS_LOGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get()
		);

		// Sign tags
		this.tag(BlockTags.SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);
		this.tag(BlockTags.WALL_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get()
		);
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get()
		);

		// Limestone tags
		this.tag(BlockTags.WALLS).add(
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get()
		);
		this.tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(
			WetlandWhimsyBlocks.LIMESTONE.get()
		);
	}
}
