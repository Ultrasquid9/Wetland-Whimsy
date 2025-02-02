package uwu.juni.wetland_whimsy.client.particles.muddrip;

import javax.annotation.Nonnull;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class MudDripProvider implements ParticleProvider<SimpleParticleType> {
	private final SpriteSet sprites;

	public MudDripProvider(SpriteSet sprites) {
		this.sprites = sprites;
	}

	@Override
	public Particle createParticle(
		@Nonnull SimpleParticleType type, 
		@Nonnull ClientLevel level, 
		double x, 
		double y, 
		double z,
		double xSpeed, 
		double ySpeed, 
		double zSpeed
	) {
		return new MudDripParticle(level, x, y, z, sprites);
	}
}
