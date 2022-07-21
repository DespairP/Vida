package teamHTBP.vida.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.creativetab.ItemGroupLoader;
import teamHTBP.vida.helper.RegisterItemBlock;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemBlockLoader {
    /**储存需要注册的方块*/
    public static final Map<String,RegistryObject<Block>> REGISTRY_BLOCK_LIST = new LinkedHashMap<>();
    /**储存注册完成的ItemBlock,字段名称或者*/
    public static final Map<String,RegistryObject<Item>> REGISTRY_ITEMBLOCK_MAP = new LinkedHashMap<>();
    /**LOGGER*/
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * 对方块进行BlockItem的注册
     * 分别调用{@link ItemBlockLoader#init()}进行需要注册的Block的获取，
     * {@link ItemBlockLoader#inject()}进行Item的注入
     * */
    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) throws IllegalAccessException {
        //获取注册方块的字段
        init();
        // 依次进行注册
        REGISTRY_BLOCK_LIST.forEach((key,block) -> {
            Item.Properties properties = new Item.Properties().tab(ItemGroupLoader.vidaItemGroup);
            ResourceLocation registerName = Optional.ofNullable(block.get().getRegistryName()).orElse(block.getId());
            BlockItem blockItem  = new BlockItem(block.get(),properties);
            blockItem.setRegistryName(registerName);
            event.getRegistry().register(blockItem);
            // 注册完成后放入Map中
            REGISTRY_ITEMBLOCK_MAP.put(key,RegistryObject.of(registerName, event.getRegistry()));
        });
        // 进行注入
        inject();
    }


    /**获取所有可以被注册的字段*/
    private static void init() throws IllegalAccessException {
        for(Field decoratedBlock : BlockLoader.class.getDeclaredFields()){
            if(decoratedBlock.getType() == RegistryObject.class && decoratedBlock.isAnnotationPresent(RegisterItemBlock.class)){
                decoratedBlock.setAccessible(true);
                REGISTRY_BLOCK_LIST.put(decoratedBlock.getName(),(RegistryObject<Block>) decoratedBlock.get(null));
            }
        }
    }

    /**将注册后的字段注入到ItemLoader中*/
    private static void inject() throws IllegalAccessException {
        for(Field itemField : ItemLoader.class.getDeclaredFields()){
            if(itemField.getType() == RegistryObject.class && itemField.isAnnotationPresent(RegisterItemBlock.class)){
                RegistryObject<Item> registryObject = REGISTRY_ITEMBLOCK_MAP.get(itemField.getName());
                if(registryObject == null){
                    LOGGER.error("cannot inject field :{},please check the field name same as Block field name",itemField.getName());
                    break;
                }
                itemField.set(null,registryObject);
            }
        }
    }
}
