package teamHTBP.vida.event.custom;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.lifecycle.IModBusEvent;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;

/**
 * 用于guidebook组件注册的事件
 * 储存全部注册的组件,见{@link GuidebookHelper#GUIDEBOOK_COMPONENTS}
 * */
@Deprecated
@Cancelable
public class GuidebookComponentRegisterEvent extends Event implements IModBusEvent {

    public GuidebookComponentRegisterEvent(){

    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    /**
     * 注册组件
     *
     * @return 是否已经注册
     * */
    public boolean setComponent(String type, Class<? extends IGuidebookComponent> componentClass){
        return false;
    }


}
