package uwu.juni.wetland_whimsy.misc;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
	public static final String BIOMES = "biomes";
	public static final String SWAMP_DUNGEON = "swamp_dungeon";

	public static final boolean CURRENTLY_IRRELEVANT_VALUE = false;

	/* -- Swamp Dungeon -- */

	@Entry(
		category = SWAMP_DUNGEON,
		name = "Ancient Pot Max Particles",
		isSlider = true,
		min = 0,
		max = 256
	)
	public static int ancientPotMaxParticleCount = 24;

	@Entry(
		category = SWAMP_DUNGEON,
		name = "Ancient Pot Max Drops",
		isSlider = true,
		min = 1,
		max = 64
	)
	public static int ancientPotMaxDropCount = 10;

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
