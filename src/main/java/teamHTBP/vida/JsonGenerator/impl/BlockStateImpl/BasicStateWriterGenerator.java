package teamHTBP.vida.JsonGenerator.impl.BlockStateImpl;

import com.google.common.collect.ImmutableList;
import teamHTBP.vida.JsonGenerator.Helper.BlockStateWriterHelper;
import teamHTBP.vida.JsonGenerator.impl.IBlockStateJsonWriter;

import java.util.List;

public class BasicStateWriterGenerator {
    private List<String> indexes;
    private List<String> names;
    private List<String> attrs;

    public static IBlockStateJsonWriter generateNoneAxis() {
        return (BlockStateWriterHelper::getNoneAxisModel);
    }

    public static IBlockStateJsonWriter generateRotate() {
        return BlockStateWriterHelper::getRotationModel;
    }

    public IBlockStateJsonWriter generateSpecialWithSingleAttr() {
        return (name) -> BlockStateWriterHelper.getSpecialModel(attrs.get(0), indexes, names);
    }

    public static class Builder {
        BasicStateWriterGenerator generator;

        public static Builder getBuilder() {
            Builder builder = new Builder();
            builder.generator = new BasicStateWriterGenerator();
            return builder;
        }

        public Builder setIndexes(String... indexes) {
            generator.indexes = ImmutableList.copyOf(indexes);
            return this;
        }

        public Builder setNames(String... names) {
            generator.names = ImmutableList.copyOf(names);
            return this;
        }

        public Builder setAttrs(String... attrs) {
            generator.attrs = ImmutableList.copyOf(attrs);
            return this;
        }

        public BasicStateWriterGenerator build() {
            return generator;
        }
    }

}
