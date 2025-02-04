package uwu.juni.wetland_whimsy.tags;

import uwu.juni.wetland_whimsy.WetlandWhimsy;

import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class WetlandWhimsyWoodTypes {
	public static final BlockSetType BALD_CYPRESS_BLOCK_SET = new BlockSetType(WetlandWhimsy.MODID + ":bald_cypress");
	public static final WoodType BALD_CYPRESS = new WoodType(WetlandWhimsy.MODID + ":bald_cypress", BALD_CYPRESS_BLOCK_SET);

	public static final WoodSetProperties BALD_CYPRESS_PROPERTIES = WoodSetProperties.builder(MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.COLOR_PURPLE)
		.leavesColor(MapColor.TERRACOTTA_GREEN)
		.build();

	public static void registerWoodTypes() {
		WoodType.register(BALD_CYPRESS);
	}
}
