package uwu.juni.wetland_whimsy.content;

import com.mojang.serialization.MapCodec;

import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.predicates.ShearedPredicate;

public class WetlandWhimsyPredicates {
	public static final DeferredRegister<MapCodec<? extends EntitySubPredicate>> PREDICATES = DeferredRegister.create(
		BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE,
		WetlandWhimsy.MODID
	);

	public static final DeferredHolder<MapCodec<? extends EntitySubPredicate>, MapCodec<ShearedPredicate>> SHEARED_PREDICATE = PREDICATES.register(
		"sheared", 
		() -> ShearedPredicate.CODEC
	);
}
