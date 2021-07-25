package teamHTBP.vida.capability.skillSystem;

import net.minecraft.util.ResourceLocation;

/**技能的接口，用于定义技能的各种抽象方法*/
public interface ISkill {
    /**获取技能的名字**/
    public String getSkillName();
    /**获取技能的翻译名**/
    public String getSkillTranlateName();
    /**获取技能最大升级能量**/
    public int getSkillExpPerLevel();
    /**获取技能所在的ResourceLocation*/
    public ResourceLocation getLocation();
    /**设置技能图标所在的ResourceLocation*/
    public void setLocation(ResourceLocation location);
    /**获取技能的品质*/
    public EnumSkillType getSkillType();
    /**获取技能能升到的最大等级*/
    public int getMaxSkillLevel();
    /**设置技能所在的X轴位置*/
    public void setSkillX(int skillX);
    /**设置技能所在的Y轴位置*/
    public void setSkillY(int skillY);
    /**设置技能X,Y坐标*/
    public void setSkillPosition(int skillX,int skillY);
    /**设置子技能*/
    public void setChildSkills(SkillGroup skillGroup);
    /**获得子技能*/
    public SkillGroup getChildSkills();
}
