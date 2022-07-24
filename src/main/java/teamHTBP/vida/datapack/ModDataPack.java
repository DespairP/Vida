package teamHTBP.vida.datapack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.Vida;
import teamHTBP.vida.utils.json.JsonUtils;

import java.util.Map;

/**
 * @author DustW
 */
public abstract class ModDataPack<T> extends SimpleJsonResourceReloadListener implements IForgeRegistryEntry<T> {
    public final ResourceLocation NAME;
    public final ResourceKey<Registry<T>> REGISTRY_KEY;

    protected Map<ResourceLocation, JsonElement> object;

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        this.object = object;
        load();
    }

    protected abstract T load();

    protected String save() {
        return JsonUtils.INSTANCE.noExpose.toJson(object);
    }

    public ModDataPack(Gson gson, String path) {
        super(gson, path);
        NAME = new ResourceLocation(Vida.MOD_ID, path);
        REGISTRY_KEY = ResourceKey.createRegistryKey(NAME);
    }

    @Override
    public T setRegistryName(ResourceLocation name) {
        return (T) this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return NAME;
    }
}
