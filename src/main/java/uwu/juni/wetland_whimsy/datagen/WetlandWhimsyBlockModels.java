package uwu.juni.wetland_whimsy.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.content.blocks.AncientPotBlock;
import uwu.juni.wetland_whimsy.content.blocks.BrazierBlock;
import uwu.juni.wetland_whimsy.content.blocks.PennywortBlock;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyCompat;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class WetlandWhimsyBlockModels extends BlockStateProvider {
	public WetlandWhimsyBlockModels(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, WetlandWhimsy.MODID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {

		// Wood
		axisBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(), 
			modLoc("block/bald_cypress_log"), 
			modLoc("block/bald_cypress_log_top")
		);
		axisBlock(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(), 
			modLoc("block/stripped_bald_cypress_log"), 
			modLoc("block/stripped_bald_cypress_log_top")
		);
		axisBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(), 
			modLoc("block/bald_cypress_log"), 
			modLoc("block/bald_cypress_log")
		);
		axisBlock(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get(), 
			modLoc("block/stripped_bald_cypress_log"), 
			modLoc("block/stripped_bald_cypress_log")
		);

		simpleBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			models()
				.leaves("bald_cypress_leaves", modLoc("block/bald_cypress_leaves"))
				.renderType("minecraft:cutout")
		);
		simpleBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get(),
			models()
				.withExistingParent("bald_cypress_sapling", mcLoc("block/cross"))
				.texture("cross", modLoc("block/bald_cypress_sapling"))
				.renderType("minecraft:cutout")
		);

		simpleBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());
		slabBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get(), 
			modLoc("block/bald_cypress_planks"), 
			modLoc("block/bald_cypress_planks")
		);
		stairsBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get(), 
			modLoc("block/bald_cypress_planks")
		);
		fenceBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get(),
			modLoc("block/bald_cypress_planks")
		);
		fenceGateBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get(), 
			modLoc("block/bald_cypress_planks")
		);
		buttonBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get(), 
			modLoc("block/bald_cypress_planks")
		);
		pressurePlateBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get(), 
			modLoc("block/bald_cypress_planks")
		);
		doorBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(), 
			modLoc("block/bald_cypress_door_bottom"), 
			modLoc("block/bald_cypress_door_top")
		);
		trapdoorBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get(), 
			modLoc("block/bald_cypress_trapdoor"), 
			true
		);
		signBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(), 
			modLoc("block/bald_cypress_planks")
		);
		hangingSignBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(), 
			modLoc("block/bald_cypress_planks")
		);

		// Limestone
		simpleBlock(WetlandWhimsyBlocks.LIMESTONE.get());
		simpleBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get());
		simpleBlock(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get());
		stairsBlock(
			WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(), 
			modLoc("block/limestone")
		);
		stairsBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(), 
			modLoc("block/polished_limestone")
		);
		stairsBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(), 
			modLoc("block/limestone_bricks")
		);
		slabBlock(
			WetlandWhimsyBlocks.LIMESTONE_SLAB.get(), 
			modLoc("block/limestone"),
			modLoc("block/limestone")
		);
		slabBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(), 
			modLoc("block/polished_limestone"),
			modLoc("block/polished_limestone")
		);
		slabBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(), 
			modLoc("block/limestone_bricks"),
			modLoc("block/limestone_bricks")
		);
		wallBlock(
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(), 
			modLoc("block/limestone")
		);
		wallBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(), 
			modLoc("block/polished_limestone")
		);
		wallBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(), 
			modLoc("block/limestone_bricks")
		);
		axisBlock(
			WetlandWhimsyBlocks.LIMESTONE_PILLAR.get(), 
			modLoc("block/limestone_pillar_side"), 
			modLoc("block/limestone_pillar_top")
		);

		simpleBlock(WetlandWhimsyBlocks.CORDGRASS_THATCH.get());

		// Plants
		simpleBlock(
			WetlandWhimsyBlocks.CORDGRASS.get(),
			models()
				.withExistingParent("cordgrass", modLoc("block/cordgrass_base"))
		);
		pennywort(WetlandWhimsyBlocks.PENNYWORT.get());

		simpleBlock(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get(),
			models()
				.withExistingParent("bloodcap", mcLoc("block/cross"))
				.texture("cross", modLoc("block/bloodcap_mushroom"))
				.renderType("minecraft:cutout")
		);

		brazier(
			WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get(),
			"brazier",
			"block/limestone_brazier_base",
			"block/limestone_brazier_lit_base"
		);
		brazier(
			WetlandWhimsyBlocks.SOUL_BRAZIER.get(),
			"soul_brazier",
			"block/limestone_brazier_base",
			"block/soul_brazier_lit_base"
		);
		ancientBrazier(
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(),
			"ancient_brazier",
			"block/ancient_brazier_base",
			"block/ancient_brazier_lit_base"
		);

		getVariantBuilder(WetlandWhimsyBlocks.ANCIENT_POT.get()).forAllStates((state) -> ConfiguredModel.builder() 
			.modelFile(
				models().withExistingParent("ancient_pot", modLoc("block/ancient_pot_base"))
			)
			.rotationY(
				switch (state.getValue(AncientPotBlock.FACING)) {
					case NORTH -> 180;
					case EAST -> 270;
					case SOUTH -> 0;
					case WEST -> 90; 
					default -> 0; 
				}
			)
			.build()
		);

		getVariantBuilder(WetlandWhimsyBlocks.ARIA_MUSHROOM.get()).forAllStates((state) -> ConfiguredModel.builder() 
			.modelFile(
				models().withExistingParent("aria", modLoc("block/aria_mushroom_base"))
			)
			.rotationY(
				switch (state.getValue(AncientPotBlock.FACING)) {
					case NORTH -> 180;
					case EAST -> 270;
					case SOUTH -> 0;
					case WEST -> 90; 
					default -> 0; 
				}
			)
			.build()
		);

		simpleBlock(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(),
			models()
				.withExistingParent("aria_mushroom_block", mcLoc("block/cube_all"))
				.texture("all", modLoc("block/aria_mushroom_block"))
				.renderType("minecraft:translucent")
		);
		simpleBlock(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK.get(),
			models()
				.withExistingParent("bloodcap_mushroom_block", mcLoc("block/cube_all"))
				.texture("all", modLoc("block/bloodcap_mushroom_block"))
				.renderType("minecraft:translucent")
		);
		simpleBlock(
			WetlandWhimsyBlocks.ARIA_SPORES.get(),
			models()
				.withExistingParent("aria_spores", modLoc("block/aria_spores_base"))
				.renderType("minecraft:cutout")
		);

		simpleBlock(
			WetlandWhimsyBlocks.POTTED_BALD_CYPRESS_SAPLING.get(),
			models()
				.withExistingParent("potted_bald_cypress_sapling", mcLoc("block/flower_pot_cross"))
				.texture("plant", modLoc("block/bald_cypress_sapling"))
				.renderType("minecraft:cutout")
		);		
		simpleBlock(
			WetlandWhimsyBlocks.POTTED_PENNYWORT.get(),
			models()
				.withExistingParent("potted_pennywort", modLoc("block/potted_pennywort_base"))
				.renderType("minecraft:cutout")
		);
		simpleBlock(
			WetlandWhimsyBlocks.POTTED_CORDGRASS.get(),
			models()
				.withExistingParent("potted_cordgrass", mcLoc("block/flower_pot_cross"))
				.texture("plant", modLoc("block/potted_cordgrass"))
				.renderType("minecraft:cutout")
		);
		simpleBlock(
			WetlandWhimsyBlocks.POTTED_BLOODCAP_MUSHROOM.get(),
			models()
				.withExistingParent("potted_bloodcap_mushroom", mcLoc("block/flower_pot_cross"))
				.texture("plant", modLoc("block/bloodcap_mushroom"))
				.renderType("minecraft:cutout")
		);
		simpleBlock(
			WetlandWhimsyBlocks.POTTED_ARIA_MUSHROOM.get(),
			models()
				.withExistingParent("potted_aria_mushroom", mcLoc("block/flower_pot_cross"))
				.texture("plant", modLoc("block/potted_aria_mushroom"))
				.renderType("minecraft:cutout")
		);

		if (WetlandWhimsyCompat.FARMERS_DELIGHT)
			cabinetBlock(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get().get(), "bald_cypress");
	}

	private void pennywort(Block pennywort) {
		getVariantBuilder(pennywort).forAllStates((state) -> ConfiguredModel.builder()
			.modelFile(
				switch (state.getValue(PennywortBlock.PENNYWORT_COUNT)) {
					case 1 -> models().withExistingParent("p_1", modLoc("block/pennywort/pennywort_one"));
					case 2 -> models().withExistingParent("p_2", modLoc("block/pennywort/pennywort_two"));
					case 3 -> models().withExistingParent("p_3", modLoc("block/pennywort/pennywort_three"));
					default -> models().withExistingParent("p_4", modLoc("block/pennywort/pennywort_four"));
				}
			)
			.rotationY(
				switch (state.getValue(PennywortBlock.FACING)) {
					case NORTH -> 0;
					case EAST -> 90;
					case SOUTH -> 180;
					case WEST -> 270; 
					default -> 0; 
				}
			)
			.build()
		);
	}

	private void brazier(Block brazier, String name, String unlitBase, String litBase) {
		getVariantBuilder(brazier).forAllStates((state) -> {
			var model = state.getValue(BrazierBlock.LIT)
				? models().withExistingParent(name + "_lit", modLoc(litBase))
				: models().withExistingParent(name, modLoc(unlitBase));

			return ConfiguredModel.builder()
				.modelFile(model)
				.build();
		});
	}

	private void ancientBrazier(Block brazier, String name, String unlitBase, String litBase) {
		getVariantBuilder(brazier).forAllStates((state) -> {
			var model = switch (state.getValue(AncientBrazierBlock.FLAME)) {
				case AncientBrazierBlock.Flame.LIT -> models().withExistingParent(name + "_lit", modLoc(litBase));
				case AncientBrazierBlock.Flame.SMOLDERING -> models().withExistingParent(name + "_smoldering", modLoc(litBase.replace("lit", "smoldering")));
				case AncientBrazierBlock.Flame.UNLIT -> models().withExistingParent(name, modLoc(unlitBase));
			};

			return ConfiguredModel.builder()
				.modelFile(model)
				.build();
		});
	}

	// Modified version of the Farmer's Delight cabinet datagen
	public void cabinetBlock(Block block, String woodType) {
		horizontalBlock(block, state -> {
			var prefix = "block/compat/farmersdelight/";
			var suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";

			return models().orientable(BuiltInRegistries.BLOCK.getKey(block).getPath() + suffix,
				WetlandWhimsy.rLoc(prefix + woodType + "_cabinet_side"),
				WetlandWhimsy.rLoc(prefix + woodType + "_cabinet_front" + suffix),
				WetlandWhimsy.rLoc(prefix + woodType + "_cabinet_top"));
		});
	}
}
