package corundum.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.data.worldgen.WetlandWhimsyBiomesDatagen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("unchecked")
public class WetlandWhimsyBiomeTagsDatagen extends BiomeTagsProvider {
	public WetlandWhimsyBiomeTagsDatagen(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> registries, 
		ExistingFileHelper helper
	) {
		super(output, registries, WetlandWhimsy.MODID, helper);
	}

	@Override
	public void addTags(@Nonnull HolderLookup.Provider provider) {
		addOptionalTagToList(
			Tags.Biomes.IS_SWAMP, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.IS_OVERWORLD, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_MINESHAFT, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_RUINED_PORTAL_SWAMP, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);


		addOptionalTagToList(
			BiomeTags.HAS_TRAIL_RUINS, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);


		addOptionalTagToList(
			BiomeTags.HAS_SWAMP_HUT, 
			WetlandWhimsyBiomesDatagen.BOG,
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_SHIPWRECK_BEACHED, 
			WetlandWhimsyBiomesDatagen.MARSH
		);

		addOptionalTagToList(
			BiomeTags.IS_BEACH, 
			WetlandWhimsyBiomesDatagen.MARSH
		);
	}

	private void addOptionalTagToList(TagKey<Biome> tag, ResourceKey<Biome>... biomes) {
		for (var biome : biomes) {
			tag(tag).addOptional(biome.location());
		}
	}
}
