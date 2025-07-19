package uwu.juni.wetland_whimsy.content.entities.goals;

import java.util.Optional;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;

public class CraneFlyGoal extends JumpGoal {
	private static final Integer[] ALLOWED_ANGLES = { 20, 25, 30, 35, 40, 45 };

	protected final CraneEntity crane;
	protected Vec3 target;

	public CraneFlyGoal(CraneEntity crane) {
		super();
		this.crane = crane;
	}

	@Override
	public boolean canUse() {
		final var discardChance = 80;

		if (
			crane.getRandom().nextInt(0, discardChance) < discardChance - 1
			|| (!crane.onGround() && !crane.isInWater())
			//|| Swim.shouldSwim(crane)
		) return false;
		
		target = getTarget();
		return true;
	}

	protected Vec3 getTarget() {
		var random = crane.getRandom();

		Supplier<Integer> a = () -> {
			var i = random.nextInt(7, 14);
			return random.nextBoolean() ? i : i * -1;	
		};

		return crane.position().add(
			a.get(), 
			0, 
			a.get()
		);
	}

	@Override
	public void start() {
		var v3 = calculateOptimalJumpVector(crane, crane.getRandom(), target);

		if (v3.isEmpty())
			return;

		crane.setDeltaMovement(v3.get());
		crane.lookAt(EntityAnchorArgument.Anchor.EYES, target);
		crane.addEffect(new MobEffectInstance(
			MobEffects.SLOW_FALLING, 
			30, 
			1, 
			true, 
			false
		));
	}

	private static Optional<Vec3> calculateOptimalJumpVector(CraneEntity crane, RandomSource random, Vec3 target) {
		for (int i : Util.shuffledCopy(ALLOWED_ANGLES, random)) {
			var opt = LongJumpUtil.calculateJumpVectorForAngle(
				crane, 
				target,
				1.4F,
				i,
				false
			);
			if (opt.isPresent()) 
				return opt;
		}

		return Optional.empty();
	}
}
