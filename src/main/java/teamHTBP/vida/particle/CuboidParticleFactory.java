package teamHTBP.vida.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class CuboidParticleFactory implements IParticleFactory<CuboidParticleData> {
    private final IAnimatedSprite sprites;

    public CuboidParticleFactory(IAnimatedSprite sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CuboidParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
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
