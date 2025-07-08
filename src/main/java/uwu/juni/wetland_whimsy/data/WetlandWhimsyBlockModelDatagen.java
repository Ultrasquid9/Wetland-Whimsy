package uwu.juni.wetland_whimsy.data;

import com.teamabnormals.caverns_and_chasms.core.CavernsAndChasms;
import com.teamabnormals.endergetic.core.EndergeticExpansion;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AncientBrazierBlock;
import uwu.juni.wetland_whimsy.content.blocks.AncientPotBlock;
import uwu.juni.wetland_whimsy.content.blocks.AriaMushroomBlock;
import uwu.juni.wetland_whimsy.content.blocks.BrazierBlock;
import uwu.juni.wetland_whimsy.content.blocks.PennywortBlock;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyBlockModelDatagen extends BlockStateProvider {
	public WetlandWhimsyBlockModelDatagen(PackOutput output, ExistingFileHelper fileHelper) {
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

		getVariantBuilder(WetlandWhimsyBlocks.ARIA_MUSHROOM.get()).forAllStates(state -> ConfiguredModel
			.builder() 
			.modelFile(
				state.getValue(AriaMushroomBlock.FACING) == Direction.UP 
					? models()
						.withExistingParent("aria_ground", mcLoc("cross"))
						.texture("cross", modLoc("block/compat/aria_mushroom_box"))
						.renderType("minecraft:cutout")
					: models()
						.withExistingParent("aria", modLoc("block/aria_mushroom_base"))
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
			WetlandWhimsyBlocks.ARIA_SPORES.get(),
			models()
				.withExistingParent("aria_spores", modLoc("block/aria_spores_base"))
				.renderType("minecraft:cutout")
		);

		ancientBrazier(
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(),
			"ancient_brazier",
			"block/ancient_brazier_base",
			"block/ancient_brazier_lit_base"
		);

		brazier(
			WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get(),
			"limestone_brazier",
			modLoc("block/brazier/brazier_top_lit"),
			mcLoc("block/campfire_fire")
		);
		brazier(
			WetlandWhimsyBlocks.SOUL_BRAZIER.get(),
			"soul_brazier",
			modLoc("block/brazier/soul_brazier_top_lit"),
			mcLoc("block/soul_campfire_fire")
		);

		if (Compat.ENDERGETIC)
			brazier(
				WetlandWhimsyBlocks.ENDER_BRAZIER.get().get(),
				"ender_brazier",
				modLoc("block/brazier/ender_brazier_top_lit"),
				new ResourceLocation(EndergeticExpansion.MOD_ID, "block/ender_campfire_fire")
			);
		if (Compat.CNC)
			brazier(
				WetlandWhimsyBlocks.CUPRIC_BRAZIER.get().get(),
				"cupric_brazier",
				modLoc("block/brazier/cupric_brazier_top_lit"),
				new ResourceLocation(CavernsAndChasms.MOD_ID, "block/cupric_campfire_fire")
			);

		getVariantBuilder(WetlandWhimsyBlocks.ANCIENT_POT.get()).forAllStates(state -> ConfiguredModel
			.builder() 
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

	public void hangingSignBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ResourceLocation texture) {
		var name = ForgeRegistries.BLOCKS.getKey(signBlock).getPath();
		var sign = models().sign(name, texture);
		
		simpleBlock(signBlock, sign);
		simpleBlock(wallSignBlock, sign);
	}

	private void brazier(Block brazier, String name, ResourceLocation topTexture, ResourceLocation flameTexture) {
		final var UNLIT_BASE = modLoc("block/brazier_base");
		final var LIT_BASE = modLoc("block/brazier_lit_base");
		final var UNLIT_TOP_TEXTURE = modLoc("block/brazier/brazier_top");

		getVariantBuilder(brazier).forAllStates(state -> {
			var model = state.getValue(BrazierBlock.LIT)
				? models()
					.withExistingParent(name + "_lit", LIT_BASE)
					.texture("top", topTexture)
					.texture("fire", flameTexture)
				: models()
					.withExistingParent(name, UNLIT_BASE)
					.texture("top", UNLIT_TOP_TEXTURE);

			return ConfiguredModel.builder()
				.modelFile(model)
				.build();
		});
	}

	private void ancientBrazier(Block brazier, String name, String unlitBase, String litBase) {
		this.getVariantBuilder(brazier).forAllStates((state) -> {
			var model = switch (state.getValue(AncientBrazierBlock.FLAME)) {
				case LIT -> models().withExistingParent(name + "_lit", modLoc(litBase));
				case SMOLDERING -> models().withExistingParent(name + "_smoldering", modLoc(litBase.replace("lit", "smoldering")));
				case UNLIT -> models().withExistingParent(name, modLoc(unlitBase));
			};

			return ConfiguredModel.builder()
				.modelFile(model)
				.build();
		});
	}
}
