package uwu.juni.wetland_whimsy.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.Compat;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class WetlandWhimsyRecipes extends RecipeProvider {
	protected WetlandWhimsyRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {

		// Bald Cypress
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
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyBlocks.BALD_CYPRESS_SIGN, 3)
			.group("wooden_sign")
			.define('P', WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS)
			.define('/', Tags.Items.RODS_WOODEN)
			.pattern("PPP")
			.pattern("PPP")
			.pattern(" / ")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS))
			.save(recipeOutput);

		// Limestone
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
			4
		);
		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS, 
			4
		);

		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE, 
			WetlandWhimsyBlocks.LIMESTONE_STAIRS, 
			WetlandWhimsyBlocks.LIMESTONE_SLAB
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB
		);
		stairsAndSlab(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS, 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS, 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE, 
			WetlandWhimsyBlocks.LIMESTONE_WALL
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL
		);
		wall(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS, 
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL
		);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WetlandWhimsyBlocks.LIMESTONE_PILLAR.get(), 2)
			.group("limestone")
			.define('L', WetlandWhimsyBlocks.POLISHED_LIMESTONE)
			.pattern("L")
			.pattern("L")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get()), has(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get()))
			.save(recipeOutput);

		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE, 
			List.of(
				WetlandWhimsyBlocks.LIMESTONE_STAIRS,
				WetlandWhimsyBlocks.LIMESTONE_SLAB,
				WetlandWhimsyBlocks.LIMESTONE_WALL,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL,
				WetlandWhimsyBlocks.LIMESTONE_BRICKS,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL,
				WetlandWhimsyBlocks.LIMESTONE_PILLAR
			)
		);
		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
			List.of(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB,
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL,
				WetlandWhimsyBlocks.LIMESTONE_BRICKS,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL,
				WetlandWhimsyBlocks.LIMESTONE_PILLAR
			)
		);
		stonecutterList(
			recipeOutput, 
			WetlandWhimsyBlocks.LIMESTONE_BRICKS, 
			List.of(
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB,
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL,
				WetlandWhimsyBlocks.LIMESTONE_PILLAR
			)
		);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, WetlandWhimsyItems.PENNYWORT_SALAD)
			.requires(WetlandWhimsyBlocks.PENNYWORT, 4)
			.requires(Items.BEETROOT, 2)
			.requires(Items.BOWL)
			.unlockedBy(getHasName(WetlandWhimsyBlocks.PENNYWORT), has(WetlandWhimsyBlocks.PENNYWORT))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 2)
			.define('D', Items.DIAMOND)
			.define('T', WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE)
			.define('L', WetlandWhimsyBlocks.LIMESTONE)
			.pattern("DTD")
			.pattern("DLD")
			.pattern("DDD")
			.unlockedBy(getHasName(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()), has(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get(), 1)
			.define('C', ItemTags.COALS)
			.define('R', Items.REDSTONE)
			.define('L', WetlandWhimsyBlocks.POLISHED_LIMESTONE)
			.pattern("LCL")
			.pattern("LRL")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get()), has(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get()))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyBlocks.SOUL_BRAZIER.get(), 1)
			.define('C', ItemTags.SOUL_FIRE_BASE_BLOCKS)
			.define('R', Items.REDSTONE)
			.define('L', WetlandWhimsyBlocks.POLISHED_LIMESTONE)
			.pattern("LCL")
			.pattern("LRL")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.SOUL_BRAZIER.get()), has(WetlandWhimsyBlocks.SOUL_BRAZIER.get()))
			.save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WetlandWhimsyBlocks.ARIA_SPORES.get(), 3)
			.requires(WetlandWhimsyBlocks.ARIA_MUSHROOM)
			.unlockedBy(getHasName(WetlandWhimsyBlocks.ARIA_MUSHROOM), has(WetlandWhimsyBlocks.ARIA_MUSHROOM))
			.save(recipeOutput);

		twoByTwo(
			recipeOutput, 
			WetlandWhimsyBlocks.CORDGRASS, 
			WetlandWhimsyBlocks.CORDGRASS_THATCH, 
			1
		);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyItems.DAGGER, 1)
			.define('D', WetlandWhimsyItems.RUSTED_ARTIFACT)
			.define('B', WetlandWhimsyItems.BLEMISH_ROD)
			.pattern(" D")
			.pattern("B ")
			.unlockedBy(getHasName(WetlandWhimsyItems.RUSTED_ARTIFACT), has(WetlandWhimsyItems.RUSTED_ARTIFACT))
			.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyItems.BALD_CYPRESS_BOAT, 1)
			.define('P', WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS)
			.pattern("P P")
			.pattern("PPP")
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS))
			.save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT)
			.requires(WetlandWhimsyItems.BALD_CYPRESS_BOAT)
			.requires(Items.CHEST)
			.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS), has(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS))
			.save(recipeOutput);

		if (Compat.FARMERS_DELIGHT)
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get(), 1)
				.define('T', WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR)
				.define('S', WetlandWhimsyBlocks.BALD_CYPRESS_SLAB)
				.pattern("SSS")
				.pattern("T T")
				.pattern("SSS")
				.unlockedBy(getHasName(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR), has(WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR))
				.save(recipeOutput);
	}

	private void twoByTwo(RecipeOutput recipeOutput, ItemLike input, ItemLike output, int count) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
			.define('I', input)
			.pattern("II")
			.pattern("II")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}

	private void stairsAndSlab(RecipeOutput recipeOutput, ItemLike input, ItemLike stairs, ItemLike slab) {
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

	private void wall(RecipeOutput recipeOutput, ItemLike input, ItemLike wall) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
			.define('P', input)
			.pattern("PPP")
			.pattern("PPP")
			.unlockedBy(getHasName(input), has(input))
			.save(recipeOutput);
	}

	private void doorAndTrapdoor(RecipeOutput recipeOutput, ItemLike input, ItemLike door, ItemLike trapdoor) {
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

	private void buttonAndPressurePlate(RecipeOutput recipeOutput, ItemLike input, ItemLike button, ItemLike pressurePlate) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, button, 1)
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

	private void stonecutterList(RecipeOutput recipeOutput, ItemLike input, List<ItemLike> outputs) {
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
