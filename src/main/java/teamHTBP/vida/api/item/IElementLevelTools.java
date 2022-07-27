package teamHTBP.vida.api.item;

import net.minecraft.item.ItemStack;

/**拥有经验的元素工具*/
public interface IElementLevelTools extends IElementTools{
    /**获取当前工具的等级*/
    public int getCurrentLevel(ItemStack stack);

    /**获取工具下一级所需的经验*/
    public double getNextLevelRequiredXP(ItemStack stack);

    /**获取现在的*/
    public double getCurrentLevelXP(ItemStack stack);
}
