package teamHTBP.vida.capability.BlueprintSystem;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundNBT;
import teamHTBP.vida.Helper.BlueprintHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlueprintCapability implements IBlueprintCapability{
    private LinkedHashMap<String,Blueprint> unlockedBluePrint;

    public BlueprintCapability() {
        this.unlockedBluePrint = new LinkedHashMap();
    }

    @Override
    public Map<String,Blueprint> getAllBlueprints() {
        return BlueprintHelper.getAllBlueprints();
    }

    @Override
    public boolean unlockBlueprint(Blueprint blueprint) {
        if(blueprint == null){
            throw new NullPointerException("blueprint is NULL");
        }
        if(unlockedBluePrint.containsValue(blueprint) || unlockedBluePrint.containsKey(blueprint.getBlueprintID())){
            return false;
        }
        unlockedBluePrint.put(blueprint.getBlueprintID(), blueprint);
        return true;
    }

    @Override
    public Map<String,Blueprint> getUnlockedBlueprints() {
        return unlockedBluePrint;
    }

    @Override
    public Map<String,Blueprint> getLockedBlueprints() {
        final Map<String,Blueprint> allBluePrints = getAllBlueprints();
        final Map<String,Blueprint> lockedPrints = new LinkedHashMap<>();
        allBluePrints.forEach((id,blueprint)->{
            if(!unlockedBluePrint.containsKey(id) && !unlockedBluePrint.containsValue(blueprint))
                lockedPrints.put(id,blueprint);
        });
        return lockedPrints;
    }

    @Override
    public Map<String,Blueprint> getBlueprintsWithRarity(EnumBlueprintRarity rarity, boolean shouldLockedContain) {
        if(!shouldLockedContain)
           return unlockedBluePrint.entrySet().stream()
                   .filter(stringBlueprintEntry -> stringBlueprintEntry.getValue().getRarity() == rarity)
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return getAllBlueprints().entrySet().stream()
                .filter(stringBlueprintEntry -> stringBlueprintEntry.getValue().getRarity() == rarity)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT playerNbt = new CompoundNBT();
        CompoundNBT nbtBluePrint = new CompoundNBT();
        final Map<String,Blueprint> allBlueprints = getAllBlueprints();
        allBlueprints.forEach((id,blueprint) -> nbtBluePrint.putBoolean(id + ".blueprint", false));
        unlockedBluePrint.forEach((id, blueprint) -> nbtBluePrint.putBoolean(id + ".blueprint", true));
        playerNbt.put("blueprints", nbtBluePrint);
        return playerNbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CompoundNBT bluePrintNbt = nbt.getCompound("blueprints");
        final Map<String,Blueprint> allBlueprints = getAllBlueprints();
        unlockedBluePrint.clear();
        allBlueprints.forEach((id,blueprint) -> {
            if (bluePrintNbt.getBoolean(id + ".blueprint")) {
                unlockedBluePrint.put(id , blueprint);
            }
        });
    }
}
