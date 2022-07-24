package teamHTBP.vida.helper.guidebook;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebook.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebook.layout.GuidebookFlowLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * 一篇Guide{@link Guide}的基本一页
 * 一页包含
 * */
public class GuidebookSinglePage {
    /**所属的guide*/
    @Expose
    public String guide;
    /**处于一个第几页*/
    @Expose
    public int page;
    /**这一页所有的可渲染组件*/
    @Expose
    public List<IGuidebookComponent> components = new LinkedList<>();
    /**位置*/
    public ResourceLocation resourceLocation;
    /**Logger*/
    public final static Logger LOGGER = LogManager.getLogger();
    /**流式布局*/
    List<GuidebookFlowLayout> layouts = new LinkedList<>();

    public void reset(){
        layouts.clear();
    }

    public void addLayout(int startX,int startY,int maxHeight,int maxWidth){
        layouts.add(new GuidebookFlowLayout(startX, startY, maxHeight, maxWidth));
    }

    /**渲染一整页所有组件*/
    public void renderPage(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks){
        List<IGuidebookComponent> remainComponents = components;
        for(GuidebookFlowLayout layout: layouts) {
            if(remainComponents == null || remainComponents.isEmpty()){
                break;
            }
            remainComponents = layout.renderComponents(matrixStack, mouseX, mouseY, partialTicks, remainComponents);
        }
    }

    /***/
    public List<IGuidebookComponent> getPageListener(){
        return components;
    }

}
