package corundum.wetland_whimsy.data;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class WetlandWhimsyLanguageDatagen extends LanguageProvider {
	public WetlandWhimsyLanguageDatagen(PackOutput output) {
		super(output, WetlandWhimsy.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LOG, "Bald Cypress Log");
		this.addBlock(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG, "Stripped Bald Cypress Log");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES, "Bald Cypress Leaves");
		this.addBlock(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, "Bald Cypress Planks");
		this.addBlock(WetlandWhimsyBlocks.CORDGRASS, "Cordgrass");
	}
}
