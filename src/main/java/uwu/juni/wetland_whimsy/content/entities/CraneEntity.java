package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.entities.goals.CraneFlyGoal;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class CraneEntity extends Animal {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	public final AnimationState flyAnimationState = new AnimationState();
	private int flyAnimationTimeout = 0;

	public CraneEntity(EntityType<? extends CraneEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
    protected void registerGoals() {
		var goals = ImmutableList.of(
			new FloatGoal(this),
			new PanicGoal(this, 1.2),
			new BreedGoal(this, 1.0),
			new TemptGoal(this, 1.0, item -> isFood(item), false),
			new FollowParentGoal(this, 1.1),
			new RandomStrollGoal(this, 1.0),
			new CraneFlyGoal(this),
			new LookAtPlayerGoal(this, Player.class, 6.0F),
			new RandomLookAroundGoal(this)
		);
	
		var priority = 0;

		for (var goal : goals) {
			goalSelector.addGoal(priority, goal);
			priority++;
		}
    }

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createLivingAttributes()
			.add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.25)
			.add(Attributes.MOVEMENT_SPEED, 0.285)
			.add(Attributes.MAX_HEALTH, 30.0)
			.add(Attributes.FOLLOW_RANGE, 6);
	}

	private void setupAnimationStates() {
		if (!onGround() && !isInWater()) {
			idleAnimationState.stop();
			flyAnimationState.startIfStopped(tickCount);

			if (flyAnimationTimeout <= 0) {
				flyAnimationTimeout = 15;
				flyAnimationState.start(tickCount);
			} else flyAnimationTimeout--;
		} else {
			flyAnimationState.stop();
			idleAnimationState.startIfStopped(tickCount);

			if (idleAnimationTimeout <= 0) {
				idleAnimationTimeout = 30;
				idleAnimationState.start(tickCount);
			} else idleAnimationTimeout--;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level().isClientSide) 
			setupAnimationStates();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		var vec3 = this.getDeltaMovement();
		if (!onGround() && vec3.y < 0.0 && !hasEffect(MobEffects.SLOW_FALLING))
			setDeltaMovement(vec3.multiply(1.0, 0.8, 1.0));
	}

	@Override
	public boolean isFood(ItemStack item) {
		return item.is(WetlandWhimsyTags.Items.CRANE_FOOD);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return WetlandWhimsyEntityTypes.CRANE.get().create(level);
	}
}
