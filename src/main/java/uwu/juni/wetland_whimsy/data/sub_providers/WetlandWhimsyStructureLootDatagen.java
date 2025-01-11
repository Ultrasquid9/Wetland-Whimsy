package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

@SuppressWarnings("null")
public class WetlandWhimsyStructureLootDatagen implements LootTableSubProvider {

	public static final ResourceKey<LootTable> BASIC_LOOT = createKey("basic_loot");
	public static final ResourceKey<LootTable> INTERMEDIATE_LOOT = createKey("intermediate_loot");
	public static final ResourceKey<LootTable> EPIC_LOOT = createKey("epic_loot");

	public static final ResourceKey<LootTable> ANCIENT_COIN = createKey("vault/ancient_coin");

	private static ResourceKey<LootTable> createKey(String name) {
		return ResourceKey.create(
			Registries.LOOT_TABLE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}


	@SuppressWarnings("unused") // Why did they want me to store this thing 
	private HolderLookup.Provider thing;

	public WetlandWhimsyStructureLootDatagen(HolderLookup.Provider lookupProvider) {
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
				.setRolls(UniformGenerator.between(1.F, 3.F))
				.add(LootItem.lootTableItem(WetlandWhimsyItems.ANCIENT_COIN.get()))
			)
		);
	}
}
