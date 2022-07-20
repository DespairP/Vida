package teamHTBP.vida.datagen;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Items;
import teamHTBP.vida.event.server.datapack.element.ElementPotential;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.utils.json.JsonUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
@AllArgsConstructor
public class ElementPotentialProvider implements DataProvider {
    final List<ElementPotential> potentials = new ArrayList<>();

    String modId;
    DataGenerator generator;

    @Override
    public void run(HashCache cache) throws IOException {
        addPotentials(potentials);
        
        for (ElementPotential potential : potentials) {
            Gson gson = JsonUtils.INSTANCE.noExpose;
            Path path = generator.getOutputFolder().resolve("data/" + modId + "/element_potential/" + potential.getItem() + ".json");

            DataProvider.save(gson, cache, gson.toJsonTree(potential), path);
        }
    }

    protected void addPotentials(List<ElementPotential> list) {
        list.add(new ElementPotential(Items.DIAMOND, EnumElements.GOLD, 1000, "diamond"));  //钻石
        list.add(new ElementPotential(Items.EMERALD, EnumElements.GOLD, 1000, "emerald"));  //绿宝石
        list.add(new ElementPotential(Items.LAPIS_LAZULI, EnumElements.GOLD, 500, "lapis_lazuli"));  //青金石
        list.add(new ElementPotential(Items.GOLD_INGOT, EnumElements.GOLD, 540, "gold_ingot"));  //金锭
        list.add(new ElementPotential(Items.GOLD_NUGGET, EnumElements.GOLD, 60, "gold_nugget"));  //金粒
        list.add(new ElementPotential(Items.IRON_INGOT, EnumElements.GOLD, 450, "iron_ingot"));  //铁锭
        list.add(new ElementPotential(Items.IRON_NUGGET, EnumElements.GOLD, 50, "iron_nugget"));  //铁粒
        list.add(new ElementPotential(Items.REDSTONE, EnumElements.GOLD, 50, "redstone"));  //红石

        list.add(new ElementPotential(Items.OAK_WOOD, EnumElements.WOOD, 200, "oak_wood"));  //橡木
        list.add(new ElementPotential(Items.OAK_LOG, EnumElements.WOOD, 200, "oak_wood"));  //橡木
        list.add(new ElementPotential(Items.OAK_PLANKS, EnumElements.WOOD, 50, "oak_wood_planks"));  //橡木木板
        list.add(new ElementPotential(Items.SPRUCE_WOOD, EnumElements.WOOD, 200, "spruce_wood"));  //云杉木
        list.add(new ElementPotential(Items.SPRUCE_PLANKS, EnumElements.WOOD, 50, "spruce_wood_planks"));  //云杉木木板
        list.add(new ElementPotential(Items.BIRCH_WOOD, EnumElements.WOOD, 200, "birch_wood"));  //白桦木
        list.add(new ElementPotential(Items.BIRCH_PLANKS, EnumElements.WOOD, 50, "birch_wood_planks"));  //白桦木板
        list.add(new ElementPotential(Items.JUNGLE_WOOD, EnumElements.WOOD, 200, "jungle_wood"));  //丛林木
        list.add(new ElementPotential(Items.JUNGLE_PLANKS, EnumElements.WOOD, 50, "jungle_wood_planks"));  //丛林木板
        list.add(new ElementPotential(Items.ACACIA_WOOD, EnumElements.WOOD, 200, "acacia_wood"));  //金合欢木
        list.add(new ElementPotential(Items.ACACIA_PLANKS, EnumElements.WOOD, 50, "acacia_wood_planks"));  //金合欢木板
        list.add(new ElementPotential(Items.DARK_OAK_WOOD, EnumElements.WOOD, 200, "dark_oak_wood"));  //深色橡木
        list.add(new ElementPotential(Items.DARK_OAK_PLANKS, EnumElements.WOOD, 50, "dark_oak_wood_planks"));  //深色橡木木板
        list.add(new ElementPotential(Items.STICK, EnumElements.WOOD, 25, "stick"));  //木棍
        list.add(new ElementPotential(Items.GRASS, EnumElements.WOOD, 1, "grass"));  //草
        list.add(new ElementPotential(Items.FERN, EnumElements.WOOD, 2, "fern"));  //蕨
        list.add(new ElementPotential(Items.SUGAR_CANE, EnumElements.WOOD, 10, "sugar_canes"));  //甘蔗

        list.add(new ElementPotential(Items.CARROT, EnumElements.EARTH, 10, "carrot"));  //胡萝卜
        list.add(new ElementPotential(Items.POTATO, EnumElements.EARTH, 10, "potato"));  //马铃薯
        list.add(new ElementPotential(Items.COCOA_BEANS, EnumElements.EARTH, 50, "cocoa_beans"));  //可可豆

        list.add(new ElementPotential(Items.SNOWBALL, EnumElements.AQUA, 15, "snowball"));  //雪球
        list.add(new ElementPotential(Items.SNOW, EnumElements.AQUA, 15, "snow"));  //雪
        list.add(new ElementPotential(Items.ICE, EnumElements.AQUA, 5, "ice"));  //冰
        list.add(new ElementPotential(Items.PACKED_ICE, EnumElements.AQUA, 50, "packed_ice"));  //浮冰
        list.add(new ElementPotential(Items.BLUE_ICE, EnumElements.AQUA, 100, "blue_ice"));  //蓝冰
        list.add(new ElementPotential(Items.PRISMARINE_CRYSTALS, EnumElements.AQUA, 100, "prismarine_crystals"));  //海晶砂粒
        list.add(new ElementPotential(Items.BRAIN_CORAL, EnumElements.AQUA, 100, "prismarine_shard"));  //珊瑚
        list.add(new ElementPotential(Items.BUBBLE_CORAL, EnumElements.AQUA, 30, "coral"));  //珊瑚


        list.add(new ElementPotential(Items.BLAZE_ROD, EnumElements.FIRE, 200, "blaze_rod "));  //烈焰棒
        list.add(new ElementPotential(Items.BLAZE_POWDER, EnumElements.FIRE, 100, "blaze_powder"));  //烈焰粉
        list.add(new ElementPotential(Items.NETHERRACK, EnumElements.FIRE, 10, "netherrack"));  //地狱岩
        list.add(new ElementPotential(Items.NETHER_BRICK, EnumElements.FIRE, 50, "nether_brick"));  //地狱砖块
        list.add(new ElementPotential(Items.RED_NETHER_BRICKS, EnumElements.FIRE, 100, "red_nether_brick"));  //红色地狱砖


        list.add(new ElementPotential(Items.DIRT, EnumElements.EARTH, 1, "dirt"));  //泥土
        list.add(new ElementPotential(Items.COARSE_DIRT, EnumElements.EARTH, 15, "coarse_dirt"));  //砂土
        list.add(new ElementPotential(Items.GRAVEL, EnumElements.EARTH, 10, "gravel"));  //沙砾
        list.add(new ElementPotential(Items.FLINT, EnumElements.EARTH, 20, "flint"));  //燧石
        list.add(new ElementPotential(Items.CLAY, EnumElements.EARTH, 50, "clay"));  //粘土块
        list.add(new ElementPotential(Items.STONE, EnumElements.EARTH, 10, "stone"));  //石头
        list.add(new ElementPotential(Items.COBBLESTONE, EnumElements.EARTH, 5, "cobblestone"));  //圆石
        list.add(new ElementPotential(Items.DIORITE, EnumElements.EARTH, 50, "diorite"));  //闪长岩
        list.add(new ElementPotential(Items.GRANITE, EnumElements.EARTH, 50, "granite"));  //花岗岩
        list.add(new ElementPotential(Items.ANDESITE, EnumElements.EARTH, 50, "andesite"));  //闪长岩
        list.add(new ElementPotential(Items.SAND, EnumElements.EARTH, 2, "sand"));  //沙子
        list.add(new ElementPotential(Items.SANDSTONE, EnumElements.EARTH, 8, "sandstone"));  //砂岩
        list.add(new ElementPotential(Items.RED_SAND, EnumElements.EARTH, 50, "red_sand"));  //红砂
        list.add(new ElementPotential(Items.RED_SANDSTONE, EnumElements.EARTH, 200, "red_sandstone"));  //红砂岩

        list.add(new ElementPotential(ItemLoader.goldEnergyGemFragment.get(), EnumElements.GOLD, 500, "ARTIFICIAL_ELEMENTGEM_GOLD"));
        list.add(new ElementPotential(ItemLoader.woodEnergyGemFragment.get(), EnumElements.WOOD, 500, "ARTIFICIAL_ELEMENTGEM_WOOD"));
        list.add(new ElementPotential(ItemLoader.aquaEnergyGemFragment.get(), EnumElements.AQUA, 500, "ARTIFICIAL_ELEMENTGEM_AQUA"));
        list.add(new ElementPotential(ItemLoader.fireEnergyGemFragment.get(), EnumElements.FIRE, 500, "ARTIFICIAL_ELEMENTGEM_FIRE"));
        list.add(new ElementPotential(ItemLoader.earthEnergyGemFragment.get(), EnumElements.EARTH, 500, "ARTIFICIAL_ELEMENTGEM_EARTH"));

        list.add(new ElementPotential(ItemLoader.ELEMENTCORE_GOLD.get(), EnumElements.GOLD, 10000, "goldelementcore"));
        list.add(new ElementPotential(ItemLoader.ELEMENTCORE_WOOD.get(), EnumElements.WOOD, 10000, "woodelementcore"));
        list.add(new ElementPotential(ItemLoader.ELEMENTCORE_AQUA.get(), EnumElements.AQUA, 10000, "ELEMENTCORE_AQUA"));
        list.add(new ElementPotential(ItemLoader.ELEMENTCORE_FIRE.get(), EnumElements.FIRE, 10000, "ELEMENTCORE_FIRE"));
        list.add(new ElementPotential(ItemLoader.ELEMENTCORE_EARTH.get(), EnumElements.EARTH, 10000, "ELEMENTCORE_EARTH"));

        list.add(new ElementPotential(ItemLoader.CREATIVE_ELEMENTPOTION_GOLD.get(), EnumElements.GOLD, 500000, "CREATIVE_ELEMENTPOTION_GOLD"));
        list.add(new ElementPotential(ItemLoader.CREATIVE_ELEMENTPOTION_WOOD.get(), EnumElements.WOOD, 500000, "CREATIVE_ELEMENTPOTION_WOOD"));
        list.add(new ElementPotential(ItemLoader.CREATIVE_ELEMENTPOTION_AQUA.get(), EnumElements.AQUA, 500000, "CREATIVE_ELEMENTPOTION_AQUA"));
        list.add(new ElementPotential(ItemLoader.CREATIVE_ELEMENTPOTION_FIRE.get(), EnumElements.FIRE, 500000, "CREATIVE_ELEMENTPOTION_FIRE"));
        list.add(new ElementPotential(ItemLoader.CREATIVE_ELEMENTPOTION_EARTH.get(), EnumElements.EARTH, 500000, "CREATIVE_ELEMENTPOTION_EARTH"));
    }
    
    @Override
    public String getName() {
        return "Element Potential Provider";
    }
}
