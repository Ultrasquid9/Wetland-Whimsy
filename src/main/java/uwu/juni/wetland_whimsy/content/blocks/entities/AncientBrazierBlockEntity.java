package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import org.joml.Vector3f;

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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.datapacks.Datapacks;
import uwu.juni.wetland_whimsy.datapacks.Incense;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

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

		var item = strToItem.apply(tag.getString("current_incense"));
		if (item != null && new ItemStack(item).is(WetlandWhimsyTags.Items.INCENSE))
			currentIncense = Optional.of(item);
		else
			currentIncense = Optional.empty();
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
		if (currentIncense.isPresent() || usedIncenses.contains(item.asItem()))
			return false;
		
		setChanged();
		currentIncense = Optional.of(item.asItem());
		if (level instanceof ServerLevel sLevel)
			spawner.setRandomEntity(sLevel, worldPosition);

		return true;
	}

	public boolean hasIncense() {
		return currentIncense.isPresent();
	}


	private static String ERROR_MESSAGE = """
		\n-- What is this wierd creature in my world? --\n

		What you are seeing is `wetland_whimsy:silly`, an entity 
		that was initially created for testing purposes. However, 
		this entity has since then been reused as a default for 
		Incense data files, as it should be pretty clear that when 
		you're seeing it, something has gone wrong.\n

		If you are seeing this message, the most likely cause would
		be that an item was tagged with `wetland_whimsy:incense` 
		without having an incense data file to go along with it. 
		If that json file does exist, it may be invalid - please 
		check it to see if its syntax is correct or if it is in a 
		valid location. \n

		If you are playing a modpack, please report this to the 
		modpack's developer BEFORE reporting it to the Wetland 
		Whimsy team. 
		""";
	public Incense getIncense(ServerLevel level) {
		var registries = level.getServer()
			.registryAccess()
			.registryOrThrow(Datapacks.INCENSE)
			.entrySet();
		
		for (var registry : registries) {
			var incense = registry.getValue();

			if (incense.item() == currentIncense.get())
				return incense;
		}

		if (ERROR_MESSAGE != null)
			WetlandWhimsy.LOGGER.warn(ERROR_MESSAGE);
		ERROR_MESSAGE = null;

		return new Incense(
			Items.DIRT, 
			WetlandWhimsy.rLoc(""),
			new Vector3f(0, 0, 0), 
			List.of(WetlandWhimsy.rLoc("silly"))
		);
	}

	public void killIncense() {
		if (currentIncense.isPresent())
			usedIncenses.add(currentIncense.get());

		currentIncense = Optional.empty();
	}
}
