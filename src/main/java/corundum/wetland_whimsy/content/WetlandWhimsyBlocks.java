package corundum.wetland_whimsy.content;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.item.BlockItem;
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

	// Block items
	// I am certain that this can be automated, but i do not know how to do that

	public static final DeferredItem<BlockItem> BALD_CYPRESS_LOG_ITEM = WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
		"bald_cypress_log", 
		BALD_CYPRESS_LOG
	);
}
