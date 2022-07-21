package teamHTBP.vida.item.function;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamHTBP.vida.creativetab.ItemGroupLoader;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.ItemLoader;

import java.util.List;

public class ItemElementCoreVoid extends Item {
    public ItemElementCoreVoid() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getWorld() == null) return;
        if (event.getPlayer().getInventory().getSelected().getItem() == ItemLoader.ELEMENTCORE_VOID.get() && !event.getWorld().isClientSide && event.getHand() == InteractionHand.MAIN_HAND) {
            int x = event.getPos().getX();
            int y = event.getPos().getY();
            int z = event.getPos().getZ();
            AABB bb = new AABB(x - 1, y, z - 1, x + 1, y + 1, z + 1);
            List<? extends EntityFaintLight> list = event.getPlayer().level.getEntitiesOfClass(EntityLoader.faintLight.get().create(event.getWorld()).getClass(), bb);
            if (!list.isEmpty()) {
                list.get(0).discard();
                ItemStack stack = event.getPlayer().getInventory().getSelected();
                stack.setCount(stack.getCount() - 1);
                ItemStack getStack = ItemStack.EMPTY;
                EntityFaintLight entityFaintLight = (EntityFaintLight) list.get(0);

                if (entityFaintLight.getFaintLightType() instanceof EnumElements) {
                    switch ((EnumElements) entityFaintLight.getFaintLightType()) {
                        case GOLD -> getStack = new ItemStack(ItemLoader.ELEMENTCORE_GOLD.get(), 1);
                        case WOOD -> getStack = new ItemStack(ItemLoader.ELEMENTCORE_WOOD.get(), 1);
                        case AQUA -> getStack = new ItemStack(ItemLoader.ELEMENTCORE_AQUA.get(), 1);
                        case FIRE -> getStack = new ItemStack(ItemLoader.ELEMENTCORE_FIRE.get(), 1);
                        case EARTH -> getStack = new ItemStack(ItemLoader.ELEMENTCORE_EARTH.get(), 1);
                    }
                }
                event.getPlayer().getInventory().add(getStack);

            }

        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {

        System.out.println("ssss");
        return InteractionResult.SUCCESS;
    }

}
