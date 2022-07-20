package teamHTBP.vida.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;

import javax.annotation.Nullable;

public class CubeParticleFactory implements ParticleProvider<CubeParticleData> {
    private final SpriteSet sprites;

    public CubeParticleFactory(SpriteSet sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CubeParticleData typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        CubeParticle particle = new CubeParticle(worldIn,x,y,z,xSpeed,ySpeed,zSpeed,typeIn.getR(),typeIn.getG(),typeIn.getB(),typeIn.getScale());
        particle.pickSprite(sprites);
        return particle;
    }
}
