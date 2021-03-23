package teamHTBP.vida.capability.Skill;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.Item.staff.ItemElementSword;
import teamHTBP.vida.Vida;

import java.util.LinkedHashMap;

public class SkillHelper {
    public static LinkedHashMap<Item,SkillCategory> skillCategories;

    static{
        Vida.LOGGER.info("loading skill...");
        skillCategories = new LinkedHashMap<>();
        try {
            SkillCategory category = new SkillCategory();
            SkillGroup group = new SkillGroup();
            group.appendSkill(new Skill("luck", 500, 1,EnumSkillType.BRONZE, 1), EnumSkillDirection.Left)
                    .appendSkill(new Skill("OPS",500,1,EnumSkillType.BRONZE,2), EnumSkillDirection.Right)
                    .appendSkillWithBranch(new Skill("stars",500,2,EnumSkillType.GOLD,2), EnumSkillDirection.TopLeft)
                    .appendSkill(new Skill("starsAppend",500,2,EnumSkillType.PlATIUM,2), EnumSkillDirection.TopLeft);
            category.startSkill = group;
            skillCategories.put(ItemLoader.aquaElementPickaxe.get(),category);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Vida.LOGGER.info("loading skill complete!");
    }

    /**通过itemstack获取所有技能skills，并二次封装成skillSurface方便后续操作
     *
     * @param itemStack 需要获取封装技能组的物品，通过该物品会取得关于所有
     *                  关于此武器/物品的封装技能组
     *                  @see SkillSurface
     * @return 返回一个封装技能组，形式是[ID，封装技能]
     * */
    public static LinkedHashMap<String,SkillSurface> getAllSkillSurfaceFromItemStack(ItemStack itemStack){
        if(itemStack == null || itemStack.isEmpty()) return null;
        else{
            SkillCategory skillCategory = skillCategories.get(itemStack.getItem());
            if(skillCategory == null) return new LinkedHashMap<>();
            //先获取所有的Skill
            LinkedHashMap<String,SkillSurface> skillsSurface = skillCategory.getAllSkillsSurface();
            CompoundNBT nbt = itemStack.getOrCreateTag();
            //开始封装所有获取的skill
            skillsSurface.forEach((name,skill)->{
                int expLevel[] = getSkillExpAndLevel(skill, nbt);
                skill.skillCurrentExp = expLevel[0];
                skill.skillCurrentLevel = expLevel[1];
            });
            return skillsSurface;
        }
    }

    private static int[] getSkillExpAndLevel(ISkill skill,CompoundNBT nbt){
        int[] expLevel = new int[2];
        if(skill == null || nbt == null) return expLevel;
        expLevel[0] = nbt.getInt(skill.getSkillName() + "Exp");
        expLevel[1] = nbt.getInt(skill.getSkillName() + "Level");
        return expLevel;
    }

    /**
     * 刷新封装后技能的经验和等级，会自动更新surface对象中的参数
     * @param surface 要刷新的技能
     * @param nbt 物品的NBT
     * */
    public static void refreshSkillSurfaceExpAndLevel(SkillSurface surface,CompoundNBT nbt){
        int expLevel[] = getSkillExpAndLevel(surface,nbt);
        if(expLevel.length == 2) {
            surface.skillCurrentExp = expLevel[0];
            surface.skillCurrentLevel = expLevel[1];
        }
    }
}
