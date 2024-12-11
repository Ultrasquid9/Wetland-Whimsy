package corundum.wetland_whimsy.content;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WetlandWhimsyTags {
	public static final BlockSetType BALD_CYPRESS_BLOCK_SET = new BlockSetType(WetlandWhimsy.MODID + ":bald_cypress");
	public static final WoodType BALD_CYPRESS = new WoodType(WetlandWhimsy.MODID + ":bald_cypress", BALD_CYPRESS_BLOCK_SET);

	public static class Blocks {
		public static final TagKey<Block> BALD_CYPRESS_LOGS = TagKey.create(
			Registries.BLOCK, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "bald_cypress_logs")
		);
	}
}
