package teamHTBP.vida.JsonGenerator.Properties;

import com.google.gson.annotations.SerializedName;

public class ItemModelJson {
    @SerializedName("parent")
    public String parent;
    @SerializedName("textures")
    public String textures;

    public ItemModelJson(String parent, String textures) {
        this.parent = parent;
        this.textures = textures;
    }
}
