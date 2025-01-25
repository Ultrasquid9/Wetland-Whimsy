package uwu.juni.wetland_whimsy.datagen.registries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.neoforged.neoforge.common.Tags;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

public class WetlandWhimsyStructures {
	public static final ResourceKey<Structure> ARCH = createKey("arch");
	public static final ResourceKey<Structure> PILLAR = createKey("pillar");
	public static final ResourceKey<Structure> WALL = createKey("wall");
	public static final ResourceKey<Structure> ARENA = createKey("arena");
	public static final ResourceKey<Structure> SWAMP_DUNGEON = createKey("swamp_dungeon");

	private static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(
			Registries.STRUCTURE, 
			WetlandWhimsy.rLoc(name)
		);
	}

	public static void bootstap(BootstrapContext<Structure> context) {
		registerSurfaceRuins(
			context, 
			ARCH, 
			WetlandWhimsyStructurePools.ARCH
		);

		registerSurfaceRuins(
			context, 
			PILLAR, 
			WetlandWhimsyStructurePools.PILLAR
		);

		registerSurfaceRuins(
			context, 
			WALL, 
			WetlandWhimsyStructurePools.WALL
		);

		registerSurfaceRuins(
			context, 
			ARENA, 
			WetlandWhimsyStructurePools.ARENA
		);

		// Me when the nesting 
		var biomes = context.lookup(Registries.BIOME);
		registerSurfaceRuins(
			context, 
			SWAMP_DUNGEON, 
			WetlandWhimsyStructurePools.SWAMP_DUNGEON_ENTRANCE,
			new StructureSettings(
				biomes.getOrThrow(Tags.Biomes.IS_SWAMP), 
				Map.of(
					MobCategory.MONSTER,
					new StructureSpawnOverride(
						BoundingBoxType.PIECE, 
						WeightedRandomList.create(
							new MobSpawnSettings.SpawnerData(
								WetlandWhimsyEntityTypes.BLEMISH.get(), 
								2, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								WetlandWhimsyEntityTypes.SWAMP_SPIDER.get(), 
								1, 
								1, 
								1
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.BOGGED, 
								3, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.CAVE_SPIDER, 
								3, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.DROWNED, 
								3, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.ZOMBIE, 
								4, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.SKELETON, 
								4, 
								1, 
								3
							),
							new MobSpawnSettings.SpawnerData(
								EntityType.SPIDER, 
								3, 
								1, 
								2
							)
						)
					)
				), 
				GenerationStep.Decoration.SURFACE_STRUCTURES, 
				TerrainAdjustment.NONE
			)
		);
	}

	public static void registerSurfaceRuins(
		BootstrapContext<Structure> context, 
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool
	) { 
		var biomes = context.lookup(Registries.BIOME);

		registerSurfaceRuins(
			context, 
			structure, 
			pool,
			new StructureSettings(
				biomes.getOrThrow(Tags.Biomes.IS_SWAMP), 
				Map.of(), 
				GenerationStep.Decoration.SURFACE_STRUCTURES, 
				TerrainAdjustment.BEARD_THIN
			)
		);
	}

	public static void registerSurfaceRuins(
		BootstrapContext<Structure> context, 
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool,
		StructureSettings settings
	) {
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(
			structure, 
			new JigsawStructure(
				settings,
				templatePools.getOrThrow(pool), 
				Optional.empty(), 
				16, 
				ConstantHeight.of(VerticalAnchor.absolute(0)), 
				false, 
				Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 
				100, 
				List.of(), 
				DimensionPadding.ZERO, 
				LiquidSettings.IGNORE_WATERLOGGING
			)
		);
	}
}
