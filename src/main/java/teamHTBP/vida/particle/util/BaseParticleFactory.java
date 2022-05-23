package teamHTBP.vida.particle.util;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class BaseParticleFactory implements IParticleFactory<BaseParticleData> {
    private final IAnimatedSprite sprites;
    private final BaseVidaParticleType type;


    public BaseParticleFactory(IAnimatedSprite sprite, BaseVidaParticleType type) {
        sprites = sprite;
        this.type = type;
    }


    @Nullable
    @Override
    public Particle makeParticle(BaseParticleData data, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        try {
            Particle p = type.getParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, data.getR(), data.getG(), data.getB());
            if (p instanceof SpriteTexturedParticle) {
                ((SpriteTexturedParticle) p).selectSpriteRandomly(sprites);
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
