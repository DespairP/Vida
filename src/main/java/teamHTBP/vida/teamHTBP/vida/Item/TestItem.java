package teamHTBP.vida.teamHTBP.vida.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
