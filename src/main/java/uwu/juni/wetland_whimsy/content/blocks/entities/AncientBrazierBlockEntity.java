package uwu.juni.wetland_whimsy.content.blocks.entities;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class AncientBrazierBlockEntity extends BlockEntity {
	private AncientBrazier spawner = new AncientBrazier();

	public AncientBrazierBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), pos, state);
	}

	@Override
	public void load(@Nonnull CompoundTag tag) {
		super.load(tag);
		this.spawner.load(level, worldPosition, tag);
	}

	@Override
	protected void saveAdditional(@Nonnull CompoundTag tag) {
		super.saveAdditional(tag);
		this.spawner.save(tag);
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
}
