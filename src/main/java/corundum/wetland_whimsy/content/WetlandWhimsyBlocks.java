package corundum.wetland_whimsy.content;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.blocks.CordgrassBlock;
import corundum.wetland_whimsy.content.blocks.StrippableLogBlock;
import corundum.wetland_whimsy.worldgen.WetlandWhimsyTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyBlocks {	
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WetlandWhimsy.MODID);

	// Blocks 

	public static final DeferredBlock<StrippableLogBlock> BALD_CYPRESS_LOG = BLOCKS.register(
		"bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
		)
	);

	public static final DeferredBlock<StrippableLogBlock> STRIPPED_BALD_CYPRESS_LOG = BLOCKS.register(
		"stripped_bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		)
	);

	public static final DeferredBlock<Block> BALD_CYPRESS_LEAVES = BLOCKS.registerSimpleBlock(
		"bald_cypress_leaves", 
		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
	);

	public static final DeferredBlock<SaplingBlock> BALD_CYPRESS_SAPLING = BLOCKS.register(
		"bald_cypress_sapling",
		() -> new SaplingBlock(
			WetlandWhimsyTreeGrowers.BALD_CYPRESS,
			Block.Properties.ofFullCopy(Blocks.OAK_SAPLING)
		)
	);

	public static final DeferredBlock<LeavesBlock> BALD_CYPRESS_PLANKS = BLOCKS.register(
		"bald_cypress_planks", 
		() -> new LeavesBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
		)
	);

	public static final DeferredBlock<CordgrassBlock> CORDGRASS = BLOCKS.register(
		"cordgrass",
		() -> new CordgrassBlock (
			BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
				.noOcclusion()
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	);

	// Block items
	// I am certain that this can be automated, but i do not know how to do that

	public static final DeferredItem<BlockItem> BALD_CYPRESS_LOG_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_log", 
		BALD_CYPRESS_LOG
	);
	public static final DeferredItem<BlockItem> STRIPPED_BALD_CYPRESS_LOG_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"stripped_bald_cypress_log", 
		STRIPPED_BALD_CYPRESS_LOG
	);
	public static final DeferredItem<BlockItem> BALD_CYPRESS_LEAVES_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_leaves", 
		BALD_CYPRESS_LEAVES
	);
	public static final DeferredItem<BlockItem> BALD_CYPRESS_SAPLING_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_sapling", 
		BALD_CYPRESS_SAPLING
	);
	public static final DeferredItem<BlockItem> BALD_CYPRESS_PLANKS_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_planks", 
		BALD_CYPRESS_PLANKS
	);
	public static final DeferredItem<BlockItem> CORDGRASS_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"cordgrass", 
		CORDGRASS
	);
}
