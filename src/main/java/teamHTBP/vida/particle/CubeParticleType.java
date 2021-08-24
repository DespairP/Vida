package teamHTBP.vida.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class CubeParticleType extends ParticleType<CubeParticleData> {
    private final Codec<CubeParticleData> codec = Codec.unit(new CubeParticleData(0, 0, 0));

    public CubeParticleType() {
        super(false, CubeParticleData.DESERIALIZER);
    }

    @Override
    public Codec<CubeParticleData> func_230522_e_() {
        return codec;
    }
}
