package teamHTBP.vida.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import teamHTBP.vida.Entity.EntityFaintLight;
import teamHTBP.vida.Entity.EntityLoader;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemFaintLight extends Item {
    public int element = 1;
    public ItemFaintLight() {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
    }

    public ItemFaintLight(int element) {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote && handIn == Hand.MAIN_HAND && playerIn.isCreative()){
            System.out.println(element);
            EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), worldIn, element);
            entityFaintLight.setPosition(playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ());
            entityFaintLight.setFaintLightType(element);
            worldIn.addEntity(entityFaintLight);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
