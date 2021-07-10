package teamHTBP.vida.Item;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Vida.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemBlockLoader {
    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockLoader.init(); //获取注册方块的字段
        BlockLoader.REGISTRY_OBJECT_LIST.forEach(block->{
            BlockItem blockItem = new BlockItem(block.get(),new Item.Properties().group(ItemGroupLoader.vidaItemGroup));
            blockItem.setRegistryName(Objects.requireNonNull(block.get().getRegistryName()));
            event.getRegistry().register(blockItem);
        });

    }
}
