package uwu.juni.wetland_whimsy.content.items;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;

public class FlammableSignItem extends SignItem {
	private int time;

	public FlammableSignItem(StandingSignBlock standing, WallSignBlock wall, Properties prop, int time) {
		super(prop, standing, wall);

		this.time = time;
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return this.time;
	}
}
