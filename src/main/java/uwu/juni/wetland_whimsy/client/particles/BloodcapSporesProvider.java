package uwu.juni.wetland_whimsy.client.particles;

import javax.annotation.Nonnull;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class BloodcapSporesProvider implements ParticleProvider<SimpleParticleType> {
	private final SpriteSet sprites;

	public BloodcapSporesProvider(SpriteSet sprites) {
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
		return new BloodcapSporesParticle(level, x, y, z, sprites);
	}
}
