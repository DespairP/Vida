package teamHTBP.vida.common.particle.option.base;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import teamHTBP.vida.common.particle.provider.VidaParticleFactoryLoader;

import java.util.Locale;

/**
 * 基础Vida粒子数据<br/>
 * 粒子数据是创造粒子时需要的数据，见 {@link Level#addParticle},<br/>
 * 在指令或者代码生成时都需要使用到ParticleData<br/>
 * 首先{@link ClientLevel#addParticle}提醒renderer渲染粒子，形参中要加入{@link ParticleOptions}的实例<br/>
 * 然后{@link ParticleManager#addParticle}根据形参中的{@link ParticleOptions}从factories里取出能处理该data的工厂<br/>
 * 最后{@link ParticleProvider#makeParticle}根据实例构造出该粒子<br/>
 *
 * - 工厂注册可见：{@link VidaParticleFactoryLoader#onParticleFactoryRegistration}
 * */
public class BaseParticleOptions implements ParticleOptions {
    /**命令行解析器*/
    public static final Deserializer<BaseParticleOptions> DESERIALIZER = new Deserializer<BaseParticleOptions>() {

        @Override
        public BaseParticleOptions fromCommand(ParticleType<BaseParticleOptions> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            return new BaseParticleOptions(particleTypeIn, 255, 255, 255, 1 , 100);
        }

        @Override
        public BaseParticleOptions fromNetwork(ParticleType<BaseParticleOptions> particleTypeIn, FriendlyByteBuf buffer) {
            int r = buffer.readInt();
            int g = buffer.readInt();
            int b = buffer.readInt();
            float scale = buffer.readFloat();
            int age = buffer.readInt();
            return new BaseParticleOptions(particleTypeIn, r, g, b, scale, age);
        }
    };

    private final int age;
    private float scale;
    private int r;
    private int g;
    private int b;
    ParticleType<BaseParticleOptions> particleTypeIn;

    public BaseParticleOptions(ParticleType<BaseParticleOptions> particleTypeIn, int r, int g, int b, float scale, int age) {
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
    public void writeToNetwork(FriendlyByteBuf buffer) {
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
