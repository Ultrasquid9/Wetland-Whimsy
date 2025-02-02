package uwu.juni.wetland_whimsy.client.particles.muddrip;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class MudDripParticle extends TextureSheetParticle {
	private final SpriteSet sprites;

	public MudDripParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
		super(level, x, y, z);
		this.sprites = sprites;

		this.gravity = 0;
		this.lifetime = 100;

		float size = random.nextInt(2, 4);
		this.quadSize = size / 10;

		setSpriteFromAge(this.sprites);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		if (gravity == 0)
			quadSize += 0.075F;
		else if (this.onGround)
			quadSize -= 0.125F;
		else 
			quadSize -= 0.025F;

		if (quadSize > (float)random.nextInt(6, 9) / 10)
			gravity = 1;

		if (quadSize <= 0)
			lifetime = 0;

		super.tick();
	}
}
