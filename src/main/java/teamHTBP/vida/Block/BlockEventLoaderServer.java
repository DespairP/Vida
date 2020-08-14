package teamHTBP.vida.Block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;

import java.util.Random;


public class BlockEventLoaderServer {

    @SubscribeEvent
    public static void kill(LivingDeathEvent event) {
        //System.out.println("SSSS");
        if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
             PlayerEntity playerEntity = (PlayerEntity)event.getSource().getImmediateSource();
             ItemStack stack = playerEntity.inventory.armorInventory.get(1);
             if(stack.getItem() instanceof ItemArmorElementLegginsWithBottles){
                CompoundNBT nbt = stack.getOrCreateTag();
                for(int i = 1;i <= 3;i++) {
                    if (nbt.contains("bottle" + i)) {
                        ItemStack stack1 = ItemStack.read(nbt.getCompound("bottle" + i));
                        if (stack1 != ItemStack.EMPTY && !stack1.isEmpty()) {
                            int progress = nbt.getInt("bottle"+ i +"Num");
                            if (progress < 100) nbt.putInt("bottle"+i+"Num", progress + new Random().nextInt(3) + 1);
                        }
                    }
                }
             }
        }
    }
}
