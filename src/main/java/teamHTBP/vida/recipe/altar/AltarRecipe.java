package teamHTBP.vida.recipe.altar;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.recipe.RecipeSerializers;
import teamHTBP.vida.recipe.RecipeTypes;
import teamHTBP.vida.recipe.utils.base.BaseTileEntityRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author DustW
 */
public class AltarRecipe extends BaseTileEntityRecipes<AltarRecipe, TileEntityElementCoreAltar> {
    /**核心物品*/
    public ItemStack core;
    /**四周物品*/
    public List<ItemStack> other;
    /**合成元素*/
    public IElement element;
    /**合成品*/
    public ItemStack result = ItemStack.EMPTY;

    private AltarRecipe(){}

    public AltarRecipe(IElement element, ItemStack result, ItemStack core, List<ItemStack> other) {
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
        return false;
    }

    /**
     *
     * */
    public boolean matches(TileEntityElementCoreAltar altar) {
        if(altar == null) return false;

        NonNullList<ItemStack> altarItems = altar.altarItem;
        Item coreItem = Optional.of(altar.coreItem).orElse(ItemStack.EMPTY).getItem();
        List<ItemStack> requiredItems = new ArrayList<>(altarItems);

        if(core.getItem() != coreItem) return false;
        for(ItemStack stack:altarItems){
            //System.out.println(stack);
            if(!requiredItems.contains(stack)){
                return false;
            }
            requiredItems.remove(stack);
        }

        return requiredItems.size() == 0;
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
        return RecipeSerializers.ALTAR.get();
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
        RecipeSerializers.ALTAR.get().write(json, this);
    }



    /**合成图标，用于JEI或合成使用*/
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ItemLoader.altarcubeMaker.get());
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeTypes.ALTAR;
    }

    /**不会出现在合成书中*/
    @Override
    public boolean isDynamic() {
        return false;
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

        public Builder core(Item core){
            recipe.core = new ItemStack(core);
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

        public Builder others(Item o1, Item o2, Item o3, Item o4){
            recipe.other = ImmutableList.of(new ItemStack(o1),
                                            new ItemStack(o2),
                                            new ItemStack(o3),
                                            new ItemStack(o4)
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
