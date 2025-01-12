package uwu.juni.wetland_whimsy.misc;

import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@Config(name = WetlandWhimsy.MODID)
public class WetlandWhimsyConfig implements ConfigData {

	public int ancientPotMaxParticleCount = 24;

	public int ancientPotMaxDropCount = 10;

	public List<String> ancientPotItems = List.of(
		"minecraft:diamond",
		"minecraft:emerald",
		"minecraft:lapis_lazuli",
		"minecraft:amethyst_shard",
		"minecraft:gold_ingot",
		"minecraft:iron_ingot",
		"minecraft:raw_gold",
		"minecraft:raw_iron",

		WetlandWhimsy.MODID + "disc",
		WetlandWhimsy.MODID + "dots_armor_trim_smithing_template",

		"minecraft:diamond_helmet",
		"minecraft:diamond_chestplate",
		"minecraft:diamond_leggings",
		"minecraft:diamond_boots",
		"minecraft:diamond_pickaxe",
		"minecraft:diamond_axe",
		"minecraft:diamond_sword",
		"minecraft:diamond_shovel",
		"minecraft:diamond_hoe",
		"minecraft:iron_helmet",
		"minecraft:iron_chestplate",
		"minecraft:iron_leggings",
		"minecraft:iron_boots",
		"minecraft:iron_pickaxe",
		"minecraft:iron_axe",
		"minecraft:iron_sword",
		"minecraft:iron_shovel",
		"minecraft:iron_hoe",
		"minecraft:gold_helmet",
		"minecraft:gold_chestplate",
		"minecraft:gold_leggings",
		"minecraft:gold_boots",
		"minecraft:gold_pickaxe",
		"minecraft:gold_axe",
		"minecraft:gold_sword",
		"minecraft:gold_shovel",
		"minecraft:gold_hoe",

		WetlandWhimsy.MODID + "bald_cypress_log",
		WetlandWhimsy.MODID + "bald_cypress_sapling",

		"minecraft:golden_carrot",
		"minecraft:bread",
		"minecraft:wheat",
		"minecraft:beetroot",
		"minecraft:wheat_seeds",
		"minecraft:beetroot_seeds",
		"minecraft:blue_orchid",
		"minecraft:red_mushroom",
		"minecraft:brown_mushroom",
		"minecraft:slimeball",
		"minecraft:mud",
		WetlandWhimsy.MODID + "pennywort",
		WetlandWhimsy.MODID + "cordgrass"
	);

	public List<String> ancientBrazierEntities = List.of(
		"minecraft:zombie",
		"minecraft:skeleton",
		"minecraft:spider",
		"minecraft:cave_spider",
		"minecraft:drowned",
		"minecraft:witch"
	);
}
