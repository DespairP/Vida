package teamHTBP.vida.common.block.decoration;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

/**
 * 方块工厂类
 * 有关Block.Properties,参见：{@link Block.Properties}<br/>
 *
 */
public class DecoBlockFactory {
    private static final Block.Properties BASIC_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F);
    private static final Block.Properties PLANT_PROPERTIES = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().zeroHardnessAndResistance().sound(SoundType.PLANT);

    public static Block yAxis(){
        return  new DecoWithYBlock(BASIC_PROPERTIES);
    }

    public static Block normal(){
        return new DecoBasicBlock(BASIC_PROPERTIES);
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
        return new DecoBasicBlock(PLANT_PROPERTIES);
    }

    public static Block doublePlant(){
        return new DoublePlantBlock(PLANT_PROPERTIES);
    }

    public static Block log(){
        return new RotatedPillarBlock(BASIC_PROPERTIES);
    }
}
