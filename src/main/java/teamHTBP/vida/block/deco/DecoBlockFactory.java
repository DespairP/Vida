package teamHTBP.vida.block.deco;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

/**
 * 方块工厂类
 * 有关Block.Properties,参见：{@link Block.Properties}<br/>
 *
 */
public class DecoBlockFactory {
    private static final Block.Properties BASIC_PROPERTIES = Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F);

    public static Block yAxis(){
        return  new DecoWithYBlock(BASIC_PROPERTIES);
    }

    public static Block normal(){
        return new DecoBlock(BASIC_PROPERTIES);
    }

    public static Block fence(){
        return new DecoFenceBlock(BASIC_PROPERTIES);
    }

    public static Block door(){
        return new DecoDoorBlock(BASIC_PROPERTIES);
    }

    public static Block slab(){
        return new DecoSlabBlock(BASIC_PROPERTIES);
    }

    public static Block stairs(){
        return null;
    }
}
