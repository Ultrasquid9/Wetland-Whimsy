package uwu.juni.wetland_whimsy.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class BloodcapSporesParticle extends TextureSheetParticle {
	private final SpriteSet sprites;

	private float x_movement;
	private float z_movement;

	public BloodcapSporesParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
		super(level, x, y, z);
		this.sprites = sprites;

		this.x_movement = (float)random.nextInt(-5, 5) / 50F;
		this.z_movement = (float)random.nextInt(-5, 5) / 50F;

		this.xd = x_movement * 5;
		this.zd = z_movement * 5;

		this.gravity = -0.5F;
		this.quadSize = (float)random.nextInt(1, 2) / 2.5F;
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		if (this.gravity <= 0)
			this.gravity += 0.05F;

		if (this.quadSize > 0)
			this.quadSize -= 0.025;

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
