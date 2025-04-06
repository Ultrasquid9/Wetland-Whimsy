package uwu.juni.wetland_whimsy.content.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class BaldCypressChestBoatEntity extends ChestBoat {
	public BaldCypressChestBoatEntity(
		EntityType<? extends BaldCypressChestBoatEntity> type,
		Level level
	) {
		super(type, level);
	}
	
	public BaldCypressChestBoatEntity(
		Level level, 
		double x, 
		double y, 
		double z
	) {
		this(WetlandWhimsyEntityTypes.BALD_CYPRESS_CHEST_BOAT.get(), level);
		setPos(x, y, z);
		xo = x;
		yo = y;
		zo = z;
	}

	@Override
	public Item getDropItem() {
		return WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT.get();
	}
}
