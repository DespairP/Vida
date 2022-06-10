package teamHTBP.vida.particle.util;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.particle.CuboidParticle;
import teamHTBP.vida.particle.ElementFireParticle;
import teamHTBP.vida.particle.LeafParticle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum BaseVidaParticleType {
    CUBOID(CuboidParticle.class, ConstructorType.WITHXYZSPEED),
    LEAF(LeafParticle.class, ConstructorType.WITHXYZSPEED),
    ELEMENT_FIRE(ElementFireParticle.class,ConstructorType.WITHXYZSPEED);

    public Class<?> particleClass;
    public ResourceLocation location;
    public ConstructorType type = ConstructorType.NONE;

    BaseVidaParticleType(Class<?> particleClass, ConstructorType type) {
        this.particleClass = particleClass;
        this.type = type;
    }

    BaseVidaParticleType(Class<?> particleClass, ResourceLocation location, ConstructorType type) {
        this.particleClass = particleClass;
        this.location = location;
        this.type = type;
    }

    public Particle getParticle(ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int r, int g, int b) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        assert particleClass != null;
        Constructor<?> c = null;
        switch (type) {
            case WITHXYZSPEED:
                // world, x, y, z, xSpeed, ySpeed, zSpeed
                c = particleClass.getConstructor(ClientWorld.class, double.class, double.class, double.class, double.class, double.class, double.class);
                return (Particle) c.newInstance(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            case WITHALL:
                // world, x, y, z, xSpeed, ySpeed, zSpeed, r, g, b
                c = particleClass.getConstructor(ClientWorld.class, double.class, double.class, double.class, double.class, double.class, double.class, int.class, int.class, int.class);
                return (Particle) c.newInstance(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, r % 256,  g % 256, b % 256);
            default:
                throw new NoSuchMethodException();
        }
    }



    enum ConstructorType {
        NONE, WITHXYZ, WITHXYZSPEED, WITHALL;
    }
}
