package teamHTBP.vida.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class CubeParticleType extends ParticleType<CubeParticleData> {
    public CubeParticleType() {
        super(false, CubeParticleData.DESERIALIZER);
    }
}
