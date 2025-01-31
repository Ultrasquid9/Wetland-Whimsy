package uwu.juni.wetland_whimsy.client.particles.coloredfire;

import javax.annotation.Nonnull;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;

public class ColoredFireProvider implements ParticleProvider<ColoredFireParticleOptions> {
	private final SpriteSet sprites;

	public ColoredFireProvider(SpriteSet sprites) {
		this.sprites = sprites;
	}

	@Override
	public Particle createParticle(
		@Nonnull ColoredFireParticleOptions options, 
		@Nonnull ClientLevel level, 
		double x, 
		double y, 
		double z,
		double xSpeed, 
		double ySpeed, 
		double zSpeed
	) {
		return new ColoredFireParticle(
			level, 
			options, 
			sprites, 
			x, 
			y, 
			z, 
			xSpeed, 
			ySpeed, 
			zSpeed
		);
	}
}
