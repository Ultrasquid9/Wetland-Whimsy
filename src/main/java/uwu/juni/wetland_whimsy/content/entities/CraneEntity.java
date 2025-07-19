package uwu.juni.wetland_whimsy.content.entities;

import java.util.function.BiFunction;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.content.entities.goals.CraneFlyGoal;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class CraneEntity extends Animal {
	public boolean befriended = false;
	static final String befriendedId = "befriended";

	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState flyAnimationState = new AnimationState();

	public CraneEntity(EntityType<? extends CraneEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		var goals = ImmutableList.of(
			new FloatGoal(this),
			new LeapAtTargetGoal(this, 0.4F),
			new MeleeAttackGoal(this, 1.0, true),
			new PanicGoal(this, 1.2),
			new BreedGoal(this, 1.0),
			new TemptGoal(this, 1.0, Ingredient.of(WetlandWhimsyTags.Items.CRANE_FOOD), false),
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

		targetSelector.addGoal(
			priority, 
			new NearestAttackableTargetGoal<>(
				this, 
				Monster.class, 
				true, 
				e -> befriended
			)
		);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createLivingAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.285)
			.add(Attributes.MAX_HEALTH, 30)
			.add(Attributes.ATTACK_DAMAGE, 4)
			.add(Attributes.FOLLOW_RANGE, 6);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean(befriendedId, befriended);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains(befriendedId))
			befriended = compound.getBoolean(befriendedId);
	}

	void setupAnimationStates() {
		if (!onGround() && !isInWater()) {
			idleAnimationState.stop();
			flyAnimationState.startIfStopped(tickCount);
		} else {
			flyAnimationState.stop();
			idleAnimationState.startIfStopped(tickCount);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide) 
			setupAnimationStates();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		var vec3 = getDeltaMovement();
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

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		BiFunction<Player, InteractionHand, InteractionResult> fn = super::mobInteract;

		if (!isFood(player.getItemInHand(hand)))
			fn = super::mobInteract;

		else if (!befriended && !isBaby())
			fn = this::befriend;

		else if (getHealth() < getMaxHealth())
			fn = this::heal;

		return fn.apply(player, hand);
	}

	InteractionResult heal(Player player, InteractionHand hand) {
		var props = player
			.getItemInHand(hand)
			.getFoodProperties(this); // Parameter does not seem to be used
		
		heal(props == null ? 4 : props.getNutrition() * 2);
		feed(player, hand);

		return InteractionResult.SUCCESS;
	}

	InteractionResult befriend(Player player, InteractionHand hand) {
		befriended = true;
		feed(player, hand);
		sendParticles(ParticleTypes.HAPPY_VILLAGER, 10);

		return InteractionResult.SUCCESS;
	}

	void feed(Player player, InteractionHand hand) {
		usePlayerItem(player, hand, player.getItemInHand(hand));
		gameEvent(GameEvent.EAT);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getEntity() instanceof Player) {
			if (befriended)
				sendParticles(ParticleTypes.ANGRY_VILLAGER, 3);

			befriended = false;
		}

		return super.hurt(source, amount);
	}

	<T extends ParticleOptions> void sendParticles(T type, int amount) {
		if (!(level() instanceof ServerLevel sLevel))
			return;

		var pos = position();

		sLevel.sendParticles(
			type, 
			pos.x, 
			pos.y, 
			pos.z, 
			amount, 
			0.5, 
			1, 
			0, 
			0
		);
	}

	@Override
	@Nullable
	protected SoundEvent getAmbientSound() {
		return WetlandWhimsySounds.CRANE_AMBIENT.get();
	}
	
	@Override
	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return WetlandWhimsySounds.CRANE_HIT.get();
	}

	@Override
	@Nullable
	protected SoundEvent getDeathSound() {
		return WetlandWhimsySounds.CRANE_DEATH.get();
	}
}
