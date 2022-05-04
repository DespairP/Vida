package teamHTBP.vida.item.staff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IElementLevelTools extends IElementTools{
    public int getCurrentLevel(ItemStack stack);

    public double getNextLevelRequiredXP(ItemStack stack);

    public double getCurrentLevelXP(ItemStack stack);
}
