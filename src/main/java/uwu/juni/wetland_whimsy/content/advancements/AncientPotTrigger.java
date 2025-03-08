package uwu.juni.wetland_whimsy.content.advancements;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger.SimpleInstance;
import net.minecraft.server.level.ServerPlayer;

public class AncientPotTrigger extends SimpleCriterionTrigger<AncientPotTrigger.AncientPotTriggerInstance> {
	@Override
	public Codec<AncientPotTriggerInstance> codec() {
		return AncientPotTriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int quality) {
		trigger(player, t -> t.check(quality));
	}

	public record AncientPotTriggerInstance(
		Optional<ContextAwarePredicate> player,
		Integer quality
	) implements SimpleInstance {
		public static final Codec<AncientPotTriggerInstance> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(AncientPotTriggerInstance::player),
				Codec.INT.fieldOf("quality").forGetter(AncientPotTriggerInstance::quality)
			)
			.apply(instance, AncientPotTriggerInstance::new)
		);

		public boolean check(int checked) {
			return checked >= quality;
		}
	}
}
