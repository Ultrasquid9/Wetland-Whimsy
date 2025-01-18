package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

public class SillyEntity extends Animal {

	public SillyEntity(EntityType<? extends SillyEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean isFood(@Nonnull ItemStack stack) {
		return stack.is(Items.ACACIA_BOAT);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new TemptGoal(this, 1.25, a -> true, false));
		goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

		super.registerGoals();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createLivingAttributes()
			.add(Attributes.MAX_HEALTH, 10)
			.add(Attributes.MOVEMENT_SPEED, 1.25)
			.add(Attributes.FOLLOW_RANGE, 10);
	}

	@Override
	@Nullable
	public AgeableMob getBreedOffspring(@Nonnull ServerLevel level, @Nonnull AgeableMob otherParent) {
		return WetlandWhimsyEntityTypes.SILLY_ENTITY.get().create(level);
	}
}
