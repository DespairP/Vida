package teamHTBP.vida.client.event.listener.hud;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.client.event.listener.HoldItemClientTickHandler;
import teamHTBP.vida.client.hud.ElementLevelToolsHUD;
import teamHTBP.vida.item.staff.IElementLevelTools;

import java.util.LinkedList;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDElementItemHandler extends HudHandler {
    /**渲染队列，队尾为list的第一个值(最晚出队)，队顶为list的最后一个值(最早出队)*/
    public static LinkedList<ElementLevelToolsHUD> renderQueue = new LinkedList<>();
    /**日志*/
    private final static Logger LOGGER = LogManager.getLogger();
    /**队列最大阈值*/
    private final static int MAX_STACK = 5;


    @SubscribeEvent
    public static void beforeOverlayRender(RenderGameOverlayEvent.Pre event){
        final ItemStack holdItemStack = HoldItemClientTickHandler.getHoldItem();
        final Item holdItem = Optional.ofNullable(holdItemStack).orElseGet(()->ItemStack.EMPTY).getItem();
        if(holdItem == null || HoldItemClientTickHandler.getHoldItemMillSecond() < 1500){
            return;
        }
        // 压入渲染队列
        if(holdItem instanceof IElementLevelTools){
            if(isTopOfQueueHasRender(holdItemStack)) return; // 如果存在相关hud了，就不加入渲染队伍
            final ElementLevelToolsHUD hud = new ElementLevelToolsHUD(holdItemStack);
            pushRender(hud);
        }
    }

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        final ItemStack holdItemStack = HoldItemClientTickHandler.getHoldItem();
        // 防止渲染Gui的时候还在渲染HUD
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        // 遍历进行渲染
        for(int i = 0 ; i < renderQueue.size();i++){
            setupShader();
            ElementLevelToolsHUD hud = renderQueue.get(i);
            hud.render(event.getMatrixStack(), event.getPartialTicks());
            if(i > 1 || !hud.isSameItemStack(holdItemStack)) hud.notifyStopRender(); // 非首个渲染元素或者不是需要渲染元素的提醒出队(alpha到0后会自动出队)
            if(hud.isSameItemStack(holdItemStack)) hud.renewItemStack(holdItemStack);
        }
    }

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent.Post event) {
        //RenderSystem.colorMask(true,true,true,true);
    }



    /**将hud压入渲染队列*/
    public static void pushRender(ElementLevelToolsHUD hud){
        assert renderQueue != null;
        if(renderQueue.size() > MAX_STACK){
            LOGGER.warn("HUDElementItemHander : Hud stack is overflow ,stack size is {},the max size is {},please check the code",renderQueue.size(),MAX_STACK);
            return;
        }
        if(renderQueue.contains(hud)){
            LOGGER.warn("HUDElementItemHander : Hud already exists,please check the hud {}" , hud.toString());
            return;
        }
        renderQueue.push(hud);
    }

    /**是否队列顶有改itemstack对应的HUD*/
    public static boolean isTopOfQueueHasRender(ItemStack stack){
        if(renderQueue.size() > 0){
            return renderQueue.getFirst().isSameItemStack(stack);
        }
        return false;
    }

    /**将hud移除出渲染队列*/
    public static void popRender(ElementLevelToolsHUD hud){
        if(!renderQueue.contains(hud)){
            LOGGER.warn("HUDElementItemHander : Hud already removed or not exists {}",hud.toString());
            return;
        }
        renderQueue.remove(hud);
    }


}
