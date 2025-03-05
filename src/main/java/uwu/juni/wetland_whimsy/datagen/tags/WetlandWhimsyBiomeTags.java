package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

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
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyBiomes;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public class WetlandWhimsyBiomeTags extends BiomeTagsProvider {
	public WetlandWhimsyBiomeTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> registries, 
		ExistingFileHelper helper
	) {
		super(output, registries, WetlandWhimsy.MODID, helper);
	}

	@Override
	public void addTags(@Nonnull HolderLookup.Provider provider) {
		addTagsToBiome(
			WetlandWhimsyBiomes.MARSH, 
			Tags.Biomes.IS_SWAMP, 
			BiomeTags.IS_OVERWORLD, 
			BiomeTags.HAS_MINESHAFT, 
			BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, 
			BiomeTags.HAS_RUINED_PORTAL_SWAMP, 
			BiomeTags.HAS_SWAMP_HUT, 
			BiomeTags.HAS_SHIPWRECK_BEACHED, 
			BiomeTags.IS_BEACH
		);

		addOptionalTagToList(
			BiomeTags.HAS_TRAIL_RUINS, 
			Biomes.SWAMP,
			WetlandWhimsyBiomes.MARSH
		);

		addOptionalTagToList(
			WetlandWhimsyTags.Biomes.BLOODCAP_SURFACE, 
			WetlandWhimsyBiomes.MARSH,
			Biomes.DARK_FOREST,
			Biomes.MUSHROOM_FIELDS,
			Biomes.SWAMP
		);
	}

	@SafeVarargs
	final void addOptionalTagToList(TagKey<Biome> tag, ResourceKey<Biome>... biomes) {
		for (var biome : biomes)
			tag(tag).addOptional(biome.location());
	}

	@SafeVarargs
	final void addTagsToBiome(ResourceKey<Biome> biome, TagKey<Biome>... tags) {
		var loc = biome.location();

		for (var tag : tags)
			tag(tag).addOptional(loc);
	}
}
