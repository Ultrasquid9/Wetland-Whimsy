package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import uwu.juni.wetland_whimsy.datagen.loot.WetlandWhimsyMiscLoot;

public class SwampSpiderEntity extends Spider {
    private static final EntityDataAccessor<Boolean> DATA_SHEARED = SynchedEntityData.defineId(
		SwampSpiderEntity.class, 
		EntityDataSerializers.BOOLEAN
	);
	static final String SHEARED_ID = "sheared";

	public final AnimationState idleAnimationState = new AnimationState();

	public SwampSpiderEntity(EntityType<? extends SwampSpiderEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void defineSynchedData(@Nonnull SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_SHEARED, false);
	}

	@Override
	public void addAdditionalSaveData(@Nonnull CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putBoolean(SHEARED_ID, isSheared());
	}

	@Override
	public void readAdditionalSaveData(@Nonnull CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		setSheared(compound.getBoolean(SHEARED_ID));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.375)
			.add(Attributes.ATTACK_DAMAGE, 4)
			.add(Attributes.MAX_HEALTH, 20.0);
	}

	void setupAnimationStates() {
		idleAnimationState.startIfStopped(tickCount);
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide) 
			setupAnimationStates();
	}

	// Initially stolen from the vanilla CaveSpider class, but I made the code less bad 
	@Override
	public boolean doHurtTarget(@Nonnull Entity entity) {
		if (!super.doHurtTarget(entity))
			return false;

		if (entity instanceof LivingEntity lEntity) {
			var i = switch(level().getDifficulty()) {
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
		return super
			.getPassengerAttachmentPoint(entity, dimensions, partialTick)
			.multiply(1, .5, 1);
	}

	@Override
	protected InteractionResult mobInteract(
		@Nonnull Player player, 
		@Nonnull InteractionHand hand
	) {
		var itemstack = player.getItemInHand(hand);

		if (isSheared() || !isAlive() || !itemstack.is(Tags.Items.TOOLS_SHEAR))
			return super.mobInteract(player, hand);

		level().playSound(player, player.blockPosition(), SoundEvents.BOGGED_SHEAR, SoundSource.PLAYERS);
		setSheared(true);
		spawnShearedMushrooms();
		gameEvent(GameEvent.SHEAR, player);

		if (!this.level().isClientSide)
			itemstack.hurtAndBreak(1, player, getSlotForHand(hand));

		return InteractionResult.sidedSuccess(this.level().isClientSide);
	}

	public boolean isSheared() {
		return entityData.get(DATA_SHEARED);
	}

	public void setSheared(boolean sheared) {
		entityData.set(DATA_SHEARED, sheared);
	}

	void spawnShearedMushrooms() {
		if (!(level() instanceof ServerLevel sLevel))
			return;

		var table = sLevel.getServer()
			.reloadableRegistries()
			.getLootTable(WetlandWhimsyMiscLoot.SWAMP_SPIDER_SHEAR);

		var params = new LootParams.Builder(sLevel)
			.withParameter(LootContextParams.ORIGIN, position())
			.withParameter(LootContextParams.THIS_ENTITY, this)
			.create(LootContextParamSets.SHEARING);

		for (var item : table.getRandomItems(params)) {
			spawnAtLocation(item, getBbHeight());
		}
	}
}
