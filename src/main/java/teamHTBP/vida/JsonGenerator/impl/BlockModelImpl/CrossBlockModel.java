package teamHTBP.vida.JsonGenerator.impl.BlockModelImpl;

import com.google.common.collect.ImmutableMap;
import teamHTBP.vida.JsonGenerator.impl.EnumBlockModelBasic;
import teamHTBP.vida.JsonGenerator.impl.IBlockModelJsonWriter;

import java.util.Map;

import static teamHTBP.vida.JsonGenerator.Helper.BlockModelWriterHelper.MOD_ID;

public class CrossBlockModel implements IBlockModelJsonWriter {

    @Override
    public String getParent() {
        return EnumBlockModelBasic.Cross.parent;
    }

    @Override
    public Map<String, String> getTextures(String name) {
        return ImmutableMap.of("cross",MOD_ID + ":block/" + name);
    }
}
