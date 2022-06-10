package teamHTBP.vida.gui.GUI.guidebook;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.TranslationTextComponent;
import teamHTBP.vida.gui.components.GuidebookContext;

public class GuiGuidebookMain extends AbstractGuiGuidebook {


    public GuiGuidebookMain() {
        this(GuidebookContext.empty());
    }

    public GuiGuidebookMain(GuidebookContext context) {
        super(new TranslationTextComponent("vida:guidebook"), context);
    }

    /**不处理任何事件*/
    @Override
    public void handleInitContext(GuidebookContext context) {}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }



    /**强制清空栈,并退出*/
    @Override
    public void closeScreen() {
        this.manager.forceClear();
        super.closeScreen();
    }
}
