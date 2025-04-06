package uwu.juni.wetland_whimsy.client.particles.coloredfire;

import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

public class ColoredFireParticleOptions extends ScalableParticleOptionsBase {
	public static final MapCodec<ColoredFireParticleOptions> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(ColoredFireParticleOptions::getColor),
			SCALE.fieldOf("scale").forGetter(ScalableParticleOptionsBase::getScale)
		)
		.apply(instance, ColoredFireParticleOptions::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, ColoredFireParticleOptions> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.VECTOR3F, 
		ColoredFireParticleOptions::getColor,

		ByteBufCodecs.FLOAT, 
		ScalableParticleOptionsBase::getScale,

		ColoredFireParticleOptions::new
	);

	private final Vector3f color;

	public ColoredFireParticleOptions(Vector3f color, float scale) {
		super(scale);
		this.color = color;
	}

	@Override
	public ParticleType<ColoredFireParticleOptions> getType() {
		return WetlandWhimsyParticleTypes.COLORED_FIRE.get();
	}

	public Vector3f getColor() {
		return color;
	}
}
