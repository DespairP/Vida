package teamHTBP.vida.worldGen;

import net.minecraft.world.gen.feature.Feature;;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class GenLoader {
    public static DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Vida.modId);
    public static final RegistryObject<Feature<TreeFeatureConfig>> vidaTree=FEATURES.register("vidatree", () -> new GenVidaTree());
}
