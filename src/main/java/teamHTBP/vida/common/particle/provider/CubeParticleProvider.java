package teamHTBP.vida.common.particle.provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import teamHTBP.vida.common.particle.option.CubeParticleOptions;
import teamHTBP.vida.common.particle.particle.CubeParticle;

import javax.annotation.Nullable;

public class CubeParticleProvider implements ParticleProvider<CubeParticleOptions> {
    private final SpriteSet sprites;

    public CubeParticleProvider(SpriteSet sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle createParticle(CubeParticleOptions typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        CubeParticle particle = new CubeParticle(worldIn,x,y,z,xSpeed,ySpeed,zSpeed,typeIn.getR(),typeIn.getG(),typeIn.getB(),typeIn.getScale());
        particle.pickSprite(sprites);
        return particle;
    }
}
