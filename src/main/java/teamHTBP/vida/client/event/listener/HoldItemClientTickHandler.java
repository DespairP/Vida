package teamHTBP.vida.client.event.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.helper.item.PlayerHoldData;


/**
 * 主要对玩家持有的工具进行监听，包括：现在持有的工具/持有的时间
 *
 * TODO:主副手的监听
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HoldItemClientTickHandler {
    /**最新时持有工具*/
    private static ItemStack latestHoldItem = ItemStack.EMPTY;
    /**开始持有这个工具的时间*/
    private static long startHoldSecond = 0;
    /**持有这个工具现在的时间*/
    private static long currentHoldSecond = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Player player = Minecraft.getInstance().player;
        // 如果没有进入游戏，重置参数
        if(player == null){
            reset();
            return;
        }
        // 如果玩家持有工具
        ItemStack holdItem = player.getItemInHand(InteractionHand.MAIN_HAND);
        // 如果holdItem改变了的话，记录时间
        if(holdItem != latestHoldItem){
            latestHoldItem = holdItem;
            startHoldSecond = System.currentTimeMillis();
            currentHoldSecond = System.currentTimeMillis();
            return;
        }
        // 如果没有改变，记录最新时间
        currentHoldSecond = System.currentTimeMillis();
    }


    /**重置参数*/
    private static void reset(){
        latestHoldItem = ItemStack.EMPTY;
        currentHoldSecond = 0;
        startHoldSecond = 0;
    }

    /**现在正在拿的物品*/
    public static ItemStack getHoldItem(){
        return latestHoldItem;
    }

    /**获取手持物品的时长*/
    public static long getHoldItemMillSecond(){
        return currentHoldSecond - startHoldSecond;
    }

    /**封装*/
    public static PlayerHoldData toData(){return new PlayerHoldData(latestHoldItem,startHoldSecond,currentHoldSecond);}
}
