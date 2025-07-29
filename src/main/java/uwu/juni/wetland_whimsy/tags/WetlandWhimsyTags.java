package uwu.juni.wetland_whimsy.tags;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class WetlandWhimsyTags {
	public static class Blocks {
		public static final TagKey<Block> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.BLOCK, 
			WetlandWhimsy.rLoc("bald_cypress_logs")
		);
	}

	public static class Items {
		public static final TagKey<Item> CRANE_FOOD = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("crane_food")
		);

		public static final TagKey<Item> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.ITEM, 
			WetlandWhimsy.rLoc("bald_cypress_logs")
		);
	}

	public static class Structures {
		public static final TagKey<Structure> WETLAND_RUINS = TagKey.create(
			Registries.STRUCTURE,
			WetlandWhimsy.rLoc("wetland_ruins")
		);
	}
}
