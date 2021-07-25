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
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.item.staff.ItemElementPickaxe;
import teamHTBP.vida.item.staff.ItemElementSword;

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

    /**镐子升级订阅block*/
    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event){
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
                    levelupTool(itemStack);
                }else if(block instanceof Block && level < 30){
                    int exp = nbt.getInt("pickaxeEXP");
                    nbt.putInt("pickaxeEXP",exp + random.nextInt(2));
                    levelupTool(itemStack);
                }
            }
        }
    }

    /**
     * 工具升级检测
     * @return 工具是否升级
     * */
    public static boolean levelupTool(ItemStack stack){
        CompoundNBT stackNBT = stack.getOrCreateTag();
        int currentLevel = stackNBT.getInt("level");
        int currentExp = stackNBT.getInt("pickaxeEXP");
        int futureLevel = currentLevel;
        //计算最大需求
        int maxExpRequired = currentLevel * 500;
        //循环至最大Exp大于当前Exp时结束
        while(currentExp >= maxExpRequired){
            currentExp -= maxExpRequired;
            futureLevel += 1;
            maxExpRequired = currentLevel * 500;
        }
        //检测经验是否溢出等级需求经验
        if(futureLevel != currentLevel){
            stackNBT.putInt("level", futureLevel);
            stackNBT.putInt("pickaxeEXP", currentExp);
            return true;  //工具需要升级且升级完成
        } else
            return false; //默认工具不需要升级
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
