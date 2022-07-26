package teamHTBP.vida.element;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import teamHTBP.vida.capability.skill.ISkill;
import teamHTBP.vida.capability.skill.SkillCategory;
import teamHTBP.vida.capability.skill.SkillHelper;
import teamHTBP.vida.datapack.element.ElementPotential;
import teamHTBP.vida.datapack.element.ElementPotentialManager;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 主要用于元素的计算
 *
 * @Version 0.0.1
 **/
public class ElementHelper {
    /**
     * 一个存放元素值-键的Hashmap
     */
    static Map<Item, ElementPotential> map = ElementPotentialManager.INSTANCE.getByItem();

    /**NBT辅助写入*/
    public static void write(CompoundTag nbt, IElement element) {
        nbt.putString("element", Optional.ofNullable(element).orElse(EnumElements.NONE).getElementName().toString());
    }

    /**NBT辅助读取*/
    public static IElement read(CompoundTag nbt) {
        if (!nbt.contains("element")) {
            return null;
        }

        return ElementManager.get(nbt.getString("element"));
    }

    /**
     * 获得传入物品的Containing（元素量）值
     *
     * @param itemStack 需要获取元素量的物品
     * @return 元素值[如表里未有此元素返回0]
     */
    public static int getContainingNum(ItemStack itemStack) {
        if (map.containsKey(itemStack.getItem()))
            return map.get(itemStack.getItem()).getContaining() * itemStack.getCount();
        return 0;
    }

    /**
     * 获得物品所处的元素
     *
     * @param itemStack 需要获取的物品的元素
     * @return 元素[见上面的常量解释]
     */
    public static IElement getContainingElement(ItemStack itemStack) {
        if (map.containsKey(itemStack.getItem()))
            return map.get(itemStack.getItem()).getElement();
        return EnumElements.NONE;
    }

    /**
     * 查询地形biome所处的元素
     *
     * @param biome 需要获取元素的地形biome实例
     * @return 地形的元素[见上面的常量解释]
     **/
    public static EnumElements getBiomeElement(Biome biome) {
         final List<EnumElements> biomesElementList = Arrays.stream(EnumElements.values()).filter((element) -> element.contains(biome)).collect(Collectors.toList());
         return biomesElementList.size() > 0 ? biomesElementList.get(0) : EnumElements.NONE;
    }

    /**
     * 打开GUI时给物品时，初始化所有物品特有技能的NBT
     *
     * @param stack 需要初始化NBT的物品
     **/
    public static void addTip(ItemStack stack) {
        SkillCategory category = SkillHelper.skillCategories.get(stack.getItem());
        if (category != null) {
            CompoundTag nbt = stack.getOrCreateTag();
            HashMap<String, ISkill> skills = category.getAllSkills();
            skills.forEach((name, skill) -> {
                if (!nbt.contains(name + "Exp")) nbt.putInt(name + "Exp", 1);
                if (!nbt.contains(name + "Level")) nbt.putInt(name + "Level", 1);
            });
        }
    }

    public static Allelopathy getRelationShip(IElement elementA, IElement elementB) {
        return elementA.test(elementB);
    }

}