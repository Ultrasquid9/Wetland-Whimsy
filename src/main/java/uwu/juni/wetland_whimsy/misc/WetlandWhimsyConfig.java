package uwu.juni.wetland_whimsy.misc;

import net.neoforged.neoforge.common.ModConfigSpec;

public class WetlandWhimsyConfig {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	public static final ModConfigSpec.BooleanValue GENERATE_MARSH = BUILDER
		.comment(" Whether the Marsh Biome should be allowed to generate")
		.comment(" Requires game restart")
		.gameRestart()
		.define("generate_marsh", () -> true);

	public static final ModConfigSpec.BooleanValue DISABLE_VANILLA_SWAMP_HUTS = BUILDER
		.comment(" Whether the Vanilla Swamp Hut should be disabled")
		.define("disable_vanilla_swamp_huts", () -> true);

	public static final ModConfigSpec.IntValue ANCIENT_POT_MAX_PARTICLES = BUILDER
		.comment(" How many particles the Ancient Pot can create")
		.defineInRange("ancient_pot_max_particles", () -> 24, 0, 256);

	public static final ModConfigSpec SPEC = BUILDER.build();

	public static boolean disableVanillaSwampHutsOrDefault() {
		boolean disabled;

		try {
			disabled = WetlandWhimsyConfig.DISABLE_VANILLA_SWAMP_HUTS.get();
		} catch (Exception e) {
			disabled = WetlandWhimsyConfig.DISABLE_VANILLA_SWAMP_HUTS.getDefault();
		}

		return disabled;
	}
}
