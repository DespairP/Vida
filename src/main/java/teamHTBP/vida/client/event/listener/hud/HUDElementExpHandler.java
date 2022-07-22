package teamHTBP.vida.client.event.listener.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamHTBP.vida.client.event.listener.HoldItemClientTickHandler;
import teamHTBP.vida.client.hud.ElementLevelToolsExpHUD;
import teamHTBP.vida.item.staff.IElementLevelTools;

@OnlyIn(Dist.CLIENT)
//@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDElementExpHandler extends HudHandler {
    public static boolean renderToolExp = true;
    private static ElementLevelToolsExpHUD hud = new ElementLevelToolsExpHUD();

    @SubscribeEvent
    public static void onRenderExp(RenderGameOverlayEvent.PreLayer event){
        final ItemStack holdItemStack = HoldItemClientTickHandler.getHoldItem();
        final Item holdItem = holdItemStack.getItem();

        if (event.getOverlay() != ForgeIngameGui.EXPERIENCE_BAR_ELEMENT){
            return;
        }

        if (holdItemStack.isEmpty() || !(holdItem instanceof IElementLevelTools)){
            return;
        }

        var matrixStack = event.getMatrixStack();

        //render exp
        setupShader();
        hud.render(event.getMatrixStack(), event.getPartialTicks(), holdItemStack);
        matrixStack.pushPose();
        RenderSystem.colorMask(false,false,false,false);
        matrixStack.popPose();

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static  void afterRenderExp(RenderGameOverlayEvent.PostLayer event){
        if(event.getOverlay() == ForgeIngameGui.EXPERIENCE_BAR_ELEMENT) {
            RenderSystem.colorMask(true, true, true, true);
        }
    }
}
