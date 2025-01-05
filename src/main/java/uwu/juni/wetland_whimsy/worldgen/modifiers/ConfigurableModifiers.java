package uwu.juni.wetland_whimsy.worldgen.modifiers;

import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import uwu.juni.wetland_whimsy.misc.Config;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;

// Modified version of Neoforge's built-in AddFeaturesBiomeModifier,
// with the ability to be configured 

public class ConfigurableModifiers {
	// Removes features
	public record ConfigurableRemovals(
		HolderSet<Biome> biomes, 
		HolderSet<PlacedFeature> features
	) implements BiomeModifier {
		@Override
		public void modify(
			@Nonnull Holder<Biome> biome, 
			@Nonnull Phase phase, 
			@Nonnull ModifiableBiomeInfo.BiomeInfo.Builder builder
		) {
			if (!Config.changeSwamp) return;

			if (phase == Phase.REMOVE && this.biomes.contains(biome))
				for (Decoration step : EnumSet.allOf(Decoration.class))
					builder.getGenerationSettings().getFeatures(step).removeIf(this.features::contains);
		}

		@Override
		public MapCodec<? extends BiomeModifier> codec() {
			return WetlandWhimsyBiomeModifiers.CONFIGURABLE_REMOVALS.get();
		}
	}

	// Adds features
	public record ConfigurableAdditions(
		HolderSet<Biome> biomes, 
		HolderSet<PlacedFeature> features,
		Decoration step
	) implements BiomeModifier {
		@Override
		public void modify(
			@Nonnull Holder<Biome> biome, 
			@Nonnull Phase phase, 
			@Nonnull ModifiableBiomeInfo.BiomeInfo.Builder builder
		) {
			if (!Config.changeSwamp) return;

			if (phase == Phase.ADD && this.biomes.contains(biome))
				this.features.forEach(holder -> builder.getGenerationSettings().addFeature(this.step, holder));
		}

		@Override
		public MapCodec<? extends BiomeModifier> codec() {
		return WetlandWhimsyBiomeModifiers.CONFIGURABLE_ADDITIONS.get();
		}
	}
}
