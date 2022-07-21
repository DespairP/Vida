package teamHTBP.vida.datagen.JsonGenerator.Properties;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BlockStateJson {
    @SerializedName("variants")
    public final Map variants;

    public BlockStateJson(Map variants) {
        this.variants = variants;
    }
}
