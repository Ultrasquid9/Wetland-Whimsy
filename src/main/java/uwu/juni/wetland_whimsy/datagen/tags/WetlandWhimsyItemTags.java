package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.Compat;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.mehvahdjukaar.supplementaries.reg.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WetlandWhimsyItemTags extends ItemTagsProvider {
	public WetlandWhimsyItemTags(
		PackOutput output, 
		CompletableFuture<HolderLookup.Provider> registries, 
		CompletableFuture<TagLookup<Block>> blockTags, 
		ExistingFileHelper helper
	) {
        super(output, registries, blockTags, WetlandWhimsy.MODID, helper);
    }

	@Override
	protected void addTags(@Nonnull Provider provider) {
		copy(
			WetlandWhimsyTags.Blocks.BALD_CYPRESS_LOGS, 
			WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS
		);

		tag(WetlandWhimsyTags.Items.FLAMMABLE).add(
			Items.FLINT_AND_STEEL,
			Items.FIRE_CHARGE
		);

		tag(WetlandWhimsyTags.Items.SCALABLE_DO_NOT_GROW).add(
			WetlandWhimsyItems.RUSTED_ARTIFACT.get(),
			WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get(),
			WetlandWhimsyItems.DISC.get()
		);

		tag(WetlandWhimsyTags.Items.INCENSE).add(
			Items.DIRT,
			Items.GRANITE,
			Items.ANDESITE,
			Items.DIORITE
		);

		tag(ItemTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem()
		);
		tag(ItemTags.BOATS).add(
			WetlandWhimsyItems.BALD_CYPRESS_BOAT.asItem(),
			WetlandWhimsyItems.BALD_CYPRESS_BOAT.asItem()
		);
		tag(ItemTags.CHEST_BOATS).add(
			WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT.asItem()
		);

		tag(ItemTags.TRIM_TEMPLATES).add(
			WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()	
		);

		tag(Tags.Items.MUSIC_DISCS).add(
			WetlandWhimsyItems.DISC.get()	
		);

		if (Compat.SUPPLEMENTARIES)
			tag(ModTags.FLOWER_BOX_PLANTABLE).add(
				WetlandWhimsyBlocks.CORDGRASS.asItem(),
				WetlandWhimsyBlocks.PENNYWORT.asItem(),
				WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem()
			);
	}
}
