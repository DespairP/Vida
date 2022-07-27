package teamHTBP.vida.api.common.item.tool;

import net.minecraft.world.item.ItemStack;

public interface IElementLevelTools extends IElementTools {
    public int getCurrentLevel(ItemStack stack);

    public double getNextLevelRequiredXP(ItemStack stack);

    public double getCurrentLevelXP(ItemStack stack);
}
