package teamHTBP.vida.common.datapack.element;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import teamHTBP.vida.common.datapack.AbstractVidaDataPack;
import teamHTBP.vida.helper.json.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class ElementPotentialManager extends AbstractVidaDataPack<ElementPotentialManager> {
    public static final ElementPotentialManager INSTANCE = new ElementPotentialManager();

    @Getter Map<Item, ElementPotential> byItem = new HashMap<>();

    private ElementPotentialManager() {
        super(JsonUtils.INSTANCE.noExpose, "element_potential");
    }

    @Override
    protected ElementPotentialManager load() {
        byItem.clear();

        Gson gson = JsonUtils.INSTANCE.noExpose;

        object.forEach((location, jsonElement) -> {
            ElementPotential potential = gson.fromJson(jsonElement, ElementPotential.class);
            byItem.put(potential.getItem(), potential);
        });

        return this;
    }

    @Override
    public Class<ElementPotentialManager> getRegistryType() {
        return ElementPotentialManager.class;
    }

    public static final Codec<ElementPotentialManager> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.STRING.fieldOf("data").forGetter(ElementPotentialManager::save))
                    .apply(instance, ElementPotentialManager::load));

    private static ElementPotentialManager load(String json) {
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
