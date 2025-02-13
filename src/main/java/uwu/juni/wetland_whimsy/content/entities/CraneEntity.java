package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.server.level.ServerLevel;
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
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class CraneEntity extends Animal {
	public final AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	public CraneEntity(EntityType<? extends CraneEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.4));
        goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        goalSelector.addGoal(3, new TemptGoal(this, 1.0, item -> isFood(item), false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createLivingAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.3)
			.add(Attributes.MAX_HEALTH, 30.0)
			.add(Attributes.FOLLOW_RANGE, 6);
	}

	private void setupAnimationStates() {
		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 30;
			this.idleAnimationState.start(tickCount);
		} else idleAnimationTimeout--;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level().isClientSide) 
			setupAnimationStates();
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
