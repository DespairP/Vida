package teamHTBP.vida.particle.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import teamHTBP.vida.particle.ParticleFactoryLoader;

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
            return new BaseParticleData(particleTypeIn, 255, 255, 255, 1 , 100);
        }

        @Override
        public BaseParticleData read(ParticleType<BaseParticleData> particleTypeIn, PacketBuffer buffer) {
            int r = buffer.readInt();
            int g = buffer.readInt();
            int b = buffer.readInt();
            float scale = buffer.readFloat();
            int age = buffer.readInt();
            return new BaseParticleData(particleTypeIn, r, g, b, scale, age);
        }
    };

    private final int age;
    private float scale;
    private int r;
    private int g;
    private int b;
    ParticleType<BaseParticleData> particleTypeIn;

    public BaseParticleData(ParticleType<BaseParticleData> particleTypeIn, int r, int g, int b, float scale, int age) {
        this.particleTypeIn = particleTypeIn;
        this.r = r;
        this.g = g;
        this.b = b;
        this.scale = scale;
        this.age = age;
        this.scale = scale;
    }

    @Override
    public ParticleType<?> getType() {
        return particleTypeIn;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeFloat(this.r);
        buffer.writeFloat(this.g);
        buffer.writeFloat(this.b);
        buffer.writeFloat(this.scale);
        buffer.writeInt(age);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s", this.getType().getRegistryName());
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
