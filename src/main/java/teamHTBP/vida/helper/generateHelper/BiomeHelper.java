package teamHTBP.vida.helper.generateHelper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.server.ServerLifecycleHooks;

public class BiomeHelper {
    public static ResourceKey<Biome> getRegKey(Biome biome) {
        return ServerLifecycleHooks.getCurrentServer().registryAccess()
                .registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(biome).get();
    }
}
