package teamHTBP.vida.common.particle.provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import teamHTBP.vida.common.particle.option.CuboidParticleOptions;
import teamHTBP.vida.common.particle.particle.CuboidParticle;

import javax.annotation.Nullable;

public class CuboidParticleProvider implements ParticleProvider<CuboidParticleOptions> {
    private final SpriteSet sprites;

    public CuboidParticleProvider(SpriteSet sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CuboidParticleOptions typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        CuboidParticle particle = new CuboidParticle(worldIn, x, y, z,
                typeIn.getInformation(0),
                typeIn.getInformation(1),
                typeIn.getInformation(2),
                (float) typeIn.getInformation(3),
                (float) typeIn.getInformation(4),
                (float) typeIn.getInformation(5),
                (float) typeIn.getInformation(6),
                (int) typeIn.getInformation(7)
        );
        particle.pickSprite(sprites);
        return particle;
    }
}
