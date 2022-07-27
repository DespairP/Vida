package teamHTBP.vida.core.element;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.item.Item;
import teamHTBP.vida.api.core.element.IElement;

/**
 * @author DustW
 **/
@AllArgsConstructor
@Data
public class ElementPotential {
    private Item item;
    private IElement element;
    private int containing;
    private String name;
}
