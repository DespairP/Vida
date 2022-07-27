package teamHTBP.vida.common.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
    }
}
