package teamHTBP.vida.event.server.datapack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Getter;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.Item;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.utils.json.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class ElementPotentialManager extends JsonReloadListener {
    @Getter Map<Item, ElementPotential> byItem = new HashMap<>();

    public ElementPotentialManager() {
        super(JsonUtils.INSTANCE.noExpose, "element_potential");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        byItem.clear();
        Gson gson = JsonUtils.INSTANCE.noExpose;

        objectIn.forEach((location, jsonElement) -> {
            ElementPotential potential = gson.fromJson(jsonElement, ElementPotential.class);
            byItem.put(potential.getItem(), potential);
        });
    }
}
