/* package uwu.juni.wetland_whimsy.content.blocks.entities;

import net.minecraft.references.Blocks;
import net.minecraft.world.level.BaseSpawner;
import net.neoforged.neoforge.common.extensions.IOwnedSpawner;

public class AncientBrazier extends BaseSpawner implements IOwnedSpawner {
	@SuppressWarnings("null")
	@Override
	public void broadcastEvent(Level level, BlockPos pos, int id) {
		level.blockEvent(pos, Blocks.SPAWNER, id, 0);
	}

	@SuppressWarnings("null")
	@Override
	public void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData data) {
		super.setNextSpawnData(level, pos, data);
		if (level != null) {
			BlockState blockstate = level.getBlockState(pos);
			level.sendBlockUpdated(pos, blockstate, blockstate, 4);
		}
	}

	@Override
	public com.mojang.datafixers.util.Either<net.minecraft.world.level.block.entity.BlockEntity, net.minecraft.world.entity.Entity> getOwner() {
		return com.mojang.datafixers.util.Either.left(AncientBrazierBlockEntity.this);
	}
}
 */