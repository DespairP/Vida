package teamHTBP.vida.gui.menu.guidebook;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.TranslatableComponent;
import teamHTBP.vida.gui.components.IGuidebookPageable;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.GuidebookSinglePage;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;

/**详细guide*/
public class GuiGuidebookGuide extends AbstractGuiGuidebook implements IGuidebookPageable {
    private GuidebookSinglePage singlePage = new GuidebookSinglePage();

    public GuiGuidebookGuide() {
        super(new TranslatableComponent("vida:guidebook_guide"));
        ClientLevel world = Minecraft.getInstance().level;
        singlePage = GuidebookHelper
                .getGuidePageHandler(world)
                .guideToSinglePage
                .values()
                .stream()
                .findFirst()
                .orElse(ImmutableList.of(new GuidebookSinglePage()))
                .get(0);
    }

    @Override
    public void init() {
        singlePage.reset();
        //初始化layout
        singlePage.addLayout(startX + 0, startY + 0,132,140);
        singlePage.addLayout(startX + 186, startY + 0,132,140);
        //监听器初始化
        for(IGuidebookComponent eventListener:singlePage.getPageListener()){
            addWidget(eventListener);
        }
    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public int getTotalPage() {
        return 0;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        singlePage.renderPage(matrixStack, mouseX, mouseY, partialTicks);
    }
}
