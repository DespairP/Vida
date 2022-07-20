package teamHTBP.vida.recipe.recipe;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.RecipeMatcher;
import teamHTBP.vida.blockentity.TileEntityElementCoreAltar;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.recipe.RecipeSerializers;
import teamHTBP.vida.recipe.RecipeTypes;
import teamHTBP.vida.recipe.base.BaseRecipe;

import java.util.List;

/**
 * @author DustW
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AltarRecipe extends BaseRecipe<Container> {
    /**核心物品*/
    @Expose public Ingredient core;
    /**四周物品*/
    @Expose public List<Ingredient> other;
    /**合成元素*/
    @Expose public IElement element;
    /**合成品*/
    @Expose public ItemStack result = ItemStack.EMPTY;

    /**
     * 是否合适进行合成
     * @param width 四周格子数
     * @param height 中心格子
     * */
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return (width + height) >= 5;
    }


    /**
     *
     * */
    public boolean matches(TileEntityElementCoreAltar altar) {
        return core.test(altar.coreItem) && RecipeMatcher.findMatches(altar.altarItem, other) != null;
    }

    /**
     * 获取合成结果
     * */
    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    /**
     * 获取序列化读取器,用于读取/写入Json
     *
     * @return*/
    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.ALTAR.get();
    }

    /**
     * 获取Builder，用于手动写入合成表
     * */
    public static Builder builder(){return new Builder();}


    /**合成图标，用于JEI或合成使用*/
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ItemLoader.altarcubeMaker.get());
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.ALTAR;
    }

    /**不会出现在合成书中*/
    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
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

        public Builder core(Ingredient core){
            recipe.core = core;
            return this;
        }

        public Builder core(Item core){
            recipe.core = Ingredient.of(core);
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
            recipe.other = ImmutableList.of(Ingredient.of(o1),
                                            Ingredient.of(o2),
                                            Ingredient.of(o3),
                                            Ingredient.of(o4)
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
