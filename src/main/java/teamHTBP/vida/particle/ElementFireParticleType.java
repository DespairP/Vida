package teamHTBP.vida.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class ElementFireParticleType extends ParticleType<ElementFireParticleData> {
    Codec<ElementFireParticleData> codec = Codec.unit(new ElementFireParticleData(0, 0, 0));

    public ElementFireParticleType() {
        super(false, ElementFireParticleData.DESERIALIZER);
    }

    @Override
    public Codec<ElementFireParticleData> func_230522_e_() {
        return codec;
    }
}
