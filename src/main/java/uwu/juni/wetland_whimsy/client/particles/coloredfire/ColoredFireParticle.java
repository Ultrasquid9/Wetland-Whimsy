package uwu.juni.wetland_whimsy.client.particles.coloredfire;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;

public class ColoredFireParticle extends FlameParticle {
    public ColoredFireParticle(
		ClientLevel level, 
		ColoredFireParticleOptions options,
		SpriteSet sprites,
		double x, 
		double y, 
		double z, 
		double xSpeed, 
		double ySpeed, 
		double zSpeed
	) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

		this.pickSprite(sprites);
		this.rCol = options.getColor().x;
		this.gCol = options.getColor().y;
		this.bCol = options.getColor().z;
    }

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
}
