package uwu.juni.wetland_whimsy.client.particles.coloredfire;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ColoredFireParticleType extends ParticleType<ColoredFireParticleOptions> {
	public ColoredFireParticleType(boolean bool) {
		super(bool);
	}

	@Override
	public MapCodec<ColoredFireParticleOptions> codec() {
		return ColoredFireParticleOptions.CODEC;
	}

	@Override
	public StreamCodec<? super RegistryFriendlyByteBuf, ColoredFireParticleOptions> streamCodec() {
		return ColoredFireParticleOptions.STREAM_CODEC;
	}
}
