package teamHTBP.vida.helper.guidebook.components;

import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.client.event.listener.VidaClientEventHandler;
import teamHTBP.vida.event.server.datapack.guidebook.GuideBookPageHandler;
import teamHTBP.vida.helper.guidebook.GuidebookSinglePage;

/**
 * guidebook组件接口,
 * guidebook组件主要在{@link GuidebookSinglePage}中进行显示,
 * datapack会根据定义的page中的组件进行序列化与反序列化,
 * 对于datapack中组件的反/序列化主要见{@link GuideBookPageHandler},组件会根据{@link IGuidebookComponent#getType()}进行获取组件类型,
 * <br/>
 * <b>***注意：组件类型一定要进行注册,见</b>{@link VidaClientEventHandler#onInitializeClient(FMLClientSetupEvent)}
 * */
public interface IGuidebookComponent extends Widget, GuiEventListener, NarratableEntry {
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

    @Override
    default NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    default boolean isActive() {
        return false;
    }

    @Override
    default void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
