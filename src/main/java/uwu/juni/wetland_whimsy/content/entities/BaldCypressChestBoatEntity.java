package uwu.juni.wetland_whimsy.content.entities;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class BaldCypressChestBoatEntity extends ChestBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(
		BaldCypressChestBoatEntity.class, 
		EntityDataSerializers.INT
	);

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
		return switch (getVariant2()) {
			case BALD_CYPRESS -> WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT.get();
		};
	}

	public void setVariant(BaldCypressBoatEntity.Type variant) {
		entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	public BaldCypressBoatEntity.Type getVariant2() {
		return BaldCypressBoatEntity.Type.byId((entityData.get(DATA_ID_TYPE)));
	}

	@Override
	protected void defineSynchedData(@Nonnull Builder builder) {
		builder.define(DATA_ID_TYPE, BaldCypressBoatEntity.Type.BALD_CYPRESS.ordinal());

		super.defineSynchedData(builder);
	}

	@Override
	protected void addAdditionalSaveData(@Nonnull CompoundTag compound) {
		compound.putString("Type", this.getVariant2().getSerializedName());
	}

	@Override
	protected void readAdditionalSaveData(@Nonnull CompoundTag compound) {
		if (compound.contains("Type", 8)) 
			this.setVariant(Type.byName(compound.getString("Type")));
	}
}
