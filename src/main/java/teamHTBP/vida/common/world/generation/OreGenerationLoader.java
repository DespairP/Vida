package teamHTBP.vida.common.world.generation;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.core.element.EnumElements;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;


@Mod.EventBusSubscriber
public class OreGenerationLoader {

    @SubscribeEvent
    public static void oreGenSetup(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.FOREST || event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getGeneration().getFeatures(VEGETAL_DECORATION).add(VidaGenerationLoader.VIDA_TREE_PLACED.getHolder().get());
        }

        if (EnumElements.AQUA.contains(event.getName())) {
            baseElementOre(event, VidaGenerationLoader.aquaElementOre);
        }

        if (EnumElements.GOLD.contains(event.getName())) {
            baseElementOre(event, VidaGenerationLoader.goldElementOre);
        }

        if (EnumElements.FIRE.contains(event.getName())) {
            baseElementOre(event, VidaGenerationLoader.fireElementOre);
        }

        if (EnumElements.EARTH.contains(event.getName())) {
            baseElementOre(event, VidaGenerationLoader.earthElementOre);
        }

        if (EnumElements.WOOD.contains(event.getName())) {
            baseElementOre(event, VidaGenerationLoader.woodElementOre);
        }
    }

    static void baseElementOre(BiomeLoadingEvent event, RegistryObject<PlacedFeature> ore) {
        event.getGeneration().getFeatures(UNDERGROUND_ORES).add(ore.getHolder().get());
    }
}
