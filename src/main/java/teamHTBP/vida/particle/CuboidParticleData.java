package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleOptions.IDeserializer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class CuboidParticleData implements ParticleOptions {
    public static final IDeserializer<CuboidParticleData> DESERIALIZER = new IDeserializer<CuboidParticleData>() {

        @Override
        public CuboidParticleData fromCommand(ParticleType<CuboidParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.skipWhitespace();
            double speedX = reader.readDouble();
            reader.skipWhitespace();
            double speedY = reader.readDouble();
            reader.skipWhitespace();
            double speedZ = reader.readDouble();
            reader.skipWhitespace();
            float r = reader.readFloat();
            reader.skipWhitespace();
            float g = reader.readFloat();
            reader.skipWhitespace();
            float b = reader.readFloat();
            reader.skipWhitespace();
            float scale = reader.readFloat();
            reader.skipWhitespace();
            int age = reader.readInt();

            return new CuboidParticleData(speedX, speedY, speedZ, r, g, b, scale, age);
        }

        @Override
        public CuboidParticleData fromNetwork(ParticleType<CuboidParticleData> particleTypeIn, FriendlyByteBuf buffer) {
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            float r = buffer.readFloat();
            float g = buffer.readFloat();
            float b = buffer.readFloat();
            float scale = buffer.readFloat();
            int age = buffer.readInt();
            return new CuboidParticleData(speedX, speedY, speedZ, r, g, b, scale, age);
        }
    };
    private final double speedX;
    private final double speedY;
    private final double speedZ;
    private final float r;
    private final float g;
    private final float b;
    private final int age;
    private float scale;

    public CuboidParticleData(double speedX, double speedY, double speedZ, float r, float g, float b, float scale, int age) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.r = r;
        this.g = g;
        this.b = b;
        this.scale = scale;
        this.age = age;
        this.scale = scale;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.cuboidParticle.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeDouble(this.speedX);
        buffer.writeDouble(this.speedY);
        buffer.writeDouble(this.speedZ);
        buffer.writeFloat(this.r);
        buffer.writeFloat(this.g);
        buffer.writeFloat(this.b);
        buffer.writeFloat(this.scale);
        buffer.writeInt(age);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s", this.getType().getRegistryName());
    }

    //获得x,y,z速度
    public double getInformation(int type) {
        switch (type) {
            case 0:
                return speedX;
            case 1:
                return speedY;
            case 2:
                return speedZ;
            case 3:
                return r;
            case 4:
                return g;
            case 5:
                return b;
            case 6:
                return scale;
            case 7:
                return age;
            default:
                return 0;
        }
    }


}
