package teamHTBP.vida.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class CuboidParticleType extends ParticleType<CuboidParticleData> {
    private final Codec<CuboidParticleData> codec = Codec.unit(new CuboidParticleData(0, 0, 0, 0, 0, 0, 0, 0));

    public CuboidParticleType() {
        super(false, CuboidParticleData.DESERIALIZER);
    }

    @Override
    public Codec<CuboidParticleData> codec() {
        return codec;
    }
}
