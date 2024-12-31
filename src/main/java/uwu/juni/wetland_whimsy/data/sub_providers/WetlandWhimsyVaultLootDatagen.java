package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

@SuppressWarnings("null")
public class WetlandWhimsyVaultLootDatagen implements LootTableSubProvider {

	public static final ResourceKey<LootTable> BASIC_LOOT = createKey("basic_loot");
	public static final ResourceKey<LootTable> INTERMEDIATE_LOOT = createKey("intermediate_loot");
	public static final ResourceKey<LootTable> EPIC_LOOT = createKey("epic_loot");

	public static final ResourceKey<LootTable> NORMAL_VAULT_LOOT = createKey("vault/normal_loot");
	public static final ResourceKey<LootTable> OMINOUS_VAULT_LOOT = createKey("vault/ominous_loot");

	private static ResourceKey<LootTable> createKey(String name) {
		return ResourceKey.create(
			Registries.LOOT_TABLE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}


	@SuppressWarnings("unused") // Why did they want me to store this thing 
	private HolderLookup.Provider thing;

	public WetlandWhimsyVaultLootDatagen(HolderLookup.Provider lookupProvider) {
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
			NORMAL_VAULT_LOOT,
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(NestedLootTable.lootTableReference(BASIC_LOOT).setWeight(8))
						.add(NestedLootTable.lootTableReference(INTERMEDIATE_LOOT).setWeight(2))
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(UniformGenerator.between(1.0F, 3.0F))
						.add(NestedLootTable.lootTableReference(BASIC_LOOT))
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.when(LootItemRandomChanceCondition.randomChance(0.25F))
						.add(NestedLootTable.lootTableReference(INTERMEDIATE_LOOT))
				)
		);
		output.accept(
			OMINOUS_VAULT_LOOT,
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(NestedLootTable.lootTableReference(INTERMEDIATE_LOOT).setWeight(8))
						.add(NestedLootTable.lootTableReference(EPIC_LOOT).setWeight(2))
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(UniformGenerator.between(1.0F, 3.0F))
						.add(NestedLootTable.lootTableReference(INTERMEDIATE_LOOT))
				)
				.withPool(
					LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.when(LootItemRandomChanceCondition.randomChance(0.25F))
						.add(NestedLootTable.lootTableReference(EPIC_LOOT))
				)
		);
	}
}
