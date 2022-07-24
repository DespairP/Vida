package teamHTBP.vida.client.gui;

import net.minecraft.client.Minecraft;
import teamHTBP.vida.client.gui.screen.guidebook.GuidebookMainScreen;

public class ClientScreenManager {
    public static void openGuideBook(){
        Minecraft.getInstance().setScreen(new GuidebookMainScreen());
    }
}
