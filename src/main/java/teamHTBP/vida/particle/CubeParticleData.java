package teamHTBP.vida.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleOptions.IDeserializer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class CubeParticleData implements ParticleOptions {
    public static final IDeserializer<CubeParticleData> DESERIALIZER = new IDeserializer<CubeParticleData>() {

        @Override
        public CubeParticleData fromCommand(ParticleType<CubeParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            return new CubeParticleData(1, 1, 1 ,1);
        }

        @Override
        public CubeParticleData fromNetwork(ParticleType<CubeParticleData> particleTypeIn, FriendlyByteBuf buffer) {
            float r = buffer.readFloat();
            float g = buffer.readFloat();
            float b = buffer.readFloat();
            float scale = buffer.readFloat();
            return new CubeParticleData(r, g, b, scale);
        }
    };


    private float r;
    private float g;
    private float b;
    private float scale;


    public CubeParticleData(float r, float g, float b, float scale) {
        this.scale = scale;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleLoader.cubeParticle.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.r);
        buffer.writeFloat(this.g);
        buffer.writeFloat(this.b);
        buffer.writeFloat(this.scale);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s", this.getType().getRegistryName());
    }

    public float getScale() {
        return scale;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}
