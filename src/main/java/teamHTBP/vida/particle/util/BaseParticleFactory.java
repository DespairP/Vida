package teamHTBP.vida.particle.util;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class BaseParticleFactory implements IParticleFactory<BaseParticleData> {
    private final IAnimatedSprite sprites;
    private final EnumVidaParticleType type;


    public BaseParticleFactory(IAnimatedSprite sprite, EnumVidaParticleType type) {
        sprites = sprite;
        this.type = type;
    }


    @Nullable
    @Override
    public Particle makeParticle(BaseParticleData data, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        try {
            Particle p = type.getParticle(worldIn, x, y + 1, z, data.getSpeedX(), data.getSpeedY(), data.getSpeedZ() , data.getR(), data.getG(), data.getB());
            if (p instanceof SpriteTexturedParticle)
                ((SpriteTexturedParticle) p).selectSpriteRandomly(sprites);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
