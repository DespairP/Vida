package teamHTBP.vida.client.gui.gui.guidebook;


import net.minecraft.client.gui.screens.Screen;

public interface IGuibookType {

    public <T extends Screen> T getPrev();
}
