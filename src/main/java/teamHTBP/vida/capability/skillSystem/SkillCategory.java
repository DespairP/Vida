package teamHTBP.vida.capability.skillSystem;

import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;

public class SkillCategory {
    /**技能的数量*/
    public int skillNum;
    /**每个物品的技能组的起点技能区域*/
    public SkillGroup startSkill;

    /**
     * {@link SkillHelper#getAllSkillSurfaceFromItemStack(ItemStack)}的底层实现.
     * 思路：先从{@link SkillHelper#skillCategories}中寻找itemstack的开头category,然后进行深度搜索获得所有的技能
     * */
    protected LinkedHashMap<String,SkillSurface> getAllSkillsSurface(){
        LinkedHashMap<String,SkillSurface> surfaceSkills = new LinkedHashMap<>();
        if(startSkill == null) return null;
        else{
            getAllSkillsSurfaceFromGroup(surfaceSkills,startSkill);
        }
        return surfaceSkills;
    }

    /**
     * 遍历技能组
     * @param skills 保存所有技能的列表
     * @param group 需要遍历的技能组
     * */
    private void getAllSkillsSurfaceFromGroup(LinkedHashMap<String,SkillSurface> skills,SkillGroup group){
        group.skillTree.forEach((name,skill)->{
            getAllSkillsSurfaceFromSkill(skills, skill,group);
        });
    }

    /**
     * 从技能组中获取技能，如果技能无子技能，则返回，有就继续
     * @param skills 保存所有技能的列表
     * @param skill 已经检测到的技能
     * @param group 技能所在的组
     * */
    private void getAllSkillsSurfaceFromSkill(LinkedHashMap<String,SkillSurface> skills,ISkill skill,SkillGroup group){
        SkillSurface surface =  SkillSurface.castSkilltoSkillSurface((Skill) skill);
        if(group.parentSkill != null) surface.dependentSkill.add(group.parentSkill.getSkillName());
        skills.put(skill.getSkillName(), surface);
        if(skill.getChildSkills() == null) return;
        else{
            getAllSkillsSurfaceFromGroup(skills,skill.getChildSkills());
        }
    }

    /**
     * 获取所有skill
     * @see SkillCategory#getAllSkillsSurface()
     * 这两者思路相同，区别是封装
     * */
    public LinkedHashMap<String,ISkill> getAllSkills(){
        LinkedHashMap<String,ISkill> skills = new LinkedHashMap<>();
        if(startSkill == null) return null;
        else{
            getAllSkillsFromGroup(skills,startSkill);
        }
        return skills;
    }

    /**
     * @see SkillCategory#getAllSkillsSurfaceFromGroup(LinkedHashMap, SkillGroup) 
     * */
    private void getAllSkillsFromGroup(LinkedHashMap<String,ISkill> skills,SkillGroup group){
        group.skillTree.forEach((name,skill)->{
            getAllSkillsFromSkill(skills, skill);
        });
    }

    /**
     * @see SkillCategory#getAllSkillsSurfaceFromSkill(LinkedHashMap, ISkill, SkillGroup) 
     * */
    private void getAllSkillsFromSkill(LinkedHashMap<String,ISkill> skills,ISkill skill){
        skills.put(skill.getSkillName(), skill);
        if(skill.getChildSkills() == null) return;
        else{
            getAllSkillsFromGroup(skills,skill.getChildSkills());
        }
    }
}
