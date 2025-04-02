package uwu.juni.wetland_whimsy.datagen.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

public class WetlandWhimsyBiomes {
	public static final ResourceKey<Biome> MARSH = createKey("marsh");

	static final BiomeSpecialEffects MARSH_EFFECTS = new BiomeSpecialEffects.Builder()
		.fogColor(0xAEC4DD)
		.skyColor(0x829ED4)
		.waterColor(0x446C84)
		.waterFogColor(0x446C84)
		.grassColorOverride(0x86974D)
		.foliageColorOverride(0x567238)
		//.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
		.backgroundMusic(new Music(SoundEvents.MUSIC_BIOME_SWAMP, 12000, 24000, false))
		.build();

	public static void bootstap(BootstrapContext<Biome> context) {
		context.register(MARSH, marshBiome(context));
	}

	static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, WetlandWhimsy.rLoc(name));
    }

	static Biome marshBiome(BootstrapContext<Biome> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

		var settings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		BiomeDefaultFeatures.addDefaultCarversAndLakes(settings);
		BiomeDefaultFeatures.addFerns(settings);
		BiomeDefaultFeatures.addDefaultGrass(settings);

		var mobs = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(mobs);
		BiomeDefaultFeatures.farmAnimals(mobs);
		BiomeDefaultFeatures.oceanSpawns(mobs, 10, 8, 20);

		return new Biome.BiomeBuilder()
			.hasPrecipitation(true)
			.temperature(0.8f)
			.downfall(0.9f)
			.mobSpawnSettings(
				mobs
					.addSpawn(MobCategory.AMBIENT, spawnData(EntityType.BAT, 10, 8, 8))
					.addSpawn(MobCategory.CREATURE, spawnData(EntityType.FROG, 10, 2, 5))
					.addSpawn(MobCategory.CREATURE, spawnData(WetlandWhimsyEntityTypes.CRANE.get(), 30, 2, 4))
					.addSpawn(MobCategory.MONSTER, spawnData(EntityType.BOGGED, 30, 4, 4))
					.addSpawn(MobCategory.MONSTER, spawnData(EntityType.SLIME, 100, 4, 4))
					.addSpawn(MobCategory.MONSTER, spawnData(EntityType.DROWNED, 30, 3, 3))
					.build()
			)
			.specialEffects(MARSH_EFFECTS)
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
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE_LOWER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_MEDIUM)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_LARGE)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_BURIED)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS_BURIED)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COPPER)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CavePlacements.UNDERWATER_MAGMA)
					.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY)

					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandWhimsyPlacedFeatures.LIMESTONE_DISK_MARSH)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandWhimsyPlacedFeatures.MUD_POOL_MARSH)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandWhimsyPlacedFeatures.TREES_MARSH)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandWhimsyPlacedFeatures.SUPER_THICK_CORDGRASS_PATCH)
					.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WetlandWhimsyPlacedFeatures.MUD_DISK)
					.build()
			)
			.temperatureAdjustment(Biome.TemperatureModifier.NONE)
			.build();
	}

	static MobSpawnSettings.SpawnerData spawnData(EntityType<?> entity, int weight, int min, int max) {
		return new MobSpawnSettings.SpawnerData(entity, weight, min, max);
	}
}
