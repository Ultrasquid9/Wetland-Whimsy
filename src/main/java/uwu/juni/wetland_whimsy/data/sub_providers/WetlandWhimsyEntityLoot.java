package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.stream.Stream;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;

public class WetlandWhimsyEntityLoot extends EntityLootSubProvider {
	public WetlandWhimsyEntityLoot(HolderLookup.Provider lookupProvider) {
		super(FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return WetlandWhimsyEntityTypes.ENTITIES.getEntries()
			.stream()
			.map(emtity -> (EntityType<?>) emtity.value());
	}

	@Override
	public void generate() {
		this.add(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(), 
			LootTable.lootTable()
		);

		this.add(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get(), 
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 2.F))

				.add(WetlandWhimsyLoot.lootEntry(Items.FERMENTED_SPIDER_EYE, 0, 1))
				.add(WetlandWhimsyLoot.lootEntry(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, -1, 6))
			) 
		);

		this.add(
			WetlandWhimsyEntityTypes.BLEMISH.get(), 
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 1.F))

				.add(WetlandWhimsyLoot.lootEntry(WetlandWhimsyItems.BLEMISH_ROD, -3, 2))
			)
		);
	}
}
