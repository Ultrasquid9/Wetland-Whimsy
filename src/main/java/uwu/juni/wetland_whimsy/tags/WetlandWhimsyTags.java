package uwu.juni.wetland_whimsy.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyTags {
	public static class Blocks {
		public static final TagKey<Block> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.BLOCK, 
			WetlandWhimsy.rLoc("bald_cypress_logs")
		);
	}

	public static class Items {
		public static final TagKey<Item> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("bald_cypress_logs")
		);

		public static final TagKey<Item> FLAMMABLE = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("flammable")
		);

		public static final TagKey<Item> INCENSE = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("incense")
		);

		public static final TagKey<Item> CRANE_FOOD = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("crane_food")
		);
	}

	public static class Entities {
		public static final TagKey<EntityType<?>> BLOODCAP_IMMUNE = TagKey.create(
			Registries.ENTITY_TYPE, 
			WetlandWhimsy.rLoc("bloodcap_immune")
		);

		public static final TagKey<EntityType<?>> SPAWNS_FROM_ANCIENT_BRAZIER = TagKey.create(
			Registries.ENTITY_TYPE, 
			WetlandWhimsy.rLoc("spawns_from_ancient_brazier")
		);
	}

	public static class Structures {
		public static final TagKey<Structure> WETLAND_RUINS = TagKey.create(
			Registries.STRUCTURE,
			WetlandWhimsy.rLoc("wetland_ruins")
		);
	}

	public static class Biomes {
		public static final TagKey<Biome> BLOODCAP_SURFACE = TagKey.create(
			Registries.BIOME, 
			WetlandWhimsy.rLoc("bloodcap_surface")
		);
	}

	public static class Features {
		public static final TagKey<PlacedFeature> REMOVED_SWAMP_TREES = TagKey.create(
			Registries.PLACED_FEATURE,
			WetlandWhimsy.rLoc("removed_swamp_trees")
		);
	}
}
