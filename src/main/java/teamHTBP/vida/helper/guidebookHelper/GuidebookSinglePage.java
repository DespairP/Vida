package teamHTBP.vida.helper.guidebookHelper;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.layout.GuidebookFlowLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * 一篇Guide{@link Guide}的基本一页
 * 一页包含
 * */
public class GuidebookSinglePage {
    /**所属的guide*/
    public String guide;
    /**处于一个第几页*/
    public int page;
    /**这一页所有的可渲染组件*/
    public List<IGuidebookComponent> components = new LinkedList<>();
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
    public void renderPage(MatrixStack matrixStack,int mouseX,int mouseY,float partialTicks){
        List<IGuidebookComponent> remainComponents = components;
        for(GuidebookFlowLayout layout: layouts) {
            if(remainComponents == null || remainComponents.isEmpty()){
                break;
            }
            remainComponents = layout.renderComponents(matrixStack, mouseX, mouseY, partialTicks, remainComponents);
        }
    }

    /***/
    public <T extends IGuiEventListener> List<T> getPageListener(){
        return (List<T>) components;
    }

}
