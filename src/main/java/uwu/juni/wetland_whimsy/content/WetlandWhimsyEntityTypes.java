package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;

@EventBusSubscriber(modid = WetlandWhimsy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class WetlandWhimsyEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
		Registries.ENTITY_TYPE,
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<EntityType<CraneEntity>> CRANE = registerEntity(
		"crane", 
		EntityType.Builder.of(
			CraneEntity::new, 
			MobCategory.CREATURE
		)
		.sized(0.8F, 1.6F)
	);

	// Saves a few lines 
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
		return ENTITIES.register(
			name, 
			() -> builder.build(name)
		);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(
			CRANE.get(),
			CraneEntity.createAttributes().build()
		);
	}

/* 	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(
			CRANE.get(), 
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Animal::checkAnimalSpawnRules,
			RegisterSpawnPlacementsEvent.Operation.OR
		);
	} */
}
