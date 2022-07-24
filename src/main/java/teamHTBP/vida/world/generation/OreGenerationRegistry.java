package teamHTBP.vida.world.generation;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.element.EnumElements;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;


@Mod.EventBusSubscriber
public class OreGenerationRegistry {

    @SubscribeEvent
    public static void oreGenSetup(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.FOREST || event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getGeneration().getFeatures(VEGETAL_DECORATION).add(GenerationRegistry.VIDA_TREE_PLACED.getHolder().get());
        }

        if (EnumElements.AQUA.contains(event.getName())) {
            baseElementOre(event, GenerationRegistry.aquaElementOre);
        }

        if (EnumElements.GOLD.contains(event.getName())) {
            baseElementOre(event, GenerationRegistry.goldElementOre);
        }

        if (EnumElements.FIRE.contains(event.getName())) {
            baseElementOre(event, GenerationRegistry.fireElementOre);
        }

        if (EnumElements.EARTH.contains(event.getName())) {
            baseElementOre(event, GenerationRegistry.earthElementOre);
        }

        if (EnumElements.WOOD.contains(event.getName())) {
            baseElementOre(event, GenerationRegistry.woodElementOre);
        }
    }

    static void baseElementOre(BiomeLoadingEvent event, RegistryObject<PlacedFeature> ore) {
        event.getGeneration().getFeatures(UNDERGROUND_ORES).add(ore.getHolder().get());
    }
}
