package teamHTBP.vida.Capability;

import net.minecraft.util.ResourceLocation;

public interface ISkill {
    /**获取技能的名字**/
    public abstract String getSkillName();
    /**获取技能的翻译名**/
    public abstract String getSkillTranlateName();
    /**获取技能最大升级能量**/
    public abstract int getSkillExp();
    /**获取技能所在的ResourceLocation**/
    public ResourceLocation getLocation();
    /**设置技能图标所在的ResourceLocation**/
    public void setLocation(ResourceLocation location);

}
