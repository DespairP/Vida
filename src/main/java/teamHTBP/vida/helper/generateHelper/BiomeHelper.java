package teamHTBP.vida.helper.generateHelper;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class BiomeHelper {
    public static RegistryKey<Biome> getRegKey(Biome biome) {
        return ServerLifecycleHooks.getCurrentServer().getDynamicRegistries()
                .getRegistry(Registry.BIOME_KEY).getOptionalKey(biome).get();
    }
}
