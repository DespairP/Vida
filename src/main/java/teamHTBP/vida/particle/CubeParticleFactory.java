package teamHTBP.vida.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CubeParticleFactory implements IParticleFactory<CubeParticleData> {
    private final IAnimatedSprite sprites;
    public CubeParticleFactory(IAnimatedSprite sprite) {
        sprites = sprite;
    }
    @Nullable
    @Override
    public Particle makeParticle(CubeParticleData typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {

        CubeParticle particle;
        if(typeIn.containRGBS())
            particle = new CubeParticle(worldIn, x,y , z, typeIn.getSpeed(0), typeIn.getSpeed(1), typeIn.getSpeed(2),typeIn.getRGBS(0),typeIn.getRGBS(1),typeIn.getRGBS(2), typeIn.getRGBS(3));
        else
            particle = new CubeParticle(worldIn, x,y , z, typeIn.getSpeed(0), typeIn.getSpeed(1), typeIn.getSpeed(2));


        particle.selectSpriteRandomly(sprites);
        return particle;
    }
}
