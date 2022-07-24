package teamHTBP.vida.client.model;

import com.google.common.base.CaseFormat;
import com.google.common.base.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.model.armor.armor.ArmorModelSeasonApprentice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Vida.MOD_ID, value = Dist.CLIENT)
public class LayerRegistryHandler {
    public static final ModelLayerLocation SEASON_APPRENTICE = register("season_apprentice");

    static void spRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SEASON_APPRENTICE, ArmorModelSeasonApprentice::createBodyLayer);
    }

    private static final Map<Class<?>, Supplier<Model>> FACTORY = new HashMap<>();

    public static <T extends Model> T create(Class<?> clazz) {
        return (T) FACTORY.get(clazz).get();
    }

    @SubscribeEvent
    public static void onEvent(EntityRenderersEvent.RegisterLayerDefinitions event) {
        try {
            List<ModelData> dataList = getModelData();

            for (ModelData modelData : dataList) {
                Class<?> aClass = modelData.aClass;
                String to = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, aClass.getSimpleName());
                ModelLayerLocation name = register(to);

                FACTORY.put(aClass, () -> {
                    try {
                        return (Model) aClass.getConstructor(ModelPart.class)
                                .newInstance(Minecraft.getInstance().getEntityModels().bakeLayer(name));
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    throw new RuntimeException("自动注册模型失败了，怎么惠氏呢？");
                });

                Method createBodyLayer = aClass.getMethod("createBodyLayer");
                event.registerLayerDefinition(name, () -> {
                    try {
                        return (LayerDefinition) createBodyLayer.invoke(null);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    return null;
                });
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        spRegister(event);
    }

    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String part) {
        return new ModelLayerLocation(new ResourceLocation(Vida.MOD_ID, path), part);
    }

    public static List<ModelData> getModelData() {
        List<ModFileScanData> allScanData = ModList.get().getAllScanData();
        Type type = Type.getType(AutoRegModel.class);

        List<ModelData> result = new ArrayList<>();

        for (var scanData : allScanData) {
            Set<ModFileScanData.AnnotationData> annotations = scanData.getAnnotations();

            for (var annotation : annotations) {
                if (Objects.equals(annotation.annotationType(), type)) {
                    try {
                        Class<?> aClass = Class.forName(annotation.memberName());

                        if (Model.class.isAssignableFrom(aClass)) {
                            result.add(new ModelData(aClass));
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return result;
                    }
                }
            }
        }

        return result;
    }

    public static record ModelData(Class<?> aClass) { }
}