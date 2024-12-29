package uwu.juni.wetland_whimsy.data.registries;

import java.util.Map;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.neoforged.neoforge.common.Tags;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyStructures {
	public static final ResourceKey<Structure> ARCH = createKey("arch");
	public static final ResourceKey<Structure> PILLAR = createKey("pillar");
	public static final ResourceKey<Structure> WALL = createKey("wall");
	public static final ResourceKey<Structure> ARENA = createKey("arena");

	private static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(
			Registries.STRUCTURE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
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
			WetlandWhimsyStructurePools.ARENA_BASE
		);
	}

	public static void registerSurfaceRuins(
		BootstrapContext<Structure> context, 
		ResourceKey<Structure> structure, 
		ResourceKey<StructureTemplatePool> pool
	) {
		var biomes = context.lookup(Registries.BIOME);
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(
			structure, 
			new JigsawStructure(
				new StructureSettings(
					biomes.getOrThrow(Tags.Biomes.IS_SWAMP), 
					Map.of(), 
					GenerationStep.Decoration.SURFACE_STRUCTURES, 
					TerrainAdjustment.BEARD_THIN
				), 
				templatePools.getOrThrow(pool), 
				1, 
				ConstantHeight.of(VerticalAnchor.absolute(0)), 
				false,
				Heightmap.Types.WORLD_SURFACE_WG
			)
		);
	}
}