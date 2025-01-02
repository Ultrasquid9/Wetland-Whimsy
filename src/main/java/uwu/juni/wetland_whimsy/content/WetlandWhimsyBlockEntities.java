package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;

public class WetlandWhimsyBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(
		Registries.BLOCK_ENTITY_TYPE, 
		WetlandWhimsy.MODID
	);

	@SuppressWarnings("null")
	public static final Supplier<BlockEntityType<AncientBrazierBlockEntity>> ANCIENT_BRAZIER = BLOCK_ENTITY_TYPES.register(
		"ancient_brazier",
		() -> BlockEntityType.Builder.of(
			AncientBrazierBlockEntity::new,
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get()
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
	}
}
