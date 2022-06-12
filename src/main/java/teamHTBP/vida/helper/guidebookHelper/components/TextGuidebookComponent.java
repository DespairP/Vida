package teamHTBP.vida.helper.guidebookHelper.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import teamHTBP.vida.helper.renderHelper.RenderHelper;
import teamHTBP.vida.utils.math.IntRange;

/**文字渲染组件*/
public class TextGuidebookComponent implements IGuidebookComponent {
    public final String type = "text";
    /**渲染的x位置*/
    public int x;
    /**渲染的y位置*/
    public int y;
    /**渲染文字最大长度,定义后会自动换行*/
    public int maxLength = -1;
    /**渲染文字最大高度,定义后超出会出现滚动条*/
    public int maxHeight = -1;
    /**渲染文字key,对应翻译文字*/
    public String key;
    /**文字渲染器*/
    public FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
    /**最大GUI长度*/
    public static final int MAX_GUI_LENGTH = 196;
    /**滚动大小*/
    public IntRange offset = new IntRange(0,0,0);
    /**组件*/
    private TranslationTextComponent translationText;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getHeight() {
        TranslationTextComponent component = new TranslationTextComponent(key);
        //没有定义组件最大宽度,字体的就是最大高度
        return maxLength == -1 ? fontRenderer.FONT_HEIGHT : fontRenderer.getWordWrappedHeight(component.getString(), maxLength);
    }

    @Override
    public int getWidth() {
        TranslationTextComponent component = new TranslationTextComponent(key);
        //没有定义组件最大宽度,字体的就是最大高度
        return maxLength == -1 ? fontRenderer.getStringWidth(component.getString()) : maxLength;
    }

    @Override
    public void render(MatrixStack matrixStack,int mouseX, int mouseY, float partialTicks) {
        RenderSystem.pushMatrix();

        //如果有定义最大高度,裁剪
        if(maxHeight > 0){
            offset = new IntRange(offset.get(), fontRenderer.getWordWrappedHeight(getTranslation().getString(),maxLength) + 30, 0);
            RenderHelper.renderScissor(x - 1 , y - 1, getWidth(), maxHeight);
        }

        //渲染文字
        fontRenderer.func_238418_a_(getTranslation(), x - 1, y - offset.get(), maxLength, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x + 1, y - offset.get(), maxLength, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get() - 1, maxLength, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get() + 1, maxLength, 0x000000);
        fontRenderer.func_238418_a_(getTranslation(), x, y - offset.get(), maxLength, 0x4CFF00);

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
        if(isScrollable()) {
            //向下滚动delta是负数,所以decrease
            offset.decrease((int) Math.floor(delta * 4));
            return true;
        }
        return false;
    }

    /**是否悬浮*/
    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.getWidth() && mouseY < this.y + this.getHeight();
    }

    /*-------- 其他 --------*/
    /**文本框是否能被滚动*/
    public boolean isScrollable(){
        return maxHeight != -1 && fontRenderer.getWordWrappedHeight(getTranslation().getString(), maxLength) >= maxHeight;
    }

    public int getMaxScrollOffset(){
        return 0;
    }
}
