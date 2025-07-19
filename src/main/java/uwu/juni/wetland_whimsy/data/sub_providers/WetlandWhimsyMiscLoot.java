package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;

@ParametersAreNonnullByDefault
public class WetlandWhimsyMiscLoot implements LootTableSubProvider {
	public static final ResourceLocation SWAMP_SPIDER_SHEARED = WetlandWhimsy.rLoc("entities/swamp_spider_sheared");
	public static final ResourceLocation SWAMP_SPIDER_SHEARING = WetlandWhimsy.rLoc("entities/swamp_spider_shearing");

	@Override
	public void generate(BiConsumer<ResourceLocation, Builder> output) {
		output.accept(
			SWAMP_SPIDER_SHEARED, 
			WetlandWhimsyEntityLoot.SWAMP_SPIDER_TABLE
		);

		output.accept(
			SWAMP_SPIDER_SHEARING, 
			table(item(
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get(), 
				1., 
				3.
			))
		);
	}

	static LootTable.Builder table(LootItem.Builder<?> item) {
		return LootTable.lootTable().withPool(
			LootPool.lootPool().add(item)
		);
	}

	static LootItem.Builder<?> item(ItemLike item, double min, double max) {
		return LootItem
			.lootTableItem(item)
			.apply(WetlandWhimsyLoot.lootCount((float)min, (float)max));
	}
}
