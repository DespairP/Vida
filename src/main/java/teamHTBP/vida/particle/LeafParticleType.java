package teamHTBP.vida.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleType;

public class LeafParticleType extends ParticleType<LeafParticleData> {
  public LeafParticleType(){
      super(false,LeafParticleData.DESERIALIZER);

  }


}
