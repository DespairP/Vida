package teamHTBP.vida.Item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

/**
 * 生命法杖
 * @version  0.0.1
 * **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new VidaWandProperties());
    }
}

//法杖的Properties
class VidaWandProperties extends Item.Properties{
    VidaWandProperties(){
        super();
        this.group(ItemGroupLoader.vidaItemGroup);
        this.maxStackSize(1);
    }

}