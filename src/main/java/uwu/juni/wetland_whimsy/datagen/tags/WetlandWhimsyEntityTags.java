package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WetlandWhimsyEntityTags extends EntityTypeTagsProvider {
	public WetlandWhimsyEntityTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> registries, 
		ExistingFileHelper helper
	) {
		super(output, registries, WetlandWhimsy.MODID, helper);
	}

	@Override
	protected void addTags(@Nonnull Provider provider) {
		tag(WetlandWhimsyTags.Entities.BLOODCAP_IMMUNE).add(
			EntityType.MOOSHROOM,
			WetlandWhimsyEntityTypes.BLEMISH.get(),
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get()
		);

		tag(WetlandWhimsyTags.Entities.SPAWNS_FROM_ANCIENT_BRAZIER).add(
			EntityType.ZOMBIE,
			EntityType.SKELETON,
			EntityType.SPIDER,
			EntityType.CAVE_SPIDER,
			EntityType.DROWNED,
			EntityType.BOGGED,
			WetlandWhimsyEntityTypes.BLEMISH.get(),
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get()
		);

		tag(EntityTypeTags.ARTHROPOD).add(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get()
		);

		tag(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS).add(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get()
		);

		tag(EntityTypeTags.UNDEAD).add(
			WetlandWhimsyEntityTypes.BLEMISH.get()
		);

		tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
			WetlandWhimsyEntityTypes.CRANE.get()
		);
	}
}
