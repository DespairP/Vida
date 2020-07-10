package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.Vec3d;

import java.util.Locale;

public class LeafParticleData implements IParticleData {
    private double speedX;
    private double speedY;
    private double speedZ;
    public LeafParticleData(double speedX,double speedY,double speedZ){
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }
    public static final IDeserializer<LeafParticleData> DESERIALIZER = new IDeserializer<LeafParticleData>() {

        @Override
        public LeafParticleData deserialize(ParticleType<LeafParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            return new LeafParticleData(speedX, speedY, speedZ);
        }

        @Override
        public LeafParticleData read(ParticleType<LeafParticleData> particleTypeIn, PacketBuffer buffer) {
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            return new LeafParticleData(speedX, speedY, speedZ);
        }
    };
    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.leafParticle.get();
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
}
