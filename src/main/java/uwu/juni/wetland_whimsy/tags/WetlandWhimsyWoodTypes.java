package uwu.juni.wetland_whimsy.tags;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WetlandWhimsyWoodTypes {
	static final String NAME = WetlandWhimsy.MODID + ":bald_cypress";

	public static final BlockSetType BALD_CYPRESS_BLOCK_SET = new BlockSetType(NAME);
	public static final WoodType BALD_CYPRESS = new WoodType(NAME, BALD_CYPRESS_BLOCK_SET);

	public static void registerWoodTypes() {
		WoodType.register(BALD_CYPRESS);
	}
}
