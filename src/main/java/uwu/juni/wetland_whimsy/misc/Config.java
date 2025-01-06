package uwu.juni.wetland_whimsy.misc;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class Config extends MidnightConfig {
	public static final String BIOMES = "biomes";
	public static final String SWAMP_DUNGEON = "swamp_dungeon";

	/* -- Swamp Dungeon -- */

	@Entry(
		category = SWAMP_DUNGEON,
		name = "Ancient Pot Items",
		idMode = 0
	)
	public static List<ResourceLocation> ancientPotItems = Lists.newArrayList(
		ResourceLocation.withDefaultNamespace("diamond"),
		ResourceLocation.withDefaultNamespace("emerald"),
		ResourceLocation.withDefaultNamespace("lapis_lazuli"),
		ResourceLocation.withDefaultNamespace("amethyst_shard"),
		ResourceLocation.withDefaultNamespace("gold_ingot"),
		ResourceLocation.withDefaultNamespace("iron_ingot"),
		ResourceLocation.withDefaultNamespace("raw_gold"),
		ResourceLocation.withDefaultNamespace("raw_iron"),

		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "disc"),
		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "dots_armor_trim_smithing_template"),

		ResourceLocation.withDefaultNamespace("diamond_helmet"),
		ResourceLocation.withDefaultNamespace("diamond_chestplate"),
		ResourceLocation.withDefaultNamespace("diamond_leggings"),
		ResourceLocation.withDefaultNamespace("diamond_boots"),
		ResourceLocation.withDefaultNamespace("diamond_pickaxe"),
		ResourceLocation.withDefaultNamespace("diamond_axe"),
		ResourceLocation.withDefaultNamespace("diamond_sword"),
		ResourceLocation.withDefaultNamespace("diamond_shovel"),
		ResourceLocation.withDefaultNamespace("diamond_hoe"),		
		ResourceLocation.withDefaultNamespace("iron_helmet"),
		ResourceLocation.withDefaultNamespace("iron_chestplate"),
		ResourceLocation.withDefaultNamespace("iron_leggings"),
		ResourceLocation.withDefaultNamespace("iron_boots"),
		ResourceLocation.withDefaultNamespace("iron_pickaxe"),
		ResourceLocation.withDefaultNamespace("iron_axe"),
		ResourceLocation.withDefaultNamespace("iron_sword"),
		ResourceLocation.withDefaultNamespace("iron_shovel"),
		ResourceLocation.withDefaultNamespace("iron_hoe"),
		ResourceLocation.withDefaultNamespace("gold_helmet"),
		ResourceLocation.withDefaultNamespace("gold_chestplate"),
		ResourceLocation.withDefaultNamespace("gold_leggings"),
		ResourceLocation.withDefaultNamespace("gold_boots"),
		ResourceLocation.withDefaultNamespace("gold_pickaxe"),
		ResourceLocation.withDefaultNamespace("gold_axe"),
		ResourceLocation.withDefaultNamespace("gold_sword"),
		ResourceLocation.withDefaultNamespace("gold_shovel"),
		ResourceLocation.withDefaultNamespace("gold_hoe"),

		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "bald_cypress_log"),
		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "bald_cypress_sapling"),

		ResourceLocation.withDefaultNamespace("golden_carrot"),
		ResourceLocation.withDefaultNamespace("bread"),
		ResourceLocation.withDefaultNamespace("wheat"),
		ResourceLocation.withDefaultNamespace("beetroot"),
		ResourceLocation.withDefaultNamespace("wheat_seeds"),
		ResourceLocation.withDefaultNamespace("beetroot_seeds"),
		ResourceLocation.withDefaultNamespace("blue_orchid"),
		ResourceLocation.withDefaultNamespace("red_mushroom"),
		ResourceLocation.withDefaultNamespace("brown_mushroom"),
		ResourceLocation.withDefaultNamespace("slimeball"),
		ResourceLocation.withDefaultNamespace("mud"),
		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "pennywort"),
		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "cordgrass")
	);

	/* -- Biomes -- */

	@Entry(
		category = BIOMES,
		name = "Generate Marsh"
	)
	public static boolean generateMarsh = true;

	@Entry(
		category = BIOMES,
		name = "Change Swamp"
	)
	public static boolean changeSwamp = true;
}
