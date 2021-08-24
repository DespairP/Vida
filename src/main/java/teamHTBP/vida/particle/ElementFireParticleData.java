package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import java.util.Locale;

public class ElementFireParticleData implements IParticleData {

    public static final IDeserializer<ElementFireParticleData> DESERIALIZER = new IDeserializer<ElementFireParticleData>() {

        @Override
        public ElementFireParticleData deserialize(ParticleType<ElementFireParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            return new ElementFireParticleData(speedX, speedY, speedZ);
        }

        @Override
        public ElementFireParticleData read(ParticleType<ElementFireParticleData> particleTypeIn, PacketBuffer buffer) {
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            return new ElementFireParticleData(speedX, speedY, speedZ);
        }
    };
    private final double speedX;
    private final double speedY;
    private final double speedZ;

    public ElementFireParticleData(double speedX, double speedY, double speedZ) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.elementFireParticle.get();
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
