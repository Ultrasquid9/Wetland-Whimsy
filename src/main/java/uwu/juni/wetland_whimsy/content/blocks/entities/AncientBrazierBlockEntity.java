package uwu.juni.wetland_whimsy.content.blocks.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

@ParametersAreNonnullByDefault
public class AncientBrazierBlockEntity extends BlockEntity implements Spawner {
	AncientBrazierSpawner spawner = new AncientBrazierSpawner() {
		public Either<BlockEntity,Entity> getOwner() {
			return Either.left(AncientBrazierBlockEntity.this);
		};
	};

	protected Optional<Item> incense = Optional.empty();
	protected List<Item> usedIncenses = new ArrayList<>();

	public AncientBrazierBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), pos, state);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, Provider registries) {
		super.loadAdditional(tag, registries);
		spawner.load(level, worldPosition, tag);

		var list = tag.getList("used_incenses", Tag.TAG_STRING);
		if (list != null) {
			for (var str : list) {
				var item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(str.getAsString()));
				usedIncenses.add(item);
			}
		}

		var str = tag.getString("incense");
		if (str == null) {
			return;
		}

		var incenseKey = ResourceKey.create(Registries.ITEM, ResourceLocation.parse(tag.getString("incense")));
		this.incense = Optional.of(BuiltInRegistries.ITEM.get(incenseKey));
	}

	@Override
	protected void saveAdditional(CompoundTag tag, Provider registries) {
		super.saveAdditional(tag, registries);
		spawner.save(tag);

		var list = new ListTag();
		for (var item : usedIncenses) {
			list.add(StringTag.valueOf(item.toString()));
		}

		if (!list.isEmpty()) {
			tag.put("used_incenses", list);
		}

		if (this.incense.isPresent()) {
			tag.putString("incense", this.incense.get().toString());
		}
	}

	@Override
	public void setEntityId(EntityType<?> type, RandomSource random) {
		spawner.setEntityId(type, level, random, worldPosition);
		setChanged();
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		return level == null 
			? false
			: spawner.onEventTriggered(level, id) 
				? true 
				: super.triggerEvent(id, type);
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.clientTick(level, pos);
	}
	public static void serverTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.serverTick((ServerLevel)level, pos);
	}

	public boolean trySetIncense(ItemLike itemlike) {
		var item = itemlike.asItem();

		if (
			this.incense.isPresent()
			|| usedIncenses.contains(item)
			|| !new ItemStack(item).is(WetlandWhimsyTags.Items.INCENSE)
		) {
			return false;
		}

		this.incense = Optional.of(item.asItem());
		this.usedIncenses.add(item);
		setChanged();

		if (this.level instanceof ServerLevel sLevel) {
			spawner.setRandomEntity(sLevel, worldPosition);
		}

		return true;
	}

	public boolean hasIncense() {
		return incense.isPresent();
	}

	public Optional<Incense> getIncense(ServerLevel level) {
		return this.incense.flatMap(item -> {
			var entries = level
				.registryAccess()
				.registryOrThrow(Datapacks.INCENSE)
				.entrySet();
			
			for (var entry : entries) {
				if (entry.getValue().item() == item) {
					return Optional.of(entry.getValue());
				}
			}

			WetlandWhimsy.LOGGER.error("Incense " + item + " at " + this.getBlockPos() + " lacks an incense file");
			return Optional.empty();
		});
	}

	public void killIncense() {
		this.incense = Optional.empty();
	}
}
