package teamHTBP.vida.JsonGenerator.Helper;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class BlockModelWriterHelper {
    public static final String MOD_ID = "vida";


    public static Map<String, String> pillarCube(String... name) {
        return ImmutableMap.of("end", MOD_ID + ":block/" + name[0] + "_top", "side", MOD_ID + ":block/" + name[1] + "_side");
    }

}
