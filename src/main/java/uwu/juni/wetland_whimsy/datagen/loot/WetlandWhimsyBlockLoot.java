package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.Set;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.registries.DeferredBlock;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyBlockLoot extends BlockLootSubProvider {
	public WetlandWhimsyBlockLoot(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return WetlandWhimsyBlocks.BLOCKS.getEntries()
			.stream()
			.map(block -> (Block) block.value())
			.toList();
	}

	@Override
	protected void generate() {
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_LOG);
		dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD);
		dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE);

		if (Compat.FARMERS_DELIGHT)
			dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get());

		dropSelf(WetlandWhimsyBlocks.LIMESTONE);
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICKS);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_STAIRS);
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_SLAB);
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_WALL);
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL);
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_PILLAR);

		dropSelf(WetlandWhimsyBlocks.CORDGRASS_THATCH);

		dropSelf(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM);
		dropSelf(WetlandWhimsyBlocks.ARIA_MUSHROOM);
		dropSelf(WetlandWhimsyBlocks.ARIA_SPORES);

		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRAZIER);
		dropSelf(WetlandWhimsyBlocks.SOUL_BRAZIER);

		add(
			WetlandWhimsyBlocks.PENNYWORT.get(), 
			createPetalsDrops(WetlandWhimsyBlocks.PENNYWORT.get())
		);

		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN);
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN);
		dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get()
		);
		dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get()
		);

		add(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(), 
			b -> LootTable
				.lootTable()
				.withPool(
					LootPool.lootPool()
						.when(hasSilkTouch())
						.add(LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK))
				)
				.withPool(
					LootPool
						.lootPool()
						.when(hasSilkTouch().invert())
						.add(
							LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM)
								.apply(WetlandWhimsyLoot.lootCount(-6, 2))
						)
				)
		);

		add(
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(), 
			createDoorTable(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get())
		);

		add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			createLeavesDrops(
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get(),
				NORMAL_LEAVES_SAPLING_CHANCES
			)
		);
		add(
			WetlandWhimsyBlocks.CORDGRASS.get(), 
			createCordgrassDrops(WetlandWhimsyBlocks.CORDGRASS)
		);

		add(
			WetlandWhimsyBlocks.POTTED_BALD_CYPRESS_SAPLING.get(), 
			createPotFlowerItemTable(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_PENNYWORT.get(), 
			createPotFlowerItemTable(WetlandWhimsyBlocks.PENNYWORT.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_CORDGRASS.get(), 
			createPotFlowerItemTable(WetlandWhimsyBlocks.CORDGRASS.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_BLOODCAP_MUSHROOM.get(), 
			createPotFlowerItemTable(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_ARIA_MUSHROOM.get(), 
			createPotFlowerItemTable(WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem())
		);

		noDrop(WetlandWhimsyBlocks.SUSSY_MUD);
		noDrop(WetlandWhimsyBlocks.ANCIENT_BRAZIER);
		noDrop(WetlandWhimsyBlocks.ANCIENT_POT);
	}

	LootTable.Builder createCordgrassDrops(DeferredBlock<?> block) {
		var lookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
		var fortune = lookup.getOrThrow(Enchantments.FORTUNE);

		return createShearsDispatchTable(
			block.get(),
			applyExplosionDecay(
				block,
				LootItem
					.lootTableItem(WetlandWhimsyItems.CARROT_SEEDS)
					.when(LootItemRandomChanceCondition.randomChance(0.125F))
					.apply(ApplyBonusCount.addUniformBonusCount(fortune, 2))
			)
		);
	}

	void dropSelf(DeferredBlock<?> b) { dropSelf(b.get()); }
	void noDrop(DeferredBlock<?> b) { add(b.get(), noDrop()); }
}
