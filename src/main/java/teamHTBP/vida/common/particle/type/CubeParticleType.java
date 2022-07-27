package teamHTBP.vida.common.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import teamHTBP.vida.common.particle.option.CubeParticleOptions;

public class CubeParticleType extends ParticleType<CubeParticleOptions> {
    private final Codec<CubeParticleOptions> codec = Codec.unit(new CubeParticleOptions(0, 0, 0, 1));

    public CubeParticleType() {
        super(false, CubeParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<CubeParticleOptions> codec() {
        return codec;
    }
}
