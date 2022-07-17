package teamHTBP.vida.helper.element;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class ElementManager {
    public static final Map<ResourceLocation, IElement> ELEMENTS = built();

    static Map<ResourceLocation, IElement> built() {
        Map<ResourceLocation, IElement> map = new HashMap<>();

        for (EnumElements value : EnumElements.values()) {
            map.put(value.getElementName(), value);
        }

        return map;
    }
}
