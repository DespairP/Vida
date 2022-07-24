package teamHTBP.vida.client.gui.screen.guidebook;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Deque;
import java.util.LinkedList;

/**统一化Guidebook Gui管理器,提供基础的栈gui管理*/
@OnlyIn(Dist.CLIENT)
public class GuidebookManager {
    /**minecraft*/
    private final Minecraft minecraft = Minecraft.getInstance();
    /**栈,队首显示的是当前screen*/
    private final static Deque<IGuidebookScreen> SCREEN_STACK = new LinkedList<>();

    /**回到上一页,将会携带此页面的context和新的context到上一页*/
    public void preWithContext(GuidebookContext newContext){
        // 移除最后一个元素
        IGuidebookScreen currentBook = SCREEN_STACK.pop();
        GuidebookContext context = currentBook.getContext();
        context = context.merge(newContext);
        // 获取上一个屏幕
        IGuidebookScreen preGuidebook = SCREEN_STACK.peekFirst();
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
    public void nextWithContext(IGuidebookScreen next){
        GuidebookContext newContext = next.getContext();
        IGuidebookScreen preGuidebook = SCREEN_STACK.peekFirst();
        // 合并context
        if(preGuidebook != null) {
            newContext.merge(preGuidebook.getContext());
        }
        // 打开下一层context
        minecraft.setScreen(next.getScreen());
    }

    /**打开guide*/
    public void openGuidebook(){
        IGuidebookScreen guidebook = SCREEN_STACK.peekFirst();
        if(guidebook != null){
            minecraft.setScreen(guidebook.getScreen());
            return;
        }
        // if first open
        GuidebookPageScreen newGuide = new GuidebookPageScreen();
        SCREEN_STACK.push(newGuide);
        minecraft.setScreen(newGuide);
    }

    /**强制清空所有栈中的screen*/
    public void forceClear(){
        SCREEN_STACK.clear();
    }
}
