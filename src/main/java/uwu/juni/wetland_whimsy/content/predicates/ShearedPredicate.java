package uwu.juni.wetland_whimsy.content.predicates;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

public record ShearedPredicate(Boolean bool) implements EntitySubPredicate {
	public static final MapCodec<ShearedPredicate> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
			Codec.BOOL.fieldOf("bool").forGetter(ShearedPredicate::bool)
		)
		.apply(instance, ShearedPredicate::new)
    );

	@Override
	public MapCodec<? extends EntitySubPredicate> codec() {
		return CODEC;
	}

	@Override
	public boolean matches(
		@Nonnull Entity entity, 
		@Nonnull ServerLevel level, 
		@Nullable Vec3 position
	) {
		if (entity instanceof SwampSpiderEntity spidey) {
			return spidey.isSheared() == bool();
		}
		
		return false;
	}
}
