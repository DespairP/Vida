package teamHTBP.vida.Capability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.Item.staff.ItemElementSword;
import teamHTBP.vida.Vida;

import java.util.LinkedHashMap;

public class SkillHelper {
    /**存储金元素剑的技能的HashMap*/
    static LinkedHashMap<Integer,Skill> goldSwordSkills = new LinkedHashMap<Integer,Skill>();
    /**存储木元素剑的技能的HashMap*/
    static LinkedHashMap<Integer,Skill> woodSwordSkills = new LinkedHashMap<Integer,Skill>();
    /**存储水元素剑的技能的HashMap*/
    static LinkedHashMap<Integer,Skill> aquaSwordSkills = new LinkedHashMap<Integer,Skill>();
    /**存储火元素剑的技能的HashMap*/
    static LinkedHashMap<Integer,Skill> fireSwordSkills = new LinkedHashMap<Integer,Skill>();
    /**存储土元素剑的技能的HashMap*/
    static LinkedHashMap<Integer,Skill> earthSwordSkills = new LinkedHashMap<Integer,Skill>();
    /**存储其他剑的技能的HashMap 的 HashMap*/
    static LinkedHashMap<Item,LinkedHashMap<Integer,Skill>> otherSwordSkills= new LinkedHashMap<Item,LinkedHashMap<Integer,Skill>>();

    /*
    注册技能进入HashMap
    【建议】使用SkillRegister进行注册
    以下是使用的例子
    */
    static{
        Vida.LOGGER.info("loading skill...");
        //生成一个SkillRegister的实例，该实例以中心（0,0）为起点开始注册，每次为一个物品注册一个技能组合，都要声明一个实例
        SkillRegister register = new SkillRegister();
        //注册一个叫做"luckI"的技能，appendSkillForXXXCorner就是说在原有的技能基础上添加一个技能，且处于XXX角落[左上、右上...]
        //品质为普通，图标为（0,0）
        register.appendSkillForLeftTopCorner(goldSwordSkills, "luckI", 10000, 1,0,0);
        //注册一个带有分支的节点，注意：在注册这个节点时，这个技能的前置会默认会是上一个注册的技能
        //返回一个SkillRegister，该SkillRegister的中心是刚注册的技能的（x,y）,可以在SkillRegister基础上继续注册分支
        SkillRegister luckBranch = register.createSkillBranch(SkillDirection.TopRight,goldSwordSkills,"luckII",50000,2,0,0);
        //继续注册一个分支
        luckBranch.createSkillBranch(SkillDirection.TopLeft,goldSwordSkills,"luckIIPlus",50000,2,0,0);
        //上面说到前置默认会是上一个注册的技能，如果想要修改前置的话，在SkillDirection后面加上一个index值来声明自己的前置技能的下标（0开始）
        luckBranch.createSkillBranch(SkillDirection.TopRight,1,goldSwordSkills,"luckIII",100000,3,0,0);
        register.appendSkillForRightTopCorner(goldSwordSkills, "lightburst", 30000, 2, 32, 0);
        register.appendSkillForLeftBottomCorner(goldSwordSkills, "damageUp", 30000, 1, 0, 0);
        //register.createSkillBranch(SkillDirection.BottomLeft, goldSwordSkills,"damageUpExtend",160000,3,0,0);
        register.appendSkillForRightBottomCorner(goldSwordSkills,"lightUp", 30000, 2, 64, 0);
        Vida.LOGGER.info("loading skill complete!");
    }

    /***
     * 获得剑在[指定下标index]的指定技能
     * @param itemStack 要获取指定技能的物品
     * @param index 要获取的技能下标
     * @return 指定物品下标的指定技能
     */
    public static Skill getSwordSkill(ItemStack itemStack, int index){
        int element = itemStack.getOrCreateTag().getInt("ToolElement");
        if(itemStack.getItem() instanceof ItemElementSword) element =((ItemElementSword)itemStack.getItem()).element;
        switch (element){
            case 1: if(goldSwordSkills.containsKey(index)) return goldSwordSkills.get(index);
            case 2: if(goldSwordSkills.containsKey(index)) return woodSwordSkills.get(index);
            case 3: if(goldSwordSkills.containsKey(index)) return aquaSwordSkills.get(index);
            case 4: if(goldSwordSkills.containsKey(index)) return fireSwordSkills.get(index);
            case 5: if(goldSwordSkills.containsKey(index)) return earthSwordSkills.get(index);

        }
        if(otherSwordSkills.get(itemStack.getItem())!=null){
            return (otherSwordSkills.get(itemStack.getItem())).get(index);
        }else
            return null;
    }


    /**
     * 取得某个物品拥有的技能数
     * @return 拥有的技能数量
     * */
    public static int getMaxElementSwordSkill(ItemStack itemStack){
        int element = itemStack.getOrCreateTag().getInt("ToolElement");
        if(itemStack.getItem() instanceof ItemElementSword) element =((ItemElementSword)itemStack.getItem()).element;
        switch (element){
            case 1: return goldSwordSkills.size();
            case 2: return woodSwordSkills.size();
            case 3: return aquaSwordSkills.size();
            case 4: return fireSwordSkills.size();
            case 5: return earthSwordSkills.size();
        }
        if(otherSwordSkills.get(itemStack.getItem())!=null){
            return (otherSwordSkills.get(itemStack.getItem())).size();
        }else
            return 0;
    }



}
