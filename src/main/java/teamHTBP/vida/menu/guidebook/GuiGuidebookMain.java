package teamHTBP.vida.menu.guidebook;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.TranslatableComponent;
import teamHTBP.vida.client.gui.components.GuidebookContext;

public class GuiGuidebookMain extends AbstractGuiGuidebook {


    public GuiGuidebookMain() {
        this(GuidebookContext.empty());
    }

    public GuiGuidebookMain(GuidebookContext context) {
        super(new TranslatableComponent("vida:guidebook"), context);
    }

    /**不处理任何事件*/
    @Override
    public void handleInitContext(GuidebookContext context) {}

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }



    /**强制清空栈,并退出*/
    @Override
    public void onClose() {
        this.manager.forceClear();
        super.onClose();
    }
}
