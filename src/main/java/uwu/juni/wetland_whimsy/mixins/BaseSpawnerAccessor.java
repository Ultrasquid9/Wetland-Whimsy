package uwu.juni.wetland_whimsy.mixins;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;

@Mixin(BaseSpawner.class)
public interface BaseSpawnerAccessor {
	@Accessor("spawnDelay")
	public int getSpawnDelay();

	@Accessor("spawnDelay")
	public void setSpawnDelay(int baseDelay);
	
	@Invoker("getOrCreateNextSpawnData")
	public SpawnData invokeGetOrCreateNextSpawnData(@Nullable Level p_254503_, RandomSource p_253892_, BlockPos p_254487_);
} 
