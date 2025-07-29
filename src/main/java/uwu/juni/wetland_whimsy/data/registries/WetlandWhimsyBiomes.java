package uwu.juni.wetland_whimsy.data.registries;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
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
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WetlandWhimsyBiomes {
	public static final ResourceKey<Biome> MARSH = createKey("marsh");


	static final BiomeSpecialEffects MARSH_EFFECTS = new BiomeSpecialEffects.Builder()
		.fogColor(0xAEC4DD)
		.skyColor(0x829ED4)
		.waterColor(0x446C84)
		.waterFogColor(0x446C84)
		.grassColorOverride(0x86974D)
		.foliageColorOverride(0x567238)
		.backgroundMusic(new Music(SoundEvents.MUSIC_BIOME_SWAMP, 12000, 24000, false))
		.build();

	public static void bootstap(BootstapContext<Biome> context) {
		context.register(MARSH, new Biome.BiomeBuilder()
			.hasPrecipitation(true)
			.temperature(0.8f)
			.downfall(0.9f)
			.specialEffects(MARSH_EFFECTS)
			.temperatureAdjustment(Biome.TemperatureModifier.NONE)
			.mobSpawnSettings(marshMobSettings())
			.generationSettings(marshGenSettings(context))
			.build()
		);
	}

	static MobSpawnSettings marshMobSettings() {
		var mobs = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(mobs);
		BiomeDefaultFeatures.farmAnimals(mobs);
		BiomeDefaultFeatures.oceanSpawns(mobs, 10, 8, 20);
		mobs
			.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8))
			.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5))
			.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WetlandWhimsyEntityTypes.CRANE.get(), 30, 2, 4))
			.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4))
			.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 30, 3, 3));

		return mobs.build();
	}

	static BiomeGenerationSettings marshGenSettings(BootstapContext<Biome> context) {
		final var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		final var configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

		var settings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		
		BiomeDefaultFeatures.addDefaultCarversAndLakes(settings);
		BiomeDefaultFeatures.addFerns(settings);

		addFeatures(
			settings,
			Decoration.UNDERGROUND_STRUCTURES,
			CavePlacements.AMETHYST_GEODE,
			CavePlacements.FOSSIL_UPPER,
			CavePlacements.FOSSIL_LOWER,
			CavePlacements.MONSTER_ROOM,
			CavePlacements.MONSTER_ROOM_DEEP
		);
		addFeatures(
			settings,
			Decoration.UNDERGROUND_ORES,
			OrePlacements.ORE_GRANITE_UPPER,
			OrePlacements.ORE_GRANITE_LOWER,
			OrePlacements.ORE_DIORITE_UPPER,
			OrePlacements.ORE_DIORITE_LOWER,
			OrePlacements.ORE_ANDESITE_UPPER,
			OrePlacements.ORE_ANDESITE_LOWER,
			OrePlacements.ORE_TUFF,
			OrePlacements.ORE_COAL_UPPER,
			OrePlacements.ORE_COAL_LOWER,
			OrePlacements.ORE_IRON_UPPER,
			OrePlacements.ORE_IRON_MIDDLE,
			OrePlacements.ORE_IRON_SMALL,
			OrePlacements.ORE_GOLD,
			OrePlacements.ORE_GOLD_LOWER,
			OrePlacements.ORE_REDSTONE,
			OrePlacements.ORE_REDSTONE_LOWER,
			OrePlacements.ORE_DIAMOND,
			OrePlacements.ORE_DIAMOND_LARGE,
			OrePlacements.ORE_DIAMOND_BURIED,
			OrePlacements.ORE_LAPIS,
			OrePlacements.ORE_LAPIS_BURIED,
			OrePlacements.ORE_COPPER,
			CavePlacements.UNDERWATER_MAGMA,
			MiscOverworldPlacements.DISK_CLAY
		);
		addFeatures(
			settings,
			Decoration.VEGETAL_DECORATION,
			VegetationPlacements.FLOWER_SWAMP,
			VegetationPlacements.PATCH_GRASS_NORMAL,
			VegetationPlacements.PATCH_DEAD_BUSH,
			VegetationPlacements.PATCH_SUGAR_CANE,
			VegetationPlacements.PATCH_PUMPKIN,
			AquaticPlacements.SEAGRASS_SWAMP,
			WetlandWhimsyPlacedFeatures.LIMESTONE_BLOB_MARSH,
			WetlandWhimsyPlacedFeatures.MUD_POOL_MARSH,
			WetlandWhimsyPlacedFeatures.MUD_PATCH_MARSH,
			WetlandWhimsyPlacedFeatures.TREES_MARSH,
			WetlandWhimsyPlacedFeatures.SUPER_THICK_CORDGRASS_PATCH,
			WetlandWhimsyPlacedFeatures.MUD_BLOB
		);

		return settings.build();
	}

	@SafeVarargs
	static void addFeatures(
		BiomeGenerationSettings.Builder settings, 
		Decoration decoration, 
		ResourceKey<PlacedFeature>... features
	) {
		for (var feature : features) {
			settings.addFeature(decoration, feature);
		}
	}

	static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(Registries.BIOME, WetlandWhimsy.rLoc(name));
	}
}
