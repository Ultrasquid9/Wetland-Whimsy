package uwu.juni.wetland_whimsy.client.particles.muddrip;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class MudDripParticle extends TextureSheetParticle {
	public MudDripParticle(
		ClientLevel level, 
		double x,
		double y,
		double z,
		SpriteSet sprites
	) {
		super(level, x, y, z);

		float size = random.nextInt(2, 4);
		quadSize = size / 16;
		gravity = 0;

		lifetime = 100;
		setSpriteFromAge(sprites);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		if (gravity == 0)
			quadSize += 0.07F;
		else if (onGround)
			quadSize -= 0.1F;
		else 
			quadSize -= 0.02F;

		if (quadSize > (float)random.nextInt(6, 9) / 16)
			gravity = 1;

		if (quadSize <= 0)
			remove();

		super.tick();
	}
}
