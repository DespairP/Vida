package teamHTBP.vida.datagen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import teamHTBP.vida.utils.json.JsonUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author DustW
 */
@AllArgsConstructor
public class VidaLanguageProvider implements DataProvider {
    String modId;
    DataGenerator generator;

    /**        语言                     键      值  */
    final Map<String, List<Map.Entry<String, String>>> map = new HashMap<>();

    protected void addLanguageEntries() {

    }

    @Override
    public void run(HashCache cache) throws IOException {
        addLanguageEntries();

        for (Map.Entry<String, List<Map.Entry<String, String>>> mainEntry : map.entrySet()) {
            String lang = mainEntry.getKey();
            List<Map.Entry<String, String>> list = mainEntry.getValue();

            JsonObject langJson = new JsonObject();

            list.forEach(entry -> langJson.addProperty(entry.getKey(), entry.getValue()));

            Gson gson = JsonUtils.INSTANCE.noExpose;
            Path path = generator.getOutputFolder().resolve("assets/" + modId + "/lang/" + lang + ".json");

            DataProvider.save(gson, cache, langJson, path);
        }
    }

    protected static final String EN_US = "en_us";
    protected static final String ZH_CN = "zh_cn";

    protected void withItem(Supplier<Block> block, String enUs, String zhCn) {
        String blockKey = getKey(block.get());
        String itemKey = getKey(block.get().asItem());

        List<Map.Entry<String, String>> enUsList = getLang(EN_US);
        enUsList.add(new AbstractMap.SimpleEntry<>(blockKey, enUs));
        enUsList.add(new AbstractMap.SimpleEntry<>(itemKey, enUs));

        List<Map.Entry<String, String>> zhCnList = getLang(ZH_CN);
        zhCnList.add(new AbstractMap.SimpleEntry<>(blockKey, zhCn));
        zhCnList.add(new AbstractMap.SimpleEntry<>(itemKey, zhCn));
    }

    protected String getKey(Block block) {
        return Util.makeDescriptionId("block", block.getRegistryName());
    }

    protected String getKey(Item item) {
        return Util.makeDescriptionId("item", item.getRegistryName());
    }

    protected List<Map.Entry<String, String>> getLang(String lang) {
        return map.computeIfAbsent(lang, (a) -> new ArrayList<>());
    }

    @Override
    public String getName() {
        return "Vida Language Provider";
    }
}
