package teamHTBP.vida.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.gui.GUI.guidebook.GuiGuidebookMain;

@OnlyIn(Dist.CLIENT)
public class ClientScreenManager {
    @OnlyIn(Dist.CLIENT)
    public static void openGuideBook(){
        Minecraft.getInstance().setScreen(new GuiGuidebookMain());
    }
}
