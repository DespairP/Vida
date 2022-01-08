package teamHTBP.vida.registry;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import teamHTBP.vida.helper.element.IElement;

public class VidaRegistries {
    public static final IForgeRegistry<IElement> ELEMENTS = RegistryManager.ACTIVE.getRegistry(IElement.class);
}
