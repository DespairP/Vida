package teamHTBP.vida.common.capability.blueprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import teamHTBP.vida.core.blueprint.EnumBlueprintRarity;
import teamHTBP.vida.core.blueprint.EnumBlueprintType;
import teamHTBP.vida.api.core.blueprint.IBlueprint;
import teamHTBP.vida.api.core.blueprint.IChallenge;

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
