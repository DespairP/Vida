package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import java.util.Locale;

public class CubeParticleData implements IParticleData {
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
    private final double speedX;
    private final double speedY;
    private final double speedZ;
    private float scale;
    private float r;
    private float g;
    private float b;

    public CubeParticleData(double speedX, double speedY, double speedZ) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }

    public CubeParticleData(double speedX, double speedY, double speedZ, float r, float g, float b, float scale) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.r = r;
        this.g = g;
        this.b = b;
        this.scale = scale;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.cubeParticle.get();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeDouble(this.speedX);
        buffer.writeDouble(this.speedY);
        buffer.writeDouble(this.speedZ);
        buffer.writeFloat(this.r);
        buffer.writeFloat(this.g);
        buffer.writeFloat(this.b);
        buffer.writeFloat(this.scale);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f ",
                this.getType().getRegistryName(), speedX, speedY, speedZ);
    }

    //获得x,y,z速度
    public double getSpeed(int type) {
        switch (type) {
            case 0:
                return speedX;
            case 1:
                return speedY;
            case 2:
                return speedZ;
            default:
                return 0;
        }
    }

    //获得r,g,b,scale
    public float getRGBS(int type) {
        switch (type) {
            case 0:
                return r;
            case 1:
                return g;
            case 2:
                return b;
            case 3:
                return scale;
            default:
                return 0;
        }
    }

    //得到RGBscale是否存在
    public boolean containRGBS() {
        return r != 0 || g != 0 || !(b == 0 & scale == 0);
    }

}
