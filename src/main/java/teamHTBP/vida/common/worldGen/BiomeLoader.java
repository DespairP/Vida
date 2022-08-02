package teamHTBP.vida.common.worldGen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

/**地形加载*/
@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeLoader {
    public static DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Vida.MOD_ID);

    /**用于储存所有Biomes键*/
    public static final RegistryKey<Biome> VIDA_FOREST_KEY = makeKey("vida_forest");

    public static RegistryObject<Biome> VIDA_FOREST = BIOMES.register("vida_forest", VidaBiomesMaker::makeVidaForest);

    public static void registerBiomes(){
        BiomeDictionary.addTypes(VIDA_FOREST_KEY, BiomeDictionary.Type.VOID);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(VIDA_FOREST_KEY,10));
    }

    @SubscribeEvent
    public static void setupBiomes(final FMLCommonSetupEvent event) {
        event.enqueueWork(BiomeLoader::registerBiomes);
    }


    private static RegistryKey<Biome> makeKey(String name) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Vida.MOD_ID, name));
        return key;
    }
}
