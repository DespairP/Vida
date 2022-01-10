package teamHTBP.vida.particle;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class ParticleLoader {
    public final static DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Vida.MOD_ID);
    public static RegistryObject<ParticleType<LeafParticleData>> leafParticle = PARTICLE.register("leafparticle", () -> new LeafParticleType());
    public static RegistryObject<ParticleType<CubeParticleData>> cubeParticle = PARTICLE.register("cubeparticle", () -> new CubeParticleType());
    public static RegistryObject<ParticleType<ElementFireParticleData>> elementFireParticle = PARTICLE.register("elementfireparticle", () -> new ElementFireParticleType());
    public static RegistryObject<ParticleType<CuboidParticleData>> cuboidParticle = PARTICLE.register("cuboidparticle", () -> new CuboidParticleType());

}
