package corundum.wetland_whimsy.content;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WetlandWhimsyTags {
	public static final BlockSetType BALD_CYPRESS_BLOCK_SET = new BlockSetType(WetlandWhimsy.MODID + ":bald_cypress");
	public static final WoodType BALD_CYPRESS = new WoodType(WetlandWhimsy.MODID + ":bald_cypress", BALD_CYPRESS_BLOCK_SET);
}
