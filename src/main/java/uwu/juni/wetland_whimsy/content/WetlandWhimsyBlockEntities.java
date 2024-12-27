package uwu.juni.wetland_whimsy.content;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.blocks.blockentities.SussyMudBlockEntity;
import uwu.juni.wetland_whimsy.content.blocks.blockentities.client.SussyMudBlockRenderer;

public class WetlandWhimsyBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
		ForgeRegistries.BLOCK_ENTITY_TYPES, 
		WetlandWhimsy.MODID
	);

	@SuppressWarnings("null")
	public static final RegistryObject<BlockEntityType<SussyMudBlockEntity>> SUSSY_MUD = BLOCK_ENTITIES.register(
		"suspicious_mud", 
		() -> BlockEntityType.Builder.of(SussyMudBlockEntity::new, WetlandWhimsyBlocks.SUSSY_MUD.get()).build(null)
	);

	public static void blockEntityRendering(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(
			SUSSY_MUD.get(), 
			SussyMudBlockRenderer::new
		);
	}
}
