package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.stream.Stream;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import uwu.juni.wetland_whimsy.content.predicates.ShearedPredicate;

public class WetlandWhimsyEntityLoot extends EntityLootSubProvider {
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
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(), 
			LootTable.lootTable()
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
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get(), 
			LootTable.lootTable()
				.withPool(
					LootPool.lootPool()
						.setRolls(UniformGenerator.between(1.F, 2.F))

						.add(WetlandWhimsyLoot.lootEntry(Items.FERMENTED_SPIDER_EYE, 0, 1))
						.add(WetlandWhimsyLoot.lootEntry(Items.STRING, 0, 2))
				) 
				.withPool(
					LootPool.lootPool()
						.when(
							LootItemEntityPropertyCondition.hasProperties(
								LootContext.EntityTarget.THIS,
								EntityPredicate.Builder.entity().subPredicate(new ShearedPredicate(false))
							)
						)
						.add(WetlandWhimsyLoot.lootEntry(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, -1, 3))
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
