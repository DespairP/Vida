package teamHTBP.vida.client.atlas;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaAtlasManager {
    public static final ResourceLocation ICONS = new ResourceLocation(Vida.MOD_ID, "textures/atlas/icons.png");

    @SubscribeEvent
    public static void onAtlasGenerate(ModelRegistryEvent event) {
        Set<Material> miscMaterials = ModelBakery.UNREFERENCED_TEXTURES;

        ArrayList<ResourceLocation> icons = new ArrayList<>
                (Minecraft.getInstance().getResourceManager().listResources("textures/vida_icons/", s -> s.endsWith(".png")));

        for (ResourceLocation location : icons) {
            if (location.getNamespace().equals(Vida.MOD_ID)) {
                ResourceLocation trans = trans(location);
                miscMaterials.add(new Material(ICONS, trans));
            }
        }
    }

    public static ResourceLocation trans(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), location.getPath().substring(9, location.getPath().length() - 4));
    }
}
