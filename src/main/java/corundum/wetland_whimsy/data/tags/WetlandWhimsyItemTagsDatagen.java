package corundum.wetland_whimsy.data.tags;

import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.tags.WetlandWhimsyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")
public class WetlandWhimsyItemTagsDatagen extends ItemTagsProvider {
	public WetlandWhimsyItemTagsDatagen(
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
	}
}
