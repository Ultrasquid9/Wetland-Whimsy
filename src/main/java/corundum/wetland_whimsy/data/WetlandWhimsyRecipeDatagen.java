package corundum.wetland_whimsy.data;

import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

@SuppressWarnings("null")
public class WetlandWhimsyRecipeDatagen extends RecipeProvider {
	protected WetlandWhimsyRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		planksFromLog(
			recipeOutput, 
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(),
			WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, 
			4
		);
	}
}
