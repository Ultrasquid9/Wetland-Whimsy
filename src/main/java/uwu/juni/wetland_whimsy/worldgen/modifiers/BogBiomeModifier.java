package uwu.juni.wetland_whimsy.worldgen.modifiers;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;

public record BogBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {

	@SuppressWarnings("null")
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		if (!biomes.contains(biome) || !phase.equals(Phase.BEFORE_EVERYTHING)) 
			return;

		builder.getSpecialEffects()
			//.fogColor(0xABD2FF)
			//.skyColor(0x78A7FF)
			.waterColor(0x4C6559)
			.waterFogColor(0x4C6559)
			.grassColorOverride(0x527e40)
			.foliageColorOverride(0x5F6F35)
			.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE);
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return WetlandWhimsyBiomeModifiers.BOG_ENVIRONMENT.value();
	}
}
