package teamHTBP.vida.event.server.datapack.element;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import teamHTBP.vida.utils.json.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class ElementPotentialManager extends SimpleJsonResourceReloadListener {
    @Getter Map<Item, ElementPotential> byItem = new HashMap<>();

    public ElementPotentialManager() {
        super(JsonUtils.INSTANCE.noExpose, "element_potential");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        byItem.clear();
        Gson gson = JsonUtils.INSTANCE.noExpose;

        objectIn.forEach((location, jsonElement) -> {
            ElementPotential potential = gson.fromJson(jsonElement, ElementPotential.class);
            byItem.put(potential.getItem(), potential);
        });
    }
}
