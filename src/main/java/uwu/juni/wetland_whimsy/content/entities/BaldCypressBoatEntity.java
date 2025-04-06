package uwu.juni.wetland_whimsy.content.entities;

import java.util.function.IntFunction;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class BaldCypressBoatEntity extends Boat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(
		BaldCypressBoatEntity.class, 
		EntityDataSerializers.INT
	);

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

	public void setVariant(Type variant) {
		entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	public Type getVariant2() {
		return Type.byId((entityData.get(DATA_ID_TYPE)));
	}

	@Override
	protected void defineSynchedData(@Nonnull Builder builder) {
		builder.define(DATA_ID_TYPE, Type.BALD_CYPRESS.ordinal());

		super.defineSynchedData(builder);
	}

	@Override
	protected void addAdditionalSaveData(@Nonnull CompoundTag compound) {
		compound.putString("Type", getVariant2().getSerializedName());
	}

	@Override
	protected void readAdditionalSaveData(@Nonnull CompoundTag compound) {
		if (compound.contains("Type", 8)) 
			setVariant(Type.byName(compound.getString("Type")));
	}

	public static enum Type implements StringRepresentable {
		BALD_CYPRESS(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get(), "bald_cypress");

		public static final StringRepresentable.StringRepresentableCodec<BaldCypressBoatEntity.Type> CODEC = StringRepresentable.fromEnum(
			BaldCypressBoatEntity.Type::values
		);
		static final IntFunction<BaldCypressBoatEntity.Type> BY_ID = ByIdMap.continuous(
			Enum::ordinal, 
			values(), 
			ByIdMap.OutOfBoundsStrategy.ZERO
		);

		final String name;
		final Block planks;

		Type(Block planks, String name) {
			this.name = name;
			this.planks = planks;
		}

		@Override
		public String getSerializedName() {
			return name;
		}

		public String getName() {
			return name;
		}

		public Block getPlanks() {
			return planks;
		}

		public Item getSticks() {
			return Items.STICK;
		}

		@Override
		public String toString() {
			return name;
		}

		public static Type byName(String str) {
			for (var val : values())
				if (val.name() == str)
					return val;

			return null;
		}

		public static Type byId(int id) {
			return BY_ID.apply(id);
		}
	}
}
