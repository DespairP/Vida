package teamHTBP.vida.gui.GUI.Slot;

import com.google.common.util.concurrent.AtomicDouble;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.LinkedList;

public class BluePrintScreenSlot {
    /**此格子的最大亮度*/
    public double maxLightness = 0.6;
    /**此格子现在的亮度*/
    public double currentLightness = 0;
    /**此格子所处的位置*/
    public BluePrintSlotPosition position;
    /**此格子是否有物品*/
    public boolean hasItem = false;
    /**此格子附近的格子实例*/
    public LinkedList<BluePrintScreenSlot> neibourSlot;

    public BluePrintScreenSlot(BluePrintSlotPosition position) {
        this.position = position;
        this.neibourSlot = new LinkedList<>();
    }

    /**设置此格子周围的格子*/
    public void setNeibourSlots(BluePrintScreenSlot... slots) throws NullPointerException {
        if(neibourSlot != null && slots != null)
            Arrays.stream(slots).forEach(slot->neibourSlot.add(slot));
        else
            throw new NullPointerException("neibourslot or slots is null!");
    }

    /**
     * 设置此格子有没有物品
     * @param stack 此格子的物品
     * */
    public void setHasItem(ItemStack stack){
        if(stack == null || stack == ItemStack.EMPTY) this.hasItem = false;
        else this.hasItem = true;
    }

    /**
     * 刷新最大亮度
     * */
    public void refreshMaxLightness(){
        if(hasItem) {
            maxLightness = 1;
        } else {
            AtomicDouble atomicDouble = new AtomicDouble(0.5);
            neibourSlot.forEach(slot -> {
                if (slot.hasItem) atomicDouble.addAndGet(0.08);
            });
            maxLightness = (atomicDouble.get() > 1 ? 1 : atomicDouble.get() );
        }
    }

    /**
     * 改变亮度并且获取最大亮度值
     **/
    public double changeLightnessAndGet(){
        if (currentLightness < maxLightness){
            currentLightness += 0.02;
            return currentLightness;
        }else if(currentLightness > maxLightness){
            currentLightness -= 0.02;
            return  currentLightness;
        }
        return currentLightness;
    }

    public enum BluePrintSlotPosition{
        TopLeft,TopRight,Left,Middle,Right,BottomLeft,BottomRight;
    }
}