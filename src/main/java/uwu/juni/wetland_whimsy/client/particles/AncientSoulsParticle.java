package uwu.juni.wetland_whimsy.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.util.Mth;

public class AncientSoulsParticle extends TextureSheetParticle {
	private final SpriteSet sprites;

	private float wiggle;
	private boolean wiggle_x;
	private boolean wiggle_y;

	public AncientSoulsParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
		super(level, x, y, z);
		this.sprites = sprites;

		this.gravity = -0.5F;
		this.wiggle = this.random.nextInt(-1, 1);
		this.wiggle_x = this.random.nextBoolean();
		this.wiggle_y = this.random.nextBoolean();
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
		
		if (wiggle_x)
			xd += (Mth.sin(wiggle)) / 32;
		if (wiggle_y)
			zd += (Mth.sin(wiggle)) / 32;
		wiggle += 0.25;

		setSpriteFromAge(sprites);
		super.tick();
	}
}
