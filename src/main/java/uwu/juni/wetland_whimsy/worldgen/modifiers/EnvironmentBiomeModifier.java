package uwu.juni.wetland_whimsy.worldgen.modifiers;


import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@ParametersAreNonnullByDefault
public record EnvironmentBiomeModifier(
	HolderSet<Biome> biomes,
	Optional<Integer> newFogColor,
	Optional<Integer> newSkyColor,
	Optional<Integer> newWaterColor,
	Optional<Integer> newWaterFogColor,
	Optional<Integer> newGrassColorOverride,
	Optional<Integer> newFoliageColorOverride,
	Optional<BiomeSpecialEffects.GrassColorModifier> newGrassModifier
) implements BiomeModifier {
	public static MapCodec<EnvironmentBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(EnvironmentBiomeModifier::biomes),

			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_fog_color", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newFogColor),
			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_sky_color", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newSkyColor),
			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_water_color", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newWaterColor),
			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_water_fog_color", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newWaterFogColor),
			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_grass_color_override", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newGrassColorOverride),
			ExtraCodecs.optionalEmptyMap(Codec.INT)
				.optionalFieldOf("new_foliage_color_override", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newFoliageColorOverride),
			ExtraCodecs.optionalEmptyMap(BiomeSpecialEffects.GrassColorModifier.CODEC)
				.optionalFieldOf("new_grass_color_modifier", Optional.empty())
				.forGetter(EnvironmentBiomeModifier::newGrassModifier)
		)
		.apply(instance, EnvironmentBiomeModifier::new)
	);

	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		if (!biomes.contains(biome) || !phase.equals(Phase.BEFORE_EVERYTHING)) 
			return;

		WetlandWhimsy.LOGGER.debug(
			  "\nnewFogColor: " + newFogColor 
			+ "\nnewSkyColor: " + newSkyColor
			+ "\nnewWaterColor: " + newWaterColor
			+ "\nnewWaterFogColor: " + newWaterFogColor
			+ "\nnewGrassColorOverride: " + newGrassColorOverride
			+ "\nnewFoliageColorOverride: " + newFoliageColorOverride
			+ "\nnewGrassModifier: " + newGrassModifier
		);

		var environment = builder.getSpecialEffects();

		environment
			.fogColor(newFogColor.orElse(environment.getFogColor()))
			.skyColor(newSkyColor.orElse(environment.getSkyColor()))
			.waterColor(newWaterColor.orElse(environment.waterColor()))
			.waterFogColor(newWaterFogColor.orElse(environment.getWaterFogColor()))
			.grassColorModifier(newGrassModifier.orElse(environment.getGrassColorModifier()));

		// These getters for these two return optionals, adding them to the chain could cause a panic
		if (newGrassColorOverride.isPresent())
			environment.grassColorOverride(newGrassColorOverride.get());

		if (newFoliageColorOverride.isPresent())
			environment.foliageColorOverride(newFoliageColorOverride.get());
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
