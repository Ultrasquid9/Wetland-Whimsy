package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class AncientBrazierBlockEntity extends BlockEntity implements Spawner {
	private AncientBrazierSpawner spawner = new AncientBrazierSpawner() {
		public Either<BlockEntity,Entity> getOwner() {
			return Either.left(AncientBrazierBlockEntity.this);
		};
	};

	private Optional<Item> currentIncense;
	private List<Item> usedIncenses;

	public AncientBrazierBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), pos, state);

		currentIncense = Optional.empty();
		usedIncenses = new ArrayList<>();
	}

	@Override
	protected void loadAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.loadAdditional(tag, registries);
		this.spawner.load(this.level, this.worldPosition, tag);

		Function<String, Item> strToItem = str -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(str));

		var list = tag.getList("used_incenses", Tag.TAG_STRING);
		if (list != null)
			for (var str : list)
				usedIncenses.add(strToItem.apply(str.getAsString()));

		currentIncense = Optional.ofNullable(strToItem.apply(tag.getString("current_incense")));
	}

	@Override
	protected void saveAdditional(@Nonnull CompoundTag tag, @Nonnull Provider registries) {
		super.saveAdditional(tag, registries);
		this.spawner.save(tag);

		var list = new ListTag();
		for (var item : usedIncenses) 
			list.add(StringTag.valueOf(item.toString()));

		if (!list.isEmpty())
			tag.put("used_incenses", list);
	
		if (currentIncense.isPresent())
			tag.putString("current_incense", currentIncense.get().toString());
	}

	@Override
	public void setEntityId(@Nonnull EntityType<?> type, @Nonnull RandomSource random) {
		this.spawner.setEntityId(type, this.level, random, this.worldPosition);
		this.setChanged();
	}

	@SuppressWarnings("null")
	@Override
	public boolean triggerEvent(int id, int type) {
		return this.spawner.onEventTriggered(this.level, id) 
			? true 
			: super.triggerEvent(id, type);
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.clientTick(level, pos);
	}
	public static void serverTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.serverTick((ServerLevel)level, pos);
	}

	public boolean trySetIncense(ItemLike item) {
		setChanged();

		if (currentIncense.isPresent())
			if (item.asItem().equals(currentIncense.get()))
				return false;
			else 
				usedIncenses.add(currentIncense.get());

		if (usedIncenses.contains(item.asItem()))
			return false;
		
		currentIncense = Optional.of(item.asItem());
		return true;
	}
}
