package teamHTBP.vida.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class LeafParticleType extends ParticleType<LeafParticleData> {
    Codec<LeafParticleData> codec = Codec.unit(new LeafParticleData(0, 0, 0));

    public LeafParticleType() {
        super(false, LeafParticleData.DESERIALIZER);

    }

    @Override
    public Codec<LeafParticleData> func_230522_e_() {
        return codec;
    }
}
