package teamHTBP.vida.capability.blueprintCapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;

public interface IBlueprintCapability extends INBTSerializable<CompoundNBT> {
    /**
     * 获取所有可能解锁的图纸
     *
     * @return 可解锁的图纸
     */
    Map<String, Blueprint> getAllBlueprints();

    /**
     * 解锁图纸
     *
     * @param blueprint 需要解锁的图纸
     * @return true - 解锁成功，false - 该图纸已被解锁过或其他原因
     */
    boolean unlockBlueprint(Blueprint blueprint);

    /**
     * 获取所有已解锁的图纸
     *
     * @return 已经解锁的图纸
     */
    Map<String, Blueprint> getUnlockedBlueprints();


    /**
     * 获取所有未解锁的图纸
     *
     * @return 未解锁的图纸
     */
    Map<String, Blueprint> getLockedBlueprints();

    /**
     * 获取在一定稀有度下的所有图纸
     *
     * @param rarity              稀有度
     * @param shouldLockedContain 未解锁的图纸是否包含
     */
    Map<String, Blueprint> getBlueprintsWithRarity(EnumBlueprintRarity rarity, boolean shouldLockedContain);
}
