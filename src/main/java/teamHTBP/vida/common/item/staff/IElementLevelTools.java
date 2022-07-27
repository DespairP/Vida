package teamHTBP.vida.common.item.staff;

import net.minecraft.item.ItemStack;

public interface IElementLevelTools extends IElementTools{
    public int getCurrentLevel(ItemStack stack);

    public double getNextLevelRequiredXP(ItemStack stack);

    public double getCurrentLevelXP(ItemStack stack);
}
