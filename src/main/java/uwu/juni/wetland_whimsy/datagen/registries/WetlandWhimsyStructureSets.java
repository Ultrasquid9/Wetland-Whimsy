package uwu.juni.wetland_whimsy.datagen.registries;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyStructureSets {
	public static final ResourceKey<StructureSet> WETLAND_RUINS = createKey("wetland_ruins");
	public static final ResourceKey<StructureSet> WITCH_HUT = createKey("witch_hut");

	private static ResourceKey<StructureSet> createKey(String name) {
		return ResourceKey.create(
			Registries.STRUCTURE_SET, 
			WetlandWhimsy.rLoc(name)
		);
	}

	public static void bootstap(BootstrapContext<StructureSet> context) {
		var structures = context.lookup(Registries.STRUCTURE);

		context.register(
			WETLAND_RUINS, 
			new StructureSet(
				ImmutableList.of(
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.ARCH), 4),
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.PILLAR), 4),
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.WALL), 4),
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.ARENA), 3),
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.SWAMP_DUNGEON), 2)
				),
				new RandomSpreadStructurePlacement(
					10, 
					5, 
					RandomSpreadType.LINEAR, 
					1918171615
				)
			)
		);

		context.register(
			WITCH_HUT, 
			new StructureSet(
				ImmutableList.of(
					StructureSet.entry(structures.getOrThrow(WetlandWhimsyStructures.WITCH_HUT), 1)
				),
				new RandomSpreadStructurePlacement(
					22, 
					10, 
					RandomSpreadType.LINEAR, 
					292827262
				)
			)
		);
	}
}
