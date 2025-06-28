package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyCompat;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public class WetlandWhimsyBlockTags extends BlockTagsProvider {
	public WetlandWhimsyBlockTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> lookupProvider, 
		ExistingFileHelper existingFileHelper
	) {
		super(output, lookupProvider, WetlandWhimsy.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(@Nonnull Provider provider) {
		// Mining tags 
		tag(BlockTags.MINEABLE_WITH_AXE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(),
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK.get()
		);
		tag(BlockTags.MINEABLE_WITH_HOE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			WetlandWhimsyBlocks.CORDGRASS_THATCH.get()
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
		addTagsToBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG, 
			BlockTags.LOGS,
			BlockTags.LOGS_THAT_BURN,
			BlockTags.OVERWORLD_NATURAL_LOGS
		);
		tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get()
		);
		tag(Tags.Blocks.STRIPPED_LOGS).add(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get()
		);
		tag(BlockTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()
		);

		// Woodset tags
		tag(WetlandWhimsyTags.Blocks.BALD_CYPRESS_LOGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(),
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get()
		);
		tag(BlockTags.WOODEN_DOORS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get()
		);
		tag(BlockTags.WOODEN_TRAPDOORS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get()
		);
		tag(BlockTags.BUTTONS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get()
		);
		tag(BlockTags.PRESSURE_PLATES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get()
		);
		addTagsToBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE, 
			BlockTags.WOODEN_FENCES,
			Tags.Blocks.FENCES,
			Tags.Blocks.FENCES_WOODEN
		);
		addTagsToBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE, 
			BlockTags.FENCE_GATES,
			Tags.Blocks.FENCE_GATES,
			Tags.Blocks.FENCE_GATES_WOODEN
		);

		// Sign tags
		tag(BlockTags.STANDING_SIGNS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get()
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
	
		// Generic
		tag(BlockTags.STAIRS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get(),
			WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get()
		);
		tag(BlockTags.SLABS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get(),
			WetlandWhimsyBlocks.LIMESTONE_SLAB.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get()
		);
		tag(BlockTags.WALLS).add(
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get()
		);

		// Compat
		if(WetlandWhimsyCompat.FARMERS_DELIGHT)
			tag(BlockTags.MINEABLE_WITH_AXE).add(
				WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get().get()
			);
	}
	
	@SafeVarargs
	final void addTagsToBlock(Supplier<? extends Block> defblock, TagKey<Block>... tags) {
		var block = defblock.get();

		for (var tag : tags)
			tag(tag).add(block);
	}
}
