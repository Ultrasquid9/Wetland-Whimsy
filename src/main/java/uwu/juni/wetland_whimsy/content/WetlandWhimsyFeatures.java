package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.features.BlobPatchConfig;
import uwu.juni.wetland_whimsy.content.features.BlobPatchFeature;

public class WetlandWhimsyFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = WetlandWhimsy.registry(
		Registries.FEATURE
	);

	public static Supplier<Feature<BlobPatchConfig>> BLOB_PATCH = FEATURES.register(
		"blob_patch", 
		() -> new BlobPatchFeature()
	);
}
