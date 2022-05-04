package teamHTBP.vida.recipe.altar;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.helper.element.IElement;
import teamHTBP.vida.item.ItemLoader;
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
    /**合成元素*/
    public IElement element;
    /**合成品*/
    public ItemStack result = ItemStack.EMPTY;

    private AltarRecipe(){}

    public AltarRecipe(IElement element, ItemStack result, RecipeObject<?> core, List<RecipeObject<?>> other) {
        this.core = core;
        this.other = other;
        this.element = element;
        this.result = result;
    }

    /**
     * 是否合适进行合成
     * @param width 四周格子数
     * @param height 中心格子
     * */
    @Override
    public boolean canFit(int width, int height) {
        return (width + height) >= 5;
    }

    @Override
    public boolean matches(TileEntity tileEntity) {
        return tileEntity instanceof TileEntityElementCoreAltar;
    }

    /**
     *
     * */
    public boolean matches(TileEntityElementCoreAltar altar) {
        if(altar == null) return false;

        NonNullList<ItemStack> altarItems = altar.altarItem;
        ItemStack coreItem = altar.coreItem;

        return false;
    }

    /**
     * 获取合成结果
     * */
    @Override
    public ItemStack getRecipeOutput() {
        return result.copy();
    }

    /**
     * 获取序列化读取器,用于读取/写入Json
     * */
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeLoader.ALTAR.get();
    }

    /**
     * 获取Builder，用于手动写入合成表
     * */
    public static Builder builder(){return new Builder();}

    /**
     * 序列化Json合成表
     * */
    @Override
    public void serialize(JsonObject json) {
        RecipeLoader.ALTAR.get().write(json, this);
    }



    /**合成图标，用于JEI或合成使用*/
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ItemLoader.altarcubeMaker.get());
    }

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
