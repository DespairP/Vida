package teamHTBP.vida.client.gui.GUI.guidebook;

import net.minecraft.client.gui.screen.Screen;

public interface IGuibookType {

    public <T extends Screen> T getPrev();
}
