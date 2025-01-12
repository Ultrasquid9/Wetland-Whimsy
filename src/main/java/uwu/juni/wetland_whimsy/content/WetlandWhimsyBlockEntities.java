package uwu.juni.wetland_whimsy.content;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;
import uwu.juni.wetland_whimsy.content.blocks.entities.SussyMudBlockEntity;
import uwu.juni.wetland_whimsy.content.blocks.entities.client.SussyMudBlockRenderer;

public class WetlandWhimsyBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
		ForgeRegistries.BLOCK_ENTITY_TYPES, 
		WetlandWhimsy.MODID
	);

	@SuppressWarnings("null")
	public static final RegistryObject<BlockEntityType<SussyMudBlockEntity>> SUSSY_MUD = BLOCK_ENTITIES.register(
		"suspicious_mud", 
		() -> BlockEntityType.Builder.of(
			SussyMudBlockEntity::new, 
			WetlandWhimsyBlocks.SUSSY_MUD.get()
		).build(null)
	);

	@SuppressWarnings("null")
	public static final RegistryObject<BlockEntityType<AncientBrazierBlockEntity>> ANCIENT_BRAZIER = BLOCK_ENTITIES.register(
		"ancient_brazier",
		() -> BlockEntityType.Builder.of(
			AncientBrazierBlockEntity::new,
			WetlandWhimsyBlocks.ANCIENT_BRAZIER.get()
		).build(null)
	);

	@SuppressWarnings("null")
	public static final RegistryObject<BlockEntityType<AncientPotBlockEntity>> ANCIENT_POT = BLOCK_ENTITIES.register(
		"ancient_pot",
		() -> BlockEntityType.Builder.of(
			AncientPotBlockEntity::new,
			WetlandWhimsyBlocks.ANCIENT_POT.get()
		).build(null)
	);

	public static void blockEntityRendering(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(
			SUSSY_MUD.get(), 
			SussyMudBlockRenderer::new
		);
	}
}
