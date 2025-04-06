package uwu.juni.wetland_whimsy.datagen.tags;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

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
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyCompat;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

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

		tag(ItemTags.LOGS_THAT_BURN).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.asItem()
		);
		tag(ItemTags.LEAVES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.asItem()
		);
		tag(ItemTags.SAPLINGS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem()
		);

		tag(WetlandWhimsyTags.Items.FLAMMABLE).add(
			Items.FLINT_AND_STEEL,
			Items.FIRE_CHARGE
		);

		tag(ItemTags.CHICKEN_FOOD).add(
			WetlandWhimsyItems.CARROT_SEEDS.get()
		);
		tag(Tags.Items.SEEDS).add(
			WetlandWhimsyItems.CARROT_SEEDS.get()
		);
		tag(WetlandWhimsyTags.Items.INCENSE).add(
			WetlandWhimsyItems.BOILING_INCENSE.get(),
			WetlandWhimsyItems.BRINE_INCENSE.get(),
			WetlandWhimsyItems.ROT_INCENSE.get(),
			WetlandWhimsyItems.WEBBED_INCENSE.get()
		);

		tag(WetlandWhimsyTags.Items.CRANE_FOOD).add(
			WetlandWhimsyItems.CARROT_SEEDS.get(),
			WetlandWhimsyBlocks.CORDGRASS.asItem()
		);

		tag(ItemTags.PLANKS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem()
		);
		tag(ItemTags.BUTTONS).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.asItem()
		);
		tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
			WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.asItem()
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

		tag(ItemTags.SWORD_ENCHANTABLE).add(
			WetlandWhimsyItems.DAGGER.get()
		);

		tag(ItemTags.DECORATED_POT_SHERDS).add(
			WetlandWhimsyItems.GROWTH_POTTERY_SHERD.get(),
			WetlandWhimsyItems.SEALED_POTTERY_SHERD.get()
		);

		tag(ItemTags.DECORATED_POT_INGREDIENTS).add(
			WetlandWhimsyItems.GROWTH_POTTERY_SHERD.get(),
			WetlandWhimsyItems.SEALED_POTTERY_SHERD.get()
		);

		if (WetlandWhimsyCompat.SUPPLEMENTARIES)
			tag(ModTags.FLOWER_BOX_PLANTABLE).add(
				WetlandWhimsyBlocks.CORDGRASS.asItem(),
				WetlandWhimsyBlocks.PENNYWORT.asItem(),
				WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem()
			);
	}
}
