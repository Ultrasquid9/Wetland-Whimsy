package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class AncientPotBlockEntity extends BlockEntity {
	private int lootQuality;
	private ThreadLocalRandom random;

	public AncientPotBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_POT.get(), pos, state);
		random = ThreadLocalRandom.current();
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("loot_quality", lootQuality);
	}

	@Override
	public void load(@Nonnull CompoundTag tag) {
		super.load(tag);
		this.lootQuality = tag.getInt("loot_quality");
	}

	public void increaseLootQuality() { lootQuality++; }
	public int lootQuality() { return lootQuality; }

	/// Gambling
	@SuppressWarnings("deprecation")
	public void dropLoot(Level level, BlockPos pos) {
		lootQuality += 2; // Ensuring that it is not zero

		var chance2 = WetlandWhimsy.config.ancientPotItems.size() / (int)(lootQuality * 2.5);
		if (chance2 <= 0) 
			chance2 = 1;

		var i = 0;
		var j = 0;
		for (var item : WetlandWhimsy.config.ancientPotItems) {
			var thign = lootQuality == 2 ? lootQuality : (int)(lootQuality / 1.25);
			if (random.nextInt(0, thign - random.nextInt(0, thign)) == 0) 
				continue;
			if (random.nextInt(0, chance2) != 0) 
				continue;

			i++;
			if (i >= Integer.min(lootQuality, WetlandWhimsy.config.ancientPotMaxDropCount)) break;

			var stack = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(item)));
			growStack(stack);

			if (stack.getCount() == 0) continue;

			Block.popResource(
				level, 
				pos, 
				stack
			);
			j++;
		}

		if (j == 0)
			Block.popResource(
				level, 
				pos, 
				new ItemStack(
					BuiltInRegistries.ITEM.get(
						ResourceLocation.tryParse(
							WetlandWhimsy.config.ancientPotItems.get(
								random.nextInt(
									WetlandWhimsy.config.ancientPotItems.size() - 3, 
									WetlandWhimsy.config.ancientPotItems.size()
								)
							)
						)
					)
				)
			);
	}

	private void growStack(ItemStack stack) {
		if (stack.toString().contains("template")) return;
		stack.grow(Integer.min(stack.getMaxStackSize(), random.nextInt(0, lootQuality)) - 1);

		if (stack.isDamageableItem())
			stack.setDamageValue(random.nextInt(1, stack.getMaxDamage() - 1));
	}
}
