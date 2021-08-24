package teamHTBP.vida.capability.skillSystem;

import java.util.LinkedList;

/**
 * SkillSurface是一个便于用户操作Skill的类，通常用于对用户显示<br/>
 * 可以通过SkillSurface获取当前界面中/游戏中该物品其中一个技能当前的 等级 和 经验<br/>
 * {@link SkillHelper#getAllSkillSurfaceFromItemStack(net.minecraft.item.ItemStack)}<br/>
 * 可以通过SkillHelper#getAllSkillSurface 获取该物品的所有封装后的技能组
 */
public class SkillSurface extends Skill {
    /**
     * 技能目前所获得的经验
     */
    public int skillCurrentExp = 0;
    /**
     * 技能目前所处于的技能等级
     */
    public int skillCurrentLevel = 1;
    /**
     * <b>UnusedAttribute</b>----该技能所封装的技能
     */
    public Skill containSkill;
    /**
     * 技能的前置技能的ID
     */
    public LinkedList<String> dependentSkill;

    /**
     * <b>will be Deprecated<b/>
     */
    public SkillSurface(String skillName, int skillExpPerLevel, int maxSkillLevel, EnumSkillType skillType, int textureU, int textureV, int textureSize) {
        super(skillName, skillExpPerLevel, maxSkillLevel, skillType, textureU, textureV, textureSize);
    }

    /**
     * 强制转型skill为skillSurface<br/>
     * <b>will be Deprecated</b>
     */
    public static SkillSurface castSkilltoSkillSurface(Skill skill) {
        SkillSurface surface = new SkillSurface(skill.skillName, skill.skillExpPerLevel, skill.getMaxSkillLevel(), skill.skillType, skill.textureU, skill.textureV, skill.textureSize);
        surface.setSkillX(skill.skillX);
        surface.setSkillY(skill.skillY);
        surface.dependentSkill = new LinkedList<>();
        return surface;
    }

}
