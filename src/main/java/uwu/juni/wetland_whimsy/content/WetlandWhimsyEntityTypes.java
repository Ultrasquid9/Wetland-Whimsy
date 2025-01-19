package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;
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
			MobCategory.MONSTER
		)
		.sized(1, 1)
		.build("blemish")
	);

	public static final Supplier<EntityType<SwampSpiderEntity>> SWAMP_SPIDER = ENTITIES.register(
		"swamp_spider", 
		() -> EntityType.Builder.of(
			SwampSpiderEntity::new, 
			MobCategory.MONSTER
		)
		.sized(1.5F, 1.2F)
		.build("swamp_spider")
	);

	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(
			SILLY_ENTITY.get(),
			SillyEntity.createAttributes().build()
		);
		event.put(
			BLEMISH.get(),
			BlemishEntity.createAttributes().build()
		);
		event.put(
			SWAMP_SPIDER.get(),
			SwampSpiderEntity.createAttributes().build()
		);
	}
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(
			SWAMP_SPIDER.get(), 
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Mob::checkMobSpawnRules,
			RegisterSpawnPlacementsEvent.Operation.OR
		);
	}
}
