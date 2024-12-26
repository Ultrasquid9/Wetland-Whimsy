package uwu.juni.wetland_whimsy.data.sub_providers;

import java.util.function.BiConsumer;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyLoot;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable.Builder;

@SuppressWarnings("null")
public class WetlandWhimsyVaultLootDatagen implements LootTableSubProvider {
	public WetlandWhimsyVaultLootDatagen() {}

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
