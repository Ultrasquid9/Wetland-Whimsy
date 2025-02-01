package uwu.juni.wetland_whimsy.content.items;

import javax.annotation.Nonnull;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.entities.SludgeChargeEntity;

public class SludgeChargeItem extends Item implements ProjectileItem {
	public SludgeChargeItem(Item.Properties properties) {
		super(properties);

		DispenserBlock.registerProjectileBehavior(this);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(
		@Nonnull Level level, 
		@Nonnull Player player, 
		@Nonnull InteractionHand hand
	) {
		ItemStack itemstack = player.getItemInHand(hand);
		level.playSound(
			null,
			player.getX(),
			player.getY(),
			player.getZ(),
			SoundEvents.SNOWBALL_THROW,
			SoundSource.NEUTRAL,
			0.5F,
			0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
		);
		if (!level.isClientSide) {
			var sludgeCharge = new SludgeChargeEntity(
				player,
				level
			);

			sludgeCharge.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			level.addFreshEntity(sludgeCharge);
		}

		player.getCooldowns().addCooldown(this, 10);
		player.awardStat(Stats.ITEM_USED.get(this));
		itemstack.consume(1, player);
		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}

	@Override
	public Projectile asProjectile(
		@Nonnull Level level, 
		@Nonnull Position pos, 
		@Nonnull ItemStack stack, 
		@Nonnull Direction direction
	) {
		var sludgeCharge = new SludgeChargeEntity(level, pos.x(), pos.y(), pos.z());
		sludgeCharge.addDeltaMovement(new Vec3(direction.getStepX(), direction.getStepY(), direction.getStepZ()));

		return sludgeCharge;
	}
}
