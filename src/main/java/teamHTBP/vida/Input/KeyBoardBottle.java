package teamHTBP.vida.Input;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import teamHTBP.vida.Network.PacketBottles;
import teamHTBP.vida.Network.PacketChannel;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardBottle {
    public static int alpha = 0;
    public static final KeyBinding MESSAGE_KEY = new KeyBinding("key.bottles",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            "key.category.vida");
    public static final KeyBinding MESSAGE_KEY_1 = new KeyBinding("key.bottle1.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_A,
            "key.category.vida");
    public static final KeyBinding MESSAGE_KEY_2 = new KeyBinding("key.bottle2.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_D,
            "key.category.vida");
    public static final KeyBinding MESSAGE_KEY_3 = new KeyBinding("key.bottle3.use",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_D,
            "key.category.vida");
    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (MESSAGE_KEY.isKeyDown()) {
            //alpha += 2;
        }
        if (MESSAGE_KEY_1.isPressed()){
            System.out.println("ssss");
            PacketChannel.INSTANCE.sendToServer(new PacketBottles(1));
        }
        if( MESSAGE_KEY_2.isPressed()){
            PacketChannel.INSTANCE.sendToServer(new PacketBottles(2));
        }
        if(MESSAGE_KEY_3.isPressed()){
            PacketChannel.INSTANCE.sendToServer(new PacketBottles(3));
        }
    }



}
