package uwu.juni.wetland_whimsy.client.entities.boat;

import javax.annotation.Nonnull;

import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class BaldCypressBoatRenderer extends BoatRenderer {
	private final String name = "bald_cypress";
	private final Pair<ResourceLocation, ListModel<Boat>> resources;

	public BaldCypressBoatRenderer(
		EntityRendererProvider.Context context,
		boolean isChestBoat 
	) {
		super(context, isChestBoat);

		resources = Pair.of(
			getTextureLocation(isChestBoat), 
			createBoatModel(context, isChestBoat)
		);
	}

	private ResourceLocation getTextureLocation(boolean chestBoat) {
		return chestBoat
			? WetlandWhimsy.rLoc("textures/entity/chest_boat/" + name + ".png")
			: WetlandWhimsy.rLoc("textures/entity/boat/" + name + ".png");
	}

	private ListModel<Boat> createBoatModel(
		EntityRendererProvider.Context context, 
		boolean chestBoat
	) {
		var rloc = WetlandWhimsy.rLoc(name);
		var modellocation = chestBoat 
			? new ModelLayerLocation(rloc.withPrefix("chest_boat/"), "main")
			: new ModelLayerLocation(rloc.withPrefix("boat/"), "main");

		var modelpart = context.bakeLayer(modellocation);
		return chestBoat 
			? new ChestBoatModel(modelpart) 
			: new BoatModel(modelpart);
	}

	@Override
	public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@Nonnull Boat boat) {
		return resources;
	}
}
