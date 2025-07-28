package uwu.juni.wetland_whimsy.worldgen.modifiers;


import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeSpecialEffects.GrassColorModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

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
	public static Codec<EnvironmentBiomeModifier> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(EnvironmentBiomeModifier::biomes),

			Codec.optionalField("new_fog_color", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newFogColor),
			Codec.optionalField("new_sky_color", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newSkyColor),
			Codec.optionalField("new_water_color", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newWaterColor),
			Codec.optionalField("new_water_fog_color", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newWaterColor),
			Codec.optionalField("new_grass_color_override", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newGrassColorOverride),
			Codec.optionalField("new_foliage_color_override", Codec.INT)
				.forGetter(EnvironmentBiomeModifier::newGrassColorOverride),
			Codec.optionalField("new_grass_color_modifier", GrassColorModifier.CODEC)
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
	public Codec<? extends BiomeModifier> codec() {
		return CODEC;
	}
}
