package uwu.juni.wetland_whimsy.content.items;

import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.entities.BulletEntity;

@ParametersAreNonnullByDefault
public class AK47Item extends ProjectileWeaponItem {
	public AK47Item(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(
		@Nonnull Level level, 
		@Nonnull Player player, 
		@Nonnull InteractionHand hand
	) {
		Consumer<SoundEvent> playSound = sound -> level.playSound(
			null, 
			player.getX(),
			player.getY(),
			player.getZ(), 
			sound, 
			SoundSource.PLAYERS,
			0.5F,
			0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
		);

		var item = player.getItemInHand(hand);
		var proj = player.getProjectile(item);

		if (!player.hasInfiniteMaterials()) {
			if (proj.isEmpty()) {
				playSound.accept(SoundEvents.DISPENSER_FAIL);
				return InteractionResultHolder.fail(item);
			}
	
			proj.shrink(1);
			item.hurtAndBreak(1, player, player.getEquipmentSlotForItem(item));
		}

		if (!level.isClientSide) {
			var bullet = new BulletEntity(
				player,
				level
			);

			bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
			level.addFreshEntity(bullet);
		}

		playSound.accept(SoundEvents.DISPENSER_LAUNCH);
		player.getCooldowns().addCooldown(this, 1);
		player.startUsingItem(hand);
		return InteractionResultHolder.consume(item);
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return item -> item.is(WetlandWhimsyItems.BULLET.get());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	protected void shootProjectile(
		LivingEntity shooter, 
		Projectile projectile, 
		int index, 
		float velocity,
		float inaccuracy, 
		float angle, 
		@Nullable LivingEntity target
	) {} // Idk where this function is used since its protected
}
