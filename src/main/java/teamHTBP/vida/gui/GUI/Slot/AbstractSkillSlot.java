package teamHTBP.vida.gui.GUI.Slot;

import net.minecraft.client.gui.widget.button.AbstractButton;

public abstract class AbstractSkillSlot extends AbstractButton {
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
        super(xIn, yIn, widthIn, heightIn, msg);
    }

    public void setMouseFouse(boolean mouseFouse) {
        this.mouseFocus = mouseFouse;
    }

    public boolean getMouseFocus(){ return this.mouseFocus; }

    public void setLock(boolean lock){
        this.isLock = lock;
    }

    public boolean isLock(){ return this.isLock;}

    public boolean setComplete(boolean isComplete){ return this.isComplete = isComplete;}

    public void setOverlap(boolean overlap){ this.isOverlap = overlap; }

    public boolean isOverlap(){ return this.isOverlap;}

    public void changeOverlapAlpha(){
        if(this.isOverlap && !this.getMouseFocus()){
            this.active = false;
           this.overlapAlpha = (this.overlapAlpha < 1.0f?this.overlapAlpha + 0.05f:1.0f);
        }else{
            this.active = true;
           this.overlapAlpha = (this.overlapAlpha > 0.0f?this.overlapAlpha - 0.05f:0.0f);
        }
    }
}
