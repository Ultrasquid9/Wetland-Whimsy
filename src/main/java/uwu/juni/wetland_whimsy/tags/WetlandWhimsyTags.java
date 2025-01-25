package uwu.juni.wetland_whimsy.tags;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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

		public static final TagKey<Item> ANCIENT_POT_KEY = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("scalable_reward_inputs")
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
}
