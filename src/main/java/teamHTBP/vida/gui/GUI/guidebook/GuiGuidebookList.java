package teamHTBP.vida.gui.GUI.guidebook;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import teamHTBP.vida.gui.components.GuidebookContext;

/**区域下所有的guide条目*/
public class GuiGuidebookList extends AbstractGuiGuidebook{
    /**现在的页数,-1代表不渲染*/
    protected Integer currentPage = -1;


    public GuiGuidebookList(ITextComponent titleIn) {
        super(new TranslationTextComponent("vida:guidebook_list"));
    }
}
