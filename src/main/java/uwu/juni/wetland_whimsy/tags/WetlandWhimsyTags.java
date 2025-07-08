package uwu.juni.wetland_whimsy.tags;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class WetlandWhimsyTags {
	public static class Blocks {
		public static final TagKey<Block> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.BLOCK, 
			new ResourceLocation(WetlandWhimsy.MODID, "bald_cypress_logs")
		);
	}

	public static class Items {
		public static final TagKey<Item> BALD_CYPRESS_LOGS =TagKey.create(
			Registries.ITEM, 
			new ResourceLocation(WetlandWhimsy.MODID, "bald_cypress_logs")
		);
	}
}
