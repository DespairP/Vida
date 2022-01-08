package teamHTBP.vida.helper.element;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.biome.Biome;
import teamHTBP.vida.capability.skillSystem.ISkill;
import teamHTBP.vida.capability.skillSystem.SkillCategory;
import teamHTBP.vida.capability.skillSystem.SkillHelper;
import teamHTBP.vida.helper.Allelopathy;
import teamHTBP.vida.item.ItemLoader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
    static HashMap<Item, mapItem> map = new HashMap<Item, mapItem>();

    /*使用该类时自动将物品放入map中*/
    static {
        map.put(Items.DIAMOND, new mapItem(EnumElements.GOLD, 1000, "diamond"));  //钻石
        map.put(Items.EMERALD, new mapItem(EnumElements.GOLD, 1000, "emerald"));  //绿宝石
        map.put(Items.LAPIS_LAZULI, new mapItem(EnumElements.GOLD, 500, "lapis_lazuli"));  //青金石
        map.put(Items.GOLD_INGOT, new mapItem(EnumElements.GOLD, 540, "gold_ingot"));  //金锭
        map.put(Items.GOLD_NUGGET, new mapItem(EnumElements.GOLD, 60, "gold_nugget"));  //金粒
        map.put(Items.IRON_INGOT, new mapItem(EnumElements.GOLD, 450, "iron_ingot"));  //铁锭
        map.put(Items.IRON_NUGGET, new mapItem(EnumElements.GOLD, 50, "iron_nugget"));  //铁粒
        map.put(Items.REDSTONE, new mapItem(EnumElements.GOLD, 50, "redstone"));  //红石

        map.put(Items.OAK_WOOD, new mapItem(EnumElements.WOOD, 200, "oak_wood"));  //橡木
        map.put(Items.OAK_LOG, new mapItem(EnumElements.WOOD, 200, "oak_wood"));  //橡木
        map.put(Items.OAK_PLANKS, new mapItem(EnumElements.WOOD, 50, "oak_wood_planks"));  //橡木木板
        map.put(Items.SPRUCE_WOOD, new mapItem(EnumElements.WOOD, 200, "spruce_wood"));  //云杉木
        map.put(Items.SPRUCE_PLANKS, new mapItem(EnumElements.WOOD, 50, "spruce_wood_planks"));  //云杉木木板
        map.put(Items.BIRCH_WOOD, new mapItem(EnumElements.WOOD, 200, "birch_wood"));  //白桦木
        map.put(Items.BIRCH_PLANKS, new mapItem(EnumElements.WOOD, 50, "birch_wood_planks"));  //白桦木板
        map.put(Items.JUNGLE_WOOD, new mapItem(EnumElements.WOOD, 200, "jungle_wood"));  //丛林木
        map.put(Items.JUNGLE_PLANKS, new mapItem(EnumElements.WOOD, 50, "jungle_wood_planks"));  //丛林木板
        map.put(Items.ACACIA_WOOD, new mapItem(EnumElements.WOOD, 200, "acacia_wood"));  //金合欢木
        map.put(Items.ACACIA_PLANKS, new mapItem(EnumElements.WOOD, 50, "acacia_wood_planks"));  //金合欢木板
        map.put(Items.DARK_OAK_WOOD, new mapItem(EnumElements.WOOD, 200, "dark_oak_wood"));  //深色橡木
        map.put(Items.DARK_OAK_PLANKS, new mapItem(EnumElements.WOOD, 50, "dark_oak_wood_planks"));  //深色橡木木板
        map.put(Items.STICK, new mapItem(EnumElements.WOOD, 25, "stick"));  //木棍
        map.put(Items.GRASS, new mapItem(EnumElements.WOOD, 1, "grass"));  //草
        map.put(Items.FERN, new mapItem(EnumElements.WOOD, 2, "fern"));  //蕨
        map.put(Items.SUGAR_CANE, new mapItem(EnumElements.WOOD, 10, "sugar_canes"));  //甘蔗

        map.put(Items.CARROT, new mapItem(EnumElements.EARTH, 10, "carrot"));  //胡萝卜
        map.put(Items.POTATO, new mapItem(EnumElements.EARTH, 10, "potato"));  //马铃薯
        map.put(Items.COCOA_BEANS, new mapItem(EnumElements.EARTH, 50, "cocoa_beans"));  //可可豆

        map.put(Items.SNOWBALL, new mapItem(EnumElements.AQUA, 15, "snowball"));  //雪球
        map.put(Items.SNOW, new mapItem(EnumElements.AQUA, 15, "snow"));  //雪
        map.put(Items.ICE, new mapItem(EnumElements.AQUA, 5, "ice"));  //冰
        map.put(Items.PACKED_ICE, new mapItem(EnumElements.AQUA, 50, "packed_ice"));  //浮冰
        map.put(Items.BLUE_ICE, new mapItem(EnumElements.AQUA, 100, "blue_ice"));  //蓝冰
        map.put(Items.PRISMARINE_CRYSTALS, new mapItem(EnumElements.AQUA, 100, "prismarine_crystals"));  //海晶砂粒
        map.put(Items.BRAIN_CORAL, new mapItem(EnumElements.AQUA, 100, "prismarine_shard"));  //珊瑚
        map.put(Items.BUBBLE_CORAL, new mapItem(EnumElements.AQUA, 30, "coral"));  //珊瑚


        map.put(Items.BLAZE_ROD, new mapItem(EnumElements.FIRE, 200, "blaze_rod "));  //烈焰棒
        map.put(Items.BLAZE_POWDER, new mapItem(EnumElements.FIRE, 100, "blaze_powder"));  //烈焰粉
        map.put(Items.NETHERRACK, new mapItem(EnumElements.FIRE, 10, "netherrack"));  //地狱岩
        map.put(Items.NETHER_BRICK, new mapItem(EnumElements.FIRE, 50, "nether_brick"));  //地狱砖块
        map.put(Items.RED_NETHER_BRICKS, new mapItem(EnumElements.FIRE, 100, "red_nether_brick"));  //红色地狱砖


        map.put(Items.DIRT, new mapItem(EnumElements.EARTH, 1, "dirt"));  //泥土
        map.put(Items.COARSE_DIRT, new mapItem(EnumElements.EARTH, 15, "coarse_dirt"));  //砂土
        map.put(Items.GRAVEL, new mapItem(EnumElements.EARTH, 10, "gravel"));  //沙砾
        map.put(Items.FLINT, new mapItem(EnumElements.EARTH, 20, "flint"));  //燧石
        map.put(Items.CLAY, new mapItem(EnumElements.EARTH, 50, "clay"));  //粘土块
        map.put(Items.STONE, new mapItem(EnumElements.EARTH, 10, "stone"));  //石头
        map.put(Items.COBBLESTONE, new mapItem(EnumElements.EARTH, 5, "cobblestone"));  //圆石
        map.put(Items.DIORITE, new mapItem(EnumElements.EARTH, 50, "diorite"));  //闪长岩
        map.put(Items.GRANITE, new mapItem(EnumElements.EARTH, 50, "granite"));  //花岗岩
        map.put(Items.ANDESITE, new mapItem(EnumElements.EARTH, 50, "andesite"));  //闪长岩
        map.put(Items.SAND, new mapItem(EnumElements.EARTH, 2, "sand"));  //沙子
        map.put(Items.SANDSTONE, new mapItem(EnumElements.EARTH, 8, "sandstone"));  //砂岩
        map.put(Items.RED_SAND, new mapItem(EnumElements.EARTH, 50, "red_sand"));  //红砂
        map.put(Items.RED_SANDSTONE, new mapItem(EnumElements.EARTH, 200, "red_sandstone"));  //红砂岩

        map.put(ItemLoader.goldEnergyGemFragment.get(), new mapItem(EnumElements.GOLD, 500, "ARTIFICIAL_ELEMENTGEM_GOLD"));
        map.put(ItemLoader.woodEnergyGemFragment.get(), new mapItem(EnumElements.WOOD, 500, "ARTIFICIAL_ELEMENTGEM_WOOD"));
        map.put(ItemLoader.aquaEnergyGemFragment.get(), new mapItem(EnumElements.AQUA, 500, "ARTIFICIAL_ELEMENTGEM_AQUA"));
        map.put(ItemLoader.fireEnergyGemFragment.get(), new mapItem(EnumElements.FIRE, 500, "ARTIFICIAL_ELEMENTGEM_FIRE"));
        map.put(ItemLoader.earthEnergyGemFragment.get(), new mapItem(EnumElements.EARTH, 500, "ARTIFICIAL_ELEMENTGEM_EARTH"));

        map.put(ItemLoader.ELEMENTCORE_GOLD.get(), new mapItem(EnumElements.GOLD, 10000, "goldelementcore"));
        map.put(ItemLoader.ELEMENTCORE_WOOD.get(), new mapItem(EnumElements.WOOD, 10000, "woodelementcore"));
        map.put(ItemLoader.ELEMENTCORE_AQUA.get(), new mapItem(EnumElements.AQUA, 10000, "ELEMENTCORE_AQUA"));
        map.put(ItemLoader.ELEMENTCORE_FIRE.get(), new mapItem(EnumElements.FIRE, 10000, "ELEMENTCORE_FIRE"));
        map.put(ItemLoader.ELEMENTCORE_EARTH.get(), new mapItem(EnumElements.EARTH, 10000, "ELEMENTCORE_EARTH"));

        map.put(ItemLoader.CREATIVE_ELEMENTPOTION_GOLD.get(), new mapItem(EnumElements.GOLD, 500000, "CREATIVE_ELEMENTPOTION_GOLD"));
        map.put(ItemLoader.CREATIVE_ELEMENTPOTION_WOOD.get(), new mapItem(EnumElements.WOOD, 500000, "CREATIVE_ELEMENTPOTION_WOOD"));
        map.put(ItemLoader.CREATIVE_ELEMENTPOTION_AQUA.get(), new mapItem(EnumElements.AQUA, 500000, "CREATIVE_ELEMENTPOTION_AQUA"));
        map.put(ItemLoader.CREATIVE_ELEMENTPOTION_FIRE.get(), new mapItem(EnumElements.FIRE, 500000, "CREATIVE_ELEMENTPOTION_FIRE"));
        map.put(ItemLoader.CREATIVE_ELEMENTPOTION_EARTH.get(), new mapItem(EnumElements.EARTH, 500000, "CREATIVE_ELEMENTPOTION_EARTH"));
    }


    /**NBT辅助写入*/
    public static void write(CompoundNBT nbt, IElement element) {
        nbt.putString("element", Optional.ofNullable(element).orElseGet(()->EnumElements.NONE).getElementName());
    }

    /**NBT辅助读取*/
    public static IElement read(CompoundNBT nbt) {
        if (!nbt.contains("element")) {
            return null;
        }

        return EnumElements.valueOf(nbt.getString("element"));
    }

    /**
     * 获得传入物品的Containing（元素量）值
     *
     * @param itemStack 需要获取元素量的物品
     * @return 元素值[如表里未有此元素返回0]
     */
    public static int getContainingNum(ItemStack itemStack) {
        if (map.containsKey(itemStack.getItem()))
            return map.get(itemStack.getItem()).containing * itemStack.getCount();
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
            return map.get(itemStack.getItem()).element;
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
     * 查询祭坛物品是否符合该元素所需的物品
     *
     * @param coreItem 祭坛的核心（物品）
     * @param list     祭坛物品列表
     * @param element  举行仪式的元素
     * @return 列表是否符合该元素所需的所有物品
     * <p>
     * todo 之后将element改为IElement
     **/
    public static boolean beganAltarProgress(ItemStack coreItem, List<Item> list, IElement element) {
        if (element == EnumElements.GOLD && coreItem.getItem() == ItemLoader.ELEMENTCORE_GOLD.get() &&
                list.contains(Items.GOLD_INGOT) &&
                list.contains(Items.DIAMOND) &&
                list.contains(Items.REDSTONE) &&
                list.contains(Items.IRON_INGOT)) {
            return true;
        } else if (element == EnumElements.WOOD && coreItem.getItem() == ItemLoader.ELEMENTCORE_WOOD.get() &&
                list.contains(Items.FERN) &&
                list.contains(Items.OAK_LOG) &&
                list.contains(Items.COCOA_BEANS) &&
                list.contains(Items.GREEN_DYE)) {
            return true;
        } else if (element == EnumElements.AQUA && coreItem.getItem() == ItemLoader.ELEMENTCORE_AQUA.get() &&
                list.contains(Items.PRISMARINE_CRYSTALS) &&
                list.contains(Items.ICE) &&
                list.contains(Items.SEA_LANTERN) &&
                list.contains(Items.BRAIN_CORAL)) {
            return true;
        } else if (element == EnumElements.FIRE && coreItem.getItem() == ItemLoader.ELEMENTCORE_FIRE.get() &&
                list.contains(Items.BLAZE_POWDER) &&
                list.contains(Items.NETHERRACK) &&
                list.contains(Items.NETHER_WART) &&
                list.contains(Items.BLAZE_ROD)) {
            return true;
        } else return element == EnumElements.EARTH && coreItem.getItem() == ItemLoader.ELEMENTCORE_EARTH.get() &&
                list.contains(Items.DIRT) &&
                list.contains(Items.CLAY_BALL) &&
                list.contains(Items.SAND) &&
                list.contains(Items.FLINT);
    }


    /**
     * 打开GUI时给物品时，初始化所有物品特有技能的NBT
     *
     * @param stack 需要初始化NBT的物品
     **/
    public static void addTip(ItemStack stack) {
        SkillCategory category = SkillHelper.skillCategories.get(stack.getItem());
        if (category != null) {
            CompoundNBT nbt = stack.getOrCreateTag();
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

class mapItem {
    //所处的元素
    IElement element;
    //所含的元素值
    int containing = 0;
    //物品名字
    String name = "";

    mapItem() {

    }

    //注册物品对应的元素
    mapItem(IElement element, int containing, String name) {
        this.element = element;
        this.containing = containing;
        this.name = name;
    }
}
