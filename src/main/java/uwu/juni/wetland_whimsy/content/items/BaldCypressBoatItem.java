package uwu.juni.wetland_whimsy.content.items;

import java.util.function.Predicate;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressBoatEntity;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressChestBoatEntity;

@ParametersAreNonnullByDefault
public class BaldCypressBoatItem extends Item {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

	private final boolean isChestBoat;

	public BaldCypressBoatItem(
		Boolean isChestBoat, 
		Properties properties
	) {
		super(properties);

		this.isChestBoat = isChestBoat;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(
		Level level, 
		Player player,
		InteractionHand hand
	) {
		var itemstack = player.getItemInHand(hand);
		var hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

		if (hitresult.getType() == HitResult.Type.MISS)
			return InteractionResultHolder.pass(itemstack);

		var vec3 = player.getViewVector(1.0F);
		var list = level.getEntities(
			player, 
			player.getBoundingBox().expandTowards(vec3.scale(5.0)).inflate(1.0), 
			ENTITY_PREDICATE
		);
		if (!list.isEmpty()) {
			var vec31 = player.getEyePosition();

			for (var entity : list) {
				var aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
				if (aabb.contains(vec31))
					return InteractionResultHolder.pass(itemstack);
			}
		}

		if (hitresult.getType() != HitResult.Type.BLOCK)
			return InteractionResultHolder.pass(itemstack);

		var boat = getBoat(level, hitresult, itemstack, player);
		boat.setYRot(player.getYRot());

		if (!level.noCollision(boat, boat.getBoundingBox()))
			return InteractionResultHolder.fail(itemstack);
		
		if (!level.isClientSide) {
			level.addFreshEntity(boat);
			level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
			itemstack.consume(1, player);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}

	private Boat getBoat(
		Level level, 
		HitResult hitResult, 
		ItemStack stack, 
		Player player
	) {
		var vec3 = hitResult.getLocation();
		var boat = isChestBoat 
			? new BaldCypressChestBoatEntity(level, vec3.x, vec3.y, vec3.z) 
			: new BaldCypressBoatEntity(level, vec3.x, vec3.y, vec3.z);

		if (level instanceof ServerLevel serverlevel)
			EntityType.createDefaultStackConfig(serverlevel, stack, player).accept(boat);

		return boat;
	}
}
