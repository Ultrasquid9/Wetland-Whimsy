package corundum.wetland_whimsy.content;

import java.util.function.Supplier;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.blocks.CordgrassBlock;
import corundum.wetland_whimsy.content.blocks.PennywortBlock;
import corundum.wetland_whimsy.content.blocks.StrippableLogBlock;
import corundum.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import corundum.wetland_whimsy.worldgen.WetlandWhimsyTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
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
	public static final DeferredBlock<StairBlock> BALD_CYPRESS_STAIRS = registerBlockAndItem(
		"bald_cypress_stairs", 
		() -> new StairBlock(
			BALD_CYPRESS_PLANKS.get().defaultBlockState(),
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
	public static final DeferredBlock<SlabBlock> BALD_CYPRESS_SLAB = registerBlockAndItem(
		"bald_cypress_slab", 
		() -> new SlabBlock(
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
	public static final DeferredBlock<FenceBlock> BALD_CYPRESS_FENCE = registerBlockAndItem(
		"bald_cypress_fence", 
		() -> new FenceBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
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
	public static final DeferredBlock<FenceGateBlock> BALD_CYPRESS_FENCE_GATE = registerBlockAndItem(
		"bald_cypress_fence_gate", 
		() -> new FenceGateBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS,
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

	// Non-flammable members of the woodset
	// This includes:
	// - The door
	// - The trapdoor
	// - The button
	// - The pressure plate
	public static final DeferredBlock<DoorBlock> BALD_CYPRESS_DOOR = registerBlockAndItem(
		"bald_cypress_door",
		() -> new DoorBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
		)
	);
	public static final DeferredBlock<TrapDoorBlock> BALD_CYPRESS_TRAPDOOR = registerBlockAndItem(
		"bald_cypress_trapdoor",
		() -> new TrapDoorBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
		)
	);
	public static final DeferredBlock<ButtonBlock> BALD_CYPRESS_BUTTON = registerBlockAndItem(
		"bald_cypress_button",
		() -> new ButtonBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			30,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
		)
	);
	public static final DeferredBlock<PressurePlateBlock> BALD_CYPRESS_PRESSURE_PLATE = registerBlockAndItem(
		"bald_cypress_pressure_plate",
		() -> new PressurePlateBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
		)
	);

	// Signs
	public static final DeferredBlock<StandingSignBlock> BALD_CYPRESS_SIGN = BLOCKS.register(
		"bald_cypress_sign", 
		() -> new StandingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)
		)
	);
	public static final DeferredBlock<WallSignBlock> BALD_CYPRESS_WALL_SIGN = BLOCKS.register(
		"bald_cypress_wall_sign", 
		() -> new WallSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
				.lootFrom(BALD_CYPRESS_SIGN)
		)
	);
	public static final DeferredBlock<CeilingHangingSignBlock> BALD_CYPRESS_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_hanging_sign", 
		() -> new CeilingHangingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)
		)
	);
	public static final DeferredBlock<WallHangingSignBlock> BALD_CYPRESS_WALL_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_wall_hanging_sign", 
		() -> new WallHangingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS, 
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
				.lootFrom(BALD_CYPRESS_HANGING_SIGN)
		)
	);

	// Limestone 
	public static final DeferredBlock<Block> LIMESTONE = registerBlockAndItem(
		"limestone", 
		() -> new Block(
			BlockBehaviour.Properties
				.ofFullCopy(Blocks.STONE)
				.sound(SoundType.CALCITE)
		)
	);
	public static final DeferredBlock<Block> POLISHED_LIMESTONE = registerBlockAndItem(
		"polished_limestone", 
		() -> new Block(BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get()))
	);
	public static final DeferredBlock<Block> LIMESTONE_BRICKS = registerBlockAndItem(
		"limestone_bricks", 
		() -> new Block(BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get()))
	);
	public static final DeferredBlock<StairBlock> LIMESTONE_STAIRS = registerBlockAndItem(
		"limestone_stairs", 
		() -> new StairBlock(
			LIMESTONE.get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<StairBlock> POLISHED_LIMESTONE_STAIRS = registerBlockAndItem(
		"polished_limestone_stairs", 
		() -> new StairBlock(
			POLISHED_LIMESTONE.get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<StairBlock> LIMESTONE_BRICK_STAIRS = registerBlockAndItem(
		"limestone_brick_stairs", 
		() -> new StairBlock(
			LIMESTONE_BRICKS.get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<SlabBlock> LIMESTONE_SLAB = registerBlockAndItem(
		"limestone_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<SlabBlock> POLISHED_LIMESTONE_SLAB = registerBlockAndItem(
		"polished_limestone_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<SlabBlock> LIMESTONE_BRICK_SLAB = registerBlockAndItem(
		"limestone_brick_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<WallBlock> LIMESTONE_WALL = registerBlockAndItem(
		"limestone_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<WallBlock> POLISHED_LIMESTONE_WALL = registerBlockAndItem(
		"polished_limestone_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<WallBlock> LIMESTONE_BRICK_WALL = registerBlockAndItem(
		"limestone_brick_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
		)
	);
	public static final DeferredBlock<RotatedPillarBlock> LIMESTONE_PILLAR = registerBlockAndItem(
		"limestone_pillar", 
		() -> new RotatedPillarBlock(
			BlockBehaviour.Properties.ofFullCopy(LIMESTONE.get())
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

	public static void createSignItems() {
		WetlandWhimsyItems.ITEMS.register(
			"bald_cypress_sign", 
			() -> new SignItem(
				new Item.Properties().stacksTo(16), 
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
			)
		);

		WetlandWhimsyItems.ITEMS.register(
			"bald_cypress_hanging_sign", 
			() -> new HangingSignItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
				new Item.Properties().stacksTo(16)
			)
		);
	}
}
