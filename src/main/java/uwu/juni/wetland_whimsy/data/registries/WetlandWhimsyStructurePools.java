package uwu.juni.wetland_whimsy.data.registries;

import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.SinglePoolElementAccessor;

public class WetlandWhimsyStructurePools {
	public static final ResourceKey<StructureTemplatePool> ARCH = createKey("arch");
	public static final ResourceKey<StructureTemplatePool> PILLAR = createKey("pillar");
	public static final ResourceKey<StructureTemplatePool> WALL = createKey("wall");

	public static final ResourceKey<StructureTemplatePool> ARENA_BASE = createKey("arena/arena_base");
	public static final ResourceKey<StructureTemplatePool> ARENA_SPAWNER = createKey("arena/arena_spawner");

	private static ResourceKey<StructureTemplatePool> createKey(String name) {
		return ResourceKey.create(
			Registries.TEMPLATE_POOL, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}

	public static void bootstap(BootstrapContext<StructureTemplatePool> context) {
		var fallback = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);

		context.register(
			ARCH, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("arch_1", context), 1),
					Pair.of(pool("arch_2", context), 1),
					Pair.of(pool("arch_3", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			PILLAR, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("pillar_1", context), 1),
					Pair.of(pool("pillar_2", context), 1),
					Pair.of(pool("pillar_3", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			WALL, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("wall_1", context), 1),
					Pair.of(pool("wall_2", context), 1),
					Pair.of(pool("wall_3", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			ARENA_BASE, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("arena/arena_base/arena_1", context), 1),
					Pair.of(pool("arena/arena_base/arena_2", context), 1),
					Pair.of(pool("arena/arena_base/arena_3", context), 1),
					Pair.of(pool("arena/arena_base/arena_4", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			ARENA_SPAWNER, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("arena/arena_spawner/spawner_1", context), 1),
					Pair.of(pool("arena/arena_spawner/spawner_2", context), 1),
					Pair.of(pool("arena/arena_spawner/spawner_3", context), 1),
					Pair.of(pool("arena/arena_spawner/spawner_4", context), 1),
					Pair.of(pool("arena/arena_spawner/spawner_5", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
	}

	private static Function<StructureTemplatePool.Projection, SinglePoolElementAccessor> pool(
		String id, 
		BootstrapContext<StructureTemplatePool> context
	) {
		return pool -> new SinglePoolElementAccessor(
			Either.left(ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, id)), 
			context.lookup(Registries.PROCESSOR_LIST).getOrThrow(WetlandWhimsyStructureProcessors.LIMESTONE_RUBBLE), 
			pool,
			Optional.empty()
		);
	}
}
