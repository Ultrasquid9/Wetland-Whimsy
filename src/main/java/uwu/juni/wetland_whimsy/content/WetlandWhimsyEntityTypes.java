package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.BlemishEntity;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

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

	public static final RegistryObject<EntityType<BlemishEntity>> BLEMISH = registerEntity(
		"blemish", 
		EntityType.Builder.of(
			BlemishEntity::new, 
			MobCategory.MONSTER
		)
		.sized(1, 1)
	);

	public static final RegistryObject<EntityType<SwampSpiderEntity>> SWAMP_SPIDER = registerEntity(
		"swamp_spider", 
		EntityType.Builder.of(
			SwampSpiderEntity::new, 
			MobCategory.MONSTER
		)
		.sized(1.5F, 1.2F)
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
		event.put(
			BLEMISH.get(),
			BlemishEntity.createAttributes().build()
		);
		event.put(
			SWAMP_SPIDER.get(),
			SwampSpiderEntity.createAttributes().build()
		);
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(
			SWAMP_SPIDER.get(), 
			SpawnPlacements.Type.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules,
			SpawnPlacementRegisterEvent.Operation.OR
		);
		event.register(
			BLEMISH.get(), 
			SpawnPlacements.Type.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Mob::checkMobSpawnRules,
			SpawnPlacementRegisterEvent.Operation.OR
		);
		event.register(
			CRANE.get(), 
			SpawnPlacements.Type.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Animal::checkAnimalSpawnRules,
			SpawnPlacementRegisterEvent.Operation.OR
		);
	}
}
