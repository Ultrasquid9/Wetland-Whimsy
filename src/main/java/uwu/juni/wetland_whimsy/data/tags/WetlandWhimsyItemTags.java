package uwu.juni.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
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
	protected void addTags(Provider provider) {
		this.copy(
			WetlandWhimsyTags.Blocks.BALD_CYPRESS_LOGS, 
			WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS
		);

		this.tag(ItemTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem()	
		);

		this.tag(ItemTags.TRIM_TEMPLATES).add(
			WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get()	
		);

		this.tag(Tags.Items.MUSIC_DISCS).add(
			WetlandWhimsyItems.DISC.get()	
		);
	}
}
