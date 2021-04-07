package teamHTBP.vida.capability.BlueprintSystem;

import net.minecraft.client.resources.I18n;

public class Blueprint {
    /**图纸稀有度*/
    private EnumBlueprintRarity rarity = EnumBlueprintRarity.normal;
    /**图纸id*/
    private String blueprintID = "";
    /**图纸index，无实际作用*/
    private int index = 0;

    public Blueprint(EnumBlueprintRarity rarity, String blueprintID,int index) {
        this.rarity = rarity;
        this.blueprintID = blueprintID;
        this.index = index;
    }

    /**获取翻译后的图纸名称*/
    public String getTranslatedName(){
        return I18n.format(blueprintID);
    }

    /**获取原生名字*/
    @Deprecated
    public String getOriginName(){
        return blueprintID;
    }

    /**获取图纸ID*/
    public String getBlueprintID(){
        return blueprintID;
    }

    /**获取图纸稀有度*/
    public EnumBlueprintRarity getRarity(){
        return rarity;
    }



}
