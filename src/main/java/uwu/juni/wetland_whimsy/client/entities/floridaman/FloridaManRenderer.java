package uwu.juni.wetland_whimsy.client.entities.floridaman;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@ParametersAreNonnullByDefault
public class FloridaManRenderer extends ZombieRenderer {
	private static final ResourceLocation RLOC = WetlandWhimsy.rLoc("textures/entity/florida_man.png");

	public FloridaManRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie a) {
		return RLOC;
	}
}
