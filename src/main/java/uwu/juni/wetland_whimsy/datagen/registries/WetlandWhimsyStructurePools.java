package uwu.juni.wetland_whimsy.datagen.registries;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.SinglePoolElementAccessor;

public class WetlandWhimsyStructurePools {
	public static final ResourceKey<StructureTemplatePool> ARCH = createKey("arch");
	public static final ResourceKey<StructureTemplatePool> PILLAR = createKey("pillar");
	public static final ResourceKey<StructureTemplatePool> WALL = createKey("wall");

	public static final ResourceKey<StructureTemplatePool> ARENA = createKey("arena/arena_base");

	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_ENTRANCE = createKey("swamp_dungeon/entrance");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_HOLE = createKey("swamp_dungeon/hole");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_ATRIUM = createKey("swamp_dungeon/atrium");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_ROOM = createKey("swamp_dungeon/room");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_ROOM_LOWER = createKey("swamp_dungeon/room_lower");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_SPAWNER = createKey("swamp_dungeon/spawner");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_LOOT = createKey("swamp_dungeon/loot");
	public static final ResourceKey<StructureTemplatePool> SWAMP_DUNGEON_DEAD_END = createKey("swamp_dungeon/dead_end");

	public static final ResourceKey<StructureTemplatePool> WITCH_HUT = createKey("witch_hut/witch_hut");
	public static final ResourceKey<StructureTemplatePool> WITCH = createKey("witch_hut/witch");
	public static final ResourceKey<StructureTemplatePool> CAT = createKey("witch_hut/cat");

	private static ResourceKey<StructureTemplatePool> createKey(String name) {
		return ResourceKey.create(
			Registries.TEMPLATE_POOL, 
			WetlandWhimsy.rLoc(name)
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
			ARENA, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(pool("arena/arena_1", context), 1),
					Pair.of(pool("arena/arena_2", context), 1),
					Pair.of(pool("arena/arena_3", context), 1),
					Pair.of(pool("arena/arena_4", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			SWAMP_DUNGEON_DEAD_END, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_1", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_2", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_3", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_4", context), 1),

					Pair.of(dungeon_pool("swamp_dungeon/hole/hole_dead_end", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		var fallbackSwampDungeon = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(SWAMP_DUNGEON_DEAD_END);

		context.register(
			SWAMP_DUNGEON_ENTRANCE, 
			new StructureTemplatePool(
				fallbackSwampDungeon, 
				ImmutableList.of(
					Pair.of(pool("swamp_dungeon/entrance/entrance", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_HOLE, 
			new StructureTemplatePool(
				fallbackSwampDungeon, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/hole/hole", context), 6),
					Pair.of(dungeon_pool("swamp_dungeon/hole/hole_dead_end", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_ATRIUM, 
			new StructureTemplatePool(
				fallbackSwampDungeon, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/atrium/atrium_1", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/atrium/atrium_2", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/atrium/atrium_3", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_ROOM, 
			new StructureTemplatePool(
				fallbackSwampDungeon,
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_1", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_2", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_3", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_4", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_5", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_6", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_7", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_8", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_9", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/combat_room_10", context), 1),

					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_1", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_2", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_3", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/dead_end_4", context), 1),
					
					Pair.of(dungeon_pool("swamp_dungeon/room/digsite_1", context), 1),

					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_1", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_2", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_3", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_4", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_5", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_6", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_7", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_8", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_9", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_10", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room/hallway_11", context), 2)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_ROOM_LOWER, 
			new StructureTemplatePool(
				fallbackSwampDungeon, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/room_lower/lower_room_1", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room_lower/lower_room_2", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/room_lower/lower_room_3", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_SPAWNER, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/spawner/regular_brazier", context), 1),
					Pair.of(dungeon_pool("swamp_dungeon/spawner/ancient_brazier", context), 3)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			SWAMP_DUNGEON_LOOT, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(dungeon_pool("swamp_dungeon/loot/loot", context), 2),
					Pair.of(dungeon_pool("swamp_dungeon/loot/loot_empty", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);

		context.register(
			WITCH_HUT, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(boring_pool("witch_hut/witch_hut", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			WITCH, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(boring_pool("witch_hut/witch", context), 1)
				),
				StructureTemplatePool.Projection.RIGID
			)
		);
		context.register(
			CAT, 
			new StructureTemplatePool(
				fallback, 
				ImmutableList.of(
					Pair.of(boring_pool("witch_hut/cat", context), 1)
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
			Either.left(WetlandWhimsy.rLoc(id)), 
			context.lookup(Registries.PROCESSOR_LIST).getOrThrow(WetlandWhimsyStructureProcessors.LIMESTONE_RUBBLE), 
			pool,
			Optional.empty()
		);
	}

	private static Function<StructureTemplatePool.Projection, SinglePoolElementAccessor> dungeon_pool(
		String id, 
		BootstrapContext<StructureTemplatePool> context
	) {
		return pool -> new SinglePoolElementAccessor(
			Either.left(WetlandWhimsy.rLoc(id)), 
			context.lookup(Registries.PROCESSOR_LIST).getOrThrow(WetlandWhimsyStructureProcessors.DUNGEON), 
			pool,
			Optional.empty()
		);
	}

	private static Function<StructureTemplatePool.Projection, SinglePoolElementAccessor> boring_pool(
		String id, 
		BootstrapContext<StructureTemplatePool> context
	) {
		return pool -> new SinglePoolElementAccessor(
			Either.left(WetlandWhimsy.rLoc(id)), 
			Holder.direct(new StructureProcessorList(List.of())),
			pool,
			Optional.empty()
		);
	}
}
