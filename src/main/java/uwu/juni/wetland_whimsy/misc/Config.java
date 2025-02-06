package uwu.juni.wetland_whimsy.misc;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
	public static final String WORLDGEN = "worldgen";
	public static final String SWAMP_DUNGEON = "swamp_dungeon";

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

	/* -- World Generation -- */

	@Entry(
		category = WORLDGEN,
		name = "Generate Marsh"
	)
	public static boolean generateMarsh = true;

	@Entry(
		category = WORLDGEN,
		name = "Change Swamp"
	)
	public static boolean changeSwamp = true;

	@Entry(
		category = WORLDGEN,
		name = "Disable Vanilla Swamp Huts"
	)
	public static boolean disableVanillaSwampHuts = true;
}
