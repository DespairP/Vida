package teamHTBP.vida.common.worldGen;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class GenLoader {
    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Vida.MOD_ID);
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> vidaTree = FEATURES.register("vidatree", () -> new GenVidaTree());
}
