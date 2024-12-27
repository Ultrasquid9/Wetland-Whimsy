package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.CordgrassBlock;
import uwu.juni.wetland_whimsy.content.blocks.PennywortBlock;
import uwu.juni.wetland_whimsy.content.blocks.StrippableLogBlock;
import uwu.juni.wetland_whimsy.content.blocks.SussyMudBlock;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.BaldCypressTree;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
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
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("null")
public class WetlandWhimsyBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WetlandWhimsy.MODID);

	// Logs
	public static final RegistryObject<StrippableLogBlock> BALD_CYPRESS_LOG = registerBlockAndItem(
		"bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
		)
	);
	public static final RegistryObject<StrippableLogBlock> STRIPPED_BALD_CYPRESS_LOG = registerBlockAndItem(
		"stripped_bald_cypress_log", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
		)
	);
	public static final RegistryObject<StrippableLogBlock> BALD_CYPRESS_WOOD = registerBlockAndItem(
		"bald_cypress_wood", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
		)
	);
	public static final RegistryObject<StrippableLogBlock> STRIPPED_BALD_CYPRESS_WOOD = registerBlockAndItem(
		"stripped_bald_cypress_wood", 
		() -> new StrippableLogBlock(
			BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
		)
	);

	// Sapling & Leaves
	public static final RegistryObject<SaplingBlock> BALD_CYPRESS_SAPLING = registerBlockAndItem(
		"bald_cypress_sapling",
		() -> new SaplingBlock(
			new BaldCypressTree(),
			Block.Properties.copy(Blocks.OAK_SAPLING)
		)
	);
	public static final RegistryObject<LeavesBlock> BALD_CYPRESS_LEAVES = registerBlockAndItem(
		"bald_cypress_leaves", 
		() -> new LeavesBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
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
	public static final RegistryObject<Block> BALD_CYPRESS_PLANKS = registerBlockAndItem(
		"bald_cypress_planks", 
		() -> new Block(
			BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
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
	@SuppressWarnings("deprecation")
	public static final RegistryObject<StairBlock> BALD_CYPRESS_STAIRS = registerBlockAndItem(
		"bald_cypress_stairs", 
		() -> new StairBlock(
			BALD_CYPRESS_PLANKS.get().defaultBlockState(),
			BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
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
	public static final RegistryObject<SlabBlock> BALD_CYPRESS_SLAB = registerBlockAndItem(
		"bald_cypress_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
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
	public static final RegistryObject<FenceBlock> BALD_CYPRESS_FENCE = registerBlockAndItem(
		"bald_cypress_fence", 
		() -> new FenceBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
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
	public static final RegistryObject<FenceGateBlock> BALD_CYPRESS_FENCE_GATE = registerBlockAndItem(
		"bald_cypress_fence_gate", 
		() -> new FenceGateBlock(
			BlockBehaviour.Properties.copy(BALD_CYPRESS_FENCE.get()), 
			WetlandWhimsyWoodTypes.BALD_CYPRESS
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
	public static final RegistryObject<DoorBlock> BALD_CYPRESS_DOOR = registerBlockAndItem(
		"bald_cypress_door",
		() -> new DoorBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_DOOR),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		)
	);
	public static final RegistryObject<TrapDoorBlock> BALD_CYPRESS_TRAPDOOR = registerBlockAndItem(
		"bald_cypress_trapdoor",
		() -> new TrapDoorBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		)
	);
	public static final RegistryObject<ButtonBlock> BALD_CYPRESS_BUTTON = registerBlockAndItem(
		"bald_cypress_button",
		() -> new ButtonBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			30,
			false
		)
	);
	public static final RegistryObject<PressurePlateBlock> BALD_CYPRESS_PRESSURE_PLATE = registerBlockAndItem(
		"bald_cypress_pressure_plate",
		() -> new PressurePlateBlock(
			Sensitivity.EVERYTHING, 
			BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), 
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		)
	);

	// Signs
	public static final RegistryObject<StandingSignBlock> BALD_CYPRESS_SIGN = BLOCKS.register(
		"bald_cypress_sign", 
		() -> new BlueprintStandingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<WallSignBlock> BALD_CYPRESS_WALL_SIGN = BLOCKS.register(
		"bald_cypress_wall_sign", 
		() -> new BlueprintWallSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
				.lootFrom(BALD_CYPRESS_SIGN),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<CeilingHangingSignBlock> BALD_CYPRESS_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_hanging_sign", 
		() -> new BlueprintCeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<WallHangingSignBlock> BALD_CYPRESS_WALL_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_wall_hanging_sign", 
		() -> new BlueprintWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
				.lootFrom(BALD_CYPRESS_HANGING_SIGN),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);

	// Limestone 
	public static final RegistryObject<Block> LIMESTONE = registerBlockAndItem(
		"limestone", 
		() -> new Block(
			BlockBehaviour.Properties
				.copy(Blocks.STONE)
				.sound(SoundType.CALCITE)
		)
	);
	public static final RegistryObject<Block> POLISHED_LIMESTONE = registerBlockAndItem(
		"polished_limestone", 
		() -> new Block(BlockBehaviour.Properties.copy(LIMESTONE.get()))
	);
	public static final RegistryObject<Block> LIMESTONE_BRICKS = registerBlockAndItem(
		"limestone_bricks", 
		() -> new Block(BlockBehaviour.Properties.copy(LIMESTONE.get()))
	);
	@SuppressWarnings("deprecation")
	public static final RegistryObject<StairBlock> LIMESTONE_STAIRS = registerBlockAndItem(
		"limestone_stairs", 
		() -> new StairBlock(
			LIMESTONE.get().defaultBlockState(),
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	@SuppressWarnings("deprecation")
	public static final RegistryObject<StairBlock> POLISHED_LIMESTONE_STAIRS = registerBlockAndItem(
		"polished_limestone_stairs", 
		() -> new StairBlock(
			POLISHED_LIMESTONE.get().defaultBlockState(),
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	@SuppressWarnings("deprecation")
	public static final RegistryObject<StairBlock> LIMESTONE_BRICK_STAIRS = registerBlockAndItem(
		"limestone_brick_stairs", 
		() -> new StairBlock(
			LIMESTONE_BRICKS.get().defaultBlockState(),
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<SlabBlock> LIMESTONE_SLAB = registerBlockAndItem(
		"limestone_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<SlabBlock> POLISHED_LIMESTONE_SLAB = registerBlockAndItem(
		"polished_limestone_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<SlabBlock> LIMESTONE_BRICK_SLAB = registerBlockAndItem(
		"limestone_brick_slab", 
		() -> new SlabBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<WallBlock> LIMESTONE_WALL = registerBlockAndItem(
		"limestone_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<WallBlock> POLISHED_LIMESTONE_WALL = registerBlockAndItem(
		"polished_limestone_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<WallBlock> LIMESTONE_BRICK_WALL = registerBlockAndItem(
		"limestone_brick_wall", 
		() -> new WallBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);
	public static final RegistryObject<RotatedPillarBlock> LIMESTONE_PILLAR = registerBlockAndItem(
		"limestone_pillar", 
		() -> new RotatedPillarBlock(
			BlockBehaviour.Properties.copy(LIMESTONE.get())
		)
	);

	// Plants
	public static final RegistryObject<CordgrassBlock> CORDGRASS = registerBlockAndItem(
		"cordgrass",
		() -> new CordgrassBlock (
			BlockBehaviour.Properties.copy(Blocks.POPPY)
				.noOcclusion()
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	);
	public static final RegistryObject<PennywortBlock> PENNYWORT = registerBlockAndItem(
		"pennywort",
		() -> new PennywortBlock(
			MobEffects.DARKNESS, 
			3,
			BlockBehaviour.Properties.copy(Blocks.POPPY)
				.noOcclusion()
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	);

	// Miscellaneous
	public static final RegistryObject<SussyMudBlock> SUSSY_MUD = registerBlockAndItem(
		"suspicious_mud",
		() -> new SussyMudBlock (
			Blocks.MUD,
			SoundEvents.BRUSH_SAND,
			SoundEvents.BRUSH_SAND_COMPLETED,
			BlockBehaviour.Properties.copy(Blocks.MUD)
		)
	);

	/// There was indeed a way to automate this 
	public static <T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> block) {
		var register = BLOCKS.register(name, block);

		WetlandWhimsyItems.ITEMS.register(
			name, 
			() -> new BlockItem(
				register.get(), 
				new Item.Properties()
			)
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
