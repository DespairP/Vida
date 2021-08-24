package teamHTBP.vida.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.item.Potion.ItemCreativeElementPotion;
import teamHTBP.vida.item.armor.*;
import teamHTBP.vida.item.staff.*;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

//注册item的类
public class ItemLoader {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vida.modId);
    //图纸
    public final static RegistryObject<Item> BLUEPRINT_NORMAL = ITEMS.register("blueprint_normal", () -> new ItemBluePrint(1));
    public final static RegistryObject<Item> BLUEPRINT_EPIC = ITEMS.register("blueprint_epic", () -> new ItemBluePrint(2));
    public final static RegistryObject<Item> BLUEPRINT_CHALLENGE = ITEMS.register("blueprint_challenge", () -> new ItemBluePrint(3));
    public final static RegistryObject<Item> BLUEPRINT_VOID = ITEMS.register("blueprint_void", () -> new ItemBluePrint(4));
    //元素核心
    public final static RegistryObject<Item> ELEMENTCORE_VOID = ITEMS.register("elementcore_void", ItemElementCoreVoid::new);
    public final static RegistryObject<Item> ELEMENTCORE_GOLD = ITEMS.register("elementcore_gold", ItemElementCore::new);
    public final static RegistryObject<Item> ELEMENTCORE_WOOD = ITEMS.register("elementcore_wood", ItemElementCore::new);
    public final static RegistryObject<Item> ELEMENTCORE_AQUA = ITEMS.register("elementcore_aqua", ItemElementCore::new);
    public final static RegistryObject<Item> ELEMENTCORE_FIRE = ITEMS.register("elementcore_fire", ItemElementCore::new);
    public final static RegistryObject<Item> ELEMENTCORE_EARTH = ITEMS.register("elementcore_earth", ItemElementCore::new);
    //元素微光
    public final static RegistryObject<Item> SPARK_GOLD = ITEMS.register("faintlight_gold", () -> new ItemFaintLight(1));
    public final static RegistryObject<Item> SPARK_WOOD = ITEMS.register("faintlight_wood", () -> new ItemFaintLight(2));
    public final static RegistryObject<Item> SPARK_AQUA = ITEMS.register("faintlight_aqua", () -> new ItemFaintLight(3));
    public final static RegistryObject<Item> SPARK_FIRE = ITEMS.register("faintlight_fire", () -> new ItemFaintLight(4));
    public final static RegistryObject<Item> SPARK_EARTH = ITEMS.register("faintlight_earth", () -> new ItemFaintLight(5));
    //元素宝石
    public final static RegistryObject<Item> ELEMENTGEM_GOLD = ITEMS.register("elementgem_gold", ItemGemElement::new);
    public final static RegistryObject<Item> ELEMENTGEM_WOOD = ITEMS.register("elementgem_wood", ItemGemElement::new);
    public final static RegistryObject<Item> ELEMENTGEM_AQUA = ITEMS.register("elementgem_aqua", ItemGemElement::new);
    public final static RegistryObject<Item> ELEMENTGEM_FIRE = ITEMS.register("elementgem_fire", ItemGemElement::new);
    public final static RegistryObject<Item> ELEMENTGEM_EARTH = ITEMS.register("elementgem_earth", ItemGemElement::new);
    //创造用元素试剂
    public final static RegistryObject<Item> CREATIVE_ELEMENTPOTION_GOLD = ITEMS.register("creative_purfired_elementpotion_gold", () -> new ItemCreativeElementPotion(EnumElements.GOLD));
    public final static RegistryObject<Item> CREATIVE_ELEMENTPOTION_WOOD = ITEMS.register("creative_purfired_elementpotion_wood", () -> new ItemCreativeElementPotion(EnumElements.WOOD));
    public final static RegistryObject<Item> CREATIVE_ELEMENTPOTION_AQUA = ITEMS.register("creative_purfired_elementpotion_aqua", () -> new ItemCreativeElementPotion(EnumElements.AQUA));
    public final static RegistryObject<Item> CREATIVE_ELEMENTPOTION_FIRE = ITEMS.register("creative_purfired_elementpotion_fire", () -> new ItemCreativeElementPotion(EnumElements.FIRE));
    public final static RegistryObject<Item> CREATIVE_ELEMENTPOTION_EARTH = ITEMS.register("creative_purfired_elementpotion_earth", () -> new ItemCreativeElementPotion(EnumElements.EARTH));
    //人工制品水晶
    public final static RegistryObject<Item> ARTIFICIAL_ELEMENTGEM_GOLD = ITEMS.register("artificial_elementgem_gold", ItemArtificialElementGem::new);
    public final static RegistryObject<Item> ARTIFICIAL_ELEMENTGEM_WOOD = ITEMS.register("artificial_elementgem_wood", ItemArtificialElementGem::new);
    public final static RegistryObject<Item> ARTIFICIAL_ELEMENTGEM_AQUA = ITEMS.register("artificial_elementgem_aqua", ItemArtificialElementGem::new);
    public final static RegistryObject<Item> ARTIFICIAL_ELEMENTGEM_FIRE = ITEMS.register("artificial_elementgem_fire", ItemArtificialElementGem::new);
    public final static RegistryObject<Item> ARTIFICIAL_ELEMENTGEM_EARTH = ITEMS.register("artificial_elementgem_earth", ItemArtificialElementGem::new);
    //元素之魂
    public final static RegistryObject<Item> goldEnergyGemFragment = ITEMS.register("soul_fragment_gold", ItemEnergyElementFragment::new);
    public final static RegistryObject<Item> woodEnergyGemFragment = ITEMS.register("soul_fragment_wood", ItemEnergyElementFragment::new);
    public final static RegistryObject<Item> aquaEnergyGemFragment = ITEMS.register("soul_fragment_aqua", ItemEnergyElementFragment::new);
    public final static RegistryObject<Item> fireEnergyGemFragment = ITEMS.register("soul_fragment_fire", ItemEnergyElementFragment::new);
    public final static RegistryObject<Item> earthEnergyGemFragment = ITEMS.register("soul_fragment_earth", ItemEnergyElementFragment::new);
    //测试物品（PartyParrot）
    public static RegistryObject<Item> itemA = ITEMS.register("item_test", TestItem::new);
    //生命法杖
    public static RegistryObject<Item> WAND_VIDA = ITEMS.register("wand_vida", ItemVidaWand::new);
    //生命树枝
    public static RegistryObject<Item> vidaBranch = ITEMS.register("vidabranch", ItemVidaBranch::new);
    //棱镜
    public static RegistryObject<Item> prism = ITEMS.register("prism", ItemPrism::new);
    //元素之瓶
    public static RegistryObject<Item> elementBottle = ITEMS.register("elementbottle", ItemElementBottle::new);
    //凿子
    public static RegistryObject<Item> chisel = ITEMS.register("chisel", ItemChisel::new);
    //元素盔甲
    public static RegistryObject<Item> armor_test = ITEMS.register("testaromor", TestHelmet::new);
    public static RegistryObject<Item> armor_gold_helmet = ITEMS.register("goldhelmetarmor", () -> new ItemArmorElementHelmet(1));
    public static RegistryObject<Item> armor_wood_helmet = ITEMS.register("woodhelmetarmor", () -> new ItemArmorElementHelmet(2));
    public static RegistryObject<Item> armor_fire_helmet = ITEMS.register("aquahelmetarmor", () -> new ItemArmorElementHelmet(3));
    public static RegistryObject<Item> armor_aqua_helmet = ITEMS.register("firehelmetarmor", () -> new ItemArmorElementHelmet(4));
    public static RegistryObject<Item> armor_earth_helmet = ITEMS.register("earthhelmetarmor", () -> new ItemArmorElementHelmet(5));

    public static RegistryObject<Item> armor_gold_chestplates = ITEMS.register("goldchestplatesarmor", () -> new ItemArmorElementChestplates(1));
    public static RegistryObject<Item> armor_wood_chestplates = ITEMS.register("woodchestplatesarmor", () -> new ItemArmorElementChestplates(2));
    public static RegistryObject<Item> armor_aqua_chestplates = ITEMS.register("aquachestplatesarmor", () -> new ItemArmorElementChestplates(3));
    public static RegistryObject<Item> armor_fire_chestplates = ITEMS.register("firechestplatesarmor", () -> new ItemArmorElementChestplates(4));
    public static RegistryObject<Item> armor_earth_chestplates = ITEMS.register("earthchestplatesarmor", () -> new ItemArmorElementChestplates(5));

    public static RegistryObject<Item> armor_gold_leggings = ITEMS.register("goldleggingssarmor", () -> new ItemArmorElementLeggings(1));
    public static RegistryObject<Item> armor_wood_leggings = ITEMS.register("woodleggingssarmor", () -> new ItemArmorElementLeggings(2));
    public static RegistryObject<Item> armor_aqua_leggings = ITEMS.register("aqualeggingssarmor", () -> new ItemArmorElementLeggings(3));
    public static RegistryObject<Item> armor_fire_leggings = ITEMS.register("fireleggingssarmor", () -> new ItemArmorElementLeggings(4));
    public static RegistryObject<Item> armor_earth_leggings = ITEMS.register("earthleggingssarmor", () -> new ItemArmorElementLeggings(5));

    public static RegistryObject<Item> armor_gold_leggings_Withbottle = ITEMS.register("goldleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(1));
    public static RegistryObject<Item> armor_wood_leggings_Withbottle = ITEMS.register("woodleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(2));
    public static RegistryObject<Item> armor_aqua_leggings_Withbottle = ITEMS.register("aqualeggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(3));
    public static RegistryObject<Item> armor_fire_leggings_Withbottle = ITEMS.register("fireleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(4));
    public static RegistryObject<Item> armor_earth_leggings_Withbottle = ITEMS.register("earthleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(5));
    public static RegistryObject<Item> bluePrint_belt = ITEMS.register("blueprintbelt", () -> new ItemArmorBelt());

    public static RegistryObject<Item> armor_gold_boots = ITEMS.register("goldbootsarmor", () -> new ItemArmorElementBoots(1));
    public static RegistryObject<Item> armor_wood_boots = ITEMS.register("woodbootsarmor", () -> new ItemArmorElementBoots(2));
    public static RegistryObject<Item> armor_aqua_boots = ITEMS.register("aquabootsarmor", () -> new ItemArmorElementBoots(3));
    public static RegistryObject<Item> armor_fire_boots = ITEMS.register("firebootsarmor", () -> new ItemArmorElementBoots(4));
    public static RegistryObject<Item> armor_earth_boots = ITEMS.register("earthbootsarmor", () -> new ItemArmorElementBoots(5));

    //元素工具
    public static RegistryObject<Item> goldElementPickaxe = ITEMS.register("goldelementpickaxe", () -> new ItemElementPickaxe(1));
    public static RegistryObject<Item> woodElementPickaxe = ITEMS.register("woodelementpickaxe", () -> new ItemElementPickaxe(2));
    public static RegistryObject<Item> aquaElementPickaxe = ITEMS.register("aquaelementpickaxe", () -> new ItemElementPickaxe(3));
    public static RegistryObject<Item> fireElementPickaxe = ITEMS.register("fireelementpickaxe", () -> new ItemElementPickaxe(4));
    public static RegistryObject<Item> earthElementPickaxe = ITEMS.register("earthelementpickaxe", () -> new ItemElementPickaxe(5));

    public static RegistryObject<Item> goldElementSword = ITEMS.register("goldelementsword", () -> new ItemElementSword(1));
    public static RegistryObject<Item> woodElementSword = ITEMS.register("woodelementsword", () -> new ItemElementSword(2));
    public static RegistryObject<Item> aquaElementSword = ITEMS.register("aquaelementsword", () -> new ItemElementSword(3));
    public static RegistryObject<Item> fireElementSword = ITEMS.register("fireelementsword", () -> new ItemElementSword(4));
    public static RegistryObject<Item> earthElementSword = ITEMS.register("earthelementsword", () -> new ItemElementSword(5));

    public static RegistryObject<Item> logVida = ITEMS.register("logvida", () -> new BlockItem(BlockLoader.logVida.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> leavesVida = ITEMS.register("leavesvida", () -> new BlockItem(BlockLoader.leavesVida.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_0 = ITEMS.register("plankvida0", () -> new BlockItem(BlockLoader.plankVida_0.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_1 = ITEMS.register("plankvida1", () -> new BlockItem(BlockLoader.plankVida_1.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_2 = ITEMS.register("plankvida2", () -> new BlockItem(BlockLoader.plankVida_2.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    public static RegistryObject<Item> goldElementOre = ITEMS.register("goldelementore", () -> new BlockItem(BlockLoader.goldElementOre.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> woodElementOre = ITEMS.register("woodelementore", () -> new BlockItem(BlockLoader.woodElementOre.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> aquaElementOre = ITEMS.register("aquaelementore", () -> new BlockItem(BlockLoader.aquaElementOre.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> fireElementOre = ITEMS.register("fireelementore", () -> new BlockItem(BlockLoader.fireElementOre.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> earthElementOre = ITEMS.register("earthelementore", () -> new BlockItem(BlockLoader.earthElementOre.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    public static RegistryObject<Item> samping = ITEMS.register("saplingvida", () -> new BlockItem(BlockLoader.saplingVida.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static RegistryObject<Item> plankLush = ITEMS.register("lush_planks", () -> new BlockItem(BlockLoader.LUSH_PLANKS.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    //功能性方块
    public static RegistryObject<Item> gemShower = ITEMS.register("gemshower", () -> new BlockItem(BlockLoader.gemShower.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> elementcoreAltar = ITEMS.register("elementcorealtar", () -> new BlockItem(BlockLoader.elementcoreAltar.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> prismTable = ITEMS.register("prismtable", () -> new BlockItem(BlockLoader.prismTable.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(1)));
    public static RegistryObject<Item> oreReactionMachine = ITEMS.register("orereactionmachine", () -> new BlockItem(BlockLoader.oreReactionMachine.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> collector = ITEMS.register("collector", () -> new BlockItem(BlockLoader.collector.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> injectionTable = ITEMS.register("injecttable", () -> new BlockItem(BlockLoader.injectionTable.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> altarcubeMaker = ITEMS.register("altarcubemaker", () -> new BlockItem(BlockLoader.altarcubeMaker.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> steleLife = ITEMS.register("lifestele", () -> new BlockItem(BlockLoader.steleLife.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> blueprintDesigner = ITEMS.register("blueprintdesigner", () -> new BlockItem(BlockLoader.blueprintDesigner.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> purfiedCauldron = ITEMS.register("purfiedcauldron", () -> new BlockItem(BlockLoader.purfiedCauldron.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    //悬浮元素水晶方块
    public static RegistryObject<Item> goldElementCrystal = ITEMS.register("goldelementcrystal", () -> new BlockItem(BlockLoader.elementCrystalGold.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> woodElementCrystal = ITEMS.register("woodelementcrystal", () -> new BlockItem(BlockLoader.elementCrystalWood.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> aquaElementCrystal = ITEMS.register("aquaelementcrystal", () -> new BlockItem(BlockLoader.elementCrystalAqua.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> fireElementCrystal = ITEMS.register("fireelementcrystal", () -> new BlockItem(BlockLoader.elementCrystalFire.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> earthElementCrystal = ITEMS.register("earthelementcrystal", () -> new BlockItem(BlockLoader.elementCrystalEarth.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    //元素农作物方块
    public static RegistryObject<Item> crimsonCrest = ITEMS.register("crop_crimsoncrest", () -> new BlockItem(BlockLoader.crimsonCrest.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> heartOfWal = ITEMS.register("crop_heartofwal", () -> new BlockItem(BlockLoader.heartOfWal.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> nitriteThorns = ITEMS.register("crop_nitritethorns", () -> new BlockItem(BlockLoader.nitriteThorns.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> plamStem = ITEMS.register("crop_plamstem", () -> new BlockItem(BlockLoader.plamStem.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> sullenHydrangea = ITEMS.register("crop_sullenhydrangea", () -> new BlockItem(BlockLoader.sullenHydrangea.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static RegistryObject<Item> sweetCyanReed = ITEMS.register("crop_sweetcyanreed", () -> new BlockItem(BlockLoader.sweetCyanReed.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

}
