package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.Set;
import java.util.function.Supplier;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyBlockLootDatagen extends BlockLootSubProvider {
	public WetlandWhimsyBlockLootDatagen() {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return WetlandWhimsyBlocks.BLOCKS.getEntries()
			.stream()
			.map(Supplier::get)
			.toList();
	}

	@Override
	public void generate() {
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

		if (Compat.ENDERGETIC)
			dropSelf(WetlandWhimsyBlocks.ENDER_BRAZIER.get().get());
		if (Compat.CNC)
			dropSelf(WetlandWhimsyBlocks.CUPRIC_BRAZIER.get().get());
		
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
			BlockLootSubProvider.createShearsOnlyDrop(WetlandWhimsyBlocks.CORDGRASS.get().asItem())
		);

		dropOther(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get(),
			WetlandWhimsyBlocks.ARIA_MUSHROOM.get()
		);
		dropShrooms(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK, 
			WetlandWhimsyBlocks.ARIA_MUSHROOM
		);
		dropShrooms(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK, 
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM
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
	}


	void dropShrooms(RegistryObject<? extends Block> block, RegistryObject<? extends Block> shroom) {
		add(
			block.get(), 
			b -> LootTable
				.lootTable()
				.withPool(
					LootPool.lootPool()
						.when(HAS_SILK_TOUCH)
						.add(LootItem.lootTableItem(block.get()))
				)
				.withPool(
					LootPool
						.lootPool()
						.when(HAS_SILK_TOUCH.invert())
						.add(
							LootItem.lootTableItem(shroom.get())
								.apply(WetlandWhimsyLoot.lootCount(-6, 2))
						)
				)
		);
	}
}
