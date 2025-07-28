package uwu.juni.wetland_whimsy.misc;

import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@Config(name = WetlandWhimsy.MODID)
public class WetlandWhimsyConfig implements ConfigData {

	public int ancientPotMaxParticleCount = 24;

	public int ancientPotMaxDropCount = 10;

	public List<String> ancientBrazierEntities = List.of(
		"wetland_whimsy:swamp_spider",	
		"minecraft:zombie",
		"minecraft:skeleton",
		"minecraft:spider",
		"minecraft:cave_spider",
		"minecraft:drowned",
		"minecraft:witch"
	);

	public boolean disableVanillaSwampHuts = true;
	public boolean upgradeDisks = true;
}
