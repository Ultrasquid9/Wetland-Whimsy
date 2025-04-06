package uwu.juni.wetland_whimsy.content.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class BaldCypressBoatEntity extends Boat {
	public BaldCypressBoatEntity(
		EntityType<? extends BaldCypressBoatEntity> type,
		Level level
	) {
		super(type, level);
	}

	public BaldCypressBoatEntity(
		Level level, 
		double x, 
		double y, 
		double z
	) {
		this(WetlandWhimsyEntityTypes.BALD_CYPRESS_BOAT.get(), level);
		setPos(x, y, z);
		xo = x;
		yo = y;
		zo = z;
	}

	@Override
	public Item getDropItem() {
		return WetlandWhimsyItems.BALD_CYPRESS_BOAT.get();
	}
}
