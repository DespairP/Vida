package teamHTBP.vida.capability.blueprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import teamHTBP.vida.helper.blueprint.EnumBlueprintRarity;
import teamHTBP.vida.helper.blueprint.EnumBlueprintType;
import teamHTBP.vida.helper.blueprint.IBlueprint;
import teamHTBP.vida.helper.blueprint.IChallenge;

@Data
@AllArgsConstructor
public class Blueprint implements IBlueprint {
    /**图纸稀有度*/
    private EnumBlueprintRarity rarity;
    /**图纸id*/
    private final String id;
    /**图纸功能*/
    private final EnumBlueprintType type;
    /**图纸对应挑战*/
    private final IChallenge challenge;
}
