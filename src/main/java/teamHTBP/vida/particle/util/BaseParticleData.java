package teamHTBP.vida.particle.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import teamHTBP.vida.particle.CuboidParticleData;
import teamHTBP.vida.particle.ParticleFactoryLoader;
import teamHTBP.vida.particle.ParticleLoader;

import java.util.Locale;

/**
 * 基础Vida粒子数据<br/>
 * 粒子数据是创造粒子时需要的数据，见 {@link World#addParticle},<br/>
 * 在指令或者代码生成时都需要使用到ParticleData<br/>
 * 首先{@link ClientWorld#addParticle}提醒renderer渲染粒子，形参中要加入{@link IParticleData}的实例<br/>
 * 然后{@link ParticleManager#addParticle}根据形参中的{@link IParticleData}从factories里取出能处理该data的工厂<br/>
 * 最后{@link IParticleFactory#makeParticle}根据实例构造出该粒子<br/>
 *
 * - 工厂注册可见：{@link ParticleFactoryLoader#onParticleFactoryRegistration}
 * */
public class BaseParticleData implements IParticleData {
    /**命令行解析器*/
    public static final IDeserializer<BaseParticleData> DESERIALIZER = new IDeserializer<BaseParticleData>() {

        @Override
        public BaseParticleData deserialize(ParticleType<BaseParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            reader.expect(' ');
            float r = reader.readFloat();
            reader.expect(' ');
            float g = reader.readFloat();
            reader.expect(' ');
            float b = reader.readFloat();
            reader.expect(' ');
            float scale = reader.readFloat();
            reader.expect(' ');
            int age = reader.readInt();
            return new BaseParticleData(speedX, speedY, speedZ, r, g, b, scale, age);
        }

        @Override
        public BaseParticleData read(ParticleType<BaseParticleData> particleTypeIn, PacketBuffer buffer) {
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            float r = buffer.readFloat();
            float g = buffer.readFloat();
            float b = buffer.readFloat();
            float scale = buffer.readFloat();
            int age = buffer.readInt();
            return new BaseParticleData(speedX, speedY, speedZ, r, g, b, scale, age);
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

    public BaseParticleData(double speedX, double speedY, double speedZ, float r, float g, float b, float scale, int age) {
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
        return ParticleLoader.particle.get();
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
        buffer.writeInt(age);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2d %.2d %.2d %f %f %f %f %d",
                this.getType().getRegistryName(), speedX, speedY, speedZ, r, g, b, scale, age);
    }
}
