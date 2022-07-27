package teamHTBP.vida.common.particle.provider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.common.particle.type.VidaParticleTypeLoader;
import teamHTBP.vida.common.particle.provider.base.BaseParticleProvider;
import teamHTBP.vida.common.particle.type.base.BaseParticleType;
import teamHTBP.vida.common.particle.type.base.BaseVidaParticleType;


/**
 * 粒子注册工厂{@link ParticleProvider}<br/>
 *
 * 具体注册需要看{@link net.minecraft.client.particle.ParticleManager#registerFactory}形参中需要一个Provider和一个ParticleType<br/>
 *
 * ParticleType是工厂的Key<br/>
 *
 * - ParticleType {@link BaseParticleType}<br/>
 * */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VidaParticleFactoryLoader {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(VidaParticleTypeLoader.leafParticle.get(),(sprite)->new BaseParticleProvider(sprite, BaseVidaParticleType.LEAF));
        Minecraft.getInstance().particleEngine.register(VidaParticleTypeLoader.cubeParticle.get(), CubeParticleProvider::new);
        Minecraft.getInstance().particleEngine.register(VidaParticleTypeLoader.elementFireParticle.get(), (sprite)->new BaseParticleProvider(sprite, BaseVidaParticleType.ELEMENT_FIRE));
        Minecraft.getInstance().particleEngine.register(VidaParticleTypeLoader.cuboidParticle.get(), CuboidParticleProvider::new);
        Minecraft.getInstance().particleEngine.register(VidaParticleTypeLoader.particle.get(),(sprite)->new BaseParticleProvider(sprite, BaseVidaParticleType.CUBOID));
    }
}
