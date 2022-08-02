package teamHTBP.vida.common.worldGen;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;

public class VidaBiomesMaker {

    public static Biome makeVidaForest(){
        return new Biome.Builder()
                .scale(0.1f)
                .depth(0.2f)
                .category(Biome.Category.NONE)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .withGenerationSettings(BiomeGenerationSettings.DEFAULT_SETTINGS)
                .withGenerationSettings(new BiomeGenerationSettings.Builder()
                        .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE)
                        .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.UNDERWATER_CAVE)
                        .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FANCY_OAK)
                        .withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        .build()
                )
                .precipitation(Biome.RainType.SNOW)
                .temperature(.8f)
                .downfall(0.4f)
                .withMobSpawnSettings(new MobSpawnInfo.Builder().build())
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(4159204)
                        .setWaterFogColor(329011)
                        .setFogColor(12638463)
                        .withSkyColor(getSkyColorWithTemperatureModifier(0.7F))
                        .build()
                )
                .build();

    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
