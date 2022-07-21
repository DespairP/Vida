package teamHTBP.vida.capability.blueprintCapability;

import lombok.AllArgsConstructor;
import lombok.Data;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintRarity;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintType;
import teamHTBP.vida.helper.blueprintHelper.IBlueprint;
import teamHTBP.vida.helper.blueprintHelper.IChallenge;

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
