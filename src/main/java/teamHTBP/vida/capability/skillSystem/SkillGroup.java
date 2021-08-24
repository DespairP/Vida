package teamHTBP.vida.capability.skillSystem;

import java.util.LinkedHashMap;

/**
 * SkillGroup是技能的一组，具体表现为:<br/>
 * X X <br/>
 * X 主 X <br/>
 * X X <br/>
 * [X的组合即表示SkillGroup]<br/>
 * 所有的X技能都依赖于主技能，即该技能组的前置技能都是 这个 主技能<br/>
 * 一个父技能的所有子技能都存储在父技能的childSkill属性中--{@link Skill#childSkills}<br/>
 * SkillGroup还记录了主技能的中心位置，所以注册技能时使用SkillGroup可以方便的进行技能的注册<br/>
 *
 * @see SkillGroup#appendSkill(ISkill, EnumSkillDirection)
 * @see SkillGroup#appendSkillWithBranch(ISkill, EnumSkillDirection)
 */
public class SkillGroup {
    int centerX = 0;
    int centerY = 0;
    /**
     * 技能组
     */
    LinkedHashMap<String, ISkill> skillTree;
    /**
     * 该技能树组的父技能
     */
    ISkill parentSkill = null;

    /**
     * 构造一个新的子技能组
     */
    public SkillGroup() {
        skillTree = new LinkedHashMap<String, ISkill>();
    }

    /**
     * 以中央为x和y生成一个子技能组
     */
    protected SkillGroup(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        skillTree = new LinkedHashMap<String, ISkill>();
    }

    /**
     * 在子技能组中增加一个技能
     *
     * @param skill          要添加的技能
     * @param skillDirection 技能的位置--{@link EnumSkillDirection}
     * @return 此技能组，可以继续链式的添加
     */
    public SkillGroup appendSkill(ISkill skill, EnumSkillDirection skillDirection) {
        switch (skillDirection) {
            case TopLeft:
                skill.setSkillPosition(centerX - 25, centerY - 46);
                break;
            case TopRight:
                skill.setSkillPosition(centerX + 25, centerY - 46);
                break;
            case Left:
                skill.setSkillPosition(centerX - 49, centerY);
                break;
            case Right:
                skill.setSkillPosition(centerX + 49, centerY);
                break;
            case BottomLeft:
                skill.setSkillPosition(centerX - 25, centerY + 46);
                break;
            case BottomRight:
                skill.setSkillPosition(centerX + 25, centerY + 46);
                break;
        }
        if (skill == null) throw new NullPointerException("skill is NULL");
        skillTree.put(skill.getSkillName(), skill);
        return this;
    }

    /**
     * 以该技能为父结点，增加一个技能子组
     *
     * @param skill          需要添加的技能
     * @param skillDirection 技能的位置
     * @return 该技能的子组
     */
    public SkillGroup appendSkillWithBranch(ISkill skill, EnumSkillDirection skillDirection) {
        SkillGroup childGroup = new SkillGroup();
        switch (skillDirection) {
            case TopLeft:
                childGroup.setCenter(centerX - 25, centerY - 46);
                break;
            case TopRight:
                childGroup.setCenter(centerX + 25, centerY - 46);
                break;
            case Left:
                childGroup.setCenter(centerX - 49, centerY);
                break;
            case Right:
                childGroup.setCenter(centerX + 49, centerY);
                break;
            case BottomLeft:
                childGroup.setCenter(centerX - 25, centerY + 46);
                break;
            case BottomRight:
                childGroup.setCenter(centerX + 25, centerY + 46);
                break;
        }
        skill.setSkillPosition(childGroup.centerX, childGroup.centerY);
        skill.setChildSkills(childGroup);
        childGroup.parentSkill = skill;
        skillTree.put(skill.getSkillName(), skill);
        return childGroup;
    }

    /**
     * 设置此组的中心
     */
    public void setCenter(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }
}
