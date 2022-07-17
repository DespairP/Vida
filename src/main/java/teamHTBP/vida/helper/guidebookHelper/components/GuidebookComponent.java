package teamHTBP.vida.helper.guidebookHelper.components;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.matrix.MatrixStack;

public abstract class GuidebookComponent implements IGuidebookComponent{
    /**是否被悬浮*/
    protected boolean isHovered;
    /**焦点是否*/
    protected boolean isFocus;
    /**渲染的x位置*/
    @Expose
    public int x;
    /**渲染的y位置*/
    @Expose
    public int y;


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


    /**是否是激活状态*/
    public boolean isActive(){
        return isFocus || isHovered;
    }

    /**是否悬浮*/
    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.getWidth() && mouseY < this.y + this.getHeight();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered){
            isFocus = true;
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        isFocus = false;
        return true;
    }
}
