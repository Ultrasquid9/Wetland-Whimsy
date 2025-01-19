package uwu.juni.wetland_whimsy.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.modifiers.*;
import uwu.juni.wetland_whimsy.worldgen.modifiers.ConfigurableModifiers.ConfigurableAdditions;
import uwu.juni.wetland_whimsy.worldgen.modifiers.ConfigurableModifiers.ConfigurableRemovals;

public class WetlandWhimsyBiomeModifiers {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = WetlandWhimsy.registry(
		NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS
	);

	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<BogBiomeModifier>> BOG_ENVIRONMENT = BIOME_MODIFIERS.register(
		"bog_environment", 
		() -> RecordCodecBuilder.mapCodec(instance -> instance
			.group(
				Biome.LIST_CODEC
					.fieldOf("biomes")
					.forGetter(BogBiomeModifier::biomes)
			)
			.apply(instance, BogBiomeModifier::new)
		)
	);

	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<ConfigurableAdditions>> CONFIGURABLE_ADDITIONS = BIOME_MODIFIERS.register(
		"configurable_additions", 
		() -> RecordCodecBuilder.mapCodec(instance -> instance
			.group(
				Biome.LIST_CODEC
					.fieldOf("biomes")
					.forGetter(ConfigurableAdditions::biomes),

				PlacedFeature.LIST_CODEC
					.fieldOf("features")
					.forGetter(ConfigurableAdditions::features),

				Decoration.CODEC
					.fieldOf("step")
					.forGetter(ConfigurableAdditions::step)
			)
			.apply(instance, ConfigurableAdditions::new)
		)
	);
	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<ConfigurableRemovals>> CONFIGURABLE_REMOVALS = BIOME_MODIFIERS.register(
		"configurable_removals", 
		() -> RecordCodecBuilder.mapCodec(instance -> instance
			.group(
				Biome.LIST_CODEC
					.fieldOf("biomes")
					.forGetter(ConfigurableRemovals::biomes),

				PlacedFeature.LIST_CODEC
					.fieldOf("features")
					.forGetter(ConfigurableRemovals::features)
			)
			.apply(instance, ConfigurableRemovals::new)
		)
	);
}
