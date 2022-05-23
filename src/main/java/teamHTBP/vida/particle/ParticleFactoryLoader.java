package teamHTBP.vida.particle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.particle.util.BaseParticleFactory;
import teamHTBP.vida.particle.util.BaseVidaParticleType;


/**
 * 粒子注册工厂{@link net.minecraft.client.particle.IParticleFactory}<br/>
 *
 * 具体注册需要看{@link net.minecraft.client.particle.ParticleManager#registerFactory}形参中需要一个Provider和一个ParticleType<br/>
 *
 * ParticleType是工厂的Key<br/>
 *
 * - ParticleType {@link teamHTBP.vida.particle.util.BaseParticleType}<br/>
 * */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryLoader {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.leafParticle.get(),(sprite)->new BaseParticleFactory(sprite, BaseVidaParticleType.LEAF));
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.cubeParticle.get(), CubeParticleFactory::new);
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.elementFireParticle.get(), (sprite)->new BaseParticleFactory(sprite, BaseVidaParticleType.ELEMENT_FIRE));
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.cuboidParticle.get(), CuboidParticleFactory::new);
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.particle.get(),(sprite)->new BaseParticleFactory(sprite, BaseVidaParticleType.CUBOID));
    }
}
