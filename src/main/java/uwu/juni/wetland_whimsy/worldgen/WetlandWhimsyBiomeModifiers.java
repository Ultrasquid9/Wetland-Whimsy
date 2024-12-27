package uwu.juni.wetland_whimsy.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.modifiers.BogEnvironmentBiomeModifier;

public class WetlandWhimsyBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(
		ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<Codec<BogEnvironmentBiomeModifier>> BOG_ENVIRONMENT = BIOME_MODIFIERS.register(
		"bog_environment", 
		() -> RecordCodecBuilder.create(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(BogEnvironmentBiomeModifier::biomes)
		).apply(builder, BogEnvironmentBiomeModifier::new))
	);
}
