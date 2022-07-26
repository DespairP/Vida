package teamHTBP.vida.worldGen;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.block.VidaBlockLoader;
import teamHTBP.vida.core.element.EnumElements;


@Mod.EventBusSubscriber()
public class BiomeGenLoader {

    @SubscribeEvent
    public static void biomeGenSetup(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.PLAINS)
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(
                    () -> GenLoader.vidaTree.get()
                            .withConfiguration(VidaTree.VIDATREE)
                            .withPlacement(Placement.CHANCE.configure(new ChanceConfig(4))));
    }

    static void eleOreDefaultConfig(BiomeLoadingEvent event, Block block) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(
                () -> Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                block.getDefaultState(),
                                8)
                ).range(16).square()
        );
    }

    @SubscribeEvent
    public static void oreGenSetup(BiomeLoadingEvent event) {
        if (EnumElements.AQUA.contains(event.getName())) {
            eleOreDefaultConfig(event, VidaBlockLoader.aquaElementOre.get());
        }

        if (EnumElements.GOLD.contains(event.getName())) {
            eleOreDefaultConfig(event, VidaBlockLoader.goldElementOre.get());
        }

        if (EnumElements.FIRE.contains(event.getName())) {
            eleOreDefaultConfig(event, VidaBlockLoader.fireElementOre.get());
        }

        if (EnumElements.EARTH.contains(event.getName())) {
            eleOreDefaultConfig(event, VidaBlockLoader.earthElementOre.get());
        }

        if (EnumElements.WOOD.contains(event.getName())) {
            eleOreDefaultConfig(event, VidaBlockLoader.woodElementOre.get());
        }
    }
}
