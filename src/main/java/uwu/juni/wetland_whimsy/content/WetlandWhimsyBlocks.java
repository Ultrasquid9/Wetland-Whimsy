package uwu.juni.wetland_whimsy.content;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.teamabnormals.blueprint.common.block.sign.*;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.*;
import uwu.juni.wetland_whimsy.content.items.*;
import uwu.juni.wetland_whimsy.misc.Compat;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.BaldCypressTree;
import uwu.juni.wetland_whimsy.worldgen.HugeAriaMushroom;

public class WetlandWhimsyBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WetlandWhimsy.MODID);

	// Logs
	public static final RegistryObject<StrippableLogBlock> BALD_CYPRESS_LOG = registerBlockAndItem(
		"bald_cypress_log", 
		() -> new StrippableLogBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.log()
		),
		300
	);
	public static final RegistryObject<StrippableLogBlock> STRIPPED_BALD_CYPRESS_LOG = registerBlockAndItem(
		"stripped_bald_cypress_log", 
		() -> new StrippableLogBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.log()
		),
		300
	);
	public static final RegistryObject<StrippableLogBlock> BALD_CYPRESS_WOOD = registerBlockAndItem(
		"bald_cypress_wood", 
		() -> new StrippableLogBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.log()
		),
		300
	);
	public static final RegistryObject<StrippableLogBlock> STRIPPED_BALD_CYPRESS_WOOD = registerBlockAndItem(
		"stripped_bald_cypress_wood", 
		() -> new StrippableLogBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.log()
		),
		300
	);

	// Sapling & Leaves
	public static final RegistryObject<SaplingBlock> BALD_CYPRESS_SAPLING = registerBlockAndItem(
		"bald_cypress_sapling",
		() -> new SaplingBlock(
			new BaldCypressTree(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.sapling()
		),
		100
	);
	public static final RegistryObject<LeavesBlock> BALD_CYPRESS_LEAVES = registerBlockAndItem(
		"bald_cypress_leaves", 
		() -> new LeavesBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.leaves()

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
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.planks()
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
		},
		300
	);
	@SuppressWarnings("deprecation")
	public static final RegistryObject<StairBlock> BALD_CYPRESS_STAIRS = registerBlockAndItem(
		"bald_cypress_stairs", 
		() -> new StairBlock(
			BALD_CYPRESS_PLANKS.get().defaultBlockState(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.planks()
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
		},
		300
	);
	public static final RegistryObject<SlabBlock> BALD_CYPRESS_SLAB = registerBlockAndItem(
		"bald_cypress_slab", 
		() -> new SlabBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.planks()
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
		},
		300
	);
	public static final RegistryObject<FenceBlock> BALD_CYPRESS_FENCE = registerBlockAndItem(
		"bald_cypress_fence", 
		() -> new FenceBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.planks()
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
		},
		300
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
		},
		300
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
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.door(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		),
		200
	);
	public static final RegistryObject<TrapDoorBlock> BALD_CYPRESS_TRAPDOOR = registerBlockAndItem(
		"bald_cypress_trapdoor",
		() -> new TrapDoorBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.trapdoor(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		),
		300
	);
	public static final RegistryObject<ButtonBlock> BALD_CYPRESS_BUTTON = registerBlockAndItem(
		"bald_cypress_button",
		() -> new ButtonBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.button(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET, 
			30,
			false
		),
		100
	);
	public static final RegistryObject<PressurePlateBlock> BALD_CYPRESS_PRESSURE_PLATE = registerBlockAndItem(
		"bald_cypress_pressure_plate",
		() -> new PressurePlateBlock(
			Sensitivity.EVERYTHING, 
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.pressurePlate(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS_BLOCK_SET
		),
		300
	);

	// Signs
	public static final RegistryObject<StandingSignBlock> BALD_CYPRESS_SIGN = BLOCKS.register(
		"bald_cypress_sign", 
		() -> new BlueprintStandingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.sign(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<WallSignBlock> BALD_CYPRESS_WALL_SIGN = BLOCKS.register(
		"bald_cypress_wall_sign", 
		() -> new BlueprintWallSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.sign()
				.lootFrom(BALD_CYPRESS_SIGN),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<CeilingHangingSignBlock> BALD_CYPRESS_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_hanging_sign", 
		() -> new BlueprintCeilingHangingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.hangingSign(),
			WetlandWhimsyWoodTypes.BALD_CYPRESS
		)
	);
	public static final RegistryObject<WallHangingSignBlock> BALD_CYPRESS_WALL_HANGING_SIGN = BLOCKS.register(
		"bald_cypress_wall_hanging_sign", 
		() -> new BlueprintWallHangingSignBlock(
			WetlandWhimsyWoodTypes.BALD_CYPRESS_PROPERTIES.hangingSign()
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
	public static final RegistryObject<BloodcapMushroomBlock> BLOODCAP_MUSHROOM = registerBlockAndItem(
		"bloodcap_mushroom", 
		() -> new BloodcapMushroomBlock(
			BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM)
				.offsetType(BlockBehaviour.OffsetType.XZ)
				.lightLevel($ -> 2)
		)
	);
	public static final RegistryObject<AriaMushroomBlock> ARIA_MUSHROOM = registerBlockAndItem(
		"aria_mushroom", 
		() -> new AriaMushroomBlock(
			BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM)
				.noOcclusion()
				.lightLevel($ -> 7)
		)
	);
	public static final RegistryObject<HalfTransparentBlock> ARIA_MUSHROOM_BLOCK = registerBlockAndItem(
		"aria_mushroom_block", 
		() -> new HalfTransparentBlock(
			BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
				.noOcclusion()
				.lightLevel($ -> 9)
		)
	);
	public static final RegistryObject<SaplingBlock> ARIA_SPORES = registerBlockAndItem(
		"aria_spores", 
		() -> new SaplingBlock(
			new HugeAriaMushroom(), 
			BlockBehaviour.Properties.copy(ARIA_MUSHROOM.get())
		) {
			protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

			@Override
			public VoxelShape getShape(
				@Nonnull BlockState state, 
				@Nonnull BlockGetter level, 
				@Nonnull BlockPos pos, 
				@Nonnull CollisionContext context
			) {
				return SHAPE;
			}
		}
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

	public static final RegistryObject<BrazierBlock> LIMESTONE_BRAZIER = registerBlockAndItem(
		"limestone_brazier",
		() -> new BrazierBlock (
			BlockBehaviour.Properties.copy(LIMESTONE.get())
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
		)
	);
	public static final RegistryObject<BrazierBlock> SOUL_BRAZIER = registerBlockAndItem(
		"soul_brazier",
		() -> new BrazierBlock (
			BlockBehaviour.Properties.copy(LIMESTONE_BRAZIER.get())
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 10 : 0)
		)
	);

	public static Optional<RegistryObject<BrazierBlock>> ENDER_BRAZIER = Compat.ENDERGETIC
		? Optional.of(registerBlockAndItem(
			"ender_brazier", 
			() -> new BrazierBlock(BlockBehaviour.Properties.copy(LIMESTONE_BRAZIER.get()))
		))
		: Optional.empty();

	public static final RegistryObject<AncientBrazierBlock> ANCIENT_BRAZIER = registerBlockAndItem(
		"ancient_brazier",
		() -> new AncientBrazierBlock (
			BlockBehaviour.Properties.copy(LIMESTONE_BRAZIER.get())
				//.sound(SoundType.)
				.lightLevel(
					state -> switch (state.getValue(AncientBrazierBlock.FLAME)) {
						case LIT -> 10;
						case SMOLDERING -> 6;
						default -> 0;
					}
				)
		)
	);
	public static final RegistryObject<AncientPotBlock> ANCIENT_POT = registerBlockAndItem(
		"ancient_pot",
		() -> new AncientPotBlock (
			BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)
				.sound(WetlandWhimsySounds.ANCIENT_POT_SOUNDS)
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

	/// Creates a flammable block item
	private static <T extends Block> RegistryObject<T> registerBlockAndItem(
		String name, 
		Supplier<T> block, 
		int burntime
	) {
		var register = BLOCKS.register(name, block);

		WetlandWhimsyItems.ITEMS.register(
			name, 
			() -> new FlammableBlockItem(
				register.get(), 
				new Item.Properties(),
				burntime
			)
		);

		return register;
	}

	public static void createSignItems() {
		WetlandWhimsyItems.ITEMS.register(
			"bald_cypress_sign", 
			() -> new FlammableSignItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(),
				new Item.Properties().stacksTo(16),
				200
			)
		);

		WetlandWhimsyItems.ITEMS.register(
			"bald_cypress_hanging_sign", 
			() -> new FlammableHangingSignItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
				new Item.Properties().stacksTo(16),
				800
			)
		);
	}
}
