package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

public class SludgeChargeEntity extends AbstractHurtingProjectile implements ItemSupplier {
	public SludgeChargeEntity(EntityType<? extends SludgeChargeEntity> type, Level level) {
		super(type, level);
	}

	public SludgeChargeEntity(
		Level level, 
		Entity owner, 
		double x, 
		double y, 
		double z
	) {
		super(WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(), x, y, z, level);
		this.setOwner(owner);
		this.accelerationPower = 0.0;
	}


	public SludgeChargeEntity(Level level, double x, double y, double z) {
		super(WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(), x, y, z, level);
		accelerationPower = 0.0;
	}

	@Override
	public ItemStack getItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected void onHit(@Nonnull HitResult result) {
		super.onHit(result);

		if (!this.level().isClientSide)
			discard();
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	@Nullable
	protected ParticleOptions getTrailParticle() {
		return null;
	}

	@Override
	protected float getInertia() {
		return 1;
	}

	@Override
	protected float getLiquidInertia() {
		return 1;
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide && this.getBlockY() > this.level().getMaxBuildHeight() + 30) 
			this.discard();

		super.tick();
	}
}
