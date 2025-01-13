package uwu.juni.wetland_whimsy.worldgen;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SinglePoolElementAccessor extends SinglePoolElement {
	public SinglePoolElementAccessor(
		Either<ResourceLocation, StructureTemplate> template,
		Holder<StructureProcessorList> processors,
		StructureTemplatePool.Projection projection
	) {
		super(
			template,
			processors,
			projection
		);
	}
}
