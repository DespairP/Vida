package teamHTBP.vida.common.block.deco;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

/**
 * 方块工厂类
 * 有关Block.Properties,参见：{@link Block.Properties}<br/>
 *
 */
public class DecoBlockFactory {
    private static final Block.Properties BASIC_PROPERTIES = Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F);
    private static final Block.Properties PLANT_PROPERTIES = Block.Properties.of(Material.PLANT).noCollission().strength(0.0f).sound(SoundType.GRASS);;

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

    public static Block plant(){
        return new DecoBlock(PLANT_PROPERTIES);
    }
}
