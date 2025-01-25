package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class WetlandWhimsyBlockTags extends BlockTagsProvider {
	public WetlandWhimsyBlockTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> lookupProvider, 
		ExistingFileHelper existingFileHelper
	) {
		super(output, lookupProvider, WetlandWhimsy.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		// Mining tags 
		tag(BlockTags.MINEABLE_WITH_AXE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get()
		);
		tag(BlockTags.MINEABLE_WITH_HOE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()
		);
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
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
			WetlandWhimsyBlocks.LIMESTONE_PILLAR.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get()
			);
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
			WetlandWhimsyBlocks.SUSSY_MUD.get()
		);

		// Wood and leaves and stuff
		tag(BlockTags.LEAVES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()
		);
		tag(BlockTags.SAPLINGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()
		);
		tag(BlockTags.LOGS_THAT_BURN).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()
		);
		tag(BlockTags.LOGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()
		);
		tag(BlockTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()
		);

		// Woodset tags
		tag(BlockTags.WOODEN_FENCES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get()
		);
		tag(WetlandWhimsyTags.Blocks.BALD_CYPRESS_LOGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get()
		);

		// Sign tags
		tag(BlockTags.SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);
		tag(BlockTags.WALL_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);
		tag(BlockTags.CEILING_HANGING_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get()
		);
		tag(BlockTags.WALL_HANGING_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get()
		);

		// Limestone tags
		tag(BlockTags.WALLS).add(
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get()
		);
		tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(
			WetlandWhimsyBlocks.LIMESTONE.get()
		);
	}
}
