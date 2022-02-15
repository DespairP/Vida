package teamHTBP.vida.recipe.altar;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.helper.element.IElement;
import teamHTBP.vida.recipe.RecipeLoader;
import teamHTBP.vida.recipe.RecipesBase;
import teamHTBP.vida.recipe.recipeobj.RecipeObject;
import teamHTBP.vida.recipe.recipeobj.RecipeObjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class AltarRecipe extends RecipesBase<AltarRecipe, TileEntityElementCoreAltar> {
    /**核心物品*/
    public RecipeObject<?> core;
    /**四周物品*/
    public List<RecipeObject<?>> other;
    public IElement element;
    public ItemStack result = ItemStack.EMPTY;

    private AltarRecipe(){

    }

    @Deprecated
    public AltarRecipe(IElement element, ItemStack result, RecipeObject<?> core, List<RecipeObject<?>> other) {
        this.core = core;
        this.other = other;
        this.element = element;
        this.result = result;
    }


    @Override
    public void serialize(JsonObject json) {
        RecipeLoader.ALTAR.get().write(json, this);
    }

    @Override
    public boolean matches(TileEntityElementCoreAltar altar) {
        if (core.matches(altar.coreItem)) {
            ArrayList<Integer> markList = new ArrayList<>();

            a :
            for (RecipeObject<?> recipeObject : other) {
                for (int i1 = 0; i1 < altar.altarItem.length; i1++) {
                    if (recipeObject.matches(altar.altarItem[i1]) && !markList.contains(i1)) {
                        markList.add(i1);
                        continue a;
                    }
                }
            }

            return markList.size() == altar.altarItem.length;
        }

        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result.copy();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeLoader.ALTAR.get();
    }

    public static Builder builder(){return new Builder();}

    /**
     * AltarRecipe建造者
     * [注意]：你必须在填入完core、element、result和others以后才能build，否则会发生问题
     * */
    public static class Builder{
        private AltarRecipe recipe;

        public Builder(){
            this.recipe = new AltarRecipe();
        }

        public Builder core(Object core){
            recipe.core = RecipeObjectType.of(core);
            return this;
        }

        public Builder element(IElement element){
            recipe.element = element;
            return this;
        }

        public Builder result(ItemStack stack){
            if(stack == null) throw new NullPointerException("RecipesBase ResultStack is Null,please check the code");
            recipe.result = stack;
            return this;
        }

        public Builder others(Object o1,Object o2,Object o3,Object o4){
            recipe.other = ImmutableList.of(RecipeObjectType.of(o1),
                                            RecipeObjectType.of(o2),
                                            RecipeObjectType.of(o3),
                                            RecipeObjectType.of(o4)
            );
            return this;
        }

        public AltarRecipe build(){
            if(recipe.other == null) throw new IllegalArgumentException("recipe no relevant data filled in: others");
            if(recipe.element == null) throw new IllegalArgumentException("recipe no relevant data filled in:element");
            if(recipe.core == null) throw new IllegalArgumentException("recipe no relevant data filled in:core");
            return this.recipe;
        }
    }

}
