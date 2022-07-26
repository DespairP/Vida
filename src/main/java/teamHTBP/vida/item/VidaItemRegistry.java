package teamHTBP.vida.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.VidaBlockRegistry;
import teamHTBP.vida.creativetab.ItemGroupRegistry;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.helper.RegisterItemBlock;
import teamHTBP.vida.item.armor.*;
import teamHTBP.vida.item.environment.ItemVidaBranch;
import teamHTBP.vida.item.function.*;
import teamHTBP.vida.item.potion.ItemCreativeElementPotion;
import teamHTBP.vida.item.staff.*;

import static teamHTBP.vida.element.EnumElements.*;

//注册item的类
public class VidaItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vida.MOD_ID);
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
    public final static RegistryObject<Item> ARMOR_TEST = ITEMS.register("testaromor", TestHelmet::new);
    public final static RegistryObject<Item> ARMOR_GOLD_HELMET = ITEMS.register("goldhelmetarmor", () -> new ItemArmorElementHelmet(1));
    public final static RegistryObject<Item> ARMOR_WOOD_HELMET = ITEMS.register("woodhelmetarmor", () -> new ItemArmorElementHelmet(2));
    public final static RegistryObject<Item> ARMOR_FIRE_HELMET = ITEMS.register("aquahelmetarmor", () -> new ItemArmorElementHelmet(3));
    public final static RegistryObject<Item> ARMOR_AQUA_HELMET = ITEMS.register("firehelmetarmor", () -> new ItemArmorElementHelmet(4));
    public final static RegistryObject<Item> ARMOR_EARTH_HELMET = ITEMS.register("earthhelmetarmor", () -> new ItemArmorElementHelmet(5));

    public final static RegistryObject<Item> ARMOR_GOLD_CHESTPLATES = ITEMS.register("goldchestplatesarmor", () -> new ItemArmorElementChestplates(1));
    public final static RegistryObject<Item> ARMOR_WOOD_CHESTPLATES = ITEMS.register("woodchestplatesarmor", () -> new ItemArmorElementChestplates(2));
    public final static RegistryObject<Item> ARMOR_AQUA_CHESTPLATES = ITEMS.register("aquachestplatesarmor", () -> new ItemArmorElementChestplates(3));
    public final static RegistryObject<Item> ARMOR_FIRE_CHESTPLATES = ITEMS.register("firechestplatesarmor", () -> new ItemArmorElementChestplates(4));
    public final static RegistryObject<Item> ARMOR_EARTH_CHESTPLATES = ITEMS.register("earthchestplatesarmor", () -> new ItemArmorElementChestplates(5));

    public final static RegistryObject<Item> ARMOR_GOLD_LEGGINGS = ITEMS.register("goldleggingssarmor", () -> new ItemArmorElementLeggings(1));
    public final static RegistryObject<Item> ARMOR_WOOD_LEGGINGS = ITEMS.register("woodleggingssarmor", () -> new ItemArmorElementLeggings(2));
    public final static RegistryObject<Item> ARMOR_AQUA_LEGGINGS = ITEMS.register("aqualeggingssarmor", () -> new ItemArmorElementLeggings(3));
    public final static RegistryObject<Item> ARMOR_FIRE_LEGGINGS = ITEMS.register("fireleggingssarmor", () -> new ItemArmorElementLeggings(4));
    public final static RegistryObject<Item> ARMOR_EARTH_LEGGINGS = ITEMS.register("earthleggingssarmor", () -> new ItemArmorElementLeggings(5));

    public final static RegistryObject<Item> ARMOR_GOLD_LEGGINGS_WITHBOTTLE = ITEMS.register("goldleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(1));
    public final static RegistryObject<Item> ARMOR_WOOD_LEGGINGS_WITHBOTTLE = ITEMS.register("woodleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(2));
    public final static RegistryObject<Item> ARMOR_AQUA_LEGGINGS_WITHBOTTLE = ITEMS.register("aqualeggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(3));
    public final static RegistryObject<Item> ARMOR_FIRE_LEGGINGS_WITHBOTTLE = ITEMS.register("fireleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(4));
    public final static RegistryObject<Item> ARMOR_EARTH_LEGGINGS_WITHBOTTLE = ITEMS.register("earthleggingsarmorwithbottles", () -> new ItemArmorElementLegginsWithBottles(5));
    public final static RegistryObject<Item> BLUEPRINT_BELT = ITEMS.register("blueprintbelt", ItemArmorBelt::new);

    public final static RegistryObject<Item> ARMOR_GOLD_BOOTS = ITEMS.register("goldbootsarmor", () -> new ItemArmorElementBoots(1));
    public final static RegistryObject<Item> ARMOR_WOOD_BOOTS = ITEMS.register("woodbootsarmor", () -> new ItemArmorElementBoots(2));
    public final static RegistryObject<Item> ARMOR_AQUA_BOOTS = ITEMS.register("aquabootsarmor", () -> new ItemArmorElementBoots(3));
    public final static RegistryObject<Item> ARMOR_FIRE_BOOTS = ITEMS.register("firebootsarmor", () -> new ItemArmorElementBoots(4));
    public final static RegistryObject<Item> ARMOR_EARTH_BOOTS = ITEMS.register("earthbootsarmor", () -> new ItemArmorElementBoots(5));

    // 测试用盔甲
    public final static RegistryObject<Item> ARMOR_DEMO_HELMET = ITEMS.register("armor_demo_helmet", () -> new ItemArmorDemo(EquipmentSlot.HEAD));
    public final static RegistryObject<Item> ARMOR_DEMO_CHEST = ITEMS.register("armor_demo_chest", () -> new ItemArmorDemo(EquipmentSlot.CHEST));
    public final static RegistryObject<Item> ARMOR_DEMO_LEGGINGS = ITEMS.register("armor_demo_leggings", () -> new ItemArmorDemo(EquipmentSlot.LEGS));
    public final static RegistryObject<Item> ARMOR_DEMO_BOOT = ITEMS.register("armor_demo_boots", () -> new ItemArmorDemo(EquipmentSlot.FEET));

    //元素工具
    public static RegistryObject<Item> goldElementPickaxe = ITEMS.register("goldelementpickaxe", () -> new ItemElementPickaxe(GOLD));
    public static RegistryObject<Item> woodElementPickaxe = ITEMS.register("woodelementpickaxe", () -> new ItemElementPickaxe(WOOD));
    public static RegistryObject<Item> aquaElementPickaxe = ITEMS.register("aquaelementpickaxe", () -> new ItemElementPickaxe(AQUA));
    public static RegistryObject<Item> fireElementPickaxe = ITEMS.register("fireelementpickaxe", () -> new ItemElementPickaxe(FIRE));
    public static RegistryObject<Item> earthElementPickaxe = ITEMS.register("earthelementpickaxe", () -> new ItemElementPickaxe(EARTH));

    public static RegistryObject<Item> goldElementSword = ITEMS.register("goldelementsword", () -> new ItemElementSword(GOLD));
    public static RegistryObject<Item> woodElementSword = ITEMS.register("woodelementsword", () -> new ItemElementSword(WOOD));
    public static RegistryObject<Item> aquaElementSword = ITEMS.register("aquaelementsword", () -> new ItemElementSword(AQUA));
    public static RegistryObject<Item> fireElementSword = ITEMS.register("fireelementsword", () -> new ItemElementSword(FIRE));
    public static RegistryObject<Item> earthElementSword = ITEMS.register("earthelementsword", () -> new ItemElementSword(EARTH));

    public final static RegistryObject<Item> logVida = ITEMS.register("logvida", () -> new BlockItem(VidaBlockRegistry.logVida.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> leavesVida = ITEMS.register("leavesvida", () -> new BlockItem(VidaBlockRegistry.leavesVida.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_0 = ITEMS.register("plankvida0", () -> new BlockItem(VidaBlockRegistry.plankVida_0.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_1 = ITEMS.register("plankvida1", () -> new BlockItem(VidaBlockRegistry.plankVida_1.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> plankvida_2 = ITEMS.register("plankvida2", () -> new BlockItem(VidaBlockRegistry.plankVida_2.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));


    public static RegistryObject<Item> goldElementOre = ITEMS.register("goldelementore", () -> new BlockItem(VidaBlockRegistry.goldElementOre.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> woodElementOre = ITEMS.register("woodelementore", () -> new BlockItem(VidaBlockRegistry.woodElementOre.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> aquaElementOre = ITEMS.register("aquaelementore", () -> new BlockItem(VidaBlockRegistry.aquaElementOre.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> fireElementOre = ITEMS.register("fireelementore", () -> new BlockItem(VidaBlockRegistry.fireElementOre.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));
    public static RegistryObject<Item> earthElementOre = ITEMS.register("earthelementore", () -> new BlockItem(VidaBlockRegistry.earthElementOre.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));


    public static RegistryObject<Item> samping = ITEMS.register("saplingvida", () -> new BlockItem(VidaBlockRegistry.saplingVida.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));

    public static RegistryObject<Item> plankLush = ITEMS.register("lush_planks", () -> new BlockItem(VidaBlockRegistry.LUSH_PLANKS.get(), new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup)));


    //功能性方块
    @RegisterItemBlock
    public static RegistryObject<Item> GEM_STAND;
    @RegisterItemBlock
    public static RegistryObject<Item> CORE_ALTAR;
    public static RegistryObject<Item> prismTable = block(VidaBlockRegistry.prismTable);
    public static RegistryObject<Item> oreReactionMachine = block(VidaBlockRegistry.oreReactionMachine);
    public static RegistryObject<Item> collector = block(VidaBlockRegistry.collector);
    public static RegistryObject<Item> injectionTable = block(VidaBlockRegistry.injectionTable);
    public static RegistryObject<Item> altarcubeMaker = block(VidaBlockRegistry.altarcubeMaker);
    public static RegistryObject<Item> steleLife = block(VidaBlockRegistry.steleLife);
    public static RegistryObject<Item> blueprintDesigner = block(VidaBlockRegistry.blueprintDesigner);
    public static RegistryObject<Item> purfiedCauldron = block(VidaBlockRegistry.purfiedCauldron);

    //悬浮元素水晶方块
    public static RegistryObject<Item> goldElementCrystal = block(VidaBlockRegistry.GOLD_CRYSTAL);
    public static RegistryObject<Item> woodElementCrystal = block(VidaBlockRegistry.WOOD_CRYSTAL);
    public static RegistryObject<Item> aquaElementCrystal = block(VidaBlockRegistry.AQUA_CRYSTAL);
    public static RegistryObject<Item> fireElementCrystal = block(VidaBlockRegistry.FIRE_CRYSTAL);
    public static RegistryObject<Item> earthElementCrystal = block(VidaBlockRegistry.EARTH_CRYSTAL);

    //元素农作物方块
    public static RegistryObject<Item> CROP_CRISMCREST = block(VidaBlockRegistry.CROP_CRISMCREST);
    public static RegistryObject<Item> CROP_HEARTOFWAL = block(VidaBlockRegistry.CROP_HEARTOFWAL);
    public static RegistryObject<Item> CROP_NITRITETHORNS = block(VidaBlockRegistry.CROP_NITRITETHORNS);
    public static RegistryObject<Item> CROP_PLAMSTEM = block(VidaBlockRegistry.CROP_PLAMSTEM);
    public static RegistryObject<Item> CROP_SULLENHYDRANGEA = block(VidaBlockRegistry.CROP_SULLENHYDRANGEA);
    public static RegistryObject<Item> CROP_SWEETCYANREED = block(VidaBlockRegistry.CROP_SWEETCYANREED);

    //注册书
    public final static RegistryObject<Item> GUIDE_BOOK = ITEMS.register("vida_guidebook", ItemGuidebook::new);

    public Item getItem(RegistryObject<Item> registryObject) {
        return registryObject != null ? registryObject.orElse(Items.AIR) : Items.AIR;
    }

    static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), base()));
    }

    static Item.Properties base() {
        return new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup);
    }
}