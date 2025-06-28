package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressBoatEntity;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressChestBoatEntity;
import uwu.juni.wetland_whimsy.content.entities.BlemishEntity;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;
import uwu.juni.wetland_whimsy.content.entities.SludgeChargeEntity;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

@EventBusSubscriber(modid = WetlandWhimsy.MODID)
public class WetlandWhimsyEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = WetlandWhimsy.registry(
		Registries.ENTITY_TYPE
	);

	public static final Supplier<EntityType<SillyEntity>> SILLY_ENTITY = registerEntity(
		"silly", 
		EntityType.Builder.of(
			SillyEntity::new, 
			MobCategory.CREATURE
		)
		.sized(1, 1)
	);

	public static final Supplier<EntityType<CraneEntity>> CRANE = registerEntity(
		"crane", 
		EntityType.Builder.of(
			CraneEntity::new, 
			MobCategory.CREATURE
		)
		.sized(0.8F, 1.6F)
	);

	public static final Supplier<EntityType<BlemishEntity>> BLEMISH = registerEntity(
		"blemish", 
		EntityType.Builder.of(
			BlemishEntity::new, 
			MobCategory.MONSTER
		)
		.sized(1, 1)
	);

	public static final Supplier<EntityType<SwampSpiderEntity>> SWAMP_SPIDER = registerEntity(
		"swamp_spider", 
		EntityType.Builder.of(
			SwampSpiderEntity::new, 
			MobCategory.MONSTER
		)
		.sized(1.5F, 1.2F)
	);

	public static final Supplier<EntityType<BaldCypressBoatEntity>> BALD_CYPRESS_BOAT = registerEntity(
		"bald_cypress_boat", 
		EntityType.Builder.<BaldCypressBoatEntity>of(
			BaldCypressBoatEntity::new, 
			MobCategory.MISC
		)
		.sized(1.375F, 0.5625F)
	);
	public static final Supplier<EntityType<BaldCypressChestBoatEntity>> BALD_CYPRESS_CHEST_BOAT = registerEntity(
		"bald_cypress_chest_boat", 
		EntityType.Builder.<BaldCypressChestBoatEntity>of(
			BaldCypressChestBoatEntity::new, 
			MobCategory.MISC
		)
		.sized(1.375F, 0.5625F)
	);

	public static final Supplier<EntityType<SludgeChargeEntity>> SLUDGE_CHARGE = registerEntity(
		"sludge_charge", 
		EntityType.Builder.<SludgeChargeEntity>of(
			SludgeChargeEntity::new, 
			MobCategory.MISC
		)
		.sized(.4F, .4F)
	);

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(
			SILLY_ENTITY.get(),
			SillyEntity.createAttributes().build()
		);
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
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(
			SWAMP_SPIDER.get(), 
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Mob::checkMobSpawnRules,
			RegisterSpawnPlacementsEvent.Operation.OR
		);
		event.register(
			BLEMISH.get(), 
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Mob::checkMobSpawnRules,
			RegisterSpawnPlacementsEvent.Operation.OR
		);
		event.register(
			CRANE.get(), 
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Animal::checkAnimalSpawnRules,
			RegisterSpawnPlacementsEvent.Operation.OR
		);
	}

	// Saves a few lines 
	private static <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
		return ENTITIES.register(
			name, 
			() -> builder.build(name)
		);
	}
}
