package teamHTBP.vida.common.particle.type.base;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleType;
import teamHTBP.vida.common.particle.option.base.BaseParticleOptions;

/**
 * */
public class BaseParticleType<T extends Particle> extends ParticleType<BaseParticleOptions> {
    Codec<BaseParticleOptions> codec = Codec.unit(new BaseParticleOptions(null,0, 0, 0,0,0));

    public BaseParticleType() {
       super(false, BaseParticleOptions.DESERIALIZER);
    }


    @Override
    public Codec<BaseParticleOptions> codec() {
        return codec;
    }
}
