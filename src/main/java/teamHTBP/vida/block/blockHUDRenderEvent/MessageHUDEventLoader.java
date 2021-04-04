package teamHTBP.vida.block.blockHUDRenderEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.gui.HUD.MessageHUD;

import java.util.LinkedList;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class MessageHUDEventLoader {
    public static final LinkedList<String> messageList = new LinkedList<>();
    public static boolean isMessage = false;
    public static MessageHUD currentRnederer;

    @SubscribeEvent
    public static void renderMessageHUD(RenderGameOverlayEvent event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null) {
            return;
        }
        if(!isMessage) return;
        if(currentRnederer == null && isMessage && messageList.size() > 0){
            currentRnederer = new MessageHUD(messageList.get(0));
            currentRnederer.render();
        }else if(currentRnederer != null && isMessage){
            currentRnederer.render();
        }
        if((currentRnederer == null || currentRnederer.alpha <= 0) && messageList.size() > 0){
            System.out.println(currentRnederer.alpha <= 0);
            removeMessage();
        }
        return;

    }

    public static void setMessage(String message){
        messageList.add(message);
        System.out.println(message);
        isMessage = true;
    }

    public static void removeMessage(){
        messageList.remove(0);
        currentRnederer = null;
        if(messageList.size() == 0){
            isMessage = false;
        }else{
            isMessage = true;
        }
    }

}
