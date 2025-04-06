package uwu.juni.wetland_whimsy.client.particles.bloodcapspores;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class BloodcapSporesParticle extends TextureSheetParticle {
	private float x_movement;
	private float z_movement;

	public BloodcapSporesParticle(
		ClientLevel level, 
		double x, 
		double y, 
		double z, 
		SpriteSet sprites
	) {
		super(level, x, y, z);

		x_movement = (float)random.nextInt(-5, 5) / 50F;
		z_movement = (float)random.nextInt(-5, 5) / 50F;

		xd = x_movement * 5;
		zd = z_movement * 5;

		gravity = -0.5F;
		quadSize = (float)random.nextInt(1, 2) / 2.5F;
		setSpriteFromAge(sprites);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		if (gravity <= 0)
			gravity += 0.05F;

		if (quadSize > 0)
			quadSize -= 0.025;

		xd -= x_movement;
		zd -= z_movement;

		if (random.nextInt(5) == 0 || Math.abs(xd) >= 0.20 ) {
			x_movement *= -1;
			xd *= xd > 0 ? 0.15 : -0.15;
		}
		if (random.nextInt(5) == 0 || Math.abs(zd) >= 0.20 ) {
			z_movement *= -1;
			zd *= zd > 0 ? 0.15 : -0.15;
		} 

		super.tick();
	}
}
