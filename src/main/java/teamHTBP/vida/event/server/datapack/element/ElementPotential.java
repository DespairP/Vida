package teamHTBP.vida.event.server.datapack.element;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.item.Item;
import teamHTBP.vida.helper.elementHelper.IElement;

/**
 * @author DustW
 **/
@AllArgsConstructor
@Data
public class ElementPotential {
    Item item;
    IElement element;
    int containing;
    String name;
}
