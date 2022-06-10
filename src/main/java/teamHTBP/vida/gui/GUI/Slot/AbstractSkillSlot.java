package teamHTBP.vida.gui.GUI.Slot;

import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.capability.skillSystem.SkillSurface;

public abstract class AbstractSkillSlot extends AbstractButton {
    //点击标记监听器
    public FocusChangeListener listener;
    //焦点是否在这个按钮上
    protected boolean mouseFocus;
    //是否是锁住的技能
    protected boolean isLock;
    //是否是完成能量注入的技能
    protected boolean isComplete;
    //主要计算是否和UI重叠，如果重叠了alpha+=0.05
    protected float overlapAlpha = 0;
    //是否重叠
    protected boolean isOverlap = false;

    public AbstractSkillSlot(int xIn, int yIn, int widthIn, int heightIn, String msg) {
        super(xIn, yIn, widthIn, heightIn, new StringTextComponent(msg));
    }

    /**
     * 设置改技能框是否取得焦点
     */
    public void setMouseFouse(boolean mouseFouse) {
        this.mouseFocus = mouseFouse;
    }

    /**
     * 获取该技能框是否取得焦点
     */
    public boolean getMouseFocus() {
        return this.mouseFocus;
    }

    /**
     * 获取该技能是否被上锁
     */
    public boolean isLock() {
        return this.isLock;
    }

    /**
     * 设置该技能是否被上锁
     */
    public void setLock(boolean lock) {
        this.isLock = lock;
    }

    /**
     * 设置该技能是否被完成
     */
    public boolean setComplete(boolean isComplete) {
        return this.isComplete = isComplete;
    }

    /**
     * 获取技能框是否被重叠
     */
    public boolean isOverlap() {
        return this.isOverlap;
    }

    /**
     * 设置该技能框是否重叠在其他UI之上
     */
    public void setOverlap(boolean overlap) {
        this.isOverlap = overlap;
    }

    /**
     * TODO
     */
    public void changeOverlapAlpha() {
        if (this.isOverlap && !this.getMouseFocus()) {
            this.active = false;
            this.overlapAlpha = (this.overlapAlpha < 1.0f ? this.overlapAlpha + 0.05f : 1.0f);
        } else {
            this.active = true;
            this.overlapAlpha = (this.overlapAlpha > 0.0f ? this.overlapAlpha - 0.05f : 0.0f);
        }
    }

    public abstract void updateSkillSlotInfo(int InX, int InY, int offsetX, int offsetY,int mouseX,int mouseY);

    public abstract SkillSurface getSkillSurface();

    public interface FocusChangeListener {
        void onClick(String skillName, int index, AbstractSkillSlot slot);
    }
}
