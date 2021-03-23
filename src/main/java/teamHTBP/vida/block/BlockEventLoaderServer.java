package teamHTBP.vida.block;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.Item.staff.ItemElementPickaxe;
import teamHTBP.vida.Item.staff.ItemElementSword;

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
                            if (progress < 100 && ((ItemArmorElementLegginsWithBottles) stack.getItem()).element != 4) {nbt.putInt("bottle"+i+"Num", progress + new Random().nextInt(3) + 1);
                            if(((ItemArmorElementLegginsWithBottles)(stack.getItem())).element == 1){
                                    if(new Random().nextFloat() > 0.85D)
                                        nbt.putInt("bottle"+(new Random().nextInt(3)+1)+"Num",100);
                                }
                            } else if(progress < 200 && ((ItemArmorElementLegginsWithBottles) stack.getItem()).element ==4) {nbt.putInt("bottle"+i+"Num", progress + new Random().nextInt(3) + 1);}
                        }
                    }
                }
             }
        }
    }

    @SubscribeEvent
    public static void block_break(BlockEvent.BreakEvent event){
        //System.out.println("sss");
        PlayerEntity playerEntity = event.getPlayer();
        if(playerEntity != null){
            ItemStack itemStack = playerEntity.getHeldItem(Hand.MAIN_HAND);
            if(itemStack.getItem() instanceof ItemElementPickaxe) {
                CompoundNBT nbt = itemStack.getOrCreateTag();
                Block block = event.getState().getBlock();
                Random random = new Random();
                int level = nbt.getInt("level");
                if(block instanceof OreBlock && level < 30){
                      int exp = nbt.getInt("pickaxeEXP");
                      nbt.putInt("pickaxeEXP",exp + random.nextInt(5) + 5);
                      levelupTool(exp, level,itemStack);
                }else if(block instanceof Block && level < 30){
                    int exp = nbt.getInt("pickaxeEXP");
                    //System.out.println(exp);
                    nbt.putInt("pickaxeEXP",exp + random.nextInt(2));
                    levelupTool(exp, level,itemStack);
                }
            }
        }
    }

    public static boolean levelupTool(int exp,int level,ItemStack stack){
        if(level * 500 <= exp){
            CompoundNBT nbt = stack.getOrCreateTag();
            int newEXP = exp - level * 500;
            if(level + 1 < 30) {
                nbt.putInt("level", level + 1);
                nbt.putInt("pickaxeEXP", newEXP);
            }else{
                nbt.putInt("level", 30);
                nbt.putInt("exp", -1);
            }
            stack.setDamage(0);
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void hitEntity(LivingAttackEvent event){
        Entity entity = event.getSource().getImmediateSource();
        if(entity instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity)entity;
            ItemStack stack = playerEntity.getHeldItem(Hand.MAIN_HAND);
            if(stack.getItem() instanceof ItemElementSword){
                CompoundNBT nbt = stack.getOrCreateTag();
                int level = nbt.getInt("level");
                int exp = nbt.getInt("swordEXP");
                if(level < 30){
                    nbt.putInt("swordEXP", exp + new Random().nextInt(3) + 100);
                    level_up_sword(level, exp, stack);
                }
            }
        }
    }


    public static boolean level_up_sword(int level,int exp,ItemStack stack){
        if(level  * 200 + level * 13 <= exp){
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt("level", level + 1);
            nbt.putInt("swordEXP", exp - (level  * 200 + level * 13));
            return true;
        }
        return  false;
    }
}
