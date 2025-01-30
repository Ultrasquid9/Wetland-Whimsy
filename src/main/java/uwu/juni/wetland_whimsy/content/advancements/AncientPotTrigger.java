package uwu.juni.wetland_whimsy.content.advancements;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger.SimpleInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;

public class AncientPotTrigger extends SimpleCriterionTrigger<AncientPotTrigger.AncientPotTriggerInstance> {
	@Override
	public Codec<AncientPotTriggerInstance> codec() {
		return AncientPotTriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int quality) {
		WetlandWhimsy.LOGGER.info("triggering");
		this.trigger(player, t -> quality >= 20);
	}

	public record AncientPotTriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
		public static final Codec<AncientPotTriggerInstance> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(AncientPotTriggerInstance::player)
			)
			.apply(instance, AncientPotTriggerInstance::new)
		);

		public boolean pot(Level level, BlockPos pos) {
			var be = level.getBlockEntity(pos);

			if (be instanceof AncientPotBlockEntity ab) {
				WetlandWhimsy.LOGGER.info("it is indeed ana ancient pot");
				return ab.lootQuality() >= 20;
			}

			return false;
		}
	}
}
