package teamHTBP.vida.world.generation;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.helper.elementHelper.EnumElements;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;


@Mod.EventBusSubscriber
public class BiomeGenLoader {

    @SubscribeEvent
    public static void oreGenSetup(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.FOREST || event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getGeneration().getFeatures(VEGETAL_DECORATION).add(GenerationLoader.VIDA_TREE_PLACED.getHolder().get());
        }

        if (EnumElements.AQUA.contains(event.getName())) {
            baseElementOre(event, GenerationLoader.aquaElementOre);
        }

        if (EnumElements.GOLD.contains(event.getName())) {
            baseElementOre(event, GenerationLoader.goldElementOre);
        }

        if (EnumElements.FIRE.contains(event.getName())) {
            baseElementOre(event, GenerationLoader.fireElementOre);
        }

        if (EnumElements.EARTH.contains(event.getName())) {
            baseElementOre(event, GenerationLoader.earthElementOre);
        }

        if (EnumElements.WOOD.contains(event.getName())) {
            baseElementOre(event, GenerationLoader.woodElementOre);
        }
    }

    static void baseElementOre(BiomeLoadingEvent event, RegistryObject<PlacedFeature> ore) {
        event.getGeneration().getFeatures(UNDERGROUND_ORES).add(ore.getHolder().get());
    }
}
