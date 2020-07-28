package teamHTBP.vida.Capability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import teamHTBP.vida.Item.ItemLoader;

import java.util.HashMap;

/**
 * 主要用于元素的计算
 * @Version 0.0.1
 * **/
public class ElementHelper {
    //五元素数值
    public final static int ELEMENT_GOLD = 1;
    public final static int ELEMENT_WOOD = 2;
    public final static int ELEMENT_AQUA = 3;
    public final static int ELEMENT_FIRE = 4;
    public final static int ELEMENT_EARTH = 5;

    static HashMap<Item,mapItem> map = new HashMap<Item,mapItem>();
    static boolean isGenerate = false;
    public ElementHelper(){
        if(!isGenerate) {
            map.put(Items.DIAMOND, new mapItem(1, 1000, "diamond"));  //钻石
            map.put(Items.EMERALD, new mapItem(1, 1000, "emerald"));  //绿宝石
            map.put(Items.LAPIS_LAZULI, new mapItem(1, 50, "lapis_lazuli"));  //青金石
            map.put(Items.GOLD_INGOT, new mapItem(1, 135, "gold_ingot"));  //金锭
            map.put(Items.GOLD_NUGGET, new mapItem(1, 15, "gold_nugget"));  //金粒
            map.put(Items.IRON_INGOT, new mapItem(1, 135, "iron_ingot"));  //铁锭
            map.put(Items.IRON_NUGGET, new mapItem(1, 15, "iron_nugget"));  //铁粒
            map.put(Items.REDSTONE, new mapItem(1, 15, "redstone"));  //红石

            map.put(Items.OAK_WOOD, new mapItem(1, 100, "oak_wood"));  //橡木
            map.put(Items.OAK_PLANKS, new mapItem(1, 25, "oak_wood_planks"));  //橡木木板
            map.put(Items.SPRUCE_WOOD, new mapItem(1, 100, "spruce_wood"));  //云杉木
            map.put(Items.SPRUCE_PLANKS, new mapItem(1, 25, "spruce_wood_planks"));  //云杉木木板
            map.put(Items.BIRCH_WOOD, new mapItem(1, 100, "birch_wood"));  //白桦木
            map.put(Items.BIRCH_PLANKS, new mapItem(1, 25, "birch_wood_planks"));  //白桦木板
            map.put(Items.JUNGLE_WOOD, new mapItem(1, 100, "jungle_wood"));  //丛林木
            map.put(Items.JUNGLE_PLANKS, new mapItem(1, 25, "jungle_wood_planks"));  //丛林木板
            map.put(Items.ACACIA_WOOD, new mapItem(1, 100, "acacia_wood"));  //金合欢木
            map.put(Items.ACACIA_PLANKS, new mapItem(1, 25, "acacia_wood_planks"));  //金合欢木板
            map.put(Items.DARK_OAK_WOOD, new mapItem(1, 100, "dark_oak_wood"));  //深色橡木
            map.put(Items.DARK_OAK_PLANKS, new mapItem(1, 25, "dark_oak_wood_planks"));  //深色橡木木板
            map.put(Items.STICK, new mapItem(1, 25, "stick"));  //木棍
            map.put(Items.GRASS, new mapItem(1, 1, "grass"));  //草
            map.put(Items.FERN, new mapItem(1, 2, "fern"));  //蕨
            map.put(Items.SUGAR_CANE, new mapItem(1, 10, "sugar_canes"));  //甘蔗
            map.put(Items.CARROT, new mapItem(1, 10, "carrot"));  //胡萝卜
            map.put(Items.POTATO, new mapItem(1, 10, "potato"));  //马铃薯
            map.put(Items.COCOA_BEANS, new mapItem(1, 50, "cocoa_beans"));  //可可豆

            map.put(Items.SNOWBALL, new mapItem(1, 15, "snowball"));  //雪球
            map.put(Items.SNOW, new mapItem(1, 15, "snow"));  //雪
            map.put(Items.ICE, new mapItem(1, 5, "ice"));  //冰
            map.put(Items.PACKED_ICE, new mapItem(1, 50, "packed_ice"));  //浮冰
            map.put(Items.BLUE_ICE, new mapItem(1, 100, "blue_ice"));  //蓝冰
            map.put(Items.PRISMARINE_CRYSTALS, new mapItem(1, 100, "prismarine_crystals"));  //海晶砂粒
            map.put(Items.PRISMARINE_SHARD, new mapItem(1, 100, "prismarine_shard"));  //海晶碎片

            map.put(Items.BLAZE_ROD, new mapItem(1, 200, "blaze_rod "));  //烈焰棒
            map.put(Items.BLAZE_POWDER, new mapItem(1, 100, "blaze_powder"));  //烈焰粉
            map.put(Items.NETHERRACK, new mapItem(1, 10, "netherrack"));  //地狱岩
            map.put(Items.NETHER_BRICK, new mapItem(1, 50, "nether_brick"));  //地狱砖块
            map.put(Items.RED_NETHER_BRICKS, new mapItem(1, 100, "red_nether_brick"));  //红色地狱砖


            map.put(Items.DIRT, new mapItem(1, 1, "dirt"));  //泥土
            map.put(Items.COARSE_DIRT, new mapItem(1, 15, "coarse_dirt"));  //砂土
            map.put(Items.GRAVEL, new mapItem(1, 10, "gravel"));  //沙砾
            map.put(Items.FLINT, new mapItem(1, 20, "flint"));  //燧石
            map.put(Items.CLAY, new mapItem(1, 50, "clay"));  //粘土块
            map.put(Items.STONE, new mapItem(1, 10, "stone"));  //石头
            map.put(Items.COBBLESTONE, new mapItem(1, 5, "cobblestone"));  //圆石
            map.put(Items.DIORITE, new mapItem(1, 50, "diorite"));  //闪长岩
            map.put(Items.GRANITE, new mapItem(1, 50, "granite"));  //花岗岩
            map.put(Items.ANDESITE, new mapItem(1, 50, "andesite"));  //闪长岩
            map.put(Items.SAND, new mapItem(1, 2, "sand"));  //沙子
            map.put(Items.SANDSTONE, new mapItem(1, 8, "sandstone"));  //砂岩


           isGenerate = true;
        }

    }

    //获得当前Containing
    public static int getContainingNum(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return map.get(itemStack.getItem()).containing*itemStack.getCount();
        return 0;
    }


    public static int getContainingElement(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return  map.get(itemStack.getItem()).element;
        return 0;
    }


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