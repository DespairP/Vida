package teamHTBP.vida.Capability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import teamHTBP.vida.Item.ItemLoader;

import java.util.HashMap;
import java.util.List;

/**
 * 主要用于元素的计算
 * @Version 0.0.1
 * **/
public class ElementHelper {
    /**未知元素[unknown] = 0*/
    /**金[gold] = 1*/
    public final static int ELEMENT_GOLD = 1;
    /**木[wood] = 2*/
    public final static int ELEMENT_WOOD = 2;
    /**水[aqua] = 3*/
    public final static int ELEMENT_AQUA = 3;
    /**火[fire] = 4*/
    public final static int ELEMENT_FIRE = 4;
    /**土[earth] = 5*/
    public final static int ELEMENT_EARTH = 5;

    /**一个存放元素值-键的Hashmap*/
    static HashMap<Item,mapItem> map = new HashMap<Item,mapItem>();

    /*使用该类时自动将物品放入map中*/
    static{
        map.put(Items.DIAMOND, new mapItem(1,1000,"diamond"));  //钻石
        map.put(Items.EMERALD, new mapItem(1,1000,"emerald"));  //绿宝石
        map.put(Items.LAPIS_LAZULI, new mapItem(1,500,"lapis_lazuli"));  //青金石
        map.put(Items.GOLD_INGOT, new mapItem(1,540,"gold_ingot"));  //金锭
        map.put(Items.GOLD_NUGGET, new mapItem(1,60,"gold_nugget"));  //金粒
        map.put(Items.IRON_INGOT, new mapItem(1,450,"iron_ingot"));  //铁锭
        map.put(Items.IRON_NUGGET, new mapItem(1,50,"iron_nugget"));  //铁粒
        map.put(Items.REDSTONE, new mapItem(1,50,"redstone"));  //红石

        map.put(Items.OAK_WOOD, new mapItem(2,200,"oak_wood"));  //橡木
        map.put(Items.OAK_LOG, new mapItem(2,200,"oak_wood"));  //橡木
        map.put(Items.OAK_PLANKS, new mapItem(2,50,"oak_wood_planks"));  //橡木木板
        map.put(Items.SPRUCE_WOOD, new mapItem(2,200,"spruce_wood"));  //云杉木
        map.put(Items.SPRUCE_PLANKS, new mapItem(2,50,"spruce_wood_planks"));  //云杉木木板
        map.put(Items.BIRCH_WOOD, new mapItem(2,200,"birch_wood"));  //白桦木
        map.put(Items.BIRCH_PLANKS, new mapItem(2,50,"birch_wood_planks"));  //白桦木板
        map.put(Items.JUNGLE_WOOD, new mapItem(2,200,"jungle_wood"));  //丛林木
        map.put(Items.JUNGLE_PLANKS, new mapItem(2,50,"jungle_wood_planks"));  //丛林木板
        map.put(Items.ACACIA_WOOD, new mapItem(2,200,"acacia_wood"));  //金合欢木
        map.put(Items.ACACIA_PLANKS, new mapItem(2,50,"acacia_wood_planks"));  //金合欢木板
        map.put(Items.DARK_OAK_WOOD, new mapItem(2,200,"dark_oak_wood"));  //深色橡木
        map.put(Items.DARK_OAK_PLANKS, new mapItem(2,50,"dark_oak_wood_planks"));  //深色橡木木板
        map.put(Items.STICK, new mapItem(2,25,"stick"));  //木棍
        map.put(Items.GRASS, new mapItem(2,1,"grass"));  //草
        map.put(Items.FERN, new mapItem(2,2,"fern"));  //蕨
        map.put(Items.SUGAR_CANE, new mapItem(2,10,"sugar_canes"));  //甘蔗
        map.put(Items.CARROT, new mapItem(5,10,"carrot"));  //胡萝卜
        map.put(Items.POTATO, new mapItem(5,10,"potato"));  //马铃薯
        map.put(Items.COCOA_BEANS, new mapItem(5,50,"cocoa_beans"));  //可可豆

        map.put(Items.SNOWBALL, new mapItem(3,15,"snowball"));  //雪球
        map.put(Items.SNOW, new mapItem(3,15,"snow"));  //雪
        map.put(Items.ICE, new mapItem(3,5,"ice"));  //冰
        map.put(Items.PACKED_ICE, new mapItem(3,50,"packed_ice"));  //浮冰
        map.put(Items.BLUE_ICE, new mapItem(3,100,"blue_ice"));  //蓝冰
        map.put(Items.PRISMARINE_CRYSTALS, new mapItem(3,100,"prismarine_crystals"));  //海晶砂粒
        map.put(Items.BRAIN_CORAL, new mapItem(3,100,"prismarine_shard"));  //珊瑚
        map.put(Items.BUBBLE_CORAL, new mapItem(3,30,"coral"));  //珊瑚



        map.put(Items.BLAZE_ROD, new mapItem(4,200,"blaze_rod "));  //烈焰棒
        map.put(Items.BLAZE_POWDER, new mapItem(4,100,"blaze_powder"));  //烈焰粉
        map.put(Items.NETHERRACK, new mapItem(4,10,"netherrack"));  //地狱岩
        map.put(Items.NETHER_BRICK, new mapItem(4,50,"nether_brick"));  //地狱砖块
        map.put(Items.RED_NETHER_BRICKS, new mapItem(4,100,"red_nether_brick"));  //红色地狱砖


        map.put(Items.DIRT, new mapItem(5,1,"dirt"));  //泥土
        map.put(Items.COARSE_DIRT, new mapItem(5,15,"coarse_dirt"));  //砂土
        map.put(Items.GRAVEL, new mapItem(5,10,"gravel"));  //沙砾
        map.put(Items.FLINT, new mapItem(5,20,"flint"));  //燧石
        map.put(Items.CLAY, new mapItem(5,50,"clay"));  //粘土块
        map.put(Items.STONE, new mapItem(5,10,"stone"));  //石头
        map.put(Items.COBBLESTONE, new mapItem(5,5,"cobblestone"));  //圆石
        map.put(Items.DIORITE, new mapItem(5,50,"diorite"));  //闪长岩
        map.put(Items.GRANITE, new mapItem(5,50,"granite"));  //花岗岩
        map.put(Items.ANDESITE, new mapItem(5,50,"andesite"));  //闪长岩
        map.put(Items.SAND, new mapItem(5,2,"sand"));  //沙子
        map.put(Items.SANDSTONE, new mapItem(5,8,"sandstone"));  //砂岩
        map.put(Items.RED_SAND, new mapItem(5,50,"red_sand"));  //红砂
        map.put(Items.RED_SANDSTONE, new mapItem(5,200,"red_sandstone"));  //红砂岩

        map.put(ItemLoader.goldEnergyGemFragment.get(), new mapItem(1, 500, "goldartificialelementgem"));
        map.put(ItemLoader.woodEnergyGemFragment.get(), new mapItem(2, 500, "woodartificialelementgem"));
        map.put(ItemLoader.aquaEnergyGemFragment.get(), new mapItem(3, 500, "aquaartificialelementgem"));
        map.put(ItemLoader.fireEnergyGemFragment.get(), new mapItem(4, 500, "fireartificialelementgem"));
        map.put(ItemLoader.earthEnergyGemFragment.get(), new mapItem(5, 500,"earthartificialelementgem"));
    }

    //构造方法
    public ElementHelper(){
    }

    /**获得传入物品的Containing（元素量）值
     * @param itemStack 需要获取元素量的物品
     * @return 元素值[如表里未有此元素返回0]
     * */
    public static int getContainingNum(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return map.get(itemStack.getItem()).containing*itemStack.getCount();
        return 0;
    }


    /**获得物品所处的元素
     * @param itemStack 需要获取的物品的元素
     * @return 元素[见上面的常量解释]
     * */
    public static int getContainingElement(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return  map.get(itemStack.getItem()).element;
        return 0;
    }


    /** 查询地形biome所处的元素
     * @param biome 需要获取元素的地形biome实例
     * @return 地形的元素[见上面的常量解释]
     * **/
    public static int getBiomeElement(Biome biome){
        if(biome == Biomes.PLAINS || biome == Biomes.FOREST || biome == Biomes.BAMBOO_JUNGLE
        || biome == Biomes.FLOWER_FOREST || biome == Biomes.JUNGLE || biome == Biomes.BIRCH_FOREST ||
        biome == Biomes.SAVANNA)
            return ELEMENT_WOOD;
        else if(biome == Biomes.MOUNTAINS || biome == Biomes.MOUNTAIN_EDGE)
            return ELEMENT_FIRE;
        else if(biome == Biomes.OCEAN || biome == Biomes.RIVER || biome == Biomes.SNOWY_TAIGA || biome == Biomes.COLD_OCEAN || biome == Biomes.SNOWY_TUNDRA)
            return ELEMENT_AQUA;
        else if(biome == Biomes.ERODED_BADLANDS || biome == Biomes.DARK_FOREST)
            return ELEMENT_EARTH;
        else if(biome == Biomes.DESERT || biome == Biomes.DESERT_LAKES)
            return  ELEMENT_GOLD;
        return 0;
    }


    /**查询祭坛物品是否符合该元素所需的物品
     * @param coreItem 祭坛的核心（物品）
     * @param list 祭坛物品列表
     * @param element 举行仪式的元素
     * @return 列表是否符合该元素所需的所有物品
     * **/
    public static boolean beganAltarProgress(ItemStack coreItem,List<Item> list,int element){
        switch (element){
            case ELEMENT_GOLD: if(coreItem.getItem() == ItemLoader.goldElementCore.get() && list.contains(Items.GOLD_INGOT) && list.contains(Items.DIAMOND) && list.contains(Items.REDSTONE) && list.contains(Items.IRON_INGOT)) return true;
            case ELEMENT_WOOD: if(coreItem.getItem() == ItemLoader.woodElementCore.get() && list.contains(Items.FERN) && list.contains(Items.OAK_LOG) && list.contains(Items.COCOA_BEANS) && list.contains(Items.GREEN_DYE)) return true;
            case ELEMENT_AQUA: if(coreItem.getItem() == ItemLoader.aquaElementCore.get() && list.contains(Items.PRISMARINE_CRYSTALS) && list.contains(Items.ICE) && list.contains(Items.SEA_LANTERN) && list.contains(Items.BRAIN_CORAL)) return true;
            case ELEMENT_FIRE: if(coreItem.getItem() == ItemLoader.fireElementCore.get() && list.contains(Items.BLAZE_POWDER) && list.contains(Items.NETHERRACK) && list.contains(Items.NETHER_WART) && list.contains(Items.BLAZE_ROD)) return true;
            case ELEMENT_EARTH: if(coreItem.getItem() == ItemLoader.earthElementCore.get() && list.contains(Items.DIRT) && list.contains(Items.CLAY) && list.contains(Items.SAND) && list.contains(Items.FLINT)) return true;

        }
        return false;
    }



    /** 打开GUI时给物品时，初始化所有物品特有技能的NBT
     * @param stack 需要初始化NBT的物品
     * **/
    public static void addTip(ItemStack stack){
        if(stack.getItem() instanceof SwordItem){
           addSwordTip(stack);
        }
    }


    /** 给剑（包括元素剑）初始化NBT的
    * @param stack 需要初始化NBT的剑[物品]
    * */
    public static void addSwordTip(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        for(int i = 0;i < SkillHelper.getMaxElementSwordSkill(stack);i++){
            Skill skill = SkillHelper.getSwordSkill(stack, i);
            if(skill == null) break;
            if(!nbt.contains(skill.skillName)){
               nbt.putInt(skill.skillName, 0);
             }
            if(!nbt.contains(skill.skillName+"EXP")){
                nbt.putInt(skill.skillName+"EXP", 1);
            }
        }
    }

}
class mapItem{
    //所处的元素
    int element = 0;
    //所含的元素值
    int containing = 0;
    //物品名字
    String name = "";
    mapItem(){

    }
    //注册物品对应的元素
    mapItem(int element,int containing,String name){
        this.element = element;
        this.containing = containing;
        this.name = name;
    }
}
