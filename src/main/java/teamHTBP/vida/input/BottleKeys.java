package teamHTBP.vida.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import teamHTBP.vida.network.BottlesPacket;
import teamHTBP.vida.network.PacketManager;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class BottleKeys {
    public static final KeyMapping MESSAGE_KEY = new KeyMapping("key.bottles",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            "key.category.vida");
    public static final KeyMapping MESSAGE_KEY_1 = new KeyMapping("key.bottle1.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_A,
            "key.category.vida");
    public static final KeyMapping MESSAGE_KEY_2 = new KeyMapping("key.bottle2.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_D,
            "key.category.vida");
    public static final KeyMapping MESSAGE_KEY_3 = new KeyMapping("key.bottle3.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_W,
            "key.category.vida");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (MESSAGE_KEY.isDown()) {
            //alpha += 2;
        }
        if (MESSAGE_KEY_1.consumeClick()) {
            PacketManager.INSTANCE.sendToServer(new BottlesPacket(1));
        }
        if (MESSAGE_KEY_2.consumeClick()) {
            PacketManager.INSTANCE.sendToServer(new BottlesPacket(2));
        }
        if (MESSAGE_KEY_3.consumeClick()) {
            PacketManager.INSTANCE.sendToServer(new BottlesPacket(3));
        }
    }


}
