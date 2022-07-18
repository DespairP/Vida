package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import teamHTBP.vida.Vida;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Vida.MOD_ID)
public class DataGen {
    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(new ElementPotentialProvider(Vida.MOD_ID, generator));
        generator.addProvider(new ModRecipes(generator));
    }
}
