package teamHTBP.vida.datapack;

import com.mojang.serialization.Codec;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.datapack.blueprint.BlueprintManager;
import teamHTBP.vida.datapack.element.ElementPotentialManager;
import teamHTBP.vida.datapack.guidebook.GuideBookGuideHandler;
import teamHTBP.vida.datapack.guidebook.GuideBookPageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber
public class ModDataPacks {
    private static final List<DeferredRegister<?>> REGISTRIES = new ArrayList<>();

    @SubscribeEvent
    public static void onEvent(AddReloadListenerEvent event) {
        event.addListener(ElementPotentialManager.INSTANCE);
        event.addListener(BlueprintManager.INSTANCE);
        event.addListener(GuideBookGuideHandler.INSTANCE);
        event.addListener(GuideBookPageHandler.INSTANCE);
    }


    public static final DeferredRegister<ElementPotentialManager> ELEMENT_POTENTIAL =
            register(ElementPotentialManager.INSTANCE, ElementPotentialManager.CODEC);

    public static final DeferredRegister<BlueprintManager> BLUEPRINT_MANAGER =
            register(BlueprintManager.INSTANCE, BlueprintManager.CODEC);

    public static final DeferredRegister<GuideBookGuideHandler> GUIDE_BOOK_GUIDE_HANDLER =
            register(GuideBookGuideHandler.INSTANCE, GuideBookGuideHandler.CODEC);

    public static final DeferredRegister<GuideBookPageHandler> GUIDE_BOOK_PAGE_HANDLER =
            register(GuideBookPageHandler.INSTANCE, GuideBookPageHandler.CODEC);


    static <T extends ModDataPack<T>> DeferredRegister<T> register(
            T instance,
            Codec<T> codec
    ) {
        var key = instance.REGISTRY_KEY;

        DeferredRegister<T> register = DeferredRegister.create(key, Vida.MOD_ID);

        Supplier<IForgeRegistry<T>> registry = register.makeRegistry((Class<T>) instance.getClass(),
                () -> new RegistryBuilder<T>().disableSaving().dataPackRegistry(codec, codec));

        RegistryObject<T> registryObject = register.register(key.location().getPath(), () -> instance);

        REGISTRIES.add(register);
        return register;
    }

    public static void register(IEventBus bus) {
        REGISTRIES.forEach(r -> r.register(bus));
    }
}
