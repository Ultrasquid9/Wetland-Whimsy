package corundum.wetland_whimsy.tags;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WetlandWhimsyTags {
	public static class Blocks {
		public static final TagKey<Block> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.BLOCK, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "bald_cypress_logs")
		);
	}
}
