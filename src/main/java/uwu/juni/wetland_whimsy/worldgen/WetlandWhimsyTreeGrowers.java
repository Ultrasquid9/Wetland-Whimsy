package uwu.juni.wetland_whimsy.worldgen;

import java.util.Optional;

import net.minecraft.world.level.block.grower.TreeGrower;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyConfiguredFeatures;

public class WetlandWhimsyTreeGrowers {
	public static final TreeGrower BALD_CYPRESS = new TreeGrower(
		"bald_cypress",
		Optional.empty(),
		Optional.of(WetlandWhimsyConfiguredFeatures.BALD_CYPRESS_TREE),
		Optional.empty()
	);
}
