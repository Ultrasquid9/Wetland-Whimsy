package uwu.juni.wetland_whimsy.mixins;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.tags.StructureTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.ServerLevelAccessor;

@Mixin(Raider.class)
public abstract class WitchSetPersistance extends PatrollingMonster {
	// Java complains if there is no constructor
	private WitchSetPersistance() {
		super(null, null);
	}

	@Inject(
		method = "finalizeSpawn",
		at = @At("HEAD")
	)
	private void WetlandWhimsy_WitchSetPersistance(
 		ServerLevelAccessor level, 
		DifficultyInstance difficulty, 
		MobSpawnType spawnType, 
		@Nullable SpawnGroupData spawnGroupData,
		CallbackInfoReturnable<SpawnGroupData> cir
	) {
		var sm = level.getLevel().structureManager();

		if (
			sm.getStructureWithPieceAt(
				this.blockPosition(), 
				StructureTags.CATS_SPAWN_AS_BLACK
			)
			.isValid()
		) this.setPersistenceRequired();
	}
}
