package uwu.juni.wetland_whimsy.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class WetlandWhimsyLanguageDatagen extends LanguageProvider {
	public WetlandWhimsyLanguageDatagen(PackOutput output) {
		super(output, WetlandWhimsy.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LOG, "Bald Cypress Log");
		addBlock(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, "Stripped Bald Cypress Log");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, "Bald Cypress Wood");
		addBlock(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, "Stripped Bald Cypress Wood");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES, "Bald Cypress Leaves");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING, "Bald Cypress Sapling");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, "Bald Cypress Planks");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS, "Bald Cypress Stairs");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB, "Bald Cypress Slab");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE, "Bald Cypress Fence");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE, "Bald Cypress Fence Gate");

		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR, "Bald Cypress Door");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR, "Bald Cypress Trapdoor");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON, "Bald Cypress Button");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE, "Bald Cypress Pressure Plate");

		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN, "Bald Cypress Sign");
		addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN, "Bald Cypress Hanging Sign");

		addBlock(WetlandWhimsyBlocks.LIMESTONE, "Limestone");
		addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE, "Polished Limestone");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICKS, "Limestone Bricks");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_STAIRS, "Limestone Stairs");
		addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS, "Polished Limestone Stairs");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS, "Limestone Brick Stairs");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_SLAB, "Limestone Slab");
		addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB, "Polished Limestone Slab");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB, "Limestone Brick Slab");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_WALL, "Limestone Wall");
		addBlock(WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL, "Polished Limestone Wall");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL, "Limestone Brick Wall");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_PILLAR, "Limestone Pillar");

		addBlock(WetlandWhimsyBlocks.CORDGRASS, "Cordgrass");
		addBlock(WetlandWhimsyBlocks.PENNYWORT, "Pennywort");
		addBlock(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, "Bloodcap Mushroom");
		addBlock(WetlandWhimsyBlocks.ARIA_MUSHROOM, "Aria Mushroom");
		addBlock(WetlandWhimsyBlocks.ARIA_SPORES, "Aria Spores");
		addBlock(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK, "Aria Mushroom Block");

		addBlock(WetlandWhimsyBlocks.SUSSY_MUD, "Suspicious Mud");
		addBlock(WetlandWhimsyBlocks.ANCIENT_POT, "Ancient Pot");

		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRAZIER, "Limestone Brazier");
		addBlock(WetlandWhimsyBlocks.SOUL_BRAZIER, "Soul Brazier");
		addBlock(WetlandWhimsyBlocks.ANCIENT_BRAZIER, "Ancient Brazier");
		add("block.wetland_whimsy.ender_brazier", "Ender Brazier");

		add("block.wetland_whimsy.limestone_brazier.compat", "Limestone Brazier");
		add("block.wetland_whimsy.soul_brazier.compat", "Limestone Soul Brazier");
		add("block.wetland_whimsy.ancient_brazier.compat", "Limestone Ancient Brazier");		
		add("block.wetland_whimsy.ender_brazier.compat", "Limestone Ender Brazier");

		addItem(WetlandWhimsyItems.PENNYWORT_SALAD, "Pennywort Salad");
		addItem(WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC, "Music Disc");
		addItem(WetlandWhimsyItems.ANCIENT_COIN, "Ancient Coin");

		addItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT.getFirst(), "Bald Cypress Boat");
		addItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT.getSecond(), "Bald Cypress Chest Boat");

		add("biome.wetland_whimsy.marsh", "Marsh");
		add("trim_pattern.wetland_whimsy.dots", "Dots Armor Trim");
		add("item.wetland_whimsy.nuke_the_swamps_music_disc.desc", "Quizzly - Nuke The Swamps");

		addConfig("ancientPotMaxParticleCount", "Ancient Pot Max Particles");
		addConfig("ancientPotMaxDropCount", "Ancient Pot Max Drops");
		addConfig("ancientPotItems", "Ancient Pot Items");
		addConfig("ancientBrazierEntities", "Ancient Brazier Entities");
		add("text.autoconfig.wetland_whimsy.title", "Wetland Whimsy Config");
	}

	private void addConfig(String key, String trans) {
		add("text.autoconfig.wetland_whimsy.option." + key, trans);
	}
}
