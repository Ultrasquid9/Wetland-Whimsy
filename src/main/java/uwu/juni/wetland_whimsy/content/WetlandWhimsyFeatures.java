package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.features.BlobPatchConfig;
import uwu.juni.wetland_whimsy.content.features.BlobPatchFeature;

public class WetlandWhimsyFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
		Registries.FEATURE,
		WetlandWhimsy.MODID
	);

	public static RegistryObject<Feature<BlobPatchConfig>> BLOB_PATCH = FEATURES.register(
		"blob_patch", 
		() -> new BlobPatchFeature()
	);
}
