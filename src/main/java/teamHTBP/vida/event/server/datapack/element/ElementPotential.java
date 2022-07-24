package teamHTBP.vida.event.server.datapack.element;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.world.item.Item;
import teamHTBP.vida.element.IElement;

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
