package uwu.juni.wetland_whimsy.worldgen.modifiers;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;

public record BogEnvironmentBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {
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
	public Codec<? extends BiomeModifier> codec() {
		return WetlandWhimsyBiomeModifiers.BOG_ENVIRONMENT.get();
	}
}
