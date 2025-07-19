package uwu.juni.wetland_whimsy.data.registries;

import java.util.Map;
import java.util.Optional;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraftforge.common.Tags;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

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

	public static void bootstap(BootstapContext<Structure> context) {
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

		registerSurfaceRuins(
			context, 
			SWAMP_DUNGEON, 
			WetlandWhimsyStructurePools.SWAMP_DUNGEON_ENTRANCE,
			TerrainAdjustment.NONE
		);
	}

	public static void registerSurfaceRuins(
		BootstapContext<Structure> context, 
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool
	) {
		registerSurfaceRuins(
			context, 
			structure, 
			pool,
			TerrainAdjustment.BEARD_THIN
		);
	}

	public static void registerSurfaceRuins(
		BootstapContext<Structure> context, 
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool,
		TerrainAdjustment adjustment
	) {
		var biomes = context.lookup(Registries.BIOME);
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(
			structure, 
			new JigsawStructure(
				new StructureSettings(
					biomes.getOrThrow(Tags.Biomes.IS_SWAMP), 
					Map.of(), 
					GenerationStep.Decoration.UNDERGROUND_DECORATION, 
					adjustment
				), 
				templatePools.getOrThrow(pool), 
				Optional.empty(), 
				7, 
				ConstantHeight.of(VerticalAnchor.absolute(0)), 
				false, 
				Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 
				100
			)
		);
	}
}
