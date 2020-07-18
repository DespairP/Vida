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
    public static  RegistryObject<Item> elementCore = ITEMS.register("fireelementcore",() -> new ItemElementCore());
    public static  RegistryObject<Item> vidaBranch = ITEMS.register("vidabranch",() -> new ItemVidaBranch());
    public static  RegistryObject<Item> logVida = ITEMS.register("logvida",() -> new BlockItem(BlockLoader.logVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> leavesVida = ITEMS.register("leavesvida",() -> new BlockItem(BlockLoader.leavesVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> plankvida = ITEMS.register("plankvida",() -> new BlockItem(BlockLoader.plankVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
    public static  RegistryObject<Item> purfiedCauldron = ITEMS.register("purfiedcauldron",() -> new BlockItem(BlockLoader.purfiedCauldron.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));

    public static  RegistryObject<Item> samping = ITEMS.register("saplingvida",() -> new BlockItem(BlockLoader.saplingVida.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup)));
}
