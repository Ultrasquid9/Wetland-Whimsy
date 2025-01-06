package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
		BuiltInRegistries.PARTICLE_TYPE, 
		WetlandWhimsy.MODID
	);

	public static final Supplier<SimpleParticleType> ANCIENT_SOULS = PARTICLE_TYPES.register(
		"ancient_souls",
		() -> new SimpleParticleType(true)
	);
}
