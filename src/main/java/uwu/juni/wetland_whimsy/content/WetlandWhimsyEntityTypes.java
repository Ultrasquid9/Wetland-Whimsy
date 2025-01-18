package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;

public class WetlandWhimsyEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
		BuiltInRegistries.ENTITY_TYPE, 
		WetlandWhimsy.MODID
	);

	public static final Supplier<EntityType<SillyEntity>> SILLY_ENTITY = ENTITIES.register(
		"silly", 
		() -> EntityType.Builder.of(
			SillyEntity::new, 
			MobCategory.CREATURE
		)
		.sized(1, 1)
		.build("silly")
	);

	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(),
			SillyEntity.createAttributes().build()
		);
	}
}
