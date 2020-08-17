package teamHTBP.vida.particle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryLoader {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.leafParticle.get(), (sprite) -> { return new LeafParticleFactory(sprite);});
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.cubeParticle.get(), (sprite) -> { return new CubeParticleFactory(sprite);});
        Minecraft.getInstance().particles.registerFactory(ParticleLoader.elementFireParticle.get(), (sprite) -> { return new ElementFireParticleFactory(sprite);});
    }
}
