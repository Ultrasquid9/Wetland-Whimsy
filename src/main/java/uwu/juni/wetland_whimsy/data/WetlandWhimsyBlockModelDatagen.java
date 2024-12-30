package uwu.juni.wetland_whimsy.data;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.BrazierBlock;
import uwu.juni.wetland_whimsy.content.blocks.PennywortBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WetlandWhimsyBlockModelDatagen extends BlockStateProvider {
	public WetlandWhimsyBlockModelDatagen(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, WetlandWhimsy.MODID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {

		// Wood
		this.axisBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(), 
			modLoc("block/bald_cypress_log"), 
			modLoc("block/bald_cypress_log_top")
		);
		this.axisBlock(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(), 
			modLoc("block/stripped_bald_cypress_log"), 
			modLoc("block/stripped_bald_cypress_log_top")
		);
		this.axisBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(), 
			modLoc("block/bald_cypress_log"), 
			modLoc("block/bald_cypress_log")
		);
		this.axisBlock(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get(), 
			modLoc("block/stripped_bald_cypress_log"), 
			modLoc("block/stripped_bald_cypress_log")
		);

		this.simpleBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			this.models()
				.leaves("bald_cypress_leaves", modLoc("block/bald_cypress_leaves"))
				.renderType("minecraft:cutout")
		);
		this.simpleBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get(),
			this.models()
				.withExistingParent("bald_cypress_sapling", this.mcLoc("block/cross"))
				.texture("cross", modLoc("block/bald_cypress_sapling"))
				.renderType("minecraft:cutout")
		);

		this.simpleBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());
		this.slabBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get(), 
			modLoc("block/bald_cypress_planks"), 
			modLoc("block/bald_cypress_planks")
		);
		this.stairsBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get(), 
			modLoc("block/bald_cypress_planks")
		);
		this.fenceBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get(),
			modLoc("block/bald_cypress_planks")
		);
		this.fenceGateBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get(), 
			modLoc("block/bald_cypress_planks")
		);
		this.buttonBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get(), 
			modLoc("block/bald_cypress_planks")
		);
		this.pressurePlateBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get(), 
			modLoc("block/bald_cypress_planks")
		);
		this.doorBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(), 
			modLoc("block/bald_cypress_door_bottom"), 
			modLoc("block/bald_cypress_door_top")
		);
		this.trapdoorBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get(), 
			modLoc("block/bald_cypress_trapdoor"), 
			true
		);
		this.signBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(), 
			modLoc("block/bald_cypress_planks")
		);
		this.hangingSignBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(), 
			modLoc("block/bald_cypress_planks")
		);

		// Limestone
		this.simpleBlock(WetlandWhimsyBlocks.LIMESTONE.get());
		this.simpleBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get());
		this.simpleBlock(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get());
		this.stairsBlock(
			WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(), 
			modLoc("block/limestone")
		);
		this.stairsBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(), 
			modLoc("block/polished_limestone")
		);
		this.stairsBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(), 
			modLoc("block/limestone_bricks")
		);
		this.slabBlock(
			WetlandWhimsyBlocks.LIMESTONE_SLAB.get(), 
			modLoc("block/limestone"),
			modLoc("block/limestone")
		);
		this.slabBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(), 
			modLoc("block/polished_limestone"),
			modLoc("block/polished_limestone")
		);
		this.slabBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(), 
			modLoc("block/limestone_bricks"),
			modLoc("block/limestone_bricks")
		);
		this.wallBlock(
			WetlandWhimsyBlocks.LIMESTONE_WALL.get(), 
			modLoc("block/limestone")
		);
		this.wallBlock(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(), 
			modLoc("block/polished_limestone")
		);
		this.wallBlock(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(), 
			modLoc("block/limestone_bricks")
		);
		this.axisBlock(
			WetlandWhimsyBlocks.LIMESTONE_PILLAR.get(), 
			modLoc("block/limestone_pillar_side"), 
			modLoc("block/limestone_pillar_top")
		);

		// Plants
		this.simpleBlock(
			WetlandWhimsyBlocks.CORDGRASS.get(),
			this.models()
				.withExistingParent("cordgrass", this.modLoc("block/cordgrass_base"))
		);
		pennywort(WetlandWhimsyBlocks.PENNYWORT.get());
		brazier(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get());
	}

	private void pennywort(Block pennywort) {
		this.getVariantBuilder(pennywort).forAllStates((state) -> ConfiguredModel.builder()
			.modelFile(
				switch (state.getValue(PennywortBlock.PENNYWORT_COUNT)) {
					case 1 -> models().withExistingParent("p_1", this.modLoc("block/pennywort/pennywort_one"));
					case 2 -> models().withExistingParent("p_2", this.modLoc("block/pennywort/pennywort_two"));
					case 3 -> models().withExistingParent("p_3", this.modLoc("block/pennywort/pennywort_three"));
					default -> models().withExistingParent("p_4", this.modLoc("block/pennywort/pennywort_four"));
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

	private void brazier(Block brazier) {
		this.getVariantBuilder(brazier).forAllStates((state) -> {
			var model = state.getValue(BrazierBlock.LIT)
				? models().withExistingParent("brazier_lit", this.modLoc("block/limestone_brazier_lit_base"))
				: models().withExistingParent("brazier", this.modLoc("block/limestone_brazier_base"));

			return ConfiguredModel.builder()
				.modelFile(model)
				.build();
		});
	}
}
