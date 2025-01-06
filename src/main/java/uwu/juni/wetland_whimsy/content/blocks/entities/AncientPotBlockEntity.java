package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.misc.Config;

public class AncientPotBlockEntity extends BlockEntity {
	private int lootQuality;
	private ThreadLocalRandom random;

	public AncientPotBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_POT.get(), pos, state);
		random = ThreadLocalRandom.current();
	}

	@Override
	protected void saveAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("loot_quality", lootQuality);
	}

	@Override
	protected void loadAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.loadAdditional(tag, registries);
		this.lootQuality = tag.getInt("loot_quality");
	}

	public void increaseLootQuality() { lootQuality++; }
	public int lootQuality() { return lootQuality; }

	/// Gambling
	public void dropLoot(Level level, BlockPos pos) {
		lootQuality += 2; // Ensuring that it is not zero

		var chance2 = Config.ancientPotItems.size() / (lootQuality * 5);
		if (chance2 <= 0) 
			chance2 = 1;

		var i = 0;
		var j = 0;
		for (var item : Config.ancientPotItems) {
			if (random.nextInt(0, lootQuality - random.nextInt(0, lootQuality)) == 0) 
				continue;
			if (random.nextInt(0, chance2) != 0) 
				continue;

			i++;
			if (i >= Integer.min(lootQuality, Config.ancientPotMaxDropCount)) break;

			var stack = new ItemStack(BuiltInRegistries.ITEM.get(item));
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
						Config.ancientPotItems.get(
							random.nextInt(Config.ancientPotItems.size() - 3, Config.ancientPotItems.size() - 1)
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
