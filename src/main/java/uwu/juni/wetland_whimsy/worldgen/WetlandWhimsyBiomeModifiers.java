package uwu.juni.wetland_whimsy.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.modifiers.*;

public class WetlandWhimsyBiomeModifiers {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(
		NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, 
		WetlandWhimsy.MODID
	);

	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<BogBiomeModifier>> BOG_ENVIRONMENT = BIOME_MODIFIERS.register(
		"bog_environment", 
		() -> RecordCodecBuilder.mapCodec(instance -> instance
			.group(
				Biome.LIST_CODEC
					.fieldOf("biomes")
					.forGetter(BogBiomeModifier::biomes)
			)
			.apply(instance, BogBiomeModifier::new)
		)
	);
}
