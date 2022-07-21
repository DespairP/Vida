package teamHTBP.vida.datagen.JsonGenerator.impl.BlockModelImpl;

import com.google.common.collect.ImmutableMap;
import teamHTBP.vida.datagen.JsonGenerator.impl.EnumBlockModelBasic;
import teamHTBP.vida.datagen.JsonGenerator.impl.IBlockModelJsonWriter;

import java.util.Map;

import static teamHTBP.vida.datagen.JsonGenerator.helper.BlockModelWriterHelper.MOD_ID;

public class CrossBlockModel implements IBlockModelJsonWriter {

    @Override
    public String getParent() {
        return EnumBlockModelBasic.Cross.parent;
    }

    @Override
    public Map<String, String> getTextures(String name) {
        return ImmutableMap.of("cross", MOD_ID + ":block/" + name);
    }
}
