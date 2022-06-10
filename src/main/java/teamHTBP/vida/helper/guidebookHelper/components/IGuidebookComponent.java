package teamHTBP.vida.helper.guidebookHelper.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;

/**guidebook组件*/
public interface IGuidebookComponent extends IRenderable, IGuiEventListener {
    /**获取组件类型*/
    public String getType();
    /**渲染的x位置*/
    public int x();
    /**渲染的y位置*/
    public int y();
    /**设置渲染的x位置*/
    public void setX(int x);
    /**设置渲染的y位置*/
    public void setY(int y);
    /**设置渲染位置*/
    public default void setPosition(int x,int y){
        setX(x);
        setY(y);
    }
    /**获取组件高度*/
    public int getHeight();
    /**获取组件宽度*/
    public int getWidth();
}
