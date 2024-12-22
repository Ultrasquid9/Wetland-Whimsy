package uwu.juni.wetland_whimsy.worldgen;

import java.util.Optional;

import uwu.juni.wetland_whimsy.data.worldgen.WetlandWhimsyConfiguredFeaturesDatagen;
import net.minecraft.world.level.block.grower.TreeGrower;

public class WetlandWhimsyTreeGrowers {
	public static final TreeGrower BALD_CYPRESS = new TreeGrower(
		"bald_cypress",
		Optional.empty(),
		Optional.of(WetlandWhimsyConfiguredFeaturesDatagen.BALD_CYPRESS_TREE),
		Optional.empty()
	);
}
