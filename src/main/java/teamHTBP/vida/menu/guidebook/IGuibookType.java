package teamHTBP.vida.menu.guidebook;


import net.minecraft.client.gui.screens.Screen;

public interface IGuibookType {

    public <T extends Screen> T getPrev();
}
