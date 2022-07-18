package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * 方块工厂类
 * 有关Block.Properties,参见：{@link Block.Properties}<br/>
 *
 */
public class BlockDecoFactory {
    private static final Block.Properties BASIC_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F);

    public static Block yAxis(){
        return  new BlockDecoWithY(BASIC_PROPERTIES);
    }

    public static Block normal(){
        return new BlockDecoBasic(BASIC_PROPERTIES);
    }

    public static Block fence(){
        return new BlockDecoFence(BASIC_PROPERTIES);
    }

    public static Block door(){
        return new BlockDecoDoor(BASIC_PROPERTIES);
    }

    public static Block slab(){
        return new BlockDecoSlab(BASIC_PROPERTIES);
    }

    public static Block stairs(){
        return null;
    }
}
