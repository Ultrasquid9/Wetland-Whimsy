package uwu.juni.wetland_whimsy.datagen.loot;

import java.util.Set;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyBlockLoot extends BlockLootSubProvider {
	public WetlandWhimsyBlockLoot(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
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
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get());
		dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get());
		dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get());

		if (Compat.FARMERS_DELIGHT)
			dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get().get());

		dropSelf(WetlandWhimsyBlocks.LIMESTONE.get());
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_STAIRS.get());
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_SLAB.get());
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_WALL.get());
		dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get());
		dropSelf(WetlandWhimsyBlocks.LIMESTONE_PILLAR.get());

		dropSelf(WetlandWhimsyBlocks.CORDGRASS_THATCH.get());

		dropSelf(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get());
		dropSelf(WetlandWhimsyBlocks.ARIA_MUSHROOM.get());
		dropSelf(WetlandWhimsyBlocks.ARIA_SPORES.get());

		dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get());
		dropSelf(WetlandWhimsyBlocks.SOUL_BRAZIER.get());

		add(
			WetlandWhimsyBlocks.PENNYWORT.get(), 
			createPetalsDrops(WetlandWhimsyBlocks.PENNYWORT.get())
		);

		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get());
		dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get());
		dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get()
		);
		dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get()
		);


		dropOther(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(),
			WetlandWhimsyBlocks.ARIA_MUSHROOM.get()
		);
		add(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(), 
			block -> {
				return LootTable.lootTable()
					.withPool(
						LootPool.lootPool()
							.when(hasSilkTouch())
							.add(LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK))
					)
					.withPool(
						applyExplosionCondition(
							WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK,
							LootPool.lootPool()
								.when(hasSilkTouch().invert())
								.add(
									LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM)
										.apply(WetlandWhimsyLoot.lootCount(-6, 2))
								)
						)
					);
			}
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
			BlockLootSubProvider.createShearsOnlyDrop(WetlandWhimsyBlocks.CORDGRASS.asItem())
		);

		add(
			WetlandWhimsyBlocks.SUSSY_MUD.get(), 
			noDrop()
		);
		add(
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(), 
			noDrop()
		);
		add(
			WetlandWhimsyBlocks.ANCIENT_POT.get(), 
			noDrop()
		);

		add(
			WetlandWhimsyBlocks.POTTED_BALD_CYPRESS_SAPLING.get(), 
			this.createPotFlowerItemTable(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_PENNYWORT.get(), 
			this.createPotFlowerItemTable(WetlandWhimsyBlocks.PENNYWORT.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_CORDGRASS.get(), 
			this.createPotFlowerItemTable(WetlandWhimsyBlocks.CORDGRASS.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_BLOODCAP_MUSHROOM.get(), 
			this.createPotFlowerItemTable(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.asItem())
		);
		add(
			WetlandWhimsyBlocks.POTTED_ARIA_MUSHROOM.get(), 
			this.createPotFlowerItemTable(WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem())
		);
	}
}
