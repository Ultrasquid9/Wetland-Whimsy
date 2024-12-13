package corundum.wetland_whimsy.tags;

import corundum.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WetlandWhimsyWoodTypes {
	public static final BlockSetType BALD_CYPRESS_BLOCK_SET = new BlockSetType(WetlandWhimsy.MODID + ":bald_cypress");
	public static final WoodType BALD_CYPRESS = new WoodType(WetlandWhimsy.MODID + ":bald_cypress", BALD_CYPRESS_BLOCK_SET);

	public static void registerWoodTypes() {
		WoodType.register(BALD_CYPRESS);
	}
}
