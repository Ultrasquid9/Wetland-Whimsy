package corundum.wetland_whimsy.data.worldgen;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WetlandsWhimsyBiomesDatagen {
	private static final BiomeSpecialEffects.Builder BOG_EFFECTS = new BiomeSpecialEffects.Builder()
		.fogColor(0xABD2FF)
		.skyColor(0x78A7FF)
		.waterColor(0x4C6559)
		.waterFogColor(0x4C6559)
		.grassColorOverride(0x527e40)
		.foliageColorOverride(0x5F6F35)
		.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
		.backgroundMusic(new Music(SoundEvents.MUSIC_BIOME_SWAMP, 12000, 24000, false));

	public static final ResourceKey<Biome> BOG = createKey("bog");
	public static final ResourceKey<Biome> MARSH = createKey("marsh");

	public static void bootstap(BootstrapContext<Biome> context) {
		context.register(BOG, bogBiome(context));
		//context.register(MARSH, null);
	}

	private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name));
    }

	public static Biome bogBiome(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

		var generationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandsWhimsyPlacedFeaturesDatagen.CORDGRASS_PATCH)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandsWhimsyPlacedFeaturesDatagen.PENNYWORT_PATCH)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandsWhimsyPlacedFeaturesDatagen.TREES_BOG);

		return makeBiome(generationSettings, BOG_EFFECTS);
	}

	public static Biome makeBiome(BiomeGenerationSettings.Builder settings, BiomeSpecialEffects.Builder effects) {
		BiomeDefaultFeatures.addDefaultOres(settings);
		BiomeDefaultFeatures.addDefaultCarversAndLakes(settings);
		BiomeDefaultFeatures.addDefaultGrass(settings);
		BiomeDefaultFeatures.addDefaultMushrooms(settings);
		BiomeDefaultFeatures.addFerns(settings);
		BiomeDefaultFeatures.addDefaultSeagrass(settings);
		BiomeDefaultFeatures.addSwampVegetation(settings);
		BiomeDefaultFeatures.addSwampExtraVegetation(settings);
		BiomeDefaultFeatures.addSwampClayDisk(settings);

		var mobs = new MobSpawnSettings.Builder();

		BiomeDefaultFeatures.commonSpawns(mobs);
		BiomeDefaultFeatures.farmAnimals(mobs);

		return new Biome.BiomeBuilder()
			.hasPrecipitation(true)
			.temperature(0.8f)
			.downfall(0.9f)
			.mobSpawnSettings(
				mobs
					.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8))
					.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5))
					.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BOGGED, 30, 4, 4))
					.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4))
					.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 30, 3, 3))
					.build()
			)
			.specialEffects(effects.build())
			.generationSettings(
				settings
					.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.AMETHYST_GEODE)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.FOSSIL_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.FOSSIL_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.MONSTER_ROOM)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.MONSTER_ROOM_DEEP)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRANITE_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRANITE_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_TUFF)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP)
					.build()
			)
			.temperatureAdjustment(Biome.TemperatureModifier.NONE)
			.build();
	}
}
