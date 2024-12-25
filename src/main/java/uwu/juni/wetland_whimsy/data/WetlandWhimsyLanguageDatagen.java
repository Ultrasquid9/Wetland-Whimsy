package uwu.juni.wetland_whimsy.data;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class WetlandWhimsyLanguageDatagen extends LanguageProvider {
	public WetlandWhimsyLanguageDatagen(PackOutput output) {
		super(output, WetlandWhimsy.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LOG, "Bald Cypress Log");
		this.addBlock(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, "Stripped Bald Cypress Log");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, "Bald Cypress Wood");
		this.addBlock(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, "Stripped Bald Cypress Wood");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES, "Bald Cypress Leaves");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING, "Bald Cypress Sapling");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, "Bald Cypress Planks");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS, "Bald Cypress Stairs");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB, "Bald Cypress Slab");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE, "Bald Cypress Fence");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE, "Bald Cypress Fence Gate");

		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR, "Bald Cypress Door");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR, "Bald Cypress Trapdoor");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON, "Bald Cypress Button");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE, "Bald Cypress Pressure Plate");

		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN, "Bald Cypress Sign");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN, "Bald Cypress Hanging Sign");

		this.addBlock(WetlandWhimsyBlocks.LIMESTONE, "Limestone");
		this.addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE, "Polished Limestone");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICKS, "Limestone Bricks");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_STAIRS, "Limestone Stairs");
		this.addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS, "Polished Limestone Stairs");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS, "Limestone Brick Stairs");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_SLAB, "Limestone Slab");
		this.addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB, "Polished Limestone Slab");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB, "Limestone Brick Slab");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_WALL, "Limestone Wall");
		this.addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL, "Polished Limestone Wall");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL, "Limestone Brick Wall");
		this.addBlock(WetlandWhimsyBlocks.LIMESTONE_PILLAR, "Limestone Pillar");

		this.addBlock(WetlandWhimsyBlocks.CORDGRASS, "Cordgrass");
		this.addBlock(WetlandWhimsyBlocks.PENNYWORT, "Pennywort");

		this.addBlock(WetlandWhimsyBlocks.SUSSY_MUD, "Suspicious Mud");

		this.addItem(WetlandWhimsyItems.PENNYWORT_SALAD, "Pennywort Salad");
		this.addItem(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing Template");
		this.addItem(WetlandWhimsyItems.DISC, "Music Disc");

		this.add("trim_pattern.wetland_whimsy.dots", "Dots Armor Trim");
		this.add("jukebox_song.wetland_whimsy.nuke_the_swamps", "Quizzly - Nuke The Swamps");
	}
}
