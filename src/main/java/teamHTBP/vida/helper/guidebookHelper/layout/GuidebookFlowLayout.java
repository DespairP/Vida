package teamHTBP.vida.helper.guidebookHelper.layout;

import com.mojang.blaze3d.vertex.PoseStack;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**流式布局管理器*/
public class GuidebookFlowLayout {
    /**排布间距*/
    public int gap = 4;
    /**边界*/
    Rectangle border = new Rectangle();

    public GuidebookFlowLayout() {}

    public GuidebookFlowLayout(int startX, int startY, int maxHeight, int maxWidth) {
        border.setBounds(startX, startY, maxWidth, maxHeight);
    }

    /**设置间距*/
    public GuidebookFlowLayout setGap(int gap){
        this.gap = gap;
        return this;
    }

    /**
     * 渲染页面
     *
     * @return 未被成功渲染的component
     * */
    public List<IGuidebookComponent> renderComponents(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, final List<IGuidebookComponent> components){
        //剩下未被渲染的组件
        List<IGuidebookComponent> remainComponents = new LinkedList<>(components);
        //记录的占用的大小
        int usedHeight = 0; //已用高度
        int rowUsedWidth = 0; //行已用宽度
        Rectangle row = new Rectangle(border.x, border.y, 0, 0);
        for(IGuidebookComponent component : components){
            // 将要使用的区域
            Rectangle cell = new Rectangle(row.x + row.width, row.y + row.height, component.getWidth(), component.getHeight());
            // 如果行已经超过了使用的区域,流到下一行
            if(!fitRowSpace(rowUsedWidth, cell)){
                usedHeight += row.height;
                //换行
                row = new Rectangle(border.x, row.y + row.height, 0, 0);
                cell.setLocation(row.x,row.y);
            }
            // 如果已经超出高度范围,则取消渲染
            if(usedHeight > border.height){
                break;
            }
            //渲染
            component.setPosition(cell.x, cell.y);
            component.render(matrixStack, mouseX, mouseY, partialTicks);
            //cell加入row
            cell.grow(5,5);
            row.add(cell);
            //将渲染后的组件移除
            remainComponents.remove(component);
        }
        return remainComponents;
    }

    /**
     * 行剩余空间是否能摆放该组件
     *
     * 测试行溢出
     *
     * @param currentUse 现在该行使用的空间
     * @param component 测试区域
     * */
    private boolean fitRowSpace(int currentUse,Rectangle component){
        return border.contains(component);
    }


}
