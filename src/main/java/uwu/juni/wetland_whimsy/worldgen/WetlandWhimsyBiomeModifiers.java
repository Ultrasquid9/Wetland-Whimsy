package uwu.juni.wetland_whimsy.worldgen;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;

import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.modifiers.EnvironmentBiomeModifier;

public class WetlandWhimsyBiomeModifiers {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = WetlandWhimsy.registry(
		NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS
	);

	public static final Supplier<MapCodec<EnvironmentBiomeModifier>> BOG_ENVIRONMENT = BIOME_MODIFIERS.register(
		"environment_modifier", 
		() -> EnvironmentBiomeModifier.CODEC
	);
}
