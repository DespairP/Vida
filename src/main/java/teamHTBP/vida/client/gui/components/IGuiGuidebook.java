package teamHTBP.vida.client.gui.components;


import net.minecraft.client.gui.screens.Screen;

/**用于context管理*/
public interface IGuiGuidebook {
    /**实例化时的context*/
    public void handleInitContext(GuidebookContext context);

    /**重新打开时的context*/
    public void handleReopenContext(GuidebookContext context);

    /**获取此页面的context*/
    public GuidebookContext getContext();

    /**获取screen*/
    public Screen getScreen();
}
