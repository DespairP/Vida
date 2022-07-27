package teamHTBP.vida.common.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import teamHTBP.vida.common.particle.option.CuboidParticleOptions;

public class CuboidParticleType extends ParticleType<CuboidParticleOptions> {
    private final Codec<CuboidParticleOptions> codec = Codec.unit(new CuboidParticleOptions(0, 0, 0, 0, 0, 0, 0, 0));

    public CuboidParticleType() {
        super(false, CuboidParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<CuboidParticleOptions> codec() {
        return codec;
    }
}
