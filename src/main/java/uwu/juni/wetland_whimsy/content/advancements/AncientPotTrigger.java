package uwu.juni.wetland_whimsy.content.advancements;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@ParametersAreNonnullByDefault
public class AncientPotTrigger extends SimpleCriterionTrigger<AncientPotTrigger.AncientPotTriggerInstance> {
	static final ResourceLocation ID = WetlandWhimsy.rLoc("ancient_pot");

	public void trigger(ServerPlayer player, int quality) {
		trigger(player, t -> t.check(quality));
	}

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected AncientPotTriggerInstance createInstance(
		JsonObject json,
		ContextAwarePredicate predicate,
		DeserializationContext context
	) {
		var quality = GsonHelper.getAsInt(json, "quality");

		return new AncientPotTriggerInstance(predicate, quality);
	}

	public class AncientPotTriggerInstance extends AbstractCriterionTriggerInstance {
		private final Integer quality;
		
		public AncientPotTriggerInstance(ContextAwarePredicate predicate, Integer quality) {
			super(AncientPotTrigger.ID, predicate);
			this.quality = quality;
		}

		public boolean check(int checked) {
			return checked >= quality;
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			var json = super.serializeToJson(conditions);

			json.addProperty("quality", this.quality);

			return json;
		}
	}
}
