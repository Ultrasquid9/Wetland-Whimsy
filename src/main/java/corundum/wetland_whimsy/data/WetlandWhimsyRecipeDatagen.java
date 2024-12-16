package corundum.wetland_whimsy.data;

import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class WetlandWhimsyRecipeDatagen extends RecipeProvider {
	protected WetlandWhimsyRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		planksFromLog(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS,
			WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, 
			4
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG, 
			WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, 
			3
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, 
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, 
			3
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, 
			WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS,
			WetlandWhimsyBlocks.BALD_CYPRESS_SLAB
		);
		doorAndTrapdoor(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, 
			WetlandWhimsyBlocks.BALD_CYPRESS_DOOR,
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR
		);
		buttonAndPressurePlate(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, 
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON,
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE
		);
		fenceAndFenceGate(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS,
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE,
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE
		);
		hangingSign(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS
		);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(), 3)
			.group("wooden_sign")
			.define('P', WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get().asItem())
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("PPP")
			.pattern("PPP")
			.pattern(" / ")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get()))
			.save(recipeOutput);
	}

	private void twoByTwo(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
			.group("idk")
			.define('I', input)
			.pattern("II")
			.pattern("II")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void stairsAndSlab(RecipeOutput recipeOutput, ItemLike input, ItemLike stairs, ItemLike slab) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
			.group("stairs")
			.define('S', input)
			.pattern("  S")
			.pattern(" SS")
			.pattern("SSS")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
			.group("slabs")
			.define('S', input)
			.pattern("SSS")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void doorAndTrapdoor(RecipeOutput recipeOutput, ItemLike input, ItemLike door, ItemLike trapdoor) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
			.group("doors")
			.define('P', input)
			.pattern("PP")
			.pattern("PP")
			.pattern("PP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, trapdoor, 3)
			.group("doors")
			.define('P', input)
			.pattern("PPP")
			.pattern("PPP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
	private void buttonAndPressurePlate(RecipeOutput recipeOutput, ItemLike input, ItemLike button, ItemLike pressurePlate) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, button, 1)
			.group("doors")
			.define('P', input)
			.pattern("P")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);

		pressurePlate(
			recipeOutput, 
			pressurePlate, 
			input
		);
	}
	private void fenceAndFenceGate(RecipeOutput recipeOutput, ItemLike input, ItemLike fence, ItemLike gate) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fence, 3)
			.group("fences")
			.define('P', input)
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("P/P")
			.pattern("P/P")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, gate, 1)
			.group("fences")
			.define('P', input)
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("/P/")
			.pattern("/P/")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}
}
