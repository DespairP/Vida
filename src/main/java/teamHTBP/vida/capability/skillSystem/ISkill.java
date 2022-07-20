package teamHTBP.vida.capability.skillSystem;

import net.minecraft.resources.ResourceLocation;

/**
 * 技能的接口，用于定义技能的各种抽象方法
 */
public interface ISkill {
    /**
     * 获取技能的名字
     **/
    String getSkillName();

    /**
     * 获取技能的翻译名
     **/
    String getSkillTranlateName();

    /**
     * 获取技能最大升级能量
     **/
    int getSkillExpPerLevel();

    /**
     * 获取技能所在的ResourceLocation
     */
    ResourceLocation getLocation();

    /**
     * 设置技能图标所在的ResourceLocation
     */
    void setLocation(ResourceLocation location);

    /**
     * 获取技能的品质
     */
    EnumSkillType getSkillType();

    /**
     * 获取技能能升到的最大等级
     */
    int getMaxSkillLevel();

    /**
     * 设置技能所在的X轴位置
     */
    void setSkillX(int skillX);

    /**
     * 设置技能所在的Y轴位置
     */
    void setSkillY(int skillY);

    /**
     * 设置技能X,Y坐标
     */
    void setSkillPosition(int skillX, int skillY);

    /**
     * 获得子技能
     */
    SkillGroup getChildSkills();

    /**
     * 设置子技能
     */
    void setChildSkills(SkillGroup skillGroup);
}
