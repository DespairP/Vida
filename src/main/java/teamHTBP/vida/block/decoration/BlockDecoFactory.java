package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * 方块工厂类
 * 有关Block.Properties,参见：{@link Block.Properties}<br/>
 * @see BlockDecoFactory.DecoBlockType
 * @see BlockDecoFactory.PropertiesBuilder
 * */
public class BlockDecoFactory {
    /**生产方块的properties,使用createBuilder可以快速的建造一个properties {@link BlockDecoFactory#createBuilder()}*/
    private Block.Properties properties;

    public static PropertiesBuilder createBuilder(){
        return new PropertiesBuilder();
    }

    /**
     * 根据传入的属性生产一个装饰性方块
     * @param type 方块的属性{@link DecoBlockType}
     * @return 生产的方块
     * */
    public Block produceDecoBlock(DecoBlockType type){
        switch (type){
            case BASIC:
                return new BlockDecoBasic(properties);
            case YAXIS:
                return new BlockDecoWithY(properties);
        }
        return new BlockDecoBasic(properties);
    }

    /**Properties的静态Builder*/
    public static class PropertiesBuilder{
        public float resistence = 3.0f;
        public SoundType soundType = SoundType.STONE;
        public Material material = Material.ROCK;

        public PropertiesBuilder setResistence(float resistence) {
            this.resistence = resistence;
            return this;
        }

        public PropertiesBuilder setSoundType(SoundType soundType) {
            this.soundType = soundType;
            return this;
        }

        public BlockDecoFactory build(){
            BlockDecoFactory factory = new BlockDecoFactory();
            factory.properties = Block.Properties.create(material).sound(soundType).hardnessAndResistance(resistence);
            return factory;
        }

    }

    /**
     * 方块属性  <br/>
     * Basic为 普通方块 <br/>
     * YAXIS 按照Y轴旋转的方块
     * */
    public enum DecoBlockType{
        BASIC,YAXIS;
    }
}
