package uwu.juni.wetland_whimsy.content.items;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public class FlammableBlockItem extends BlockItem {
	private int time;

	public FlammableBlockItem(Block block, Properties prop, int time) {
		super(block, prop);

		this.time = time;
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return this.time;
	}
}
