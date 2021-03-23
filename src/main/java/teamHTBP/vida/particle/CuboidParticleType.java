package teamHTBP.vida.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class CuboidParticleType extends ParticleType<CuboidParticleData> {
    public CuboidParticleType() {
        super(false, CuboidParticleData.DESERIALIZER);
    }
}
