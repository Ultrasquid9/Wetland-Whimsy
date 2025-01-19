package uwu.juni.wetland_whimsy.content.items;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DaggerItem extends Item {
	public DaggerItem(Item.Properties properties) {
		super(properties.component(DataComponents.TOOL, createToolProperties()));
	}

	public static Tool createToolProperties() {
		return new Tool(
			ImmutableList.of(
				Tool.Rule.minesAndDrops(ImmutableList.of(Blocks.COBWEB), 15.0F), 
				Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)
			), 
			1.0F, 
			2
		);
	}

	public static ItemAttributeModifiers createAttributes(float damage, float speed) {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(
					BASE_ATTACK_DAMAGE_ID, damage, AttributeModifier.Operation.ADD_VALUE
				),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.build();
	}

	@Override
	public boolean canAttackBlock(
		@Nonnull BlockState state, 
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull Player player
	) {
		return !player.isCreative();
	}

	@Override
	public void postHurtEnemy(
		@Nonnull ItemStack stack, 
		@Nonnull LivingEntity target, 
		@Nonnull LivingEntity attacker
	) {
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public float getAttackDamageBonus(
		@Nonnull Entity target, 
		float damage, 
		@Nonnull DamageSource damageSource
	) {
		var pos1 = target.position();
		var pos2 = damageSource.getSourcePosition();
		if (pos2 == null)
			return super.getAttackDamageBonus(target, damage, damageSource);

		var dist = pos1.distanceTo(pos2);

		return 10 / (float)Math.max(dist - 0.33, 0.1);
	}
}
