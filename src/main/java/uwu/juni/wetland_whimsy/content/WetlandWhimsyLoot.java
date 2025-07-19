package uwu.juni.wetland_whimsy.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WetlandWhimsyLoot {
	public static LootTable.Builder createBasicLootTable() {
		return LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 1.F))

				.add(lootEntry(WetlandWhimsyBlocks.PENNYWORT, 4.F, 8.F))
				.add(lootEntry(WetlandWhimsyBlocks.CORDGRASS, 4.F, 8.F))
				.add(lootEntry(Blocks.BLUE_ORCHID, 4.F, 8.F))

				.add(lootEntry(Items.WHEAT, 8.F, 16.F))
				.add(lootEntry(Items.WHEAT_SEEDS, 8.F, 16.F))
				.add(lootEntry(Items.BEETROOT, 8.F, 16.F))
				.add(lootEntry(Items.BEETROOT_SEEDS, 8.F, 16.F))
				.add(lootEntry(Items.STICK, 8.F, 16.F))
				.add(lootEntry(Items.TORCH, 1.F, 5.F))

				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_LOG, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING, 2.F, 6.F))

				.add(lootEntry(WetlandWhimsyBlocks.LIMESTONE, 1.F, 10.F))
				.add(lootEntry(WetlandWhimsyBlocks.LIMESTONE_BRICKS, 1.F, 10.F))
				.add(lootEntry(WetlandWhimsyBlocks.POLISHED_LIMESTONE, 1.F, 10.F))
				.add(lootEntry(WetlandWhimsyBlocks.LIMESTONE_PILLAR, 1.F, 10.F))

				.add(lootEntry(WetlandWhimsyBlocks.LIMESTONE_STAIRS, 1.F, 10.F))
				.add(lootEntry(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS, 1.F, 10.F))
				.add(lootEntry(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS, 1.F, 10.F))

				.add(lootEntry(WetlandWhimsyItems.GROWTH_POTTERY_SHERD))

				.add(lootEntry(Items.RAW_GOLD, 1.F, 6.F))
				.add(lootEntry(Items.RAW_IRON, 1.F, 6.F))
				.add(lootEntry(Items.RAW_COPPER, 1.F, 6.F))
				.add(lootEntry(Items.GOLD_INGOT, 1.F, 3.F))
				.add(lootEntry(Items.IRON_INGOT, 1.F, 3.F))
				.add(lootEntry(Items.COPPER_INGOT, 1.F, 3.F))
				.add(lootEntry(Items.GOLD_NUGGET, 3.F, 8.F))
				.add(lootEntry(Items.IRON_NUGGET, 3.F, 8.F))

				.add(lootEntry(Items.SLIME_BALL, 2.F, 6.F))
				.add(lootEntry(Blocks.RED_MUSHROOM, 2.F, 6.F))
				.add(lootEntry(Blocks.BROWN_MUSHROOM, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, 2.F, 6.F))
				.add(lootEntry(WetlandWhimsyBlocks.ARIA_MUSHROOM, 2.F, 6.F))
				.add(lootEntry(Blocks.MUD, 2.F, 10.F))

				.add(lootEntry(Items.EMERALD, 1.F, 2.F))
				.add(lootEntry(Items.LAPIS_LAZULI, 1.F, 2.F))
				.add(lootEntry(Items.AMETHYST_SHARD, 1.F, 2.F))

				.add(lootEntry(Items.BOWL))
				.add(lootEntry(Items.MUSHROOM_STEW))
				.add(lootEntry(WetlandWhimsyItems.PENNYWORT_SALAD))
				.add(lootEntry(Items.SUSPICIOUS_STEW).apply(stew()))

				.add(lootEntry(Items.ARROW, 1.F, 4.F))
				.add(lootEntry(Items.TIPPED_ARROW, 1.F, 3.F)).apply(potion("poison"))
			);
	}

	public static LootTable.Builder createIntermediateLootTable() {
		return LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 1.F))

				.add(lootEntry(Items.BREAD, 8.F, 16.F))

				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_LOG, 4.F, 12.F))
				.add(lootEntry(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, 4.F, 12.F))
				.add(lootEntry(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, 4.F, 12.F))
				.add(lootEntry(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, 4.F, 12.F))

				.add(lootEntry(WetlandWhimsyItems.GROWTH_POTTERY_SHERD))
				.add(lootEntry(WetlandWhimsyItems.SEALED_POTTERY_SHERD))
				.add(lootEntry(Items.SKULL_POTTERY_SHERD))
				.add(lootEntry(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE))

				.add(lootEntry(Items.RAW_GOLD, 3.F, 10.F))
				.add(lootEntry(Items.RAW_IRON, 3.F, 10.F))
				.add(lootEntry(Items.RAW_COPPER, 3.F, 10.F))
				.add(lootEntry(Items.GOLD_INGOT, 3.F, 8.F))
				.add(lootEntry(Items.IRON_INGOT, 3.F, 8.F))
				.add(lootEntry(Items.COPPER_INGOT, 3.F, 8.F))
				.add(lootEntry(Items.GOLD_NUGGET, 10.F, 18.F))
				.add(lootEntry(Items.IRON_NUGGET, 10.F, 18.F))

				.add(lootEntry(Items.SLIME_BALL, 8.F, 16.F))
				.add(lootEntry(Blocks.MUD, 2.F, 10.F))

				.add(lootEntry(Items.DIAMOND, 1.F, 3.F))
				.add(lootEntry(Items.EMERALD, 1.F, 3.F))
				.add(lootEntry(Items.LAPIS_LAZULI, 1.F, 3.F))
				.add(lootEntry(Items.AMETHYST_SHARD, 1.F, 3.F))
				.add(lootEntry(Items.QUARTZ, 1.F, 3.F))

				.add(lootEntry(Items.MUSHROOM_STEW))
				.add(lootEntry(Items.BEETROOT_SOUP))
				.add(lootEntry(Items.SUSPICIOUS_STEW).apply(stew()))
				.add(lootEntry(WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC)) 

				.add(lootEntry(Items.BOW))
				.add(lootEntry(Items.ARROW, 1.F, 10.F))
				.add(lootEntry(Items.TIPPED_ARROW, 1.F, 6.F)).apply(potion("poison"))
			);
	}

	public static LootTable.Builder createEpicLootTable() {
		return LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(UniformGenerator.between(1.F, 1.F))

				.add(lootEntry(Items.GOLDEN_CARROT, 6, 14))

				.add(lootEntry(WetlandWhimsyItems.GROWTH_POTTERY_SHERD))
				.add(lootEntry(WetlandWhimsyItems.SEALED_POTTERY_SHERD))
				.add(lootEntry(Items.SKULL_POTTERY_SHERD))
				.add(lootEntry(Items.BREWER_POTTERY_SHERD))
				.add(lootEntry(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE))

				.add(lootEntry(Items.GOLD_INGOT, 7, 14))
				.add(lootEntry(Items.IRON_INGOT, 7, 14))
				.add(lootEntry(Items.COPPER_INGOT, 7, 14))

				.add(lootEntry(Items.DIAMOND, 3, 6))
				.add(lootEntry(Items.EMERALD, 7, 12))

				.add(lootEntry(Items.SUSPICIOUS_STEW).apply(stew()))
				.add(lootEntry(Items.MUSHROOM_STEW))
				.add(lootEntry(WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC)) 
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

	private static LootItemConditionalFunction.Builder<?> potion(String effect) {
		var potion = ForgeRegistries.POTIONS.getValue(ResourceLocation.withDefaultNamespace(effect));

		return SetPotionFunction.setPotion(potion);
	}

	public static LootPoolSingletonContainer.Builder<?> lootEntry(ItemLike item) {
		return LootItem.lootTableItem(item);
	}

	public static LootPoolSingletonContainer.Builder<?> lootEntry(RegistryObject<? extends ItemLike> item) {
		return LootItem.lootTableItem(item.get());
	}

	public static LootPoolSingletonContainer.Builder<?> lootEntry(ItemLike item, double min, double max) {
		return lootEntry(item).apply(lootCount((float)min, (float)max));
	}

	public static LootPoolSingletonContainer.Builder<?> lootEntry(RegistryObject<? extends ItemLike> item, double min, double max) {
		return lootEntry(item).apply(lootCount((float)min, (float)max));
	}
}
