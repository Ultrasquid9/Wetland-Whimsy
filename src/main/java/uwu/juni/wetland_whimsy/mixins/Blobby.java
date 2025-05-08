package uwu.juni.wetland_whimsy.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.minecraft.world.level.levelgen.feature.DiskFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import uwu.juni.wetland_whimsy.content.features.BlobPatchConfig;
import uwu.juni.wetland_whimsy.content.features.BlobPatchFeature;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyConfig;

@Mixin(DiskFeature.class)
public abstract class Blobby extends Feature<DiskConfiguration> {
	private Blobby() {
		super(null);
	}

	@WrapMethod(method = "place")
	private boolean WetlandWhimsy_Blobby(
		FeaturePlaceContext<DiskConfiguration> context,
		Operation<Boolean> og
	) {
		if (!WetlandWhimsyConfig.valOrDefault(WetlandWhimsyConfig.UPGRADE_DISKS)) {
			return og.call(context);
		}

		var cfg = context.config();
		var rand = context.random();
		var radius = (int)(cfg.radius().sample(rand) * 3);

		// I have no clue how this worked 
		var new_cfg = new BlobPatchConfig(cfg.stateProvider(), cfg.target(), cfg.radius());
		var feature = new BlobPatchFeature();

		return feature.randomRecursiveSphere(
			new_cfg,
			rand,
			context.level(),
			context.origin(),
			feature.calcNewRadius(radius, rand),
			0
		);
	}
}
