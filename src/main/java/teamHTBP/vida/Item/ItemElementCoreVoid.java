package teamHTBP.vida.Item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import teamHTBP.vida.Entity.EntityFaintLight;
import teamHTBP.vida.Entity.EntityLoader;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

import java.util.List;

public class ItemElementCoreVoid extends Item {
    public ItemElementCoreVoid() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {

        System.out.println("ssss");
        return true;
    }
    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteractSpecific event){
        if(event.getPlayer().inventory.getCurrentItem().getItem() == ItemLoader.voidElementCore.get().getItem() && !event.getWorld().isRemote && event.getHand() == Hand.MAIN_HAND ){
            int x = event.getPos().getX();
            int y = event.getPos().getY();
            int z = event.getPos().getZ();
            AxisAlignedBB bb = new AxisAlignedBB(x - 1,y  ,z - 1 ,x + 1,y + 1,z + 1);
            List<Entity> list =event.getPlayer().world.getEntitiesWithinAABB(EntityLoader.faintLight.get().create(event.getWorld()).getEntity().getClass(), bb);
            if(!list.isEmpty()){
                if(list.get(0) instanceof EntityFaintLight){
                     list.get(0).remove();
                         ItemStack stack = event.getPlayer().inventory.getCurrentItem();
                         stack.setCount(stack.getCount() - 1);
                         ItemStack getStack = ItemStack.EMPTY;
                         EntityFaintLight entityFaintLight = (EntityFaintLight) list.get(0);
                         switch (entityFaintLight.getFaintLightType()){
                             case 1:
                                 getStack = new ItemStack(ItemLoader.goldElementCore.get(),1);break;
                             case 2:
                                 getStack = new ItemStack(ItemLoader.woodElementCore.get(),1);break;
                             case  3:
                                 getStack = new ItemStack(ItemLoader.aquaElementCore.get(),1);break;
                             case  4:
                                 getStack = new ItemStack(ItemLoader.fireElementCore.get(),1);break;
                             case 5:
                                 getStack = new ItemStack(ItemLoader.earthElementCore.get(),1);break;
                         }
                    event.getPlayer().inventory.addItemStackToInventory(getStack);

                }
            }

        }
    }

}
