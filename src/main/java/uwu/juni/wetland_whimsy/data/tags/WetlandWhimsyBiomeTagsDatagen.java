package uwu.juni.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.IS_OVERWORLD, 
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, 
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_MINESHAFT, 
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_RUINED_PORTAL_SWAMP, 
			WetlandWhimsyBiomes.MARSH
		);


		addOptionalTagToList(
			BiomeTags.HAS_TRAIL_RUINS, 
			Biomes.SWAMP,
			WetlandWhimsyBiomes.MARSH
		);


		addOptionalTagToList(
			BiomeTags.HAS_SWAMP_HUT, 
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.HAS_SHIPWRECK_BEACHED, 
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			BiomeTags.IS_BEACH, 
			WetlandWhimsyBiomes.MARSH
		);
	}

	private void addOptionalTagToList(TagKey<Biome> tag, ResourceKey<Biome>... biomes) {
		for (var biome : biomes) {
			tag(tag).addOptional(biome.location());
		}
	}
}
