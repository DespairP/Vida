package teamHTBP.vida.common.particle.provider.base;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import teamHTBP.vida.common.particle.option.base.BaseParticleOptions;
import teamHTBP.vida.common.particle.type.base.BaseVidaParticleType;

import javax.annotation.Nullable;

public class BaseParticleProvider implements ParticleProvider<BaseParticleOptions> {
    private final SpriteSet sprites;
    private final BaseVidaParticleType type;


    public BaseParticleProvider(SpriteSet sprite, BaseVidaParticleType type) {
        sprites = sprite;
        this.type = type;
    }


    @Nullable
    @Override
    public Particle createParticle(BaseParticleOptions data, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        try {
            Particle p = type.getParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, data.getR(), data.getG(), data.getB());
            if (p instanceof TextureSheetParticle) {
                ((TextureSheetParticle) p).pickSprite(sprites);
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
