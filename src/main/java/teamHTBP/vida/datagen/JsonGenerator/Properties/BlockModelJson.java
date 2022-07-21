package teamHTBP.vida.datagen.JsonGenerator.Properties;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BlockModelJson {
    @SerializedName("parent")
    public final String parent;
    @SerializedName("textures")
    public final Map<String, String> textures;

    public BlockModelJson(String parent, Map<String, String> textures) {
        this.parent = parent;
        this.textures = textures;
    }


}
