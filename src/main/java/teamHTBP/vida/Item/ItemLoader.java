package teamHTBP.vida.Item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Item.armor.*;
import teamHTBP.vida.Item.staff.ItemVidaWand;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;
import teamHTBP.vida.Vida;

//注册item的类
public class ItemLoader {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Vida.modId);
    public static  RegistryObject<Item> itemA = ITEMS.register("item_test", () -> new TestItem());
    public static  RegistryObject<Item> vidawand = ITEMS.register("vidawand",() -> new ItemVidaWand());
    public static  RegistryObject<Item> vidaBranch = ITEMS.register("vidabranch",() -> new ItemVidaBranch());

    public static  RegistryObject<Item> voidElementCore = ITEMS.register("voidelementcore",() -> new ItemElementCoreVoid());
    public static  RegistryObject<Item> goldElementCore = ITEMS.register("goldelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> woodElementCore = ITEMS.register("woodelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> aquaElementCore = ITEMS.register("aquaelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> fireElementCore = ITEMS.register("fireelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> earthElementCore = ITEMS.register("earthelementcore",() -> new ItemElementCore());



    public static  RegistryObject<Item> goldFaintLight = ITEMS.register("goldfaintlight",() -> new ItemFaintLight());
    public static  RegistryObject<Item> woodFaintLight = ITEMS.register("woodfaintlight",() -> new ItemFaintLight());
    public static  RegistryObject<Item> aquaFaintLight = ITEMS.register("aquafaintlight",() -> new ItemFaintLight());
    public static  RegistryObject<Item> fireFaintLight = ITEMS.register("firefaintlight",() -> new ItemFaintLight());
    public static  RegistryObject<Item> earthFaintLight = ITEMS.register("earthfaintlight",() -> new ItemFaintLight());

    public static  RegistryObject<Item> goldElementGem = ITEMS.register("goldelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> woodElementGem = ITEMS.register("woodelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> aquaElementGem = ITEMS.register("aquaelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> fireElementGem = ITEMS.register("fireelementgem",() -> new ItemGemElement());
    public static  RegistryObject<Item> earthElementGem = ITEMS.register("earthelementgem",() -> new ItemGemElement());


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

    public static  RegistryObject<Item> goldEnergyGemFragment = ITEMS.register(  "goldenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> woodEnergyGemFragment = ITEMS.register(  "woodenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> aquaEnergyGemFragment = ITEMS.register(  "aquaenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> fireEnergyGemFragment = ITEMS.register(  "fireenegygemfragment",() -> new ItemEnergyElementFragment());
    public static  RegistryObject<Item> earthEnergyGemFragment = ITEMS.register("earthenegygemfragment",() -> new ItemEnergyElementFragment());

    public static  RegistryObject<Item> deepStone = ITEMS.register("deepstone",() -> new BlockItem(BlockLoader.deepStone.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Corner = ITEMS.register("deepstonebrickcorner",() -> new BlockItem(BlockLoader.deepStoneBrick_Corner.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Straight = ITEMS.register("deepstonebrickstraight",() -> new BlockItem(BlockLoader.deepStoneBrick_Straight.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Core = ITEMS.register("deepstonebrickcore",() -> new BlockItem(BlockLoader.deepStoneBrick_Core.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Pillar_0 = ITEMS.register("deepstonebrickpillar0",() -> new BlockItem(BlockLoader.deepStoneBrick_Pillar_0.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Pillar_1 = ITEMS.register("deepstonebrickpillar1",() -> new BlockItem(BlockLoader.deepStoneBrick_Pillar_1.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> deepStoneBrick_Pillar_2 = ITEMS.register("deepstonebrickpillar2",() -> new BlockItem(BlockLoader.deepStoneBrick_Pillar_2.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> samping = ITEMS.register("saplingvida",() -> new BlockItem(BlockLoader.saplingVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> dimrockBrick = ITEMS.register("dimrockbrick",() -> new BlockItem(BlockLoader.dimRockBrick.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> dimrockBrickdeco_0 = ITEMS.register("dimrockbrickdeco0",() -> new BlockItem(BlockLoader.dimRockBrickdeco_0.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> dimrockBrickdeco_1 = ITEMS.register("dimrockbrickdeco1",() -> new BlockItem(BlockLoader.dimRockBrickdeco_1.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrick        = ITEMS.register("fertilesoilbrick",     () -> new BlockItem(BlockLoader.fertileSoilBrick      .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrickdeco_0  = ITEMS.register("fertilesoilbrickdeco0",() -> new BlockItem(BlockLoader.fertileSoilBrickdeco_0.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrickdeco_1  = ITEMS.register("fertilesoilbrickdeco1",() -> new BlockItem(BlockLoader.fertileSoilBrickdeco_1.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrickdeco_2  = ITEMS.register("fertilesoilbrickdeco2",() -> new BlockItem(BlockLoader.fertileSoilBrickdeco_2.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrickdeco_3  = ITEMS.register("fertilesoilbrickdeco3",() -> new BlockItem(BlockLoader.fertileSoilBrickdeco_3.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fertileSoilBrickdeco_4  = ITEMS.register("fertilesoilbrickdeco4",() -> new BlockItem(BlockLoader.fertileSoilBrickdeco_4.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrick         = ITEMS.register("netherfirebrick",      () -> new BlockItem(BlockLoader.netherFireBrick       .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrickdeco_0   = ITEMS.register("netherfirebrickdeco0",      () -> new BlockItem(BlockLoader.netherFireBrickdeco_0 .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrickdeco_1   = ITEMS.register("netherfirebrickdeco1", () -> new BlockItem(BlockLoader.netherFireBrickdeco_1 .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrickdeco_2   = ITEMS.register("netherfirebrickdeco2", () -> new BlockItem(BlockLoader.netherFireBrickdeco_2 .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrickdeco_3   = ITEMS.register("netherfirebrickdeco3", () -> new BlockItem(BlockLoader.netherFireBrickdeco_3 .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> netherFireBrickdeco_4   = ITEMS.register("netherfirebrickdeco4", () -> new BlockItem(BlockLoader.netherFireBrickdeco_4 .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrick           = ITEMS.register("platinumbrick",        () -> new BlockItem(BlockLoader.platinumBrick         .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrickdeco_0     = ITEMS.register("platinumbrickdeco0",   () -> new BlockItem(BlockLoader.platinumBrickdeco_0   .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrickdeco_1     = ITEMS.register("platinumbrickdeco1",   () -> new BlockItem(BlockLoader.platinumBrickdeco_1   .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrickdeco_2     = ITEMS.register("platinumbrickdeco2",   () -> new BlockItem(BlockLoader.platinumBrickdeco_2   .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrickdeco_3     = ITEMS.register("platinumbrickdeco3",   () -> new BlockItem(BlockLoader.platinumBrickdeco_3   .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> platinumBrickdeco_4     = ITEMS.register("platinumbrickdeco4",   () -> new BlockItem(BlockLoader.platinumBrickdeco_4   .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrick            = ITEMS.register("verdantbrick",         () -> new BlockItem(BlockLoader.verdantBrick          .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrickDeco_0      = ITEMS.register("verdantbrickdeco0",    () -> new BlockItem(BlockLoader.verdantBrickDeco_0    .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrickDeco_1      = ITEMS.register("verdantbrickdeco1",    () -> new BlockItem(BlockLoader.verdantBrickDeco_1    .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrickDeco_2      = ITEMS.register("verdantbrickdeco2",    () -> new BlockItem(BlockLoader.verdantBrickDeco_2    .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrickDeco_3      = ITEMS.register("verdantbrickdeco3",    () -> new BlockItem(BlockLoader.verdantBrickDeco_3    .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> verdantBrickDeco_4      = ITEMS.register("verdantbrickdeco4",    () -> new BlockItem(BlockLoader.verdantBrickDeco_4    .get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));



    public static  RegistryObject<Item> gemShower = ITEMS.register("gemshower",() -> new BlockItem(BlockLoader.gemShower.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> elementcoreAltar = ITEMS.register("elementcorealtar",() -> new BlockItem(BlockLoader.elementcoreAltar.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> prismTable = ITEMS.register("prismtable", () -> new BlockItem(BlockLoader.prismTable.get(), new Item.Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(1)));

    public static  RegistryObject<Item> goldElementCrystal = ITEMS.register("goldelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalGold.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> woodElementCrystal = ITEMS.register("woodelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalWood.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> aquaElementCrystal = ITEMS.register("aquaelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalAqua.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fireElementCrystal = ITEMS.register("fireelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalFire.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> earthElementCrystal = ITEMS.register("earthelementcrystal",() -> new BlockItem(BlockLoader.elementCrystalEarth.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));



}
