package teamHTBP.vida.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class CubeParticleFactory implements IParticleFactory<CubeParticleData> {
    private final IAnimatedSprite sprites;

    public CubeParticleFactory(IAnimatedSprite sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CubeParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        CubeParticle particle = new CubeParticle(worldIn,x,y,z,xSpeed,ySpeed,zSpeed,typeIn.getR(),typeIn.getG(),typeIn.getB(),typeIn.getScale());
        particle.pickSprite(sprites);
        return particle;
    }
}
