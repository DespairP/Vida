package teamHTBP.vida.datagen.JsonGenerator.impl.BlockModelImpl;

import com.google.common.collect.ImmutableMap;
import teamHTBP.vida.datagen.JsonGenerator.impl.EnumBlockModelBasic;
import teamHTBP.vida.datagen.JsonGenerator.impl.IBlockModelJsonWriter;

import java.util.Map;

import static teamHTBP.vida.datagen.JsonGenerator.helper.BlockStateWriterHelper.MOD_ID;

public class PillarBlockModel implements IBlockModelJsonWriter {
    private String topTexture = "";
    private String sideTexture = "";

    public PillarBlockModel(String topTexture, String sideTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
    }

    public PillarBlockModel() {

    }

    @Override
    public String getParent() {
        return EnumBlockModelBasic.Pillar.parent;
    }

    @Override
    public Map<String, String> getTextures(String name) {
        if (topTexture.equals("") && sideTexture.equals(""))
            return ImmutableMap.of("end", MOD_ID + ":block/" + name, "side", MOD_ID + ":block/" + name + "_side");
        return ImmutableMap.of("end", MOD_ID + ":block/" + topTexture, "side", MOD_ID + ":block/" + sideTexture);
    }
}
