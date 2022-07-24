package teamHTBP.vida.client.gui.screen.guidebook;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

/**区域下所有的guide条目*/
public class GuidebookListScreen extends AbstractGuidebookScreen {
    /**现在的页数,-1代表不渲染*/
    protected Integer currentPage = -1;


    public GuidebookListScreen(TextComponent titleIn) {
        super(new TranslatableComponent("vida:guidebook_list"));
    }
}
