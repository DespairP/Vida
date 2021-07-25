package teamHTBP.vida.capability.skillSystem;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Vida;

public class Skill implements ISkill{
    /**技能名字*/
    public String skillName;
    /**技能每级所需要注入的能量,等级系数*/
    protected int skillExpPerLevel = -1;
    /**技能的最大等级*/
    public int maxSkillLevel = 0;
    /**技能最大品质*/
    protected EnumSkillType skillType = EnumSkillType.NORMAL;

    /**实际显示中所在的X,Y轴*/
    protected int skillX = 0;
    protected int skillY = 0;

    /**在材质中所在的位置*/
    protected int textureU = 0;
    protected int textureV = 0;

    /**材质大小*/
    protected int textureSize = 32;

    /**所处的ResourceLocation，默认是Vida的技能图标组，可以使用Skill#setLocation来设置ResourceLocation*/
    protected ResourceLocation resourceLocation =  new ResourceLocation(Vida.modId, "textures/gui/skills.png");

    /**子技能*/
    public SkillGroup childSkills = null;

    /***
     * 用textureIndex来确定Skill贴图的位置
     * @param skillName 技能名字
     * @param skillExpPerLevel 技能需要注入的能量
     * @param skillType 技能的品质
     * @param maxSkillLevel 技能最大等级
     * @param skillTextureIndex 技能贴图在材质中出现的顺序，从1开始
     */
    public Skill(String skillName,int skillExpPerLevel,int maxSkillLevel,EnumSkillType skillType,int skillTextureIndex) throws Exception {
        this.skillName = "";
        this.skillName = skillName;
        this.skillExpPerLevel = skillExpPerLevel;
        this.skillType = skillType;
        this.maxSkillLevel = maxSkillLevel;
        if(skillTextureIndex < 1) throw new IndexOutOfBoundsException("skill index < 1,the index start with 1");
        this.textureU = ((skillTextureIndex - 1) % 8) * 32;
        this.textureV = ((skillTextureIndex - 1) / 8) * 32;
        this.textureSize = 32;
    }

    /***
     * 使用标准的（u，v）来确定Skill的贴图构造方法
     * @param skillName 技能名字
     * @param skillExpPerLevel 技能每级需要注入的能量
     * @param skillType 技能的品质
     * @param maxSkillLevel 技能最大等级
     * @param textureU 技能图标左上角的U轴位置
     * @param textureV 技能图标左上角的V轴位置
     */

    public Skill(String skillName, int skillExpPerLevel,int maxSkillLevel, EnumSkillType skillType, int textureU, int textureV,int textureSize){
        this.skillName = "";
        this.skillName = skillName;
        this.skillExpPerLevel = skillExpPerLevel;
        this.maxSkillLevel = maxSkillLevel;
        this.skillType = skillType;
        this.textureU = textureU;
        this.textureV = textureV;
        this.textureSize = textureSize;
    }

    /**得到技能名字
     * @return 技能名字
     * */
    public String getSkillName() {
        return skillName;
    }

    /**得到技能的完整名字
     * @return name.技能名字.skill
     * */
    public String getSkillCompleteName() {
        return "skill.vida." + skillName + ".name";
    }

    /**得到技能翻译名，取决于用户的环境
     * @return 技能的翻译名
     * */
    public String getSkillTranlateName(){
        return I18n.format(getSkillCompleteName());
    }


    /**得到技能介绍
     * @return 技能介绍
     * */
    public String getSkillTranlateDesc(){ return I18n.format("skill.vida."+skillName + ".desc",(char)0x0A); }

    @Override
    public ResourceLocation getLocation() {
        return this.resourceLocation;
    }

    @Override
    public void setLocation(ResourceLocation location) {
        this.resourceLocation = location;
    }

    /**
     * 获得技能每级技能所需注入的能量
     * @return 需要注入的能量
     * */
    public int getSkillExpPerLevel() {
        return skillExpPerLevel;
    }

    /**
     * 获取在当前等级下需要的经验
     */
    public int getRequiredExp(int level){
        if(level > maxSkillLevel || level < 0) return -1;
        return level * skillExpPerLevel;
    }

    /***
     * 获得技能实际显示的X轴位置
     * @return 实际显示的X轴位置
     */
   public int getSkillX(){
        return this.skillX;
   }

    /***
     * 获得技能实际显示的Y轴位置
     * @return 实际显示的Y轴位置
     */
   public int getSkillY(){
        return this.skillY;
   }

   /**修改技能实际显示的X轴位置
    * @param skillX 实际显示的X轴位置
    * */
    public void setSkillX(int skillX) { this.skillX = skillX; }

    /***
     * 修改技能实际显示的Y轴位置
     * @param skillY 实际显示的Y轴位置
     */
    public void setSkillY(int skillY) { this.skillY = skillY; }

    /***
     * 修改技能图标左上角的X轴[U轴线]位置
     * @param textureX 图标左上角X轴[U轴]位置
     */
    public void setSkillTextureU(int textureX) { this.textureU = textureX; }

    /***
     * 修改技能图标左上角的Y轴[V轴线]位置
     * @param textureY 图标左上角Y轴[V轴]位置
     */
    public void setSkillTextureV(int textureY) { this.textureV = textureY; }

    /***
     * 获取技能图标左上角的X轴[U轴线]位置
     * @return 技能图标左上角X轴[U轴]位置
     */
    public int getSkillTextureU() { return textureU; }

    /***
     * 获取技能图标左上角的Y轴[V轴线]位置
     * @return 技能图标左上角Y轴[V轴]位置
     */
    public int getSkillTextureV() { return textureV; }

    @Override
    public EnumSkillType getSkillType() {
        return skillType;
    }

    @Override
    public int getMaxSkillLevel() {
        return maxSkillLevel;
    }

    public void setSkillPosition(int skillX,int skillY){
        this.skillX  = skillX;
        this.skillY = skillY;
    }

    @Override
    public void setChildSkills(SkillGroup skillGroup) {
        this.childSkills = skillGroup;
    }

    @Override
    public SkillGroup getChildSkills() {
        return childSkills;
    }
}
