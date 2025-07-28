package uwu.juni.wetland_whimsy.worldgen;

import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.modifiers.EnvironmentBiomeModifier;

public class WetlandWhimsyBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(
		ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<Codec<EnvironmentBiomeModifier>> ENVIRONMENT_MODIFIER = BIOME_MODIFIERS.register(
		"environment_modifier", 
		() -> EnvironmentBiomeModifier.CODEC
	);
}
