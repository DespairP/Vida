package teamHTBP.vida.Item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Item.staff.ItemVidaWand;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;
import teamHTBP.vida.Vida;

import java.rmi.registry.Registry;

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


    public static  RegistryObject<Item> logVida = ITEMS.register("logvida",() -> new BlockItem(BlockLoader.logVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> leavesVida = ITEMS.register("leavesvida",() -> new BlockItem(BlockLoader.leavesVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plankvida = ITEMS.register("plankvida",() -> new BlockItem(BlockLoader.plankVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> purfiedCauldron = ITEMS.register("purfiedcauldron",() -> new BlockItem(BlockLoader.purfiedCauldron.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> goldElementOre  = ITEMS.register("goldelementore",() -> new BlockItem(BlockLoader.goldElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> woodElementOre  = ITEMS.register("woodelementore",() -> new BlockItem(BlockLoader.woodElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> aquaElementOre  = ITEMS.register("aquaelementore",() -> new BlockItem(BlockLoader.aquaElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> fireElementOre  = ITEMS.register("fireelementore",() -> new BlockItem(BlockLoader.fireElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> earthElementOre = ITEMS.register("earthelementore",() -> new BlockItem(BlockLoader.earthElementOre.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));




    public static  RegistryObject<Item> samping = ITEMS.register("saplingvida",() -> new BlockItem(BlockLoader.saplingVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> gemShower = ITEMS.register("gemshower",() -> new BlockItem(BlockLoader.gemShower.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
}
