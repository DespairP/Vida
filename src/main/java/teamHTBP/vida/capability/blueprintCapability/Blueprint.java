package teamHTBP.vida.capability.blueprintCapability;

import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintRarity;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintType;
import teamHTBP.vida.helper.blueprintHelper.IBlueprint;
import teamHTBP.vida.helper.blueprintHelper.IChallenge;

public class Blueprint implements IBlueprint {
    /**图纸稀有度*/
    private EnumBlueprintRarity rarity = EnumBlueprintRarity.NORMAL;
    /**图纸id*/
    private final String id;
    /**图纸功能*/
    private final EnumBlueprintType type;
    /**图纸对应挑战*/
    private final IChallenge challenge;

    public Blueprint(EnumBlueprintRarity rarity, String id, EnumBlueprintType type, IChallenge challenge) {
        this.rarity = rarity;
        this.id = id;
        this.type = type;
        this.challenge = challenge;
    }


    @Override
    public String getId() {
        return null;
    }

    /** 获取图纸稀有度 */
    public EnumBlueprintRarity getRarity() {
        return rarity;
    }

    @Override
    public EnumBlueprintType getType() {
        return type;
    }

    @Override
    public IChallenge getChallenge() {
        return challenge;
    }

}
