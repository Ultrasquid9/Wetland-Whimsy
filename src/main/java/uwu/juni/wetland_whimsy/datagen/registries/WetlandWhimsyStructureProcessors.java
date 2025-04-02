package uwu.juni.wetland_whimsy.datagen.registries;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyStructureProcessors {
	public static final ResourceKey<StructureProcessorList> LIMESTONE_RUBBLE = createKey("limestone_rubble");
	public static final ResourceKey<StructureProcessorList> DUNGEON = createKey("dungeon");

	static ResourceKey<StructureProcessorList> createKey(String name) {
		return ResourceKey.create(
			Registries.PROCESSOR_LIST, 
			WetlandWhimsy.rLoc(name)
		);
	}

	public static void bootstap(BootstrapContext<StructureProcessorList> context) {
		context.register(LIMESTONE_RUBBLE, dummy());
		context.register(DUNGEON, dummy());
	}

	static StructureProcessorList dummy() {
		return new StructureProcessorList(
			List.of(new RuleProcessor(List.of()))
		);
	}
}
