package teamHTBP.vida.capability.blueprintCapability;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class Blueprint implements IForgeRegistryEntry<Blueprint> {
    /**
     * 图纸稀有度
     */
    private EnumBlueprintRarity rarity = EnumBlueprintRarity.normal;
    /**
     * 图纸id
     */
    private String blueprintID = "";
    /**
     * 图纸index，无实际作用
     */
    private int index = 0;
    /**
     *
     * */
    private ResourceLocation location;

    public Blueprint(EnumBlueprintRarity rarity, String blueprintID, int index) {
        this.rarity = rarity;
        this.blueprintID = blueprintID;
        this.index = index;
    }

    /**
     * 获取翻译后的图纸名称
     */
    public String getTranslatedName() {
        return I18n.format(blueprintID);
    }

    /**
     * 获取原生名字
     */
    @Deprecated
    public String getOriginName() {
        return blueprintID;
    }

    /**
     * 获取图纸ID
     */
    public String getBlueprintID() {
        return blueprintID;
    }

    /**
     * 获取图纸稀有度
     */
    public EnumBlueprintRarity getRarity() {
        return rarity;
    }


    @Override
    public Blueprint setRegistryName(ResourceLocation name) {
        throw new IllegalArgumentException("cannot setRegistryName by this method");
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return ResourceLocation.create("vida",':');
    }

    @Override
    public Class<Blueprint> getRegistryType() {
        return Blueprint.class;
    }
}
