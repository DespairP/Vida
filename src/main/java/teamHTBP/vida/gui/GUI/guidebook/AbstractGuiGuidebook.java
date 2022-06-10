package teamHTBP.vida.gui.GUI.guidebook;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.gui.components.GuiGuidebookManager;
import teamHTBP.vida.gui.components.GuidebookContext;
import teamHTBP.vida.gui.components.IGuiGuidebook;
import teamHTBP.vida.gui.components.IGuidebookPageable;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.renderHelper.RenderHelper;
import teamHTBP.vida.helper.renderHelper.guiHelper.TextureSection;

public abstract class AbstractGuiGuidebook extends Screen implements IGuiGuidebook {
    /**书渲染的x轴位置*/
    public Integer startX;
    /**书渲染的y轴位置*/
    public Integer startY;
    /**书相关贴图与渲染位置*/
    public final ResourceLocation book = new ResourceLocation("vida", "textures/gui/book.png");
    public final TextureSection bookSection = new TextureSection(0, 0, 371, 230);
    public final TextureSection pageSection = new TextureSection(19,245,74,17);
    /**上下文*/
    private GuidebookContext context;
    /**状态管理器*/
    public final GuiGuidebookManager manager;

    public AbstractGuiGuidebook(ITextComponent titleIn) {
        this(titleIn, GuidebookContext.empty());
    }

    public AbstractGuiGuidebook(ITextComponent titleIn,GuidebookContext context){
        super(titleIn);
        this.context = context;
        this.manager = GuidebookHelper.getManager();
        this.handleInitContext(context);
    }

    /*------- 以下用于GuiManager实现 -------*/

    /**实例化时的context*/
    public void handleInitContext(GuidebookContext context){}

    /**重新打开时的context*/
    public void handleReopenContext(GuidebookContext context){
        this.context = context;
    }

    /**获取此页面过去收到的context*/
    public GuidebookContext getContext(){
        return this.context;
    }

    /**获取屏幕*/
    @Override
    public Screen getScreen() {
        return this;
    }

    /*------- 以下用于Gui实现 -------*/

    /**初始化并且计算*/
    @Override
    public void init(Minecraft minecraft, int width, int height) {
        // 再次计算
        startX = (width - bookSection.w()) / 2 + 28;
        startY = (height - bookSection.h()) / 2 + 15;
        super.init(minecraft, width, height);
    }

    /**初始化后开始渲染*/
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // 渲染书和背景
        this.renderBackground(matrixStack);
        // 渲染页数
        this.renderPage(matrixStack);


    }

    /**渲染页数,需要子类继承{@link IGuidebookPageable}*/
    public void renderPage(MatrixStack matrixStack){
        if(!(this instanceof IGuidebookPageable)){
            return;
        }
        IGuidebookPageable pageable = (IGuidebookPageable) this;
        // 渲染背景
        RenderSystem.pushMatrix();
        int centerX = (width - pageSection.w()) / 2;
        int bottomY = (height - bookSection.h()) / 2 + bookSection.h() - 30;
        blit(matrixStack, centerX, bottomY, 0, pageSection.mu(), pageSection.mv(), pageSection.w(), pageSection.h(), 512, 512);
        RenderSystem.popMatrix();

        //渲染页数文字
        int currentPage = pageable.getCurrentPage();
        int totalPage = pageable.getTotalPage();
        String pageText = currentPage + "/" + totalPage;
        int centerTextX = (width - font.getStringWidth(pageText)) / 2 + 1;
        RenderSystem.pushMatrix();
        RenderHelper.renderTextWithDungeonFont(matrixStack, pageText, centerTextX, bottomY - 5);
        RenderSystem.popMatrix();
    }


    /**渲染背景和书背景*/
    @Override
    public void renderBackground(MatrixStack matrixStack) {
        super.renderBackground(matrixStack);
        this.renderGuidebookBackground(matrixStack);
    }

    /**渲染书*/
    public void renderGuidebookBackground(MatrixStack matrixStack){
        RenderSystem.pushMatrix();
        int centerX = (width - bookSection.w()) / 2;
        int centerY = (height - bookSection.h()) / 2;
        minecraft.textureManager.bindTexture(book);
        blit(matrixStack, centerX, centerY, 0, 0, 0, bookSection.w() , bookSection.h(), 512, 512);
        RenderSystem.popMatrix();
    }


    /*------ 监听器重写 ----- */
    /**拖拽事件改为全局监听*/
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        boolean isDragged =  super.mouseDragged(mouseX, mouseY, button, dragX, dragY);

        for(IGuiEventListener iguieventlistener : this.getEventListeners()){
            iguieventlistener.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        return isDragged;
    }

    /**松开事件改为全局监听*/
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.setDragging(false);
        this.getEventListeners().forEach((listener) -> {
            listener.mouseReleased(mouseX, mouseY, button);
        });
        return true;
    }

    /**强制关闭gui*/
    public void forceCloseScreen(){
        this.minecraft.displayGuiScreen((Screen) null);
    }
}
