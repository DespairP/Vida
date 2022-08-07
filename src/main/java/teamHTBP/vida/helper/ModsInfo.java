package teamHTBP.vida.helper;

import net.minecraftforge.fml.ModList;

/**
 * @author DustW
 */
public class ModsInfo {
    public static final boolean LDL_LOADED = isLoaded("ldlib");
    public static final boolean SHIMMER_LOADED = isLoaded("shimmer");

    private static boolean isLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}
