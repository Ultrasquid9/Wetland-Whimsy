package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;

public class SludgeChargeEntity extends AbstractArrow {
	private float oldvRot = 0;
	private float vRot = 0;

	public SludgeChargeEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
		super(entityType, level);
	}

	public SludgeChargeEntity(LivingEntity shooter, Level level) {
		super(
			WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(), 
			shooter, 
			level, 
			new ItemStack(WetlandWhimsyItems.SLUDGE_CHARGE.get()), 
			null
		);
	}

	public SludgeChargeEntity(Level level) {
		super(
			WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(), 
			null, 
			level, 
			new ItemStack(WetlandWhimsyItems.SLUDGE_CHARGE.get()), 
			null
		);
	}

	public SludgeChargeEntity(
		Level level,
		double x,
		double y,
		double z
	) {
		super(
			WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(),
			x,
			y,
			z,
			level,
			new ItemStack(WetlandWhimsyItems.SLUDGE_CHARGE.get()),
			null
		);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}

	public float getVRot() {
		return vRot;
	}
	public float getOldVRot() {
		return oldvRot;
	}

	public boolean isGrounded() {
		return inGround;
	}

	@Override
	protected void onHitEntity(@Nonnull EntityHitResult result) {
		var entity = result.getEntity();

		entity.hurt(this.damageSources().thrown(this, getOwner()), 5);

		if (entity instanceof LivingEntity le) {
			var effects = ImmutableList.of(
				MobEffects.MOVEMENT_SLOWDOWN,
				MobEffects.BLINDNESS,
				MobEffects.POISON
			);

			for (var effect : effects)
				le.addEffect(
					new MobEffectInstance(
						effect, 
						30, 
						1, 
						false, 
						false
					)
				);
		}

		doHit(result.getLocation());
		discard();
	}

	@Override
	protected void onHitBlock(@Nonnull BlockHitResult result) {
		doHit(result.getBlockPos().getCenter());
		discard();
	}

	private void doHit(Vec3 pos) {
		var level = level();
		if (level.isClientSide) return;

		this.playSound(
			WetlandWhimsySounds.SLUDGE_CHARGE_HIT.get(), 
			1, 
			(float)level.getRandom().nextInt(7, 13) / 10
		);

		var cloud = new AreaEffectCloud(level, getX(), getY(), getZ());
		cloud.setParticle(WetlandWhimsyParticleTypes.MUD_DRIP.get());
		cloud.setRadius(Mth.PI);
		cloud.setDuration(300);
		cloud.setRadiusPerTick(-.01F);
		cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));

		var entity = getOwner();
		if (entity != null && entity instanceof LivingEntity le)
			cloud.setOwner(le);

		level.addFreshEntity(cloud);
	}

	@Override
	public void tick() {
		if (level() instanceof ClientLevel) {
			oldvRot = vRot;
			vRot += 10;
		}

		if (inGround)
			discard();

		super.tick();
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		double d0 = this.getBoundingBox().getSize() * 4.0;
		if (Double.isNaN(d0)) {
			d0 = 4.0;
		}

		d0 *= 64.0;
		return distance < d0 * d0;
	}
}
