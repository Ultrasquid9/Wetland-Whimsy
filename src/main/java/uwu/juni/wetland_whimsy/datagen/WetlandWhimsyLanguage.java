package uwu.juni.wetland_whimsy.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyLanguage extends LanguageProvider {
	public WetlandWhimsyLanguage(PackOutput output) {
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

		if (Compat.FARMERS_DELIGHT)
			addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get(), "Bald Cypress Cabinet");

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

		addBlock(WetlandWhimsyBlocks.CORDGRASS_THATCH, "Cordgrass Thatch");

		addBlock(WetlandWhimsyBlocks.CORDGRASS, "Cordgrass");
		addBlock(WetlandWhimsyBlocks.PENNYWORT, "Pennywort");
		addBlock(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, "Bloodcap Mushroom");
		addBlock(WetlandWhimsyBlocks.ARIA_MUSHROOM, "Aria Mushroom");
		addBlock(WetlandWhimsyBlocks.ARIA_SPORES, "Aria Spores");
		addBlock(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK, "Aria Mushroom Block");

		addBlock(WetlandWhimsyBlocks.SUSSY_MUD, "Suspicious Mud");
		addBlock(WetlandWhimsyBlocks.LIMESTONE_BRAZIER, "Limestone Brazier");
		addBlock(WetlandWhimsyBlocks.SOUL_BRAZIER, "Soul Brazier");
		addBlock(WetlandWhimsyBlocks.ANCIENT_BRAZIER, "Ancient Brazier");
		addBlock(WetlandWhimsyBlocks.ANCIENT_POT, "Ancient Pot");

		addItem(WetlandWhimsyItems.PENNYWORT_SALAD, "Pennywort Salad");

		addItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT, "Bald Cypress Boat");
		addItem(WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT, "Bald Cypress Chest Boat");

		addItem(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE, "Smithing Template");
		addItem(WetlandWhimsyItems.DISC, "Music Disc");
		addItem(WetlandWhimsyItems.GROWTH_POTTERY_SHERD, "Growth Pottery Sherd");
		addItem(WetlandWhimsyItems.SEALED_POTTERY_SHERD, "Sealed Pottery Sherd");
		addItem(WetlandWhimsyItems.ANCIENT_COIN, "Ancient Coin");
		addItem(WetlandWhimsyItems.BLEMISH_ROD, "Blemish Rod");
		addItem(WetlandWhimsyItems.SLUDGE_CHARGE, "Sludge Charge");
		addItem(WetlandWhimsyItems.RUSTED_ARTIFACT, "Rusted Artifact");
		addItem(WetlandWhimsyItems.DAGGER, "Dagger");

		addItem(WetlandWhimsyItems.AK47, "AK47");
		addItem(WetlandWhimsyItems.BULLET, "Bullet");

		addItem(WetlandWhimsyItems.BASIC_INCENSE, "Basic Incense");
		addItem(WetlandWhimsyItems.BOILING_INCENSE, "Boiling Incense");
		addItem(WetlandWhimsyItems.BRINE_INCENSE, "Brine Incense");
		addItem(WetlandWhimsyItems.ROT_INCENSE, "Rot Incense");
		addItem(WetlandWhimsyItems.WEBBED_INCENSE, "Webbed Incense");

		addEntityType(WetlandWhimsyEntityTypes.BLEMISH, "Blemish");
		addItem(WetlandWhimsyItems.BLEMISH_SPAWN_EGG, "Blemish Spawn Egg");

		addEntityType(WetlandWhimsyEntityTypes.CRANE, "Crane");
		addItem(WetlandWhimsyItems.CRANE_SPAWN_EGG, "Crane Spawn Egg");

		addEntityType(WetlandWhimsyEntityTypes.SWAMP_SPIDER, "Swamp Spider");
		addItem(WetlandWhimsyItems.SWAMP_SPIDER_SPAWN_EGG, "Swamp Spider Spawn Egg");

		add("biome.wetland_whimsy.marsh", "Marsh");
		add("trim_pattern.wetland_whimsy.dots", "Dots Armor Trim");
		add("jukebox_song.wetland_whimsy.nuke_the_swamps", "Quizzly - Nuke The Swamps");

		add(WetlandWhimsy.MODID + ".midnightconfig.category.worldgen", "World Generation");
		add(WetlandWhimsy.MODID + ".midnightconfig.category.swamp_dungeon", "Swamp Dungeon");

		add(
			WetlandWhimsy.MODID + ".swamp_hut_disabled", 
			""" 
			The Vanilla Swamp Hut has been disabled by Wetland Whimsy.
			Search for "wetland_whimsy:witch_hut" instead,
			or re-enable vanilla witch huts in the config. 
			"""
		);

		addAdvancement("wetland_ruins", "Old-School", "Find Wetland Ruins in a swampy biome");
		addAdvancement("swamp_dungeon", "Attacking Vertical", "Locate and enter a Swamp Dungeon");
		addAdvancement("ancient_brazier", "🔥🔥🔥", "Light an Ancient Brazier");
		addAdvancement("dagger", "Stabby Stabby", "Craft a Dagger, which deals damage based on proximity");
		addAdvancement("incense", "You Smell Beautiful Today", "Use any incense on an Ancient Brazier");
		addAdvancement("ancient_pot", "Minimum Wage", "Insert an Ancient Coin into an Ancient Pot");
		addAdvancement("high_quality", "High Quality", "Break an Ancient Pot with 20 or more Ancient Coins inside");
	}

	private void addAdvancement(String key, String title, String desc) {
		var str = "advancements." + WetlandWhimsy.MODID + '.' + key;

		add(str + ".title", title);
		add(str + ".desc", desc);
	}
}
