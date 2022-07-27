package teamHTBP.vida.common.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.particle.option.CubeParticleOptions;
import teamHTBP.vida.common.particle.option.CuboidParticleOptions;
import teamHTBP.vida.common.particle.option.base.BaseParticleOptions;
import teamHTBP.vida.common.particle.type.base.BaseParticleType;

/**
 * 注册ParticleType
 * */
public class VidaParticleTypeLoader {
    public final static DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Vida.MOD_ID);
    public static RegistryObject<ParticleType<BaseParticleOptions>> leafParticle = PARTICLE.register("leafparticle", BaseParticleType::new);
    public static RegistryObject<ParticleType<CubeParticleOptions>> cubeParticle = PARTICLE.register("cubeparticle", CubeParticleType::new);
    public static RegistryObject<ParticleType<BaseParticleOptions>> elementFireParticle = PARTICLE.register("elementfireparticle", BaseParticleType::new);
    public static RegistryObject<ParticleType<CuboidParticleOptions>> cuboidParticle = PARTICLE.register("cuboidparticle", CuboidParticleType::new);
    public static RegistryObject<ParticleType<BaseParticleOptions>> particle = PARTICLE.register("cuboid", BaseParticleType::new);
}
