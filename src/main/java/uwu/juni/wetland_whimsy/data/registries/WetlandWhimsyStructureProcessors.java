package uwu.juni.wetland_whimsy.data.registries;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyStructureProcessors {
	public static final ResourceKey<StructureProcessorList> LIMESTONE_RUBBLE = createKey("limestone_rubble");
	public static final ResourceKey<StructureProcessorList> DUNGEON = createKey("dungeon");

	private static ResourceKey<StructureProcessorList> createKey(String name) {
		return ResourceKey.create(
			Registries.PROCESSOR_LIST, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}

	public static void bootstap(BootstrapContext<StructureProcessorList> context) {
		context.register(
			LIMESTONE_RUBBLE, 
			new StructureProcessorList(
				ImmutableList.of(
					new RuleProcessor(ImmutableList.of())
				)
			)
		);
		context.register(
			DUNGEON, 
			new StructureProcessorList(
				ImmutableList.of(
					new RuleProcessor(ImmutableList.of())
				)
			)
		);
	}
}
