package teamHTBP.vida.capability.blueprintCapability;

import net.minecraft.nbt.CompoundTag;
import teamHTBP.vida.helper.blueprintHelper.BlueprintHelper;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintRarity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlueprintCapability implements IBlueprintCapability {
    /**玩家已经解锁的blueprint*/
    private final LinkedHashMap<String, Blueprint> unlockedBluePrint;

    public BlueprintCapability() {
        this.unlockedBluePrint = new LinkedHashMap<>();
    }

    /**
     * 获取所有可能解锁的图纸
     *
     * @return 可解锁的图纸
     */
    @Override
    public Map<String, Blueprint> getAllBlueprints() {
        return BlueprintHelper.getAllBlueprints();
    }

    /**
     * 解锁图纸
     *
     * @param blueprint 需要解锁的图纸
     * @return true - 解锁成功，false - 该图纸已被解锁过或其他原因
     */
    @Override
    public boolean unlockBlueprint(Blueprint blueprint) {
        if (blueprint == null) {
            throw new NullPointerException("blueprint is NULL");
        }
        if (unlockedBluePrint.containsValue(blueprint) || unlockedBluePrint.containsKey(blueprint.getId())) {
            return false;
        }
        unlockedBluePrint.put(blueprint.getId(), blueprint);
        return true;
    }

    /**
     * 获取所有已解锁的图纸
     *
     * @return 已经解锁的图纸
     */
    @Override
    public Map<String, Blueprint> getUnlockedBlueprints() {
        return unlockedBluePrint;
    }

    /**
     * 获取所有未解锁的图纸
     *
     * @return 未解锁的图纸
     */
    @Override
    public Map<String, Blueprint> getLockedBlueprints() {
        final Map<String, Blueprint> allBluePrints = getAllBlueprints();
        final Map<String, Blueprint> lockedPrints = new LinkedHashMap<>();
        allBluePrints.forEach((id, blueprint) -> {
            if (!unlockedBluePrint.containsKey(id) && !unlockedBluePrint.containsValue(blueprint))
                lockedPrints.put(id, blueprint);
        });
        return lockedPrints;
    }

    /**
     * 获取在一定稀有度下的所有图纸
     *
     * @param rarity              稀有度
     * @param shouldLockedContain 未解锁的图纸是否包含
     * @return 稀有度等于rarity的所有图纸
     */
    @Override
    public Map<String, Blueprint> getBlueprintsWithRarity(EnumBlueprintRarity rarity, boolean shouldLockedContain) {
        if (!shouldLockedContain) {
            return unlockedBluePrint.entrySet().stream()
                    .filter(stringBlueprintEntry -> stringBlueprintEntry.getValue().getRarity() == rarity)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return getAllBlueprints().entrySet().stream()
                .filter(stringBlueprintEntry -> stringBlueprintEntry.getValue().getRarity() == rarity)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag playerNbt = new CompoundTag();
        CompoundTag nbtBluePrint = new CompoundTag();
        final Map<String, Blueprint> allBlueprints = getAllBlueprints();
        allBlueprints.forEach((id, blueprint) -> nbtBluePrint.putBoolean(id + ".blueprint", false));
        unlockedBluePrint.forEach((id, blueprint) -> nbtBluePrint.putBoolean(id + ".blueprint", true));
        playerNbt.put("blueprints", nbtBluePrint);
        return playerNbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag bluePrintNbt = nbt.getCompound("blueprints");
        final Map<String, Blueprint> allBlueprints = getAllBlueprints();
        unlockedBluePrint.clear();
        allBlueprints.forEach((id, blueprint) -> {
            if (bluePrintNbt.getBoolean(id + ".blueprint")) {
                unlockedBluePrint.put(id, blueprint);
            }
        });
    }
}
