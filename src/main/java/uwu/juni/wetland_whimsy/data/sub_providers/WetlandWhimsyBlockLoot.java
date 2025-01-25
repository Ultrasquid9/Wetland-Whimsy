package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.Set;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import uwu.juni.wetland_whimsy.misc.Compat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

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
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get());
		this.dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get());
		this.dropSelf(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get());

		if (Compat.FARMERS_DELIGHT)
			this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get().get());

		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE.get());
		this.dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_STAIRS.get());
		this.dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_SLAB.get());
		this.dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_WALL.get());
		this.dropSelf(WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get());
		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_PILLAR.get());

		this.dropSelf(WetlandWhimsyBlocks.CORDGRASS_THATCH.get());

		this.dropSelf(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get());
		this.dropSelf(WetlandWhimsyBlocks.ARIA_MUSHROOM.get());
		this.dropSelf(WetlandWhimsyBlocks.ARIA_SPORES.get());

		this.dropSelf(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get());
		this.dropSelf(WetlandWhimsyBlocks.SOUL_BRAZIER.get());

		this.add(
			WetlandWhimsyBlocks.PENNYWORT.get(), 
			this.createPetalsDrops(WetlandWhimsyBlocks.PENNYWORT.get())
		);

		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get());
		this.dropSelf(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get());
		this.dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get()
		);
		this.dropOther(
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get()
		);


		this.dropOther(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(),
			WetlandWhimsyBlocks.ARIA_MUSHROOM.get()
		);
		this.add(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(), 
			(block) -> {
				return LootTable.lootTable()
					.withPool(
						LootPool.lootPool()
							.when(this.hasSilkTouch())
							.add(LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK))
					)
					.withPool(
						applyExplosionCondition(
							WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK,
							LootPool.lootPool()
								.when(this.hasSilkTouch().invert())
								.add(
									LootItem.lootTableItem(WetlandWhimsyBlocks.ARIA_MUSHROOM)
										.apply(WetlandWhimsyLoot.lootCount(-6, 2))
								)
						)
					);
			}
		);

		this.add(
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(), 
			createDoorTable(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get())
		);

		this.add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
			this.createLeavesDrops(
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get(),
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get(),
				NORMAL_LEAVES_SAPLING_CHANCES
			)
		);
		this.add(
			WetlandWhimsyBlocks.CORDGRASS.get(), 
			BlockLootSubProvider.createShearsOnlyDrop(WetlandWhimsyBlocks.CORDGRASS.asItem())
		);

		this.add(
			WetlandWhimsyBlocks.SUSSY_MUD.get(), 
			noDrop()
		);
		this.add(
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(), 
			noDrop()
		);
		this.add(
			WetlandWhimsyBlocks.ANCIENT_POT.get(), 
			noDrop()
		);
	}
}
