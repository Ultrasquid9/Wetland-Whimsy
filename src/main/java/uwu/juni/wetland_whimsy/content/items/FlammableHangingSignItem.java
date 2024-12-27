package uwu.juni.wetland_whimsy.content.items;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;

public class FlammableHangingSignItem extends HangingSignItem {
	private int time;

	public FlammableHangingSignItem(CeilingHangingSignBlock ceiling, WallHangingSignBlock wall, Properties prop, int time) {
		super(ceiling, wall, prop);

		this.time = time;
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return this.time;
	}
}
