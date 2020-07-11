package teamHTBP.vida.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;


public class ParticleLoader {
    public final static DeferredRegister<ParticleType<?>> PARTICLE = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Vida.modId);
    public static RegistryObject<ParticleType<LeafParticleData>> leafParticle = PARTICLE.register("leafparticle", () -> new LeafParticleType());
    public static RegistryObject<ParticleType<CubeParticleData>> cubeParticle = PARTICLE.register("cubeparticle", () -> new CubeParticleType());
}
