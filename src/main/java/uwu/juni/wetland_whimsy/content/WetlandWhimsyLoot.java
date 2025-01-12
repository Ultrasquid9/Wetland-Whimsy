package uwu.juni.wetland_whimsy.content;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class WetlandWhimsyLoot {
	public static LootTable.Builder createBasicLootTable() {
		return LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 1.F))

				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.PENNYWORT.get()).apply(lootCount(4.F, 8.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.CORDGRASS.get()).apply(lootCount(4.F, 8.F)))
				.add(LootItem.lootTableItem(Blocks.BLUE_ORCHID.asItem()).apply(lootCount(4.F, 8.F)))

				.add(LootItem.lootTableItem(Items.WHEAT.asItem()).apply(lootCount(8.F, 16.F)))
				.add(LootItem.lootTableItem(Items.WHEAT_SEEDS.asItem()).apply(lootCount(8.F, 16.F)))
				.add(LootItem.lootTableItem(Items.BEETROOT.asItem()).apply(lootCount(8.F, 16.F)))
				.add(LootItem.lootTableItem(Items.BEETROOT_SEEDS.asItem()).apply(lootCount(8.F, 16.F)))
				.add(LootItem.lootTableItem(Items.STICK.asItem()).apply(lootCount(8.F, 16.F)))

				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get().asItem()).apply(lootCount(2.F, 6.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get().asItem()).apply(lootCount(2.F, 6.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get().asItem()).apply(lootCount(2.F, 6.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get().asItem()).apply(lootCount(2.F, 6.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get().asItem()).apply(lootCount(2.F, 6.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get().asItem()).apply(lootCount(2.F, 6.F)))

				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_PILLAR.get().asItem()).apply(lootCount(1.F, 10.F)))

				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_STAIRS.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_SLAB.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get().asItem()).apply(lootCount(1.F, 10.F)))
				.add(LootItem.lootTableItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get().asItem()).apply(lootCount(1.F, 10.F)))

				.add(LootItem.lootTableItem(Items.RAW_GOLD.asItem()).apply(lootCount(1.F, 8.F)))
				.add(LootItem.lootTableItem(Items.RAW_IRON.asItem()).apply(lootCount(1.F, 8.F)))
				.add(LootItem.lootTableItem(Items.RAW_COPPER.asItem()).apply(lootCount(1.F, 8.F)))
				.add(LootItem.lootTableItem(Items.GOLD_INGOT.asItem()).apply(lootCount(1.F, 6.F)))
				.add(LootItem.lootTableItem(Items.IRON_INGOT.asItem()).apply(lootCount(1.F, 6.F)))
				.add(LootItem.lootTableItem(Items.COPPER_INGOT.asItem()).apply(lootCount(1.F, 6.F)))
				.add(LootItem.lootTableItem(Items.GOLD_NUGGET.asItem()).apply(lootCount(3.F, 8.F)))
				.add(LootItem.lootTableItem(Items.IRON_NUGGET.asItem()).apply(lootCount(3.F, 8.F)))

				.add(LootItem.lootTableItem(Items.SLIME_BALL.asItem()).apply(lootCount(2.F, 10.F)))
				.add(LootItem.lootTableItem(Blocks.RED_MUSHROOM.asItem()).apply(lootCount(2.F, 10.F)))
				.add(LootItem.lootTableItem(Blocks.BROWN_MUSHROOM.asItem()).apply(lootCount(2.F, 10.F)))
				.add(LootItem.lootTableItem(Blocks.MUD.asItem()).apply(lootCount(2.F, 10.F)))

				.add(LootItem.lootTableItem(Items.DIAMOND.asItem()).apply(lootCount(1.F, 2.F)))
				.add(LootItem.lootTableItem(Items.EMERALD.asItem()).apply(lootCount(1.F, 2.F)))
				.add(LootItem.lootTableItem(Items.LAPIS_LAZULI.asItem()).apply(lootCount(1.F, 2.F)))
				.add(LootItem.lootTableItem(Items.AMETHYST_SHARD.asItem()).apply(lootCount(1.F, 2.F)))

				.add(LootItem.lootTableItem(Items.MUSHROOM_STEW.asItem()))
				.add(LootItem.lootTableItem(WetlandWhimsyItems.PENNYWORT_SALAD.get()))
				.add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW.asItem()).apply(stew()))
				.add(LootItem.lootTableItem(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
				.add(LootItem.lootTableItem(WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC.get().asItem()))
			);
	}

	@SuppressWarnings("rawtypes")
	public static LootItemConditionalFunction.Builder lootCount(float a, float b) {
		return SetItemCountFunction.setCount(UniformGenerator.between(a, b));
	}

	@SuppressWarnings("rawtypes")
	private static LootItemConditionalFunction.Builder stew() {
		return SetStewEffectFunction.stewEffect()
			.withEffect(MobEffects.POISON, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.SATURATION, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.HUNGER, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.LUCK, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.UNLUCK, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.BLINDNESS, UniformGenerator.between(3, 7))
			.withEffect(MobEffects.DARKNESS, UniformGenerator.between(3, 7));
	}
}
