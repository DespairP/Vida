package teamHTBP.vida.helper.itemHelper;

import net.minecraft.world.item.ItemStack;

public class PlayerHoldData {
    public ItemStack holdStack;
    public Long startHoldMillSecond  = 0L;
    public Long endHoldMillSecond = 0L;

    public PlayerHoldData(ItemStack holdStack, Long startHoldMillSecond, Long endHoldMillSecond) {
        this.holdStack = holdStack;
        this.startHoldMillSecond = startHoldMillSecond;
        this.endHoldMillSecond = endHoldMillSecond;
    }

    public Long duration(){
        return endHoldMillSecond - startHoldMillSecond;
    }
}
