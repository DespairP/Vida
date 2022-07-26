package teamHTBP.vida.core.element;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.item.Item;

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
