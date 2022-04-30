package teamHTBP.vida.item.function;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import java.util.List;

public class ItemElementCoreVoid extends Item {
    public ItemElementCoreVoid() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }

    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getWorld() == null) return;
        if (event.getPlayer().inventory.getCurrentItem().getItem() == ItemLoader.ELEMENTCORE_VOID.get().getItem() && !event.getWorld().isRemote && event.getHand() == Hand.MAIN_HAND) {
            int x = event.getPos().getX();
            int y = event.getPos().getY();
            int z = event.getPos().getZ();
            AxisAlignedBB bb = new AxisAlignedBB(x - 1, y, z - 1, x + 1, y + 1, z + 1);
            List<Entity> list = event.getPlayer().world.getEntitiesWithinAABB(EntityLoader.faintLight.get().create(event.getWorld()).getEntity().getClass(), bb);
            if (!list.isEmpty()) {
                if (list.get(0) instanceof EntityFaintLight) {
                    list.get(0).remove();
                    ItemStack stack = event.getPlayer().inventory.getCurrentItem();
                    stack.setCount(stack.getCount() - 1);
                    ItemStack getStack = ItemStack.EMPTY;
                    EntityFaintLight entityFaintLight = (EntityFaintLight) list.get(0);

                    if (entityFaintLight.getFaintLightType() instanceof EnumElements) {
                        switch ((EnumElements) entityFaintLight.getFaintLightType()) {
                            case GOLD:
                                getStack = new ItemStack(ItemLoader.ELEMENTCORE_GOLD.get(), 1);
                                break;
                            case WOOD:
                                getStack = new ItemStack(ItemLoader.ELEMENTCORE_WOOD.get(), 1);
                                break;
                            case AQUA:
                                getStack = new ItemStack(ItemLoader.ELEMENTCORE_AQUA.get(), 1);
                                break;
                            case FIRE:
                                getStack = new ItemStack(ItemLoader.ELEMENTCORE_FIRE.get(), 1);
                                break;
                            case EARTH:
                                getStack = new ItemStack(ItemLoader.ELEMENTCORE_EARTH.get(), 1);
                                break;
                        }
                    }
                    event.getPlayer().inventory.addItemStackToInventory(getStack);

                }
            }

        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {

        System.out.println("ssss");
        return ActionResultType.SUCCESS;
    }

}
