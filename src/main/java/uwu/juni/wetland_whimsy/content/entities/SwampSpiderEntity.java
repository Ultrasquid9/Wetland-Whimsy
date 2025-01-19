package uwu.juni.wetland_whimsy.content.entities;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;

public class SwampSpiderEntity extends Spider {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	public SwampSpiderEntity(EntityType<? extends SwampSpiderEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.425)
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
}
