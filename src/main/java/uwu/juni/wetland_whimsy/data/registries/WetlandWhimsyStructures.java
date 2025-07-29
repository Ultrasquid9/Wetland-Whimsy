package uwu.juni.wetland_whimsy.data.registries;

import java.util.Map;
import java.util.Optional;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraftforge.common.Tags;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

public class WetlandWhimsyStructures {
	public static final ResourceKey<Structure> ARCH = createKey("arch");
	public static final ResourceKey<Structure> PILLAR = createKey("pillar");
	public static final ResourceKey<Structure> WALL = createKey("wall");
	public static final ResourceKey<Structure> ARENA = createKey("arena");
	public static final ResourceKey<Structure> SWAMP_DUNGEON = createKey("swamp_dungeon");

	public static final ResourceKey<Structure> WITCH_HUT = createKey("witch_hut");

	final Map<MobCategory, StructureSpawnOverride> SWAMP_DUNGEON_SPAWNS = Map.of(
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
	);
	final Map<MobCategory, StructureSpawnOverride> WITCH_HUT_SPAWNS = Map.of(
		MobCategory.CREATURE,
		new StructureSpawnOverride(
			BoundingBoxType.PIECE, 
			WeightedRandomList.create(
				new MobSpawnSettings.SpawnerData(
					EntityType.CAT, 
					1, 
					1, 
					1
				)
			)
		),
		MobCategory.MONSTER,
		new StructureSpawnOverride(
			BoundingBoxType.PIECE, 
			WeightedRandomList.create(
				new MobSpawnSettings.SpawnerData(
					EntityType.WITCH, 
					1, 
					1, 
					1
				)
			)
		)
	);

	BootstapContext<Structure> context;

	public static void bootstap(BootstapContext<Structure> context) {
		new WetlandWhimsyStructures(context);
	}

	WetlandWhimsyStructures(BootstapContext<Structure> context) {
		this.context = context;

		registerSurfaceStructure(
			ARCH, 
			WetlandWhimsyStructurePools.ARCH
		);

		registerSurfaceStructure(
			PILLAR, 
			WetlandWhimsyStructurePools.PILLAR
		);

		registerSurfaceStructure(
			WALL, 
			WetlandWhimsyStructurePools.WALL
		);

		registerSurfaceStructure(
			ARENA, 
			WetlandWhimsyStructurePools.ARENA
		);

		registerSurfaceStructure(
			SWAMP_DUNGEON, 
			WetlandWhimsyStructurePools.SWAMP_DUNGEON_ENTRANCE,
			ConstantHeight.of(VerticalAnchor.absolute(-2)),
			new StructureSettings(
				lookupBiome(Tags.Biomes.IS_SWAMP), 
				SWAMP_DUNGEON_SPAWNS,
				GenerationStep.Decoration.SURFACE_STRUCTURES, 
				TerrainAdjustment.NONE
			)
		);

		registerSurfaceStructure(
			WITCH_HUT, 
			WetlandWhimsyStructurePools.WITCH_HUT,
			ConstantHeight.ZERO,
			new StructureSettings(
				lookupBiome(BiomeTags.HAS_SWAMP_HUT), 
				WITCH_HUT_SPAWNS,
				GenerationStep.Decoration.SURFACE_STRUCTURES, 
				TerrainAdjustment.BEARD_THIN
			)
		);
	}

	void registerSurfaceStructure(
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool
	) {
		registerSurfaceStructure(
			structure, 
			pool,
			ConstantHeight.ZERO,
			new StructureSettings(
				lookupBiome(Tags.Biomes.IS_SWAMP), 
				Map.of(), 
				GenerationStep.Decoration.SURFACE_STRUCTURES, 
				TerrainAdjustment.BEARD_THIN
			)
		);
	}

	void registerSurfaceStructure(
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool,
		ConstantHeight height,
		StructureSettings settings
	) {
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(
			structure, 
			new JigsawStructure(
				settings,
				templatePools.getOrThrow(pool), 
				Optional.empty(), 
				7, 
				height, 
				false, 
				Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 
				100
			)
		);
	}

	HolderSet<Biome> lookupBiome(TagKey<Biome> tag) {
		return context.lookup(Registries.BIOME).getOrThrow(tag);
	}

	static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(
			Registries.STRUCTURE, 
			WetlandWhimsy.rLoc(name)
		);
	}
}
