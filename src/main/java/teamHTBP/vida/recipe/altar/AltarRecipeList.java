package teamHTBP.vida.recipe.altar;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.item.ItemLoader;

import java.util.ArrayList;
import java.util.List;

public class AltarRecipeList {
    public static List<AltarRecipe> getDefaultRecipeList() {
        ArrayList<AltarRecipe> result = new ArrayList<>();

        result.add(new AltarRecipe(EnumElements.GOLD, ItemStack.EMPTY,
                new AltarRecipe.Core(ItemLoader.ELEMENTCORE_GOLD.get()),
                new AltarRecipe.Other(
                        Items.GOLD_INGOT,
                        Items.DIAMOND,
                        Items.REDSTONE,
                        Items.IRON_INGOT)));

        result.add(new AltarRecipe(EnumElements.WOOD, ItemStack.EMPTY,
                new AltarRecipe.Core(ItemLoader.ELEMENTCORE_WOOD.get()),
                new AltarRecipe.Other(
                        Items.GOLD_INGOT,
                        Items.DIAMOND,
                        Items.REDSTONE,
                        Items.IRON_INGOT)));

        result.add(new AltarRecipe(EnumElements.AQUA, ItemStack.EMPTY,
                new AltarRecipe.Core(ItemLoader.ELEMENTCORE_AQUA.get()),
                new AltarRecipe.Other(
                        Items.PRISMARINE_CRYSTALS,
                        Items.ICE,
                        Items.SEA_LANTERN,
                        Items.BRAIN_CORAL)));

        result.add(new AltarRecipe(EnumElements.FIRE, ItemStack.EMPTY,
                new AltarRecipe.Core(ItemLoader.ELEMENTCORE_FIRE.get()),
                new AltarRecipe.Other(
                        Items.BLAZE_POWDER,
                        Items.NETHERRACK,
                        Items.NETHER_WART,
                        Items.BLAZE_ROD)));

        result.add(new AltarRecipe(EnumElements.EARTH, ItemStack.EMPTY,
                new AltarRecipe.Core(ItemLoader.ELEMENTCORE_EARTH.get()),
                new AltarRecipe.Other(
                        Items.DIRT,
                        Items.CLAY_BALL,
                        Items.SAND,
                        Items.FLINT)));

        return result;
    }
}
