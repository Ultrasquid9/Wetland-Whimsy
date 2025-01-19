package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = WetlandWhimsy.registry(
		Registries.PARTICLE_TYPE
	);

	public static final Supplier<SimpleParticleType> ANCIENT_SOULS = PARTICLE_TYPES.register(
		"ancient_souls",
		() -> new SimpleParticleType(true)
	);
	public static final Supplier<SimpleParticleType> BLOODCAP_SPORES = PARTICLE_TYPES.register(
		"bloodcap_spores",
		() -> new SimpleParticleType(false)
	);
}
