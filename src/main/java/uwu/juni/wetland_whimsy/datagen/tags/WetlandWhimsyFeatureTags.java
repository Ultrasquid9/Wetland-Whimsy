package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class WetlandWhimsyFeatureTags extends TagsProvider<PlacedFeature> {
	public WetlandWhimsyFeatureTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> lookupProvider, 
		ExistingFileHelper existingFileHelper
	) {
		super(output, Registries.PLACED_FEATURE, lookupProvider, WetlandWhimsy.MODID, existingFileHelper);
	}
	
	@Override
	protected void addTags(Provider provider) {
		final var terralith = "terralith";

		tag(WetlandWhimsyTags.Features.REMOVED_SWAMP_TREES)
			.add(VegetationPlacements.TREES_SWAMP)
			.addOptional(ResourceLocation.fromNamespaceAndPath(terralith, "swamp/vanilla/trees"))
			.addOptional(ResourceLocation.fromNamespaceAndPath(terralith, "swamp/vanilla/old_trees"));
	}
}
