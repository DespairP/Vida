package teamHTBP.vida.helper.guidebookHelper.components;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import teamHTBP.vida.helper.renderHelper.RenderHelper;
import teamHTBP.vida.helper.renderHelper.TextureSection;
import teamHTBP.vida.utils.math.IntRange;

import static net.minecraft.client.gui.AbstractGui.blit;

/**文字渲染组件*/
public class TextGuidebookComponent extends GuidebookComponent implements IGuidebookComponent {
    @Expose
    public final String type = "text";
    /**渲染文字最大长度,定义后会自动换行*/
    @Expose
    public int maxLength = -1;
    /**渲染文字最大高度,定义后超出会出现滚动条*/
    @Expose
    public int maxHeight = -1;
    /**渲染文字key,对应翻译文字*/
    @Expose
    public String key;
    /**文字渲染器*/
    public FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
    /**最大GUI长度*/
    public static final int MAX_GUI_LENGTH = 126;
    /**滚动大小*/
    public IntRange offset = new IntRange(0,0,0);
    /**组件*/
    private TranslationTextComponent translationText;
    /**滚动条*/
    private final TextureManager textureManager = Minecraft.getInstance().textureManager;
    private final static ResourceLocation componentLocation = new ResourceLocation("vida","textures/gui/book.png");
    private final TextureSection scrollBarOuterTop = new TextureSection(0, 311, 8, 4);
    private final TextureSection scrollBarOuterTracker = new TextureSection(0, 313, 8, 46);
    private final TextureSection scrollBarOuterBottom = new TextureSection(0, 359, 8, 2);
    /**滚动条指示条*/
    private final TextureSection scrollPointerTop = new TextureSection(9, 311, 6, 3);
    private final TextureSection scrollPointerTracker = new TextureSection(9, 313, 6, 45);
    private final TextureSection scrollPointerBottom = new TextureSection(9, 358, 6, 2);

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getHeight() {
        int actualHeight = getTextHeight();
        //没有定义组件最大高度,计算后的高度就是组件的高度,或者是Min(实际高度,最大高度)
        return maxHeight == -1 ? actualHeight : Math.min(actualHeight, maxHeight);
    }

    @Override
    public int getWidth() {
        //没有定义组件最大宽度,计算的宽度就是组件的宽度
        return maxLength == -1 ? fontRenderer.getStringWidth(translationText.getString()) : maxLength;
    }


    /**
     * 获取整个文字高度,包括超出容器的高度
     * case 1:如果没定义最大宽度,字体高度就是fontHeight
     * case 2:如果定义了最大宽度,那么就是计算后的height
     * */
    public int getTextHeight(){
        TranslationTextComponent component = new TranslationTextComponent(key);
        return maxLength == -1 ? fontRenderer.getWordWrappedHeight(component.getString(), maxLength) : maxHeight;
    }

    @Override
    public void render(MatrixStack matrixStack,int mouseX, int mouseY, float partialTicks) {
        //首先提前加载组件
        getTranslation();
        //检测是否悬浮在该组件上
        mouseMoved(mouseX, mouseY);
        //
        renderScrollBar(matrixStack,partialTicks);
        //渲染字体
        renderText(matrixStack, partialTicks);
    }

    /**渲染滚动条*/
    public void renderScrollBar(MatrixStack matrixStack, float partialTicks){
        //如果不能滚动,不渲染滚动条
        if(!isScrollable() || !isActive()){
            return;
        }

        renderTracker(matrixStack,partialTicks);
        renderTrackButton(matrixStack, partialTicks);
    }

    public void renderTracker(MatrixStack matrixStack, float partialTicks){
        //计算滚动条大小
        int trackerHeight = Math.max(getHeight() - 4, 4);
        //根据组件长度拉长,单位长度会相应的被拉长,trackerScaledFactor计算拉长了多少倍
        float trackerScaledFactor = trackerHeight / 46.0f;
        int trackerRealYPosition = (int)Math.ceil((y + 2) * (1.0f / trackerScaledFactor));

        //如果可以滚动,渲染滚动条
        RenderSystem.pushMatrix();
        textureManager.bindTexture(componentLocation);
        //渲染tracker顶部
        blit(matrixStack, x + getWidth(), y + 0, 0, scrollBarOuterTop.mu(), scrollBarOuterTop.mv(), scrollBarOuterTop.w(), scrollBarOuterTop.h(), 512, 512);
        //渲染tracker中部
        matrixStack.push();
        matrixStack.scale(1, trackerScaledFactor,1);
        blit(matrixStack, x + getWidth(), trackerRealYPosition, 0, scrollBarOuterTracker.mu(), scrollBarOuterTracker.mv(), scrollBarOuterTracker.w(), scrollBarOuterTracker.h(), 512, 512);
        matrixStack.pop();
        //渲染tracker底部
        blit(matrixStack, x + getWidth(), y + trackerHeight + 2, 0, scrollBarOuterBottom.mu(), scrollBarOuterBottom.mv(), scrollBarOuterBottom.w(), scrollBarOuterBottom.h(), 512, 512);
        RenderSystem.popMatrix();
    }

    public void renderTrackButton(MatrixStack matrixStack, float partialTicks){
        RenderSystem.pushMatrix();

        //渲染icon
        int yoffset = offset.get();
        int textHeight = fontRenderer.getWordWrappedHeight(getTranslation().getString(), maxLength);
        int pointerHeight = (int)((getHeight() * 45.0f / textHeight));
        float pointerScaledFactor = pointerHeight / 45.0f;
        blit(matrixStack, x + getWidth() + 1, y + 1 + (int)Math.floor((yoffset) * pointerScaledFactor), 0, scrollPointerTop.mu(), scrollPointerTop.mv(), scrollPointerTop.w(), scrollPointerTop.h(), 512, 512);
        //根据组件单独拉长
        matrixStack.push();
        matrixStack.scale(1, pointerScaledFactor,1);
        blit(matrixStack, x + getWidth() + 1, (int)Math.ceil((y + 3) * (1.0f / pointerScaledFactor)) + yoffset , 0, scrollPointerTracker.mu(), scrollPointerTracker.mv(), scrollPointerTracker.w(), scrollPointerTracker.h(), 512, 512);
        matrixStack.pop();
        blit(matrixStack, x + getWidth() + 1, y + 3 + pointerHeight + (int)Math.floor((yoffset) * pointerScaledFactor), 0, scrollPointerBottom.mu(), scrollPointerBottom.mv(), scrollPointerBottom.w(), 2, 512, 512);

        RenderSystem.popMatrix();
    }

    /**渲染文字*/
    public void renderText(MatrixStack matrixStack, float partialTicks){
        RenderSystem.pushMatrix();

        //如果有定义最大高度,裁剪
        if(maxHeight > 0){
            offset = new IntRange(offset.get(), getMaxScrollOffset(), 0);
            RenderHelper.renderScissor(x - 1 , y - 1, getWidth(), maxHeight);
        }

        //
        int renderWidth = getWidth();

        //渲染文字
        fontRenderer.func_238418_a_(getTranslation(), x - 1, y - offset.get(), renderWidth + 0, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x + 1, y - offset.get(), renderWidth + 0, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get() - 1, renderWidth + 0, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get() + 1, renderWidth + 0, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get(), renderWidth + 0, 0x4CFF00);

        //结束渲染
        RenderSystem.disableScissor();
        RenderSystem.popMatrix();
    }

    /**获取翻译文字*/
    private TranslationTextComponent getTranslation(){
        if(translationText == null || !key.equals(translationText.getKey())){
            translationText = new TranslationTextComponent(key);
        }
        return translationText;
    }

    /**初始化*/
    public static TextGuidebookComponent create(String key,int x,int y,int maxHeight,int maxLength){
        TextGuidebookComponent component = new TextGuidebookComponent();
        component.key = key;
        component.x = x;
        component.y = y;
        component.maxLength = maxLength;
        component.maxHeight = maxHeight;
        return component;
    }

    /*------- 监听器 ---------*/

    /**当滚动时,滚动内容*/
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if(isScrollable() && isActive()) {
            //向下滚动delta是负数,所以decrease
            offset.decrease((int) Math.floor(delta * 4));
            return true;
        }
        return false;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        this.isHovered = isMouseOver(mouseX,mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if(isScrollable() && isFocus) {
            offset.decrease((int) Math.floor(dragY));
            return true;
        }
        return false;
    }

    /*-------- 其他 --------*/
    /**文本框是否能被滚动*/
    public boolean isScrollable(){
        if(maxHeight == -1){
            return false;
        }
        return fontRenderer.getWordWrappedHeight(getTranslation().getString(), maxLength) >= maxHeight;
    }

    /**获取最大滚动高度*/
    public int getMaxScrollOffset(){
        TranslationTextComponent component = new TranslationTextComponent(key);
        return Math.max(fontRenderer.getWordWrappedHeight(component.getString(), maxLength) - Math.abs(maxHeight) , 0);
    }
}
