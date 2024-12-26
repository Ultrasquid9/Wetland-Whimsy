package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable.Builder;

@SuppressWarnings("null")
public class WetlandWhimsyVaultLootDatagen implements LootTableSubProvider {

	@SuppressWarnings("unused") // Why did they want me to store this thing 
	private HolderLookup.Provider thing;

	public WetlandWhimsyVaultLootDatagen(HolderLookup.Provider lookupProvider) {
		thing = lookupProvider;
	}

	@Override
	public void generate(BiConsumer<ResourceLocation, Builder> output) {			
		output.accept(
			new ResourceLocation(WetlandWhimsy.MODID, "basic_vault_loot"),
			WetlandWhimsyLoot.createBasicLootTable()
		);
		output.accept(
			new ResourceLocation(WetlandWhimsy.MODID, "intermediate_vault_loot"),
			WetlandWhimsyLoot.createIntermediateLootTable()
		);
	}
}
