package teamHTBP.vida.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class LeafParticleFactory implements IParticleFactory<LeafParticleData> {
    private final IAnimatedSprite sprites;

    public LeafParticleFactory(IAnimatedSprite sprite) {
        sprites = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(LeafParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        LeafParticle particle = new LeafParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        particle.selectSpriteRandomly(sprites);
        return particle;
    }
}
