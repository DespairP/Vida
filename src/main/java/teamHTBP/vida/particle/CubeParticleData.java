package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import java.util.Locale;

public class CubeParticleData implements IParticleData {
    private double speedX;
    private double speedY;
    private double speedZ;
    public CubeParticleData(double speedX,double speedY,double speedZ){
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }
    public static final IDeserializer<CubeParticleData> DESERIALIZER = new IDeserializer<CubeParticleData>() {

        @Override
        public CubeParticleData deserialize(ParticleType<CubeParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            return new CubeParticleData(speedX, speedY, speedZ);
        }

        @Override
        public CubeParticleData read(ParticleType<CubeParticleData> particleTypeIn, PacketBuffer buffer) {
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            return new CubeParticleData(speedX, speedY, speedZ);
        }
    };
    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.cubeParticle.get();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeDouble(this.speedX);
        buffer.writeDouble(this.speedY);
        buffer.writeDouble(this.speedZ);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2d %.2d %.2d ",
                this.getType().getRegistryName(), speedX, speedY, speedZ);
    }

    //获得x,y,z速度
    public double getSpeed(int type) {
        switch (type) {
            case 0:
                return  speedX;
            case 1:
                return  speedY;
            case 2:
                 return speedZ;
                 default:
                     return 0;
        }

    }
}
