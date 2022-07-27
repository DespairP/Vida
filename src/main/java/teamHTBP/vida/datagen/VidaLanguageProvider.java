package teamHTBP.vida.datagen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import teamHTBP.vida.helper.json.JsonUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;

import static teamHTBP.vida.common.block.VidaBlockLoader.CORE_ALTAR;
import static teamHTBP.vida.common.block.VidaBlockLoader.GEM_STAND;
import static teamHTBP.vida.common.block.VidaBlockLoader.aquaElementOre;
import static teamHTBP.vida.common.block.VidaBlockLoader.collector;
import static teamHTBP.vida.common.block.VidaBlockLoader.earthElementOre;
import static teamHTBP.vida.common.block.VidaBlockLoader.fireElementOre;
import static teamHTBP.vida.common.block.VidaBlockLoader.goldElementOre;
import static teamHTBP.vida.common.block.VidaBlockLoader.leavesVida;
import static teamHTBP.vida.common.block.VidaBlockLoader.logVida;
import static teamHTBP.vida.common.block.VidaBlockLoader.oreReactionMachine;
import static teamHTBP.vida.common.block.VidaBlockLoader.prismTable;
import static teamHTBP.vida.common.block.VidaBlockLoader.purfiedCauldron;
import static teamHTBP.vida.common.block.VidaBlockLoader.woodElementOre;
import static teamHTBP.vida.common.block.VidaBlockLoader.*;
import static teamHTBP.vida.common.item.VidaItemLoader.*;

/**
 * @author DustW
 */
@AllArgsConstructor
public class VidaLanguageProvider implements DataProvider {
    String modId;
    DataGenerator generator;

    /**        语言                     键      值  */
    final Map<String, List<Map.Entry<String, String>>> map = new HashMap<>();

    protected void addLanguageEntries() {
        addEntry("itemGroup.vida", "Vida", "生命");

        // Items

        item(WAND_VIDA, "Wand of Vida", "生命法杖");
        item(vidaBranch, "Vida Tree Branches", "生命树枝");
        item(itemA, "From team party parrot", "From team party parrot");
        item(prism, "Prism", "棱镜");
        item(elementBottle, "Element Bottle", "元素之瓶");

        item(ELEMENTCORE_VOID, "Pure Core", "空白核心");
        item(ELEMENTCORE_GOLD, "Core of Gold", "金之核心");
        item(ELEMENTCORE_WOOD, "Core of Wood", "木之核心");
        item(ELEMENTCORE_AQUA, "Core of Aqua", "水之核心");
        item(ELEMENTCORE_FIRE, "Core of Fire", "火之核心");
        item(ELEMENTCORE_EARTH, "Core of Earth", "土之核心");

        item(SPARK_GOLD, "Shining Glimmer (Gold)", "闪耀微光[金]");
        item(SPARK_WOOD, "Shining Glimmer (Wood)", "闪耀微光[木]");
        item(SPARK_AQUA, "Shining Glimmer (Aqua)", "闪耀微光[水]");
        item(SPARK_FIRE, "Shining Glimmer (Fire)", "闪耀微光[火]");
        item(SPARK_EARTH, "Shining Glimmer (Earth)", "闪耀微光[土]");

        item(ELEMENTGEM_GOLD, "Shiny Metal Gem", "闪金宝石");
        item(ELEMENTGEM_WOOD, "Greenwood Gem", "朽木宝石");
        item(ELEMENTGEM_AQUA, "Aquamarine Gem", "海蓝宝石");
        item(ELEMENTGEM_FIRE, "Burstflame Gem", "烈焰宝石");
        item(ELEMENTGEM_EARTH, "Earthcore Gem", "地核宝石");

        item(ARMOR_GOLD_HELMET, "Burstflame Helmet", "烈焰头盔");
        item(ARMOR_WOOD_HELMET, "Aquamarine Helmet", "海洋头盔");
        item(ARMOR_FIRE_HELMET, "Greenwood Helmet", "朽木头盔");
        item(ARMOR_AQUA_HELMET, "Shiny Metal Helmet", "闪金头盔");
        item(ARMOR_EARTH_HELMET, "Earthcore Helmet", "地核头盔");

        item(ARMOR_GOLD_CHESTPLATES , "Burstflame Leggings", "烈焰腿甲");
        item(ARMOR_WOOD_CHESTPLATES , "Aquamarine Leggings", "海洋腿甲");
        item(ARMOR_AQUA_CHESTPLATES , "Greenwood Leggings", "朽木腿甲");
        item(ARMOR_FIRE_CHESTPLATES , "Shiny Metal Leggings", "闪金腿甲");
        item(ARMOR_EARTH_CHESTPLATES, "Earthcore Leggings", "地核腿甲");

        item(ARMOR_GOLD_LEGGINGS , "Burstflame Leggings with Element Bottles", "带有元素瓶的烈焰腿甲");
        item(ARMOR_WOOD_LEGGINGS , "Aquamarine Leggings with Element Bottles", "带有元素瓶的海洋腿甲");
        item(ARMOR_AQUA_LEGGINGS , "Greenwood Leggings with Element Bottles", "带有元素瓶的朽木腿甲");
        item(ARMOR_FIRE_LEGGINGS , "Shiny Metal Leggings with Element Bottles", "带有元素瓶的闪金腿甲");
        item(ARMOR_EARTH_LEGGINGS, "Earthcore Leggings with Element Bottles", "带有元素瓶的地核腿甲");

        item(ARMOR_GOLD_LEGGINGS_WITHBOTTLE , "Burstflame Chestplate", "烈焰胸甲");
        item(ARMOR_WOOD_LEGGINGS_WITHBOTTLE , "Aquamarine Chestplate", "海洋胸甲");
        item(ARMOR_AQUA_LEGGINGS_WITHBOTTLE , "Greenwood Chestplate", "朽木胸甲");
        item(ARMOR_FIRE_LEGGINGS_WITHBOTTLE , "Shiny Metal Chestplate", "闪金胸甲");
        item(ARMOR_EARTH_LEGGINGS_WITHBOTTLE, "Earthcore Chestplate", "地核胸甲");

        item(ARMOR_GOLD_BOOTS , "Burstflame Boots", "烈焰靴子");
        item(ARMOR_WOOD_BOOTS , "Aquamarine Chestplate", "海洋靴子");
        item(ARMOR_AQUA_BOOTS , "Greenwood Chestplate", "朽木靴子");
        item(ARMOR_FIRE_BOOTS , "Shiny Metal Chestplate", "闪金靴子");
        item(ARMOR_EARTH_BOOTS, "Earthcore Chestplate", "地核靴子");

        item(ARTIFICIAL_ELEMENTGEM_GOLD , "Gold Soul", "闪金之魂");
        item(ARTIFICIAL_ELEMENTGEM_WOOD , "Wood Soul", "朽木之魂");
        item(ARTIFICIAL_ELEMENTGEM_AQUA , "Aqua Soul", "海蓝之魂");
        item(ARTIFICIAL_ELEMENTGEM_FIRE , "Fire Soul", "烈焰之魂");
        item(ARTIFICIAL_ELEMENTGEM_EARTH, "Earth Soul", "地核之魂");

        item(goldEnergyGemFragment , "Artificial Gem (Gold)", "人工制品宝石[金]");
        item(woodEnergyGemFragment , "Artificial Gem (Wood)", "人工制品宝石[木]");
        item(aquaEnergyGemFragment , "Artificial Gem (Aqua)", "人工制品宝石[水]");
        item(fireEnergyGemFragment , "Artificial Gem (Fire)", "人工制品宝石[火]");
        item(earthEnergyGemFragment, "Artificial Gem (Earth)", "人工制品宝石[土]");

        item(ARMOR_TEST, "Leaf Helmet", "叶落头盔");

        item(goldElementPickaxe , "Shiny Metal Pickaxe", "闪金镐");
        item(woodElementPickaxe , "Greenwood Pickaxe", "朽木镐");
        item(aquaElementPickaxe , "Aquamarine Pickaxe", "海蓝镐");
        item(fireElementPickaxe , "Burstflame Pickaxe", "烈焰镐");
        item(earthElementPickaxe, "Earthcore Pickaxe", "地核镐");

        item(goldElementSword , "Shiny Metal Sword", "闪金剑");
        item(woodElementSword , "Greenwood Sword", "朽木剑");
        item(aquaElementSword , "Aquamarine Sword", "海蓝剑");
        item(fireElementSword , "Burstflame Sword", "烈焰剑");
        item(earthElementSword, "Earthcore Sword", "地核剑");

        // Blocks

        block(logVida, "Vida Wood", "生命原木");
        block(leavesVida, "Vida Leaves", "生命树叶");
        block(plankVida_0, "Horizontal Vida Wood Planks", "横纹生命木板");
        block(plankVida_1, "Vida Wood Planks", "生命木板");
        block(plankVida_2, "Cracked Vida Planks", "裂纹生命木板");
        block(saplingVida, "Vida Sapling", "生命树苗");

        block(goldElementOre , "Shiny Metal Ore", "闪金矿石");
        block(woodElementOre , "Greenwood Ore", "朽木矿石");
        block(aquaElementOre , "Aquamarine Ore", "海蓝矿石");
        block(fireElementOre , "Volcano Ore", "火山矿石");
        block(earthElementOre, "Earthcore Ore", "地核矿石");

        block(DEEPSEA_STONE, "Deepstone", "深潜石");
        block(DEEPSEA_BRICKS_CORNER, "Deepstone Brick Corner", "转角深潜石石砖");
        block(DEEPSEA_BRICKS_STRAIGHT, "Vertical Deepstone Brick", "直线深潜石石砖");
        addEntry("block.vida.deepstonebrickcore", "Central Deepstone Brick", "核心深潜石石砖");
        addEntry("block.vida.deepstonebrickpillar0", "Vertical Deepstone Column", "竖纹深潜石柱");
        addEntry("block.vida.deepstonebrickpillar1", "Horizontal Deepstone Column", "横纹深潜石柱");
        addEntry("block.vida.deepstonebrickpillar2", "Deepstone Column", "深潜石柱");
        addEntry("block.vida.fertilesoilbrick", "Fertilesoil Brick", "厚土石砖");
        addEntry("block.vida.fertilesoilbrickdeco0", "Decorated Fertilesoil Brick 1", "饰纹纹厚土石砖");
        addEntry("block.vida.fertilesoilbrickdeco1", "Horizontal Fertilesoil Brick 1", "横纹厚土石砖");
        addEntry("block.vida.fertilesoilbrickdeco2", "Decorated Fertilesoil Bricks 2", "饰纹纹厚土石砖");
        addEntry("block.vida.fertilesoilbrickdeco3", "Horizontal Fertilesoil Brick 2", "横纹厚土石砖");
        addEntry("block.vida.fertilesoilbrickdeco4", "Smooth Fertilesoil Brick", "平滑厚土石砖");
        addEntry("block.vida.netherfirebrick", "Netherfire Brick", "炎狱石砖");
        addEntry("block.vida.netherfirebrickdeco0", "Decorated Netherfire Brick 1", "饰纹炎狱石砖");
        addEntry("block.vida.netherfirebrickdeco1", "Horizontal Netherfire Brick", "横纹炎狱石砖");
        addEntry("block.vida.netherfirebrickdeco2", "Decorated Netherfire Brick 2", "饰纹炎狱石砖");
        addEntry("block.vida.netherfirebrickdeco3", "Square Netherfire Brick", "方纹炎狱石砖");
        addEntry("block.vida.netherfirebrickdeco4", "Vertical Netherfire Brick", "竖纹炎狱石砖");
        addEntry("block.vida.verdantbrick", "Verdant Brick", "丛茂石砖");
        addEntry("block.vida.verdantbrickdeco0", "Horizontal Verdant Brick", "横纹丛茂石砖");
        addEntry("block.vida.verdantbrickdeco1", "Decorated Verdant Brick 1", "饰纹丛茂石砖");
        addEntry("block.vida.verdantbrickdeco2", "Decorated Verdant Brick 2", "饰纹丛茂石砖");
        addEntry("block.vida.verdantbrickdeco3", "Vertical Verdant Brick", "竖纹丛茂石砖");
        addEntry("block.vida.verdantbrickdeco4", "Decorated Verdant Brick 3", "饰纹丛茂石砖");
        addEntry("block.vida.platinumbrick", "Platinum Brick", "白金石砖");
        addEntry("block.vida.platinumbrickdeco0", "Horizontal Platinum Brick", "横纹白金石砖");
        addEntry("block.vida.platinumbrickdeco1", "Decorated Platinum Brick 1", "饰纹白金石砖");
        addEntry("block.vida.platinumbrickdeco2", "Decorated Platinum Brick 2", "饰纹白金石砖");
        addEntry("block.vida.platinumbrickdeco3", "Decorated Platinum Brick 3", "饰纹白金石砖");
        addEntry("block.vida.platinumbrickdeco4", "Decorated Platinum Brick 4", "饰纹白金石砖");

        block(GOLD_CRYSTAL , "Shiny Metal Suspended crystal", "闪金悬浮水晶");
        block(WOOD_CRYSTAL , "Greenwood Suspended crystal", "朽木悬浮水晶");
        block(AQUA_CRYSTAL , "Aquamarine Suspended crystal", "海蓝悬浮水晶");
        block(FIRE_CRYSTAL , "Burstflame Suspended crystal", "烈焰悬浮水晶");
        block(EARTH_CRYSTAL, "Earthcore Suspended crystal", "地核悬浮水晶");

        block(purfiedCauldron, "Purification Cauldron", "纯净之锅");
        block(prismTable, "Prism Table", "棱镜台");
        block(oreReactionMachine, "Ore Reaction Machine", "矿石反应机");
        block(collector, "Element Collector", "元素收集器");
        block(CORE_ALTAR, "Element Core Altar", "核心祭坛");
        block(GEM_STAND, "Gem display stand", "宝石展示架");

        block(DIM_BRICKS, "Dimrock Brick", "黯淡石砖");
        block(DIM_BRICKS_DECO_0, "Decorated Dimrock Brick 1", "饰纹黯淡石砖");
        block(DIM_BRICKS_DECO_1, "Decorated Dimrock Brick 2", "饰纹黯淡石砖");

        addEntry("block.vida.dimrockbrickdeco2", "Decorated Dimrock Brick 3", "饰纹黯淡石砖");
        addEntry("block.vida.silentforestbrick_0", "", "寂森石砖");
        addEntry("block.vida.silentforestbrick_1", "", "饰纹寂森石砖");
        addEntry("block.vida.silentforestbrick_2", "", "饰纹寂森石砖");
        addEntry("block.vida.silentforestbrick_3", "", "竖纹寂森石砖");
        addEntry("block.vida.silentforestbrick_4", "", "饰纹寂森石砖");
        addEntry("block.vida.silentforestbrick_5", "", "饰纹寂森石砖");
        addEntry("block.vida.silentforestbrick_6", "", "饰纹寂森石砖");
        addEntry("block.vida.silentforestbrick_7", "", "饰纹寂森石砖");

        // tooltips

        addEntry("desc.picklevel.level", "LEVEL：", "等级：");
        addEntry("desc.swordlevel.level", "LEVEL：", "等级：");

        // key bindings

        addEntry("key.bottle1.use", "Element Bottle[Left]use", "元素瓶[左]使用");
        addEntry("key.bottle2.use", "Element Bottle[Right]use", "元素瓶[右]使用");
        addEntry("key.bottle3.use", "Element Bottle[Middle]use", "元素瓶[中]使用");
        addEntry("key.bottles", "Show Element Bottle Condition[%]", "元素瓶装填显示");
        addEntry("key.category.vida", "Vida Settings", "'生命'键位设置");

        // skills

        addEntry("skill.vida.luck.name", "Luck", "幸运");
        addEntry("skill.vida.requiredSkill.anno", "required Skills", "需要解锁的技能：");
        addEntry("skill.vida.luck.desc", "", "在击杀时有几率增加伤害 \n *随着等级增加伤害会增强");
    }

    @Override
    public void run(HashCache cache) throws IOException {
        addLanguageEntries();

        for (Map.Entry<String, List<Map.Entry<String, String>>> mainEntry : map.entrySet()) {
            String lang = mainEntry.getKey();
            List<Map.Entry<String, String>> list = mainEntry.getValue();

            JsonObject langJson = new JsonObject();

            list.forEach(entry -> langJson.addProperty(entry.getKey(), entry.getValue()));

            Gson gson = JsonUtils.INSTANCE.pretty;
            Path path = generator.getOutputFolder().resolve("assets/" + modId + "/lang/" + lang + ".json");

            DataProvider.save(gson, cache, langJson, path);
        }
    }

    protected static final String EN_US = "en_us";
    protected static final String ZH_CN = "zh_cn";

    protected void block(Supplier<Block> block, String enUs, String zhCn) {
        addEntry(getKey(block.get()), enUs, zhCn);
    }

    protected void item(Supplier<Item> item, String enUs, String zhCn) {
        addEntry(getKey(item.get()), enUs, zhCn);
    }

    protected void addEntry(String key, String enUs, String zhCn) {
        List<Map.Entry<String, String>> enUsList = getLang(EN_US);
        enUsList.add(new AbstractMap.SimpleEntry<>(key, enUs));

        List<Map.Entry<String, String>> zhCnList = getLang(ZH_CN);
        zhCnList.add(new AbstractMap.SimpleEntry<>(key, zhCn));
    }

    protected String getKey(Block block) {
        return Util.makeDescriptionId("block", block.getRegistryName());
    }

    protected String getKey(Item item) {
        return Util.makeDescriptionId("item", item.getRegistryName());
    }

    protected List<Map.Entry<String, String>> getLang(String lang) {
        return map.computeIfAbsent(lang, (a) -> new ArrayList<>());
    }

    @Override
    public String getName() {
        return "Vida Language Provider";
    }
}
