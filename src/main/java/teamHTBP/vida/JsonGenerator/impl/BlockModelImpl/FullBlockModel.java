package teamHTBP.vida.JsonGenerator.impl.BlockModelImpl;

import com.google.common.collect.ImmutableMap;
import teamHTBP.vida.JsonGenerator.impl.EnumBlockModelBasic;
import teamHTBP.vida.JsonGenerator.impl.IBlockModelJsonWriter;

import java.util.Map;

import static teamHTBP.vida.JsonGenerator.Helper.BlockStateWriterHelper.MOD_ID;

public class FullBlockModel implements IBlockModelJsonWriter {

    @Override
    public String getParent() {
        return EnumBlockModelBasic.Full.parent;
    }

    @Override
    public Map<String,String> getTextures(String name){
        return ImmutableMap.of("all",MOD_ID + ":block/" + name);
    }

}
