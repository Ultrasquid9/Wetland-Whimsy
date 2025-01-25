package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;
import uwu.juni.wetland_whimsy.misc.Compat;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class WetlandWhimsyBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = WetlandWhimsy.registry(
		Registries.BLOCK_ENTITY_TYPE
	);

	@SuppressWarnings("null")
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AncientBrazierBlockEntity>> ANCIENT_BRAZIER = BLOCK_ENTITY_TYPES.register(
		"ancient_brazier",
		() -> BlockEntityType.Builder.of(
			AncientBrazierBlockEntity::new,
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get()
		).build(null)
	);

	@SuppressWarnings("null")
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AncientPotBlockEntity>> ANCIENT_POT = BLOCK_ENTITY_TYPES.register(
		"ancient_pot",
		() -> BlockEntityType.Builder.of(
			AncientPotBlockEntity::new,
			WetlandWhimsyBlocks.ANCIENT_POT.get()
		).build(null)
	);

	public static void handleBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
			BlockEntityType.SIGN, 
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);

		event.modify(
			BlockEntityType.HANGING_SIGN, 
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get()
		);

		event.modify(
			BlockEntityType.BRUSHABLE_BLOCK,
			WetlandWhimsyBlocks.SUSSY_MUD.get()
		);

		if (Compat.FARMERS_DELIGHT)
			event.modify(
				ModBlockEntityTypes.CABINET.get(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get().get()
			);
	}
}
