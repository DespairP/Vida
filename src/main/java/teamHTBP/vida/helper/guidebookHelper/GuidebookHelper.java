package teamHTBP.vida.helper.guidebookHelper;

import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.event.server.datapack.GuideBookGuideEventHandler;
import teamHTBP.vida.event.server.datapack.GuideBookGuideHandler;
import teamHTBP.vida.gui.components.GuiGuidebookManager;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static teamHTBP.vida.event.server.datapack.GuideBookGuideHandler.*;

public class GuidebookHelper {
    /**所有*/
    public static final Map<String,Class<? extends IGuidebookComponent>> GUIDEBOOK_COMPONENTS = new LinkedHashMap<>();
    @OnlyIn(Dist.CLIENT)
    public static final GuiGuidebookManager MANAGER = new GuiGuidebookManager();

    /**获取guidebook的gui管理器*/
    public static GuiGuidebookManager getManager(){
        return MANAGER;
    }

    /**
     * 获取guidebook的所有guide
     *
     * @param world 不同端的world
     * */
    public static GuideBookGuideHandler getGuideHandler(World world){
        if(world.isRemote){
            return GuideBookGuideEventHandler.clientHandler;
        }
        return GuideBookGuideEventHandler.getServerHandler(world);
    }


    public static boolean setComponent(String type, Class<? extends IGuidebookComponent> componentClass){
        return GuidebookHelper.GUIDEBOOK_COMPONENTS.putIfAbsent(type, componentClass) != null;
    }
}
