package teamHTBP.vida.common.datapack.blueprint;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.common.capability.blueprint.Blueprint;
import teamHTBP.vida.common.datapack.AbstractVidaDataPack;
import teamHTBP.vida.core.blueprint.EnumBlueprintRarity;
import teamHTBP.vida.core.blueprint.EnumBlueprintType;
import teamHTBP.vida.helper.json.JsonUtils;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**PENDING,还未上线*/
public class BlueprintManager extends AbstractVidaDataPack<BlueprintManager> {
    public static final BlueprintManager INSTANCE = new BlueprintManager();

    /**蓝图管理*/
    private final Map<ResourceLocation, Blueprint> blueprints = Collections.emptyMap();
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();

    public BlueprintManager() {
        super(JsonUtils.INSTANCE.noExpose, "blueprints");
    }

    @Override
    protected BlueprintManager load() {
        blueprints.clear();
        Map<ResourceLocation,Blueprint> processBlueprints = object.entrySet().stream().map(
                (set)->{
                    Blueprint blueprint = parseBlueprint(set.getKey(), set.getValue());
                    return new AbstractMap.SimpleEntry<>(set.getKey(), blueprint);
                }
        ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        blueprints.putAll(processBlueprints);
        return this;
    }

    public Blueprint parseBlueprint(ResourceLocation location, JsonElement element){
        JsonObject object = element.getAsJsonObject();
        EnumBlueprintRarity rarity = EnumBlueprintRarity.valueOf(Optional.ofNullable(object.get("rarity").getAsString()).orElse("NORMAL"));
        EnumBlueprintType type = EnumBlueprintType.valueOf(Optional.ofNullable(object.get("type").getAsString()).orElse("OTHERS"));
        return new Blueprint(rarity, location.toString(), type, null);
    }

    @Override
    public Class<BlueprintManager> getRegistryType() {
        return BlueprintManager.class;
    }

    public static final Codec<BlueprintManager> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.STRING.fieldOf("data").forGetter(BlueprintManager::save))
                    .apply(instance, BlueprintManager::load));

    private static BlueprintManager load(String json) {
        INSTANCE.object = JsonUtils.INSTANCE.noExpose.fromJson(json,
                TypeToken.getParameterized(Map.class, ResourceLocation.class, JsonElement.class).getType());

        if (INSTANCE.object != null) {
            return INSTANCE.load();
        }
        else {
            return INSTANCE;
        }
    }
}
