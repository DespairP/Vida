package teamHTBP.vida.client.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.menu.guidebook.GuiGuidebookGuide;

import java.util.Deque;
import java.util.LinkedList;

/**统一化Guidebook Gui管理器,提供基础的栈gui管理*/
@OnlyIn(Dist.CLIENT)
public class GuiGuidebookManager {
    /**minecraft*/
    public final static Minecraft minecraft = Minecraft.getInstance();
    /**栈,队首显示的是当前screen*/
    private final static Deque<IGuiGuidebook> screenStack = new LinkedList<>();

    /**回到上一页,将会携带此页面的context和新的context到上一页*/
    public void preWithContext(GuidebookContext newContext){
        // 移除最后一个元素
        IGuiGuidebook currentBook = screenStack.pop();
        GuidebookContext context = currentBook.getContext();
        context = context.merge(newContext);
        // 获取上一个屏幕
        IGuiGuidebook preGuidebook = screenStack.peekFirst();
        // 显示上一个屏幕,并且传入context
        if(preGuidebook != null) {
            minecraft.setScreen(preGuidebook.getScreen());
            preGuidebook.handleReopenContext(context);
            return;
        }
        // 如果是null,直接不显示了
        minecraft.setScreen(null);
    }

    /**转到下一页,并且携带上一层的Context*/
    public void nextWithContext(IGuiGuidebook next){
        GuidebookContext newContext = next.getContext();
        IGuiGuidebook preGuidebook = screenStack.peekFirst();
        // 合并context
        if(preGuidebook != null) {
            newContext.merge(preGuidebook.getContext());
        }
        // 打开下一层context
        minecraft.setScreen(next.getScreen());
    }

    /**打开guide*/
    public void openGuidebook(){
        IGuiGuidebook guidebook = screenStack.peekFirst();
        if(guidebook != null){
            minecraft.setScreen(guidebook.getScreen());
            return;
        }
        // if first open
        GuiGuidebookGuide newGuide = new GuiGuidebookGuide();
        screenStack.push(newGuide);
        minecraft.setScreen(newGuide);
    }

    /**强制清空所有栈中的screen*/
    public void forceClear(){
        screenStack.clear();
    }





}
