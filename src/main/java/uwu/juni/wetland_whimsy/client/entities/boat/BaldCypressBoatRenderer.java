package uwu.juni.wetland_whimsy.client.entities.boat;

import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableMap;
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
import uwu.juni.wetland_whimsy.content.entities.BaldCypressBoatEntity;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressChestBoatEntity;

public class BaldCypressBoatRenderer extends BoatRenderer {
	private final Map<BaldCypressBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

	public BaldCypressBoatRenderer(
		EntityRendererProvider.Context context,
		boolean isChestBoat 
	) {
		super(context, isChestBoat);

		var builder = ImmutableMap.<BaldCypressBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>>builder();
		for (var type : BaldCypressBoatEntity.Type.values()) {
			builder.put(
				type, 
				Pair.of(getTextureLocation(type, isChestBoat), createBoatModel(context, type, isChestBoat))
			);
		}
		boatResources = builder.build();
	}

	private static ResourceLocation getTextureLocation(BaldCypressBoatEntity.Type type, boolean chestBoat) {
		return chestBoat
			? WetlandWhimsy.rLoc("textures/entity/chest_boat/" + type.getName() + ".png")
			: WetlandWhimsy.rLoc("textures/entity/boat/" + type.getName() + ".png");
	}

	private ListModel<Boat> createBoatModel(
		EntityRendererProvider.Context context, 
		BaldCypressBoatEntity.Type type, 
		boolean chestBoat
	) {
		var rloc = WetlandWhimsy.rLoc(type.getName());
		var modellayerlocation = chestBoat 
			? new ModelLayerLocation(rloc.withPrefix("chest_boat/"), "main")
			: new ModelLayerLocation(rloc.withPrefix("boat/"), "main");

		var modelpart = context.bakeLayer(modellayerlocation);

		return (ListModel<Boat>)(chestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart));
	}

	@Override
	public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@Nonnull Boat boat) {
		if (boat instanceof BaldCypressBoatEntity entity)
			return this.boatResources.get(entity.getVariant2());

		if (boat instanceof BaldCypressChestBoatEntity entity)
			return this.boatResources.get(entity.getVariant2());

		return null;
	}
}
