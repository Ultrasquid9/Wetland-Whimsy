package uwu.juni.wetland_whimsy.misc;

import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@Config(name = WetlandWhimsy.MODID)
public class WetlandWhimsyConfig implements ConfigData {

	public int ancientPotMaxParticleCount = 24;

	public int ancientPotMaxDropCount = 10;

	public List<ResourceLocation> ancientPotItems = List.of(
		new ResourceLocation("minecraft", "diamond"),
		new ResourceLocation("minecraft", "emerald"),
		new ResourceLocation("minecraft", "lapis_lazuli"),
		new ResourceLocation("minecraft", "amethyst_shard"),
		new ResourceLocation("minecraft", "gold_ingot"),
		new ResourceLocation("minecraft", "iron_ingot"),
		new ResourceLocation("minecraft", "raw_gold"),
		new ResourceLocation("minecraft", "raw_iron"),

		new ResourceLocation(WetlandWhimsy.MODID, "disc"),
		new ResourceLocation(WetlandWhimsy.MODID, "dots_armor_trim_smithing_template"),

		new ResourceLocation("minecraft", "diamond_helmet"),
		new ResourceLocation("minecraft", "diamond_chestplate"),
		new ResourceLocation("minecraft", "diamond_leggings"),
		new ResourceLocation("minecraft", "diamond_boots"),
		new ResourceLocation("minecraft", "diamond_pickaxe"),
		new ResourceLocation("minecraft", "diamond_axe"),
		new ResourceLocation("minecraft", "diamond_sword"),
		new ResourceLocation("minecraft", "diamond_shovel"),
		new ResourceLocation("minecraft", "diamond_hoe"),
		new ResourceLocation("minecraft", "iron_helmet"),
		new ResourceLocation("minecraft", "iron_chestplate"),
		new ResourceLocation("minecraft", "iron_leggings"),
		new ResourceLocation("minecraft", "iron_boots"),
		new ResourceLocation("minecraft", "iron_pickaxe"),
		new ResourceLocation("minecraft", "iron_axe"),
		new ResourceLocation("minecraft", "iron_sword"),
		new ResourceLocation("minecraft", "iron_shovel"),
		new ResourceLocation("minecraft", "iron_hoe"),
		new ResourceLocation("minecraft", "gold_helmet"),
		new ResourceLocation("minecraft", "gold_chestplate"),
		new ResourceLocation("minecraft", "gold_leggings"),
		new ResourceLocation("minecraft", "gold_boots"),
		new ResourceLocation("minecraft", "gold_pickaxe"),
		new ResourceLocation("minecraft", "gold_axe"),
		new ResourceLocation("minecraft", "gold_sword"),
		new ResourceLocation("minecraft", "gold_shovel"),
		new ResourceLocation("minecraft", "gold_hoe"),

		new ResourceLocation(WetlandWhimsy.MODID, "bald_cypress_log"),
		new ResourceLocation(WetlandWhimsy.MODID, "bald_cypress_sapling"),

		new ResourceLocation("minecraft", "golden_carrot"),
		new ResourceLocation("minecraft", "bread"),
		new ResourceLocation("minecraft", "wheat"),
		new ResourceLocation("minecraft", "beetroot"),
		new ResourceLocation("minecraft", "wheat_seeds"),
		new ResourceLocation("minecraft", "beetroot_seeds"),
		new ResourceLocation("minecraft", "blue_orchid"),
		new ResourceLocation("minecraft", "red_mushroom"),
		new ResourceLocation("minecraft", "brown_mushroom"),
		new ResourceLocation("minecraft", "slimeball"),
		new ResourceLocation("minecraft", "mud"),
		new ResourceLocation(WetlandWhimsy.MODID, "pennywort"),
		new ResourceLocation(WetlandWhimsy.MODID, "cordgrass")
	);

	public List<ResourceLocation> ancientBrazierEntities = List.of(
		new ResourceLocation("minecraft", "zombie"),
		new ResourceLocation("minecraft", "skeleton"),
		new ResourceLocation("minecraft", "spider"),
		new ResourceLocation("minecraft", "cave_spider"),
		new ResourceLocation("minecraft", "drowned"),
		new ResourceLocation("minecraft", "witch")
	);
}
