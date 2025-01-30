package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;

@SuppressWarnings("null")
public class WetlandWhimsyMiscLoot implements LootTableSubProvider {

	public static final ResourceKey<LootTable> BASIC_LOOT = createKey("basic_loot");
	public static final ResourceKey<LootTable> INTERMEDIATE_LOOT = createKey("intermediate_loot");
	public static final ResourceKey<LootTable> EPIC_LOOT = createKey("epic_loot");

	public static final ResourceKey<LootTable> ANCIENT_COIN = createKey("vault/ancient_coin");

	public static final ResourceKey<LootTable> SWAMP_SPIDER_SHEAR = createKey("entities/swamp_spider_shear");

	private static ResourceKey<LootTable> createKey(String name) {
		return ResourceKey.create(
			Registries.LOOT_TABLE, 
			WetlandWhimsy.rLoc(name)
		);
	}


	@SuppressWarnings("unused") // Why did they want me to store this thing 
	private HolderLookup.Provider thing;

	public WetlandWhimsyMiscLoot(HolderLookup.Provider lookupProvider) {
		thing = lookupProvider;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {			
		output.accept(
			BASIC_LOOT,
			WetlandWhimsyLoot.createBasicLootTable()
		);
		output.accept(
			INTERMEDIATE_LOOT,
			WetlandWhimsyLoot.createIntermediateLootTable()
		);
		output.accept(
			EPIC_LOOT,
			WetlandWhimsyLoot.createEpicLootTable()
		);

		output.accept(
			ANCIENT_COIN, 
			LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(WetlandWhimsyItems.ANCIENT_COIN).apply(WetlandWhimsyLoot.lootCount(1.F, 3.F)))
			)
		);

		output.accept(
			SWAMP_SPIDER_SHEAR, 
			LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM).apply(WetlandWhimsyLoot.lootCount(2.F, 4.F)))
			)
		);
	}
}
