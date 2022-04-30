package teamHTBP.vida.particle.util;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import teamHTBP.vida.particle.CuboidParticle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumVidaParticleType {
    CUBOID(CuboidParticle.class,ConstructorType.WITHXYZSPEED);

    public Class<?> particleClass;
    public ConstructorType type = ConstructorType.NONE;
    EnumVidaParticleType(Class<?> particleClass,ConstructorType type){
        this.particleClass = particleClass;
        this.type = type;
    }

    public Particle getParticle(ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        assert particleClass != null;
        Constructor<?> c = null;
        switch (type){
            case WITHXYZSPEED:
                c = particleClass.getConstructor(ClientWorld.class,double.class,double.class,double.class,double.class,double.class,double.class);
                break;
            default:
                throw new NoSuchMethodException();
        }
        return (Particle) c.newInstance(worldIn,x,y,z,xSpeed,ySpeed,zSpeed);
    }


    enum ConstructorType{
        NONE,WITHXYZ,WITHXYZSPEED,WITHALL;
    }
}
