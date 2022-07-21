package teamHTBP.vida.menu.guidebook;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.client.gui.components.GuiGuidebookManager;
import teamHTBP.vida.client.gui.components.GuidebookContext;
import teamHTBP.vida.client.gui.components.IGuiGuidebook;
import teamHTBP.vida.client.gui.components.IGuidebookPageable;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.renderHelper.RenderHelper;
import teamHTBP.vida.helper.renderHelper.guiHelper.TextureSection;

public abstract class AbstractGuiGuidebook extends Screen implements IGuiGuidebook {
    /**书渲染的x轴位置*/
    public int startX;
    /**书渲染的y轴位置*/
    public int startY;
    /**书相关贴图与渲染位置*/
    public final ResourceLocation book = new ResourceLocation("vida", "textures/gui/book.png");
    public final TextureSection bookSection = new TextureSection(0, 0, 371, 230);
    public final TextureSection pageSection = new TextureSection(19,245,74,17);
    /**上下文*/
    private GuidebookContext context;
    /**状态管理器*/
    public final GuiGuidebookManager manager;

    public AbstractGuiGuidebook(Component titleIn) {
        this(titleIn, GuidebookContext.empty());
    }

    public AbstractGuiGuidebook(Component titleIn, GuidebookContext context){
        super(titleIn);
        this.context = context;
        this.manager = GuidebookHelper.getManager();
        this.handleInitContext(context);
    }

    /*------- 以下用于GuiManager实现 -------*/

    /**实例化时的context*/
    @Override
    public void handleInitContext(GuidebookContext context){}

    /**重新打开时的context*/
    @Override
    public void handleReopenContext(GuidebookContext context){
        this.context = context;
    }

    /**获取此页面过去收到的context*/
    @Override
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
    public void init() {
        // 再次计算
        startX = (width - bookSection.w()) / 2 + 28;
        startY = (height - bookSection.h()) / 2 + 15;
    }

    /**初始化后开始渲染*/
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // 渲染书和背景
        this.renderBackground(matrixStack);
        // 渲染页数
        this.renderPage(matrixStack);


    }

    /**渲染页数,需要子类继承{@link IGuidebookPageable}*/
    public void renderPage(PoseStack matrixStack){
        if(!(this instanceof IGuidebookPageable)){
            return;
        }
        IGuidebookPageable pageable = (IGuidebookPageable) this;
        // 渲染背景
        matrixStack.pushPose();
        int centerX = (width - pageSection.w()) / 2;
        int bottomY = (height - bookSection.h()) / 2 + bookSection.h() - 30;
        blit(matrixStack, centerX, bottomY, 0, pageSection.mu(), pageSection.mv(), pageSection.w(), pageSection.h(), 512, 512);
        matrixStack.popPose();

        //渲染页数文字
        int currentPage = pageable.getCurrentPage();
        int totalPage = pageable.getTotalPage();
        String pageText = currentPage + "/" + totalPage;
        int centerTextX = (width - font.width(pageText)) / 2 + 1;
        matrixStack.pushPose();
        RenderHelper.renderTextWithDungeonFont(matrixStack, pageText, centerTextX, bottomY - 5);
        matrixStack.popPose();
    }


    /**渲染背景和书背景*/
    @Override
    public void renderBackground(PoseStack matrixStack) {
        super.renderBackground(matrixStack);
        this.renderGuidebookBackground(matrixStack);
    }

    /**渲染书*/
    public void renderGuidebookBackground(PoseStack matrixStack){
        matrixStack.pushPose();
        int centerX = (width - bookSection.w()) / 2;
        int centerY = (height - bookSection.h()) / 2;
        RenderSystem.setShaderTexture(0, book);
        blit(matrixStack, centerX, centerY, 0, 0, 0, bookSection.w() , bookSection.h(), 512, 512);
        matrixStack.popPose();
    }


    /*------ 监听器重写 ----- */
    /**拖拽事件改为全局监听*/
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        boolean isDragged =  super.mouseDragged(mouseX, mouseY, button, dragX, dragY);

        for(GuiEventListener iguieventlistener : this.children()){
            iguieventlistener.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        return isDragged;
    }

    /**松开事件改为全局监听*/
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.setDragging(false);
        this.children().forEach((listener) -> {
            listener.mouseReleased(mouseX, mouseY, button);
        });
        return true;
    }

    /**强制关闭gui*/
    public void forceCloseScreen(){
        this.minecraft.setScreen((Screen) null);
    }
}
