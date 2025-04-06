package uwu.juni.wetland_whimsy.misc;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

@EventBusSubscriber(modid = WetlandWhimsy.MODID)
public class WetlandWhimsyTrades {
	@SubscribeEvent
	public static void trades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.FARMER) {
			var trades = event.getTrades();

			trades.get(2).add(offer(
				new ItemCost(WetlandWhimsyItems.CARROT_SEEDS, 16), 
				new ItemStack(Items.EMERALD, 1), 
				8, 
				6
			));
		}
	}

	@SubscribeEvent
	public static void wanderTrades(WandererTradesEvent event) {
		var commonTrades = event.getGenericTrades();

		commonTrades.add(offer(
			new ItemCost(Items.EMERALD, 2), 
			new ItemStack(WetlandWhimsyItems.CARROT_SEEDS.get(), 4), 
			5, 
			4
		));
	}

	static ItemListing offer(
		ItemCost cost,
		ItemStack stack,
		int maxUses,
		int exp
	) {
		return (a, b) -> new MerchantOffer(
			cost, 
			stack, 
			maxUses, 
			exp, 
			0.1F // IDK what this number does 
		);
	}
}
