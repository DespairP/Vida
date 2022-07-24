package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import teamHTBP.vida.Vida;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Vida.MOD_ID)
public class VidaDataGenerator {
    @SubscribeEvent
    public static void onEvent(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new ElementPotentialProvider(Vida.MOD_ID, generator));
            generator.addProvider(new VidaRecipeProvider(generator));
            VidaBlockTagProvider blockTags = new VidaBlockTagProvider(generator, helper);
            generator.addProvider(blockTags);
            generator.addProvider(new VidaItemTagProvider(generator, blockTags, helper));
        }
        else if (event.includeClient()) {
            generator.addProvider(new VidaLanguageProvider(Vida.MOD_ID, generator));
        }
    }
}
