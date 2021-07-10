package teamHTBP.vida.Item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Item.Potion.ItemCreativeElementPotion;
import teamHTBP.vida.Item.staff.*;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.Item.armor.*;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.EnumElements;

//注册item的类
public class ItemLoader {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Vida.modId);
    public static  RegistryObject<Item> itemA = ITEMS.register("item_test", () -> new TestItem());
    public static  RegistryObject<Item> vidawand = ITEMS.register("vidawand",() -> new ItemVidaWand());
    public static  RegistryObject<Item> vidaBranch = ITEMS.register("vidabranch",() -> new ItemVidaBranch());
    public static  RegistryObject<Item> prism = ITEMS.register("prism",() -> new ItemPrism());
    public static  RegistryObject<Item> elementBottle = ITEMS.register("elementbottle",() -> new ItemElementBottle());
    public static  RegistryObject<Item> chisel = ITEMS.register("chisel",() -> new ItemChisel());


    public static  RegistryObject<Item> bluePrint_normal    = ITEMS.register("blueprintnormal",() -> new ItemBluePrint(1));
    public static  RegistryObject<Item> bluePrint_epic      = ITEMS.register("blueprintepic",() -> new ItemBluePrint(2));
    public static  RegistryObject<Item> bluePrint_challenge = ITEMS.register("blueprintchallenge",() -> new ItemBluePrint(3));
    public static  RegistryObject<Item> bluePrint_void      = ITEMS.register("blueprintvoid",() -> new ItemBluePrint(4));

    public static  RegistryObject<Item> voidElementCore = ITEMS.register("voidelementcore",() -> new ItemElementCoreVoid());
    public static  RegistryObject<Item> goldElementCore = ITEMS.register("goldelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> woodElementCore = ITEMS.register("woodelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> aquaElementCore = ITEMS.register("aquaelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> fireElementCore = ITEMS.register("fireelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> earthElementCore = ITEMS.register("earthelementcore",() -> new ItemElementCore());



    public static  RegistryObject<Item> goldFaintLight = ITEMS.register("goldfaintlight",() -> new ItemFaintLight(1));
    public static  RegistryObject<Item> woodFaintLight = ITEMS.register("woodfaintlight",() -> new ItemFaintLight(2));
    public static  RegistryObject<Item> aquaFaintLight = ITEMS.register("aquafaintlight",() -> new ItemFaintLight(3));
    public static  RegistryObject<Item> fireFaintLight = ITEMS.register("firefaintlight",() -> new ItemFaintLight(4));
    public static  RegistryObject<Item> earthFaintLight = ITEMS.register("earthfaintlight",() -> new ItemFaintLight(5));

    public static  RegistryObject<Item> goldElementGem = ITEMS.register("goldelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> woodElementGem = ITEMS.register("woodelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> aquaElementGem = ITEMS.register("aquaelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> fireElementGem = ITEMS.register("fireelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> earthElementGem = ITEMS.register("earthelementgem",() -> new ItemGemElement());

    public static  RegistryObject<Item> goldElementPotion = ITEMS.register("goldelementpotion",() -> new ItemCreativeElementPotion(EnumElements.GOLD));
    public static  RegistryObject<Item> woodElementPotion = ITEMS.register("woodelementpotion",() -> new ItemCreativeElementPotion(EnumElements.WOOD));
    public static  RegistryObject<Item> aquaElementPotion = ITEMS.register("aquaelementpotion",() -> new ItemCreativeElementPotion(EnumElements.AQUA));
    public static  RegistryObject<Item> fireElementPotion = ITEMS.register("fireelementpotion",() -> new ItemCreativeElementPotion(EnumElements.FIRE));
    public static  RegistryObject<Item> earthElementPotion = ITEMS.register("earthelementpotion",() -> new ItemCreativeElementPotion(EnumElements.EARTH));

    public static  RegistryObject<Item> armor_test = ITEMS.register("testaromor",() -> new TestHelmet());
    public static  RegistryObject<Item> armor_gold_helmet = ITEMS.register("goldhelmetarmor",() -> new ItemArmorElementHelmet(1));
    public static  RegistryObject<Item> armor_wood_helmet = ITEMS.register("woodhelmetarmor",() -> new ItemArmorElementHelmet(2));
    public static  RegistryObject<Item> armor_fire_helmet = ITEMS.register("aquahelmetarmor",() -> new ItemArmorElementHelmet(3));
    public static  RegistryObject<Item> armor_aqua_helmet = ITEMS.register("firehelmetarmor",() -> new ItemArmorElementHelmet(4));
    public static  RegistryObject<Item> armor_earth_helmet = ITEMS.register("earthhelmetarmor",() -> new ItemArmorElementHelmet(5));

    public static  RegistryObject<Item> armor_gold_chestplates = ITEMS.register("goldchestplatesarmor",() -> new ItemArmorElementChestplates(1));
    public static  RegistryObject<Item> armor_wood_chestplates = ITEMS.register("woodchestplatesarmor",() -> new ItemArmorElementChestplates(2));
    public static  RegistryObject<Item> armor_aqua_chestplates = ITEMS.register("aquachestplatesarmor",() -> new ItemArmorElementChestplates(3));
    public static  RegistryObject<Item> armor_fire_chestplates = ITEMS.register("firechestplatesarmor",() -> new ItemArmorElementChestplates(4));
    public static  RegistryObject<Item> armor_earth_chestplates = ITEMS.register("earthchestplatesarmor",() -> new ItemArmorElementChestplates(5));

    public static  RegistryObject<Item> armor_gold_leggings = ITEMS.register("goldleggingssarmor",() -> new ItemArmorElementLeggings(1));
    public static  RegistryObject<Item> armor_wood_leggings = ITEMS.register("woodleggingssarmor",() -> new ItemArmorElementLeggings(2));
    public static  RegistryObject<Item> armor_aqua_leggings = ITEMS.register("aqualeggingssarmor",() -> new ItemArmorElementLeggings(3));
    public static  RegistryObject<Item> armor_fire_leggings = ITEMS.register("fireleggingssarmor",() -> new ItemArmorElementLeggings(4));
    public static  RegistryObject<Item> armor_earth_leggings = ITEMS.register("earthleggingssarmor",() -> new ItemArmorElementLeggings(5));

    public static  RegistryObject<Item> armor_gold_leggings_Withbottle = ITEMS.register("goldleggingsarmorwithbottles",() -> new ItemArmorElementLegginsWithBottles(1));
    public static  RegistryObject<Item> armor_wood_leggings_Withbottle = ITEMS.register("woodleggingsarmorwithbottles",() -> new ItemArmorElementLegginsWithBottles(2));
    public static  RegistryObject<Item> armor_aqua_leggings_Withbottle = ITEMS.register("aqualeggingsarmorwithbottles",() -> new ItemArmorElementLegginsWithBottles(3));
    public static  RegistryObject<Item> armor_fire_leggings_Withbottle = ITEMS.register("fireleggingsarmorwithbottles",() -> new ItemArmorElementLegginsWithBottles(4));
    public static  RegistryObject<Item> armor_earth_leggings_Withbottle = ITEMS.register("earthleggingsarmorwithbottles",() -> new ItemArmorElementLegginsWithBottles(5));
    public static  RegistryObject<Item> bluePrint_belt = ITEMS.register("blueprintbelt",() -> new ItemArmorBelt());


    public static  RegistryObject<Item> armor_gold_boots    = ITEMS.register("goldbootsarmor", () -> new ItemArmorElementBoots(1));
    public static  RegistryObject<Item> armor_wood_boots    = ITEMS.register("woodbootsarmor", () -> new ItemArmorElementBoots(2));
    public static  RegistryObject<Item> armor_aqua_boots    = ITEMS.register("aquabootsarmor", () -> new ItemArmorElementBoots(3));
    public static  RegistryObject<Item> armor_fire_boots    = ITEMS.register("firebootsarmor", () -> new ItemArmorElementBoots(4));
    public static  RegistryObject<Item> armor_earth_boots    = ITEMS.register("earthbootsarmor", () -> new ItemArmorElementBoots(5));

    public static  RegistryObject<Item> logVida = ITEMS.register("logvida",() -> new BlockItem(BlockLoader.logVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> leavesVida = ITEMS.register("leavesvida",() -> new BlockItem(BlockLoader.leavesVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plankvida_0 = ITEMS.register("plankvida0",() -> new BlockItem(BlockLoader.plankVida_0.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plankvida_1 = ITEMS.register("plankvida1",() -> new BlockItem(BlockLoader.plankVida_1.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plankvida_2 = ITEMS.register("plankvida2",() -> new BlockItem(BlockLoader.plankVida_2.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> purfiedCauldron = ITEMS.register("purfiedcauldron",() -> new BlockItem(BlockLoader.purfiedCauldron.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    public static  RegistryObject<Item> goldElementOre  = ITEMS.register("goldelementore",() -> new BlockItem(BlockLoader.goldElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> woodElementOre  = ITEMS.register("woodelementore",() -> new BlockItem(BlockLoader.woodElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> aquaElementOre  = ITEMS.register("aquaelementore",() -> new BlockItem(BlockLoader.aquaElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fireElementOre  = ITEMS.register("fireelementore",() -> new BlockItem(BlockLoader.fireElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> earthElementOre = ITEMS.register("earthelementore",() -> new BlockItem(BlockLoader.earthElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item>  goldArtificialElementGem  = ITEMS.register("goldartificialelementgem",() -> new ItemArtificialElementGem());
    public static  RegistryObject<Item>  woodArtificialElementGem  = ITEMS.register("woodartificialelementgem",() -> new ItemArtificialElementGem());
    public static  RegistryObject<Item>  aquaArtificialElementGem  = ITEMS.register("aquaartificialelementgem",() -> new ItemArtificialElementGem());
    public static  RegistryObject<Item>  fireArtificialElementGem  = ITEMS.register("fireartificialelementgem",() -> new ItemArtificialElementGem());
    public static  RegistryObject<Item> earthArtificialElementGem = ITEMS.register("earthartificialelementgem",() -> new ItemArtificialElementGem());

    public static  RegistryObject<Item> goldEnergyGemFragment = ITEMS.register(  "goldenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> woodEnergyGemFragment = ITEMS.register(  "woodenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> aquaEnergyGemFragment = ITEMS.register(  "aquaenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> fireEnergyGemFragment = ITEMS.register(  "fireenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> earthEnergyGemFragment = ITEMS.register("earthenegygemfragment",() -> new ItemEnergyElementFragment());

    public static  RegistryObject<Item> samping = ITEMS.register("saplingvida",() -> new BlockItem(BlockLoader.saplingVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> plankLush = ITEMS.register("lush_planks",() -> new BlockItem(BlockLoader.LUSH_PLANKS.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    public static  RegistryObject<Item> gemShower = ITEMS.register("gemshower",() -> new BlockItem(BlockLoader.gemShower.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> elementcoreAltar = ITEMS.register("elementcorealtar",() -> new BlockItem(BlockLoader.elementcoreAltar.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> prismTable = ITEMS.register("prismtable", () -> new BlockItem(BlockLoader.prismTable.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(1)));
    public static  RegistryObject<Item> oreReactionMachine = ITEMS.register("orereactionmachine",() -> new BlockItem(BlockLoader.oreReactionMachine.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> collector = ITEMS.register("collector",() -> new BlockItem(BlockLoader.collector.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> injectionTable = ITEMS.register("injecttable",() -> new BlockItem(BlockLoader.injectionTable.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> altarcubeMaker = ITEMS.register("altarcubemaker",() -> new BlockItem(BlockLoader.altarcubeMaker.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> steleLife      = ITEMS.register("lifestele",() -> new BlockItem(BlockLoader.steleLife.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> blueprintDesigner      = ITEMS.register("blueprintdesigner",() -> new BlockItem(BlockLoader.blueprintDesigner.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));



    public static  RegistryObject<Item> goldElementCrystal = ITEMS.register("goldelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalGold.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> woodElementCrystal = ITEMS.register("woodelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalWood.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> aquaElementCrystal = ITEMS.register("aquaelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalAqua.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fireElementCrystal = ITEMS.register("fireelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalFire.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> earthElementCrystal = ITEMS.register("earthelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalEarth.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));


    public static  RegistryObject<Item> goldElementPickaxe = ITEMS.register("goldelementpickaxe",() -> new ItemElementPickaxe(1));
    public static  RegistryObject<Item> woodElementPickaxe = ITEMS.register("woodelementpickaxe",() -> new ItemElementPickaxe(2));
    public static  RegistryObject<Item> aquaElementPickaxe = ITEMS.register("aquaelementpickaxe",() -> new ItemElementPickaxe(3));
    public static  RegistryObject<Item> fireElementPickaxe = ITEMS.register("fireelementpickaxe",() -> new ItemElementPickaxe(4));
    public static  RegistryObject<Item> earthElementPickaxe = ITEMS.register("earthelementpickaxe",() -> new ItemElementPickaxe(5));

    public static  RegistryObject<Item> goldElementSword = ITEMS.register("goldelementsword",() -> new ItemElementSword(1));
    public static  RegistryObject<Item> woodElementSword = ITEMS.register("woodelementsword",() -> new ItemElementSword(2));
    public static  RegistryObject<Item> aquaElementSword = ITEMS.register("aquaelementsword",() -> new ItemElementSword(3));
    public static  RegistryObject<Item> fireElementSword = ITEMS.register("fireelementsword",() -> new ItemElementSword(4));
    public static  RegistryObject<Item> earthElementSword = ITEMS.register("earthelementsword",() -> new ItemElementSword(5));

    public static  RegistryObject<Item> crimsonCrest = ITEMS.register("crop_crimsoncrest",() -> new BlockItem(BlockLoader.crimsonCrest.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> heartOfWal = ITEMS.register("crop_heartofwal",() -> new BlockItem(BlockLoader.heartOfWal.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> nitriteThorns = ITEMS.register("crop_nitritethorns",() -> new BlockItem(BlockLoader.nitriteThorns.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plamStem = ITEMS.register("crop_plamstem",() -> new BlockItem(BlockLoader.plamStem.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> sullenHydrangea = ITEMS.register("crop_sullenhydrangea",() -> new BlockItem(BlockLoader.sullenHydrangea.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> sweetCyanReed = ITEMS.register("crop_sweetcyanreed",() -> new BlockItem(BlockLoader.sweetCyanReed.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

}
