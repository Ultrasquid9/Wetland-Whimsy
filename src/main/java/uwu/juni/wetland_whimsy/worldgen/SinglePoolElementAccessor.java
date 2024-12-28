package uwu.juni.wetland_whimsy.worldgen;

import java.util.Optional;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SinglePoolElementAccessor extends SinglePoolElement {
	public SinglePoolElementAccessor(
		Either<ResourceLocation, StructureTemplate> template,
		Holder<StructureProcessorList> processors,
		StructureTemplatePool.Projection projection,
		Optional<LiquidSettings> overrideLiquidSettings
	) {
		super(
			template,
			processors,
			projection,
			overrideLiquidSettings
		);
	}
}
