package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;

@SuppressWarnings("null")
public class WetlandWhimsyStructureLootDatagen implements LootTableSubProvider {
	public static final ResourceLocation BASIC_LOOT = WetlandWhimsy.rLoc("basic_loot");
	public static final ResourceLocation INTERMEDIATE_LOOT = WetlandWhimsy.rLoc("intermediate_loot");
	public static final ResourceLocation EPIC_LOOT = WetlandWhimsy.rLoc("epic_loot");

	public static final ResourceLocation ANCIENT_COIN = WetlandWhimsy.rLoc("vault/ancient_coin");

	public WetlandWhimsyStructureLootDatagen() {}

	@Override
	public void generate(BiConsumer<ResourceLocation, Builder> output) {			
		output.accept(
			BASIC_LOOT,
			WetlandWhimsyLoot.createBasicLootTable()
		);
		output.accept(
			INTERMEDIATE_LOOT,
			WetlandWhimsyLoot.createBasicLootTable()
		);
		output.accept(
			EPIC_LOOT,
			WetlandWhimsyLoot.createBasicLootTable()
		);

		output.accept(
			ANCIENT_COIN,
			LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(WetlandWhimsyItems.ANCIENT_COIN.get()).apply(WetlandWhimsyLoot.lootCount(1.F, 3.F)))
			)
		);
	}
}
