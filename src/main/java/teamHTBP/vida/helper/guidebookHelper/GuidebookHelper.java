package teamHTBP.vida.helper.guidebookHelper;

import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.event.server.datapack.GuideBookGuideEventHandler;
import teamHTBP.vida.event.server.datapack.GuideBookGuideHandler;
import teamHTBP.vida.event.server.datapack.GuideBookPageEventHandler;
import teamHTBP.vida.event.server.datapack.GuideBookPageHandler;
import teamHTBP.vida.gui.components.GuiGuidebookManager;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;

import java.util.LinkedHashMap;
import java.util.Map;

public class GuidebookHelper {
    /*---------------------guidebook gui管理器 工具方法-------------------------*/
    @OnlyIn(Dist.CLIENT)
    public static final GuiGuidebookManager MANAGER = new GuiGuidebookManager();

    /**获取guidebook的gui管理器*/
    public static GuiGuidebookManager getManager(){
        return MANAGER;
    }

    /*---------------------guidebook datapack 工具方法-------------------------*/

    /**
     * 获取 guidebook 的 GuideManager
     * GuideHandler存有guidebook中所有的guide
     * @param world 不同端的world
     * @return GuideHandler
     * */
    public static GuideBookGuideHandler getGuideHandler(World world){
        if(world == null || world.isClientSide){
            return GuideBookGuideEventHandler.clientHandler;
        }
        return GuideBookGuideEventHandler.getServerHandler(world);
    }

    public static GuideBookPageHandler getGuidePageHandler(World world){
        if(world == null || world.isClientSide){
            return GuideBookPageEventHandler.clientHandler;
        }
        return GuideBookPageEventHandler.getServerHandler(world);
    }

    /*---------------------guidebook component工具方法-------------------------*/

    /**所有的组件*/
    private static final Map<String,Class<? extends IGuidebookComponent>> GUIDEBOOK_COMPONENTS = new LinkedHashMap<>();

    /**
     * 添加guidebook中可用的组件
     * @param type 组件的类型名称
     * @param componentClass 组件的类
     * */
    public static boolean addBookComponent(String type, Class<? extends IGuidebookComponent> componentClass){
        return GuidebookHelper.GUIDEBOOK_COMPONENTS.putIfAbsent(type, componentClass) != null;
    }

    /**
     * 通过type名称获取组件的class
     * @param type 组件的类型名称
     * */
    public static Class<? extends IGuidebookComponent> getComponentByTypeName(String type){
        return GuidebookHelper.GUIDEBOOK_COMPONENTS.get(type);
    }
}
