package teamHTBP.vida.core.element;

import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.api.core.element.IElement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class ElementManager {
    private static final Map<ResourceLocation, IElement> ELEMENTS = built();

    private static Map<ResourceLocation, IElement> built() {
        Map<ResourceLocation, IElement> result = new HashMap<>();

        for (EnumElements value : EnumElements.values()) {
            result.put(value.getElementName(), value);
        }

        return result;
    }

    public static IElement get(ResourceLocation name) {
        return ELEMENTS.get(name);
    }

    public static IElement get(String name) {
        return get(new ResourceLocation(name));
    }
}
