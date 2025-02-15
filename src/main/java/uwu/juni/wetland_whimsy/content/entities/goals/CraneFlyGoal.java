package uwu.juni.wetland_whimsy.content.entities.goals;

import java.util.Optional;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.behavior.LongJumpUtil;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;

public class CraneFlyGoal extends JumpGoal {
    private static final ObjectArrayList<Integer> ALLOWED_ANGLES = new ObjectArrayList<>(Lists.newArrayList(20, 25, 30, 35, 40, 45));

	protected final CraneEntity crane;
	protected Vec3 target;

	public CraneFlyGoal(CraneEntity crane) {
		super();
		this.crane = crane;
	}

	@Override
	public boolean canUse() {
		if (crane.getRandom().nextInt(0, 60) < 59)
			return false;

		if (!crane.onGround() && !crane.isInWater())
			return false;

		if (Swim.shouldSwim(crane))
			return false;
		
		target = getTarget();
		return true;
	}

	private BlockPos v3toBPos(Vec3 v3) {
		return new BlockPos((int)v3.x, (int)v3.y, (int)v3.z);
	}

	protected Vec3 getTarget() {
		var level = crane.level();
		var random = crane.getRandom();

		Supplier<Integer> a = () -> {
			var i = random.nextInt(7, 14);
			return random.nextBoolean() ? i : i * -1;	
		};
		var newPos = crane.position().add(
			a.get(), 
			0, 
			a.get()
		);

		var bPos = v3toBPos(newPos);

		while(level.getBlockState(bPos).isSuffocating(level, bPos)) {
			bPos = bPos.above();
		}
		while(!level.getBlockState(bPos.below()).isSuffocating(level, bPos)) {
			bPos = bPos.below();
		}

		return new Vec3(bPos.getX(), bPos.getY(), bPos.getZ());
	}

	@Override
	public void start() {
		var maybeVec3 = calculateOptimalJumpVector(crane, crane.getRandom(), target);

		if (maybeVec3.isPresent()) {
			crane.lookAt(EntityAnchorArgument.Anchor.EYES, target);

			crane.setDeltaMovement(maybeVec3.get());
			crane.addEffect(new MobEffectInstance(
				MobEffects.SLOW_FALLING, 
				30, 
				1, 
				true, 
				false
			));
		}
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
