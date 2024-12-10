package corundum.wetland_whimsy.content;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyBlocks {	
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WetlandWhimsy.MODID);

	// Blocks 

	public static final DeferredBlock<RotatedPillarBlock> BALD_CYPRESS_LOG = BLOCKS.register(
		"bald_cypress_log", 
		() -> new RotatedPillarBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
		)
	);

	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BALD_CYPRESS_LOG = BLOCKS.register(
		"stripped_bald_cypress_log", 
		() -> new RotatedPillarBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		)
	);

	public static final DeferredBlock<Block> BALD_CYPRESS_LEAVES = BLOCKS.registerSimpleBlock(
		"bald_cypress_leaves", 
		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
	);

	public static final DeferredBlock<Block> BALD_CYPRESS_PLANKS = BLOCKS.registerSimpleBlock(
		"bald_cypress_planks", 
		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
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
	public static final DeferredItem<BlockItem> BALD_CYPRESS_PLANKS_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_planks", 
		BALD_CYPRESS_PLANKS
	);
}
