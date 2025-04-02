package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.function.BiConsumer;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;

@ParametersAreNonnullByDefault
public class WetlandWhimsyMiscLoot implements LootTableSubProvider {

	public static final ResourceKey<LootTable> BASIC_LOOT = createKey("basic_loot");
	public static final ResourceKey<LootTable> INTERMEDIATE_LOOT = createKey("intermediate_loot");
	public static final ResourceKey<LootTable> EPIC_LOOT = createKey("epic_loot");

	public static final ResourceKey<LootTable> ANCIENT_COIN = createKey("vault/ancient_coin");

	public static final ResourceKey<LootTable> BOILING_INCENSE = createKey("incense/boiling");
	public static final ResourceKey<LootTable> BRINE_INCENSE = createKey("incense/brine");
	public static final ResourceKey<LootTable> ROT_INCENSE = createKey("incense/rot");
	public static final ResourceKey<LootTable> WEBBED_INCENSE = createKey("incense/webbed");

	public static final ResourceKey<LootTable> SWAMP_SPIDER_SHEAR = createKey("entities/swamp_spider_shear");

	private static ResourceKey<LootTable> createKey(String name) {
		return ResourceKey.create(
			Registries.LOOT_TABLE, 
			WetlandWhimsy.rLoc(name)
		);
	}

	public WetlandWhimsyMiscLoot(HolderLookup.Provider lookupProvider) {}

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
			BOILING_INCENSE,
			WetlandWhimsyLoot.createBoilingLootTable()
		);
		output.accept(
			BRINE_INCENSE,
			WetlandWhimsyLoot.createBrineLootTable()
		);
		output.accept(
			ROT_INCENSE,
			WetlandWhimsyLoot.createRotLootTable()
		);
		output.accept(
			WEBBED_INCENSE,
			WetlandWhimsyLoot.createWebbedLootTable()
		);

		output.accept(
			ANCIENT_COIN, 
			table(item(
				WetlandWhimsyItems.ANCIENT_COIN,
				1.F, 
				3.F
			))
		);

		output.accept(
			SWAMP_SPIDER_SHEAR, 
			table(item(
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, 
				2., 
				4.
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
