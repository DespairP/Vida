package teamHTBP.vida.Capability;

import teamHTBP.vida.Vida;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class SkillRegister {
    int centerX = 0;
    int centerY = 0;
    int leftTopCornerX = 0;
    int leftTopCornerY = 0;
    int rightTopCornerX = 0;
    int rightTopCornerY = 0;
    int leftBottomCornerX = 0;
    int leftBottomCornerY = 7;
    int rightBottomCornerX = 0;
    int rightBottomCornerY = 7;

    public SkillRegister(){

    }

    protected  SkillRegister(int centerX,int centerY){
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public SkillRegister createSkillBranch(SkillDirection direction,LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        int left = 0;
        int top = 0;
        //附属的技能
        Skill skillBefore = hashMap.get(hashMap.size() - 1);
        if(skillBefore != null){
            left = skillBefore.getSkillX();
            top = skillBefore.getSkillY();
        }
        SkillRegister skillRegister = new SkillRegister(left,top);
        switch (direction){
            case TopLeft:
                skillRegister.appendSkillForLeftTopCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case TopRight:
                skillRegister.appendSkillForRightTopCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case BottomLeft:
                skillRegister.appendSkillForLeftBottomCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case BottomRight:
                skillRegister.appendSkillForRightBottomCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
        }
        if(hashMap.size() < 2){
            return skillRegister;
        }
        //最近插入的新技能，下标为 size-1
        Skill skill = hashMap.get(hashMap.size() - 1);
        //新技能的附属下标，也就是skillBefore的下标 size-2
        if(skill != null)
        skill.dependSkillIndex = hashMap.size() - 2;

        return skillRegister;

    }

    public SkillRegister createSkillBranch(SkillDirection direction,int dependIndex,LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        int left = 0;
        int top = 0;
        //附属的技能
        Skill skillBefore = hashMap.get(dependIndex);
        if(skillBefore != null){
            left = skillBefore.getSkillX();
            top = skillBefore.getSkillY();
        }
        SkillRegister skillRegister = new SkillRegister(left,top);
        switch (direction){
            case TopLeft:
                skillRegister.appendSkillForLeftTopCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case TopRight:
                skillRegister.appendSkillForRightTopCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case BottomLeft:
                skillRegister.appendSkillForLeftBottomCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
            case BottomRight:
                skillRegister.appendSkillForRightBottomCorner(hashMap, skillName, skillExp, skillLevel, textureX, textureY);
                break;
        }
        if(hashMap.size() < 2){
            return skillRegister;
        }
        //最近插入的新技能，下标为 size-1
        Skill skill = hashMap.get(hashMap.size() - 1);
        //新技能的附属下标，也就是skillBefore的下标 size-2
        if(skill != null)
            skill.dependSkillIndex = dependIndex;

        return skillRegister;

    }



    public static void registrySkill(){
       //TODO
    }

    public void refreshSkill(){
        long refreshBeforeSec = System.currentTimeMillis();


        long refreshAfterSec = System.currentTimeMillis();
        Date date = new Date(refreshAfterSec - refreshBeforeSec);
        Vida.LOGGER.debug("Skill refresh Success,Time:" + new SimpleDateFormat("ss").format(date));
    }

    public void appendSkillForLeftTopCorner(LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        if(skillName == ""|| skillExp == -1 || skillLevel >= 4 || skillLevel < 0 || hashMap == null) return;
        leftTopCornerX -= 25;
        leftTopCornerY -= 25;
        leftTopCornerX += centerX;
        leftTopCornerY += centerY;
        hashMap.put(hashMap.size(), new Skill(skillName, skillExp, skillLevel, leftTopCornerX,leftTopCornerY,textureX,textureY));
    }

    public void appendSkillForRightTopCorner(LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        if(skillName == ""|| skillExp == -1 || skillLevel >= 4 || skillLevel < 0 || hashMap == null) return;
        rightTopCornerX += 25;
        rightTopCornerY -= 25;
        rightTopCornerX += centerX;
        rightTopCornerY += centerY;
        hashMap.put(hashMap.size(), new Skill(skillName, skillExp, skillLevel, rightTopCornerX,rightTopCornerY,textureX,textureY));
    }

    public void appendSkillForLeftBottomCorner(LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        if(skillName == ""|| skillExp == -1 || skillLevel >= 4 || skillLevel < 0 || hashMap == null) return;
        leftBottomCornerX -= 25;
        leftBottomCornerY += 25;
        leftBottomCornerX += centerX;
        leftBottomCornerY += centerY;
        hashMap.put(hashMap.size(), new Skill(skillName, skillExp, skillLevel, leftBottomCornerX,leftBottomCornerY,textureX,textureY));
    }

    public void appendSkillForRightBottomCorner(LinkedHashMap<Integer,Skill> hashMap,String skillName,int skillExp,int skillLevel,int textureX,int textureY){
        if(skillName == ""|| skillExp == -1 || skillLevel >= 4 || skillLevel < 0 || hashMap == null) return;
        rightBottomCornerX += 25;
        rightBottomCornerY += 25;
        rightBottomCornerX += centerX;
        rightBottomCornerY += centerY;
        hashMap.put(hashMap.size(), new Skill(skillName, skillExp, skillLevel, rightBottomCornerX,rightBottomCornerY,textureX,textureY));
    }
}
