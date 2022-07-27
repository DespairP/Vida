package teamHTBP.vida.client.screen.guidebook;


import net.minecraft.client.gui.screens.Screen;

/**用于context管理*/
public interface IGuidebookScreen {
    /**实例化时的context*/
    void handleInitContext(GuidebookContext context);

    /**重新打开时的context*/
    void handleReopenContext(GuidebookContext context);

    /**获取此页面的context*/
    GuidebookContext getContext();

    /**获取screen*/
    Screen getScreen();
}
