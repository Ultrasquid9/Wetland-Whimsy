package corundum.wetland_whimsy.data;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
		this.simpleBlock(
			WetlandWhimsyBlocks.PENNYWORT.get(),
			this.models()
				.withExistingParent("pennywort", this.modLoc("block/pennywort_base"))
		);
	}
}
