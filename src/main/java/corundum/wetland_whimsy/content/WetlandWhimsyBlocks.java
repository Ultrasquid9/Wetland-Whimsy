package corundum.wetland_whimsy.content;

import java.util.function.Supplier;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.blocks.CordgrassBlock;
import corundum.wetland_whimsy.content.blocks.PennywortBlock;
import corundum.wetland_whimsy.content.blocks.StrippableLogBlock;
import corundum.wetland_whimsy.worldgen.WetlandWhimsyTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("null")
public class WetlandWhimsyBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WetlandWhimsy.MODID);

	// Logs
	public static final DeferredBlock<StrippableLogBlock> BALD_CYPRESS_LOG = registerBlockAndItem(
		"bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
		)
	);
	public static final DeferredBlock<StrippableLogBlock> STRIPPED_BALD_CYPRESS_LOG = registerBlockAndItem(
		"stripped_bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		)
	);
	public static final DeferredBlock<StrippableLogBlock> BALD_CYPRESS_WOOD = registerBlockAndItem(
		"bald_cypress_wood", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
		)
	);
	public static final DeferredBlock<StrippableLogBlock> STRIPPED_BALD_CYPRESS_WOOD = registerBlockAndItem(
		"stripped_bald_cypress_wood", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		)
	);

	// Sapling & Leaves
	public static final DeferredBlock<SaplingBlock> BALD_CYPRESS_SAPLING = registerBlockAndItem(
		"bald_cypress_sapling",
		() -> new SaplingBlock(
			WetlandWhimsyTreeGrowers.BALD_CYPRESS,
			Block.Properties.ofFullCopy(Blocks.OAK_SAPLING)
		)
	);
	public static final DeferredBlock<LeavesBlock> BALD_CYPRESS_LEAVES = registerBlockAndItem(
		"bald_cypress_leaves", 
		() -> new LeavesBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
		) {
			@Override
			public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return true;
			}
	
			@Override
			public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 30;
			}
	
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 60;
			}
		}
	);

	// Wood set
	public static final DeferredBlock<Block> BALD_CYPRESS_PLANKS = registerBlockAndItem(
		"bald_cypress_planks", 
		() -> new Block(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
		) {
			@Override
			public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return true;
			}
			@Override
			public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 20;
			}
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 5;
			}
		}
	);

	// Door and trapdoor
	// Note: these are not flammable, as vanilla doors/trapdoors arent either
	public static final DeferredBlock<DoorBlock> BALD_CYPRESS_DOOR = registerBlockAndItem(
		"bald_cypress_door",
		() -> new DoorBlock(
			WetlandWhimsyTags.BALD_CYPRESS_BLOCK_SET, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
		)
	);
	public static final DeferredBlock<TrapDoorBlock> BALD_CYPRESS_TRAPDOOR = registerBlockAndItem(
		"bald_cypress_trapdoor",
		() -> new TrapDoorBlock(
			WetlandWhimsyTags.BALD_CYPRESS_BLOCK_SET, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
		)
	);

	// Plants
	public static final DeferredBlock<CordgrassBlock> CORDGRASS = registerBlockAndItem(
		"cordgrass",
		() -> new CordgrassBlock (
			BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
				.noOcclusion()
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	);
	public static final DeferredBlock<PennywortBlock> PENNYWORT = registerBlockAndItem(
		"pennywort",
		() -> new PennywortBlock(
			MobEffects.DARKNESS, 
			3.0f,
			BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
				.noOcclusion()
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	);

	/// There was indeed a way to automate this 
	public static <T extends Block> DeferredBlock<T> registerBlockAndItem(String name, Supplier<T> block) {
		var register = BLOCKS.register(name, block);

		WetlandWhimsyItems.ITEMS.registerSimpleBlockItem(
			name, 
			register
		);

		return register;
	}
}
