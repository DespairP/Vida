package teamHTBP.vida.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class ElementFireParticleType extends ParticleType<ElementFireParticleData> {
    public ElementFireParticleType() {
        super(false, ElementFireParticleData.DESERIALIZER);
    }
}
