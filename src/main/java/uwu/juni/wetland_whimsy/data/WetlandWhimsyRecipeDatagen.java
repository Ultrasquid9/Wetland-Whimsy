package uwu.juni.wetland_whimsy.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

@SuppressWarnings("null")
public class WetlandWhimsyRecipeDatagen extends RecipeProvider {
	protected WetlandWhimsyRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {

		// Bald Cypress
		planksFromLog(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, 
			4
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get(), 
			3
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get(), 
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get(), 
			3
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get()
		);
		doorAndTrapdoor(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get()
		);
		buttonAndPressurePlate(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get()
		);
		fenceAndFenceGate(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get()
		);
		hangingSign(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(), 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()
		);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 3)
			.group("wooden_sign")
			.define('P', WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get())
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("PPP")
			.pattern("PPP")
			.pattern(" / ")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()))
			.save(recipeOutput);

		// Limestone
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE.get(), 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(), 
			4
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(), 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(), 
			4
		);

		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE.get(), 
			WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(), 
			WetlandWhimsyBlocks.LIMESTONE_SLAB.get()
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(), 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(), 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get()
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(), 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(), 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get()
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE.get(), 
			WetlandWhimsyBlocks.LIMESTONE_WALL.get()
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(), 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get()
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(), 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get()
		);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyBlocks.LIMESTONE_PILLAR.get(), 2)
			.define('L', WetlandWhimsyBlocks.POLISHED_LIMESTONE.get())
			.pattern("L")
			.pattern("L")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get()), has(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get()))
			.save(recipeOutput);

		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE.get(), 
			List.of(
				WetlandWhimsyBlocks.LIMESTONE_STAIRS.get(),
				WetlandWhimsyBlocks.LIMESTONE_SLAB.get(),
				WetlandWhimsyBlocks.LIMESTONE_WALL.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(),
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.get()
			)
		);
		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE.get(), 
			List.of(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get(),
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(),
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.get()
			)
		);
		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS.get(), 
			List.of(
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get(),
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get(),
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.get()
			)
		);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, WetlandWhimsyItems.PENNYWORT_SALAD.get())
			.requires(WetlandWhimsyBlocks.PENNYWORT.get(), 4)
			.requires(Items.BEETROOT, 2)
			.requires(Items.BOWL)
			.unlockedBy(getHasName(WetlandWhimsyBlocks.PENNYWORT.get()), has(WetlandWhimsyBlocks.PENNYWORT.get()))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 2)
			.define('T', WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get())
			.define('D', Items.DIAMOND)
			.define('L', WetlandWhimsyBlocks.LIMESTONE.get())
			.pattern("DTD")
			.pattern("DLD")
			.pattern("DDD")
			.unlockedBy(getHasName(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()), has(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyItems.BALD_CYPRESS_BOAT.getFirst().get(), 1)
			.define('P', WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get())
			.pattern("P P")
			.pattern("PPP")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()))
			.save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, WetlandWhimsyItems.BALD_CYPRESS_BOAT.getSecond().get(), 1)
			.requires(WetlandWhimsyItems.BALD_CYPRESS_BOAT.getFirst().get())
			.requires(Items.CHEST)
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()))
			.save(recipeOutput);
	}

	private void twoByTwo(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike output, int count) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
			.define('I', input)
			.pattern("II")
			.pattern("II")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void stairsAndSlab(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike stairs, ItemLike slab) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
			.define('S', input)
			.pattern("  S")
			.pattern(" SS")
			.pattern("SSS")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
			.define('S', input)
			.pattern("SSS")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void wall(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike wall) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
			.define('P', input)
			.pattern("PPP")
			.pattern("PPP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void doorAndTrapdoor(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike door, ItemLike trapdoor) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
			.define('P', input)
			.pattern("PP")
			.pattern("PP")
			.pattern("PP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, trapdoor, 3)
			.define('P', input)
			.pattern("PPP")
			.pattern("PPP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void buttonAndPressurePlate(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike button, ItemLike pressurePlate) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, button, 1)
			.requires(input)
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);

		pressurePlate(
			recipeOutput, 
			pressurePlate, 
			input
		);
	}
	private void fenceAndFenceGate(Consumer<FinishedRecipe> recipeOutput, ItemLike input, ItemLike fence, ItemLike gate) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fence, 3)
			.define('P', input)
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("P/P")
			.pattern("P/P")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, gate, 1)
			.define('P', input)
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("/P/")
			.pattern("/P/")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}

	private void stonecutterList(Consumer<FinishedRecipe> recipeOutput, ItemLike input, List<ItemLike> outputs) {
		for (ItemLike output : outputs) {
			var count = 1;
			
			if (output.asItem().toString().contains("slab")) {
				count++;
			}

			stonecutterResultFromBase(
				recipeOutput, 
				RecipeCategory.BUILDING_BLOCKS, 
				output, 
				input,
				count
			);
		}
	}
}
