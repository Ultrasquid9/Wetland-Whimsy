package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;
import uwu.juni.wetland_whimsy.content.entities.BlemishEntity;

public class WetlandWhimsyEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = WetlandWhimsy.registry(
		Registries.ENTITY_TYPE
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

	public static final Supplier<EntityType<BlemishEntity>> BLEMISH = ENTITIES.register(
		"blemish", 
		() -> EntityType.Builder.of(
			BlemishEntity::new, 
			MobCategory.CREATURE
		)
		.sized(1, 1)
		.build("blemish")
	);

	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(),
			SillyEntity.createAttributes().build()
		);
		event.put(
			WetlandWhimsyEntityTypes.BLEMISH.get(),
			BlemishEntity.createAttributes().build()
		);
	}
}
