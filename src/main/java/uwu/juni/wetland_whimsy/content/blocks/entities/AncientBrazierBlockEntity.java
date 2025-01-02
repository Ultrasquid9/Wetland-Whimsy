package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.datafixers.util.Either;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;

public class AncientBrazierBlockEntity extends BlockEntity implements Spawner {
    private final BaseSpawner spawner = new BaseSpawner() {
        @SuppressWarnings("null")
		@Override
        public void broadcastEvent(Level level, BlockPos pos, int id) {
            level.blockEvent(pos, WetlandWhimsyBlocks.ANCIENT_BRAZIER.get(), id, 0);
        }

        @SuppressWarnings("null")
		@Override
        public void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData data) {
            super.setNextSpawnData(level, pos, data);
            if (level != null) {
                var blockstate = level.getBlockState(pos);
                level.sendBlockUpdated(pos, blockstate, blockstate, 4);
            }
        }

        @Override
        public Either<BlockEntity, Entity> getOwner() {
            return Either.left(AncientBrazierBlockEntity.this);
        }
    };

	public AncientBrazierBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), pos, state);
	}

	public BaseSpawner getSpawner() { return this.spawner; }

	@SuppressWarnings("null")
	@Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.spawner.load(this.level, this.worldPosition, tag);
    }

    @SuppressWarnings("null")
	@Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        this.spawner.save(tag);
    }

	@SuppressWarnings("null")
	@Override
	public void setEntityId(EntityType<?> type, RandomSource random) {
		this.spawner.setEntityId(type, this.level, random, this.worldPosition);
		this.setChanged();
	}

	@SuppressWarnings("null")
	@Override
	public boolean triggerEvent(int id, int type) {
		return this.spawner.onEventTriggered(this.level, id) ? true : super.triggerEvent(id, type);
	}

	@Override
    public CompoundTag getUpdateTag(@Nonnull HolderLookup.Provider registries) {
        CompoundTag compoundtag = this.saveCustomOnly(registries);
        compoundtag.remove("SpawnPotentials");
        return compoundtag;
    }

	public static void clientTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.clientTick(level, pos);
	}
	public static void serverTick(Level level, BlockPos pos, BlockState state, AncientBrazierBlockEntity blockEntity) {
		blockEntity.spawner.serverTick((ServerLevel)level, pos);
	}
}
