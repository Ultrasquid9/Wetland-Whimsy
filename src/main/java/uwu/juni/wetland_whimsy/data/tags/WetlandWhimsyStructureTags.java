package uwu.juni.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyStructures;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public class WetlandWhimsyStructureTags extends StructureTagsProvider {
	public WetlandWhimsyStructureTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> registries, 
		ExistingFileHelper helper
	) {
		super(output, registries, WetlandWhimsy.MODID, helper);
	}

	@Override
	protected void addTags(@Nonnull Provider provider) {
		addOptional(
			WetlandWhimsyTags.Structures.WETLAND_RUINS, 
			WetlandWhimsyStructures.ARCH,
			WetlandWhimsyStructures.ARENA,
			WetlandWhimsyStructures.PILLAR,
			WetlandWhimsyStructures.SWAMP_DUNGEON,
			WetlandWhimsyStructures.WALL
		);

		addOptional(
			StructureTags.CATS_SPAWN_AS_BLACK, 
			WetlandWhimsyStructures.WITCH_HUT
		);
	}

	private void addOptional(TagKey<Structure> tag, ResourceKey<?>... structures) {
		for (var structure : structures)
			tag(tag).addOptional(structure.location());
	}
}
