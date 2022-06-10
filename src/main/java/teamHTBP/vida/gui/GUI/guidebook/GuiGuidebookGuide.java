package teamHTBP.vida.gui.GUI.guidebook;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import teamHTBP.vida.gui.components.IGuidebookPageable;
import teamHTBP.vida.helper.guidebookHelper.GuidebookSinglePage;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.ModelGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.TextGuidebookComponent;
import teamHTBP.vida.item.ItemLoader;

import java.util.LinkedList;
import java.util.List;

/**详细guide*/
public class GuiGuidebookGuide extends AbstractGuiGuidebook implements IGuidebookPageable {
    private GuidebookSinglePage singlePage = new GuidebookSinglePage();

    public GuiGuidebookGuide() {
        super(new TranslationTextComponent("vida:guidebook_guide"));
        List<IGuidebookComponent> guidebookComponents = new LinkedList<>();
        guidebookComponents.add(ModelGuidebookComponent.create(0, 0, new ItemStack(ItemLoader.logVida.get())));
        guidebookComponents.add(ModelGuidebookComponent.create(0, 0, new ItemStack(ItemLoader.aquaElementOre.get())));
        guidebookComponents.add(ModelGuidebookComponent.create(0, 0, new ItemStack(ItemLoader.collector.get())));
        guidebookComponents.add(TextGuidebookComponent.create("hello this is vida!", 0,0, 28,30));
        singlePage.components = guidebookComponents;
    }

    @Override
    public void init() {
        singlePage.reset();
        //初始化layout
        singlePage.addLayout(startX + 0, startY + 0,132,140);
        singlePage.addLayout(startX + 186, startY + 0,132,140);
        //监听器初始化
        for(IGuiEventListener eventListener:singlePage.getPageListener()){
            addListener(eventListener);
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
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        singlePage.renderPage(matrixStack, mouseX, mouseY, partialTicks);
    }
}
