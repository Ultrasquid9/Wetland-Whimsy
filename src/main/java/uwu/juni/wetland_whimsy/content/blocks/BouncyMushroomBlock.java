package uwu.juni.wetland_whimsy.content.blocks;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;

@ParametersAreNonnullByDefault
public class BouncyMushroomBlock extends HalfTransparentBlock {
	final float bounceMult;

	public BouncyMushroomBlock(float bounceMult, BlockBehaviour.Properties properties) {
		super(properties);
		this.bounceMult = bounceMult; 
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
		if (entity.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(level, entity);
		} else {
			bounceUp(entity);
		}
	}

	private void bounceUp(Entity entity) {
		final var vec3 = entity.getDeltaMovement();
		if (vec3.y >= 0) {
			return;
		}

		entity.playSound(
			WetlandWhimsySounds.ARIA_MUSHROOM_JUMP.get(),
			(float)vec3.y * -1F,
			ThreadLocalRandom.current().nextInt(9, 11) / 10F
		);

		final var bounceAmount = entity instanceof LivingEntity ? 1.0 : 0.8;
		entity.setDeltaMovement(
			vec3.x, 
			-vec3.y * bounceAmount * this.bounceMult, 
			vec3.z
		);
	}
}
