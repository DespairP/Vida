package teamHTBP.vida.world.generation;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.utils.math.RandomUtil;

import java.util.List;

import static net.minecraft.data.worldgen.features.OreFeatures.NATURAL_STONE;

public class GenerationLoader {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(BuiltinRegistries.CONFIGURED_FEATURE.key(), Vida.MOD_ID);

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(BuiltinRegistries.PLACED_FEATURE.key(), Vida.MOD_ID);

    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Vida.MOD_ID);
    public static final RegistryObject<Feature<TreeConfiguration>> VIDA_TREE_FEATURE = FEATURES.register("vida_tree", VidaTreeFeature::new);

    public static final RegistryObject<ConfiguredFeature<?, ?>> VIDA_TREE =
            CONFIGURED_FEATURE.register("vida_tree", () ->
                    new ConfiguredFeature<>(VIDA_TREE_FEATURE.get(), new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(BlockLoader.logVida.get().defaultBlockState()),
                            new StraightTrunkPlacer(
                                    10, 2, 2
                            ),
                            BlockStateProvider.simple(BlockLoader.leavesVida.get().defaultBlockState()),
                            new BlobFoliagePlacer(
                                    ConstantInt.of(3), ConstantInt.of(0), 3
                            ),
                            new TwoLayersFeatureSize(10, 5, 5)).build()));

    public static final RegistryObject<PlacedFeature> VIDA_TREE_PLACED =
            PLACED_FEATURE.register("vida_tree",
                    () -> new PlacedFeature(VIDA_TREE.getHolder().get(),
                            defaultTreeModifiers(8, 0.1F, RandomUtil.getRandomBounded(1, 3))));

    public static final RegistryObject<PlacedFeature> aquaElementOre = ore(BlockLoader.aquaElementOre);
    public static final RegistryObject<PlacedFeature> goldElementOre = ore(BlockLoader.goldElementOre);
    public static final RegistryObject<PlacedFeature> fireElementOre = ore(BlockLoader.fireElementOre);
    public static final RegistryObject<PlacedFeature> earthElementOre = ore(BlockLoader.earthElementOre);
    public static final RegistryObject<PlacedFeature> woodElementOre = ore(BlockLoader.woodElementOre);

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
        PLACED_FEATURE.register(bus);
        CONFIGURED_FEATURE.register(bus);
    }

    private static List<PlacementModifier> defaultTreeModifiers(int baseAmount, float extraProbability, int extraAmount) {
        return List.of(
                PlacementUtils.countExtra(baseAmount, extraProbability, extraAmount),
                InSquarePlacement.spread(),
                VegetationPlacements.TREE_THRESHOLD,
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome());
    }

    private static RegistryObject<PlacedFeature> ore(RegistryObject<Block> block) {
        String path = block.getId().getPath();

        RegistryObject<ConfiguredFeature<?, ?>> config = CONFIGURED_FEATURE.register(path, () -> new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(NATURAL_STONE, block.get().defaultBlockState(), 8)
        ));

        return PLACED_FEATURE.register(path, () -> new PlacedFeature(config.getHolder().get(), ImmutableList.of(
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.belowTop(16))),
                InSquarePlacement.spread()
        )));
    }
}
