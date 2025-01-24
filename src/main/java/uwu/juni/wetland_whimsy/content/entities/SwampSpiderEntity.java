package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SwampSpiderEntity extends Spider {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	public SwampSpiderEntity(EntityType<? extends SwampSpiderEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.375)
			.add(Attributes.ATTACK_DAMAGE, 4)
			.add(Attributes.MAX_HEALTH, 20.0);
	}

	private void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 20;
			this.idleAnimationState.start(tickCount);
		} else idleAnimationTimeout--;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level().isClientSide) 
			setupAnimationStates();
	}

	// Initially stolen from the vanilla CaveSpider class, but I made the code less bad 
	@Override
	public boolean doHurtTarget(@Nonnull Entity entity) {
		if (!super.doHurtTarget(entity))
			return false;

		if (entity instanceof LivingEntity lEntity) {
			int i = switch(this.level().getDifficulty()) {
				case Difficulty.NORMAL -> 3;
				case Difficulty.HARD -> 5;

				default -> 0;
			};

			if (i > 0)
				lEntity.addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0), this);
		}

		return true;
	}

	@Override
	protected Vec3 getPassengerAttachmentPoint(
		@Nonnull Entity entity, 
		@Nonnull EntityDimensions dimensions, 
		float partialTick
	) {
		var vec = super.getPassengerAttachmentPoint(entity, dimensions, partialTick);
		return new Vec3(vec.x, vec.y / 2, vec.z);
	}
}
