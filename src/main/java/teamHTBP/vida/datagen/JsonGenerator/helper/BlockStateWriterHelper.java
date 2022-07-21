package teamHTBP.vida.datagen.JsonGenerator.helper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlockStateWriterHelper {
    public static final String MOD_ID = "vida";

    /**
     * 获得普通BlockState
     * "" : {model : name}
     */
    public static Map<String, Map<String, String>> getNoneAxisModel(String name) {
        return ImmutableMap.of("", getModelAttrs(MOD_ID + ":block/" + name));
    }

    /**
     * 获得 <br/>
     * "attr = index_0" : { model : names_0} <br/>
     * "attr = index_1" : { model : names_1} <br/>
     * "attr = index_2" : { model : names_2} <br/>
     * 形式的Variants,生成数量为index和names数量
     */
    public static Map<String, Map<String, String>> getSpecialModel(String attr, List<String> index, List<String> names) throws Exception {
        if (index == null || names == null) throw new NullPointerException("index and names are");
        if (index.size() != names.size()) throw new Exception("BlockState: index and name length is not same!");
        Map<String, Map<String, String>> map = index.stream().collect(Collectors.toMap(
                key -> attr + "=" + key,
                key -> getModelAttrs(MOD_ID + ":block/" + names.get(index.indexOf(key)))
        ));
        return map;
    }

    /**
     * 获得 <br/>
     * "attr = index_0,facing = where" : { model : names_0 , y = int} <br/>
     * "attr = index_1,facing = where" : { model : names_1 , y = int} <br/>
     * "attr = index_2,facing = where" : { model : names_2 , y = int} <br/>
     * 形式的Variants，生成数量为 index数量 *4（有四个方向）
     */
    public static Map<String, Map<String, Object>> getSpecialModelWithAxis(String attr, ImmutableList<String> index, ImmutableList<String> names) throws Exception {
        if (index == null || names == null) throw new NullPointerException("index and names are");
        if (index.size() != names.size()) throw new Exception("BlockState: index and name length is not same!");
        Map<String, Map<String, Object>> map = new LinkedHashMap<>();
        for (int i = 0; i < EnumDirections.values().length; i++) {
            EnumDirections direction = EnumDirections.values()[i];
            index.forEach(indexName -> {
                map.put("facing=" + direction.direction + "," + attr + "=" + indexName, getModelAttrs(MOD_ID + ":block/" + index.get(index.indexOf(indexName)), direction));
            });
        }
        return map;
    }

    /**
     * 获得 <br/>
     * "facing = where" : { model : names_0 , y = int} <br/>
     * "facing = where" : { model : names_1 , y = int} <br/>
     * "facing = where" : { model : names_2 , y = int} <br/>
     * 形式的Variants，生成数量为 4（有四个方向）
     */
    public static Map<String, Map<String, Object>> getRotationModel(String name) throws Exception {
        return Arrays.stream(EnumDirections.values()).collect(Collectors.toMap(key -> "facing=" + key.direction, key -> getModelAttrs(MOD_ID + ":block/" + name, key)));
    }

    public static Map<String, String> getModelAttrs(String path) {
        return ImmutableMap.of("model", path);
    }

    public static Map<String, Object> getModelAttrs(String path, EnumDirections enumDirections) {
        return ImmutableMap.of("model", path, "y", enumDirections.axisRotate);
    }

}
