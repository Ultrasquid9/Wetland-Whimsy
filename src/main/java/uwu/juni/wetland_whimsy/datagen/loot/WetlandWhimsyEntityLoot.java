package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.stream.Stream;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;

public class WetlandWhimsyEntityLoot extends EntityLootSubProvider {
	public static final LootTable.Builder SWAMP_SPIDER_TABLE = LootTable
		.lootTable()
		.withPool(
			LootPool
				.lootPool()
				.setRolls(UniformGenerator.between(1.F, 2.F))
				.add(WetlandWhimsyMiscLoot.item(Items.FERMENTED_SPIDER_EYE, 0, 1))
				.add(WetlandWhimsyMiscLoot.item(Items.STRING, 1, 3))
		);

	public WetlandWhimsyEntityLoot(HolderLookup.Provider lookupProvider) {
		super(FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return WetlandWhimsyEntityTypes.ENTITIES.getEntries()
			.stream()
			.map(emtity -> emtity.value());
	}

	@Override
	public void generate() {
		add(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get(), 
			SWAMP_SPIDER_TABLE.withPool(
				LootPool
					.lootPool()
					.add(WetlandWhimsyMiscLoot.item(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, -1, 3))
			)
		);

		add(
			WetlandWhimsyEntityTypes.CRANE.get(), 
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(UniformGenerator.between(0, 2))
						.add(WetlandWhimsyLoot.lootEntry(Items.FEATHER))
				)
		);

		add(
			WetlandWhimsyEntityTypes.BLEMISH.get(), 
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(UniformGenerator.between(1.F, 1.F))

						.when(LootItemKilledByPlayerCondition.killedByPlayer())

						.add(WetlandWhimsyLoot.lootEntry(WetlandWhimsyItems.BLEMISH_ROD, -3, 2))
				)
		);
	}
}
