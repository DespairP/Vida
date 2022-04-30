package teamHTBP.vida.particle.util;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import teamHTBP.vida.particle.LeafParticleData;

/**
 * */
public class BaseParticleType<T extends Particle> extends ParticleType<BaseParticleData> {
    Codec<BaseParticleData> codec = Codec.unit(new BaseParticleData(0, 0, 0,0,0,0,0,0));

    public BaseParticleType() {
       super(false,BaseParticleData.DESERIALIZER);
    }


    @Override
    public Codec<BaseParticleData> func_230522_e_() {
        return codec;
    }
}
