package teamHTBP.vida.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class ElementFireParticleFactory implements IParticleFactory<ElementFireParticleData> {
    private final IAnimatedSprite sprites;

    public ElementFireParticleFactory(IAnimatedSprite sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(ElementFireParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        ElementFireParticle particle = new ElementFireParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        particle.selectSpriteRandomly(sprites);
        return particle;
    }
}
