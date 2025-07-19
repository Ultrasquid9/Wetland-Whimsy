package uwu.juni.wetland_whimsy.content.entities.goals;

import java.util.Optional;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;

// Simply a copy of the `LongJumpUtil` class from 1.21.1

public class LongJumpUtil {
	public static Optional<Vec3> calculateJumpVectorForAngle(Mob mob, Vec3 target, float maxJumpVelocity, int angle, boolean requireClearTransition) {
		var vec3 = mob.position();
		var vec31 = new Vec3(target.x - vec3.x, 0.0, target.z - vec3.z).normalize().scale(0.5);
		var vec32 = target.subtract(vec31);
		var vec33 = vec32.subtract(vec3);
		var f = (float)angle * (float) Math.PI / 180.0F;
		var d0 = Math.atan2(vec33.z, vec33.x);
		var d1 = vec33.subtract(0.0, vec33.y, 0.0).lengthSqr();
		var d2 = Math.sqrt(d1);
		var d3 = vec33.y;
		var d4 = 0.08;
		var d5 = Math.sin((double)(2.0F * f));
		var d6 = Math.pow(Math.cos((double)f), 2.0);
		var d7 = Math.sin((double)f);
		var d8 = Math.cos((double)f);
		var d9 = Math.sin(d0);
		var d10 = Math.cos(d0);
		var d11 = d1 * d4 / (d2 * d5 - 2.0 * d3 * d6);
		if (d11 < 0.0) {
			return Optional.empty();
		}
		double d12 = Math.sqrt(d11);
		if (d12 > (double)maxJumpVelocity) {
			return Optional.empty();
		}
		double d13 = d12 * d8;
		double d14 = d12 * d7;
		if (requireClearTransition) {
			int i = Mth.ceil(d2 / d13) * 2;
			double d15 = 0.0;
			Vec3 vec34 = null;
			EntityDimensions entitydimensions = mob.getDimensions(Pose.LONG_JUMPING);

			for (int j = 0; j < i - 1; j++) {
				d15 += d2 / (double)i;
				double d16 = d7 / d8 * d15 - Math.pow(d15, 2.0) * d4 / (2.0 * d11 * Math.pow(d8, 2.0));
				double d17 = d15 * d10;
				double d18 = d15 * d9;
				Vec3 vec35 = new Vec3(vec3.x + d17, vec3.y + d16, vec3.z + d18);
				if (vec34 != null && !isClearTransition(mob, entitydimensions, vec34, vec35)) {
					return Optional.empty();
				}

				vec34 = vec35;
			}
		}

		return Optional.of(new Vec3(d13 * d10, d14, d13 * d9).scale(0.95F));
	}

	private static boolean isClearTransition(Mob mob, EntityDimensions dimensions, Vec3 startPos, Vec3 endPos) {
		var vec3 = endPos.subtract(startPos);
		var d0 = (double)Math.min(dimensions.width, dimensions.height);
		var i = Mth.ceil(vec3.length() / d0);
		var vec31 = vec3.normalize();
		var vec32 = startPos;

		for (int j = 0; j < i; j++) {
			vec32 = j == i - 1 ? endPos : vec32.add(vec31.scale(d0 * 0.9F));
			if (!mob.level().noCollision(mob, dimensions.makeBoundingBox(vec32))) {
				return false;
			}
		}

		return true;
	}
}
