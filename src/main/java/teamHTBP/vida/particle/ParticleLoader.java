package teamHTBP.vida.particle;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.particle.util.BaseParticleData;
import teamHTBP.vida.particle.util.BaseParticleType;

/**
 * 注册ParticleType
 * */
public class ParticleLoader {
    public final static DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Vida.MOD_ID);
    public static RegistryObject<ParticleType<BaseParticleData>> leafParticle = PARTICLE.register("leafparticle", BaseParticleType::new);
    public static RegistryObject<ParticleType<CubeParticleData>> cubeParticle = PARTICLE.register("cubeparticle", CubeParticleType::new);
    public static RegistryObject<ParticleType<BaseParticleData>> elementFireParticle = PARTICLE.register("elementfireparticle", BaseParticleType::new);
    public static RegistryObject<ParticleType<CuboidParticleData>> cuboidParticle = PARTICLE.register("cuboidparticle", CuboidParticleType::new);
    public static RegistryObject<ParticleType<BaseParticleData>> particle = PARTICLE.register("cuboid", BaseParticleType::new);
}
