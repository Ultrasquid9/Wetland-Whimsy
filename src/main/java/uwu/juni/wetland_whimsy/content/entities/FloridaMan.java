package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

@ParametersAreNonnullByDefault
public class FloridaMan extends Zombie implements RangedAttackMob {
	public FloridaMan(EntityType<? extends FloridaMan> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloridaMan2ndAmendmentGoal(this, 1.0, 10, 50.0F));
		goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.4));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));

		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
		var bullet = new BulletEntity(this, level());

		var d0 = target.getX() - getX();
		var d1 = target.getY() - bullet.getY();
		var d2 = target.getZ() - getZ();
		var d3 = Mth.sqrt((float)(d0 * d0 + d2 * d2));

		bullet.shoot(d0, d1 + d3 * .2, d2, 1.5F, .1F);
		playSound(SoundEvents.DISPENSER_DISPENSE);
		level().addFreshEntity(bullet);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(WetlandWhimsyItems.AK47.get()));
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	protected boolean convertsInWater() {
		return false;
	}

	static class FloridaMan2ndAmendmentGoal extends RangedAttackGoal {
		private final FloridaMan man;

		public FloridaMan2ndAmendmentGoal(
			FloridaMan mob, 
			double speedModifier, 
			int attackInterval, 
			float attackRadius
		) {
			super(mob, speedModifier, attackInterval, attackRadius);
			man = mob;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && man.getMainHandItem().is(WetlandWhimsyItems.AK47.get());
		}

		@Override
		public void start() {
			man.setAggressive(true);
			man.startUsingItem(InteractionHand.MAIN_HAND);
		}

		@Override
		public void stop() {
			man.setAggressive(false);
			man.stopUsingItem();
		}
	}
}
