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
