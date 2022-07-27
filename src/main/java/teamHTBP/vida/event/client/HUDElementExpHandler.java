package teamHTBP.vida.event.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.client.gui.HUD.ElementLevelToolsExpHUD;
import teamHTBP.vida.common.item.staff.IElementLevelTools;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDElementExpHandler {
    public static boolean renderToolExp = true;
    private static ElementLevelToolsExpHUD hud = new ElementLevelToolsExpHUD();

    @SubscribeEvent
    public static void onRenderExp(RenderGameOverlayEvent.Pre event){
        final ItemStack holdItemStack = HoldItemClientTickHandler.getHoldItem();
        final Item holdItem = Optional.ofNullable(holdItemStack).orElseGet(()->ItemStack.EMPTY).getItem();
        if(event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE){
            return;
        }
        if(holdItem == null  || !(holdItem instanceof IElementLevelTools)){
            return;
        }
        if(!ForgeIngameGui.renderExperiance){
            return;
        }
        //render exp
        hud.render(event.getMatrixStack(), event.getPartialTicks(), holdItemStack);
        RenderSystem.pushMatrix();
        RenderSystem.colorMask(false,false,false,false);
        RenderSystem.popMatrix();
    }


    @SubscribeEvent
    public static  void afterRenderExp(RenderGameOverlayEvent.Post event){
        if(event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            RenderSystem.colorMask(true, true, true, true);
        }
    }
}
