package teamHTBP.vida.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.blueprintCapability.Blueprint;
import teamHTBP.vida.capability.blueprintCapability.EnumBlueprintRarity;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 图纸帮助类
 */
public class BlueprintHelper {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final AtomicReference<LinkedHashMap<String, Blueprint>> blueprints;
    public static boolean isRefreshing = false;

    static {
        blueprints = new AtomicReference<>();
    }

    /**
     * 获取所有图纸储存单例
     */
    public static LinkedHashMap<String, Blueprint> getAllBlueprints() {
        Vida.LOGGER.debug("getAllBlueprints");
        LinkedHashMap<String, Blueprint> map = blueprints.get();
        if (map == null) {
            synchronized (BlueprintHelper.blueprints) {
                map = blueprints.get();
                if (map == null) {
                    final LinkedHashMap<String, Blueprint> newblueprintList = new LinkedHashMap<>();
                    map = ((newblueprintList == null) ? BlueprintHelper.blueprints.get() : newblueprintList);
                    initBluePrint(map);
                    blueprints.set(map);
                }
            }
        }
        return blueprints.get();
    }

    /**
     * 初始化图纸
     */
    public static void initBluePrint(LinkedHashMap<String, Blueprint> map) {
        Vida.LOGGER.debug("blueprints are now being initialize");
        AtomicInteger index = new AtomicInteger(0);
        map.put("SimpleRocks", new Blueprint(EnumBlueprintRarity.normal, "SimpleRocks", index.getAndIncrement()));
        map.put("SimpleBlocks", new Blueprint(EnumBlueprintRarity.normal, "SimpleBlocks", index.getAndIncrement()));
        map.put("SimplePlants", new Blueprint(EnumBlueprintRarity.normal, "SimplePlants", index.getAndIncrement()));
        Vida.LOGGER.debug("initialize success ! you have registered " + index.get() + " blueprints!");

    }

    public static Optional<Blueprint> getBluePrint(String name) {
        return Optional.ofNullable(blueprints.get().get(name));
    }

    public synchronized static void refreshAllBluePrints() {
        isRefreshing = true;
        final LinkedHashMap<String, Blueprint> newblueprintList = new LinkedHashMap<>();
        initBluePrint(newblueprintList);
        blueprints.set(newblueprintList);
        isRefreshing = false;
    }
}
