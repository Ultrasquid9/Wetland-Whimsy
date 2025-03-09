package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class BulletEntity extends AbstractArrow {
	public BulletEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
		super(entityType, level);
	}

	public BulletEntity(LivingEntity shooter, Level level) {
		super(
			WetlandWhimsyEntityTypes.BULLET.get(), 
			shooter, 
			level, 
			new ItemStack(WetlandWhimsyItems.BULLET.get()), 
			null
		);
		setNoGravity(true);
	}

	public BulletEntity(
		Level level,
		double x,
		double y,
		double z
	) {
		super(
			WetlandWhimsyEntityTypes.BULLET.get(),
			x,
			y,
			z,
			level,
			new ItemStack(WetlandWhimsyItems.BULLET.get()),
			null
		);
		setNoGravity(true);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected float getWaterInertia() {
		return 1;
	}

	public boolean isGrounded() {
		return inGround;
	}

	@Override
	protected void onHitEntity(@Nonnull EntityHitResult result) {
		result.getEntity().hurt(damageSources().thrown(this, getOwner()), 5);
		doHit(result.getLocation());
		discard();
	}

	@Override
	protected void onHitBlock(@Nonnull BlockHitResult result) {
		doHit(result.getBlockPos().getCenter());
		discard();
	}

	void doHit(Vec3 pos) {
		if (level().isClientSide) 
			return;

		level().explode(
			this, 
			getX(), 
			getY(), 
			getZ(), 
			1.5F, 
			ExplosionInteraction.TRIGGER
		);
	}

	@Override
	public void tick() {
		if (inGround)
			discard();

		super.tick();
	}
}
