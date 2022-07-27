package teamHTBP.vida.api.core.blueprint;

import teamHTBP.vida.core.blueprint.EnumBlueprintRarity;
import teamHTBP.vida.core.blueprint.EnumBlueprintType;

public interface IBlueprint {

    /**获取图纸Id*/
    String getId();

    /**获取图纸稀有度*/
    EnumBlueprintRarity getRarity();

    /**获取图纸类型*/
    EnumBlueprintType getType();

    /**获取图纸对应挑战*/
    IChallenge getChallenge();

}