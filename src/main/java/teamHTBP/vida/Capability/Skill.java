package teamHTBP.vida.Capability;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.modelRender.RenderLoader;

public class Skill implements ISkill{
    /**技能名字*/
    protected String skillName;
    /**技能所需要注入的能量*/
    protected int skillExp = -1;
    /**技能所属的等级,normal = 1 , silver = 2 , gold = 3*/
    protected int skillLevel = 0;

    /**实际显示中所在的X,Y轴*/
    protected int skillX = 0;
    protected int skillY = 0;

    /**在材质中所在的位置*/
    protected int textureX = 0;
    protected int textureY = 0;

    /**所处的ResourceLocation，默认是Vida的技能图标组，可以使用Skill#setLocation来设置ResourceLocation*/
    protected ResourceLocation resourceLocation =  new ResourceLocation(Vida.modId, "textures/gui/skills.png");

    /**前置的Skill的index值，无前置 = -1*/
    public int dependSkillIndex = -1;

    /***
     * Skill调试用构造方法
     * @param skillName 技能名字
     * @param skillExp 技能需要注入的能量
     * @param skillLevel 技能的品质[WARNING:(0,3)]
     * @param skillX 实际显示的X轴位置
     * @param skillY 实际显示的Y轴位置
     */
    public Skill(String skillName,int skillExp,int skillLevel,int skillX,int skillY){
        this.skillName = "";
        this.skillName = skillName;
        this.skillExp = skillExp;
        this.skillLevel = skillLevel;
        this.skillX = skillX;
        this.skillY = skillY;
    }

    /***
     * Skill构造方法
     * @param skillName 技能名字
     * @param skillExp 技能需要注入的能量
     * @param skillLevel 技能的品质[WARNING:(0,3)]
     * @param skillX 实际显示的X轴位置
     * @param skillY 实际显示的Y轴位置
     * @param textureX 技能图标左上角的X轴位置
     * @param textureY 技能图标左上角的Y轴位置
     */

    public Skill(String skillName,int skillExp,int skillLevel,int skillX,int skillY,int textureX,int textureY){
        this.skillName = "";
        this.skillName = skillName;
        this.skillExp = skillExp;
        this.skillLevel = skillLevel;
        this.skillX = skillX;
        this.skillY = skillY;
        this.textureX = textureX;
        this.textureY = textureY;
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
        return "name" + skillName + ".skill";
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
    public String getSkillTranlateDesc(){ return I18n.format(getSkillCompleteName()); }

    @Override
    public ResourceLocation getLocation() {
        return this.resourceLocation;
    }

    @Override
    public void setLocation(ResourceLocation location) {
        this.resourceLocation = location;
    }

    /**
     * 获得技能所需注入的能量
     * @return 需要注入的能量
     * */
    public int getSkillExp() {
        return skillExp;
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
    public void setSkillTextureX(int textureX) { this.textureX = textureX; }

    /***
     * 修改技能图标左上角的Y轴[V轴线]位置
     * @param textureY 图标左上角Y轴[V轴]位置
     */
    public void setSkillTextureY(int textureY) { this.textureY = textureY; }

    /***
     * 获取技能的品质
     * @return 技能的品质
     */
    public int getSkillLevel(){ return  this.skillLevel;}

    /***
     * 获取技能图标左上角的X轴[U轴线]位置
     * @return 技能图标左上角X轴[U轴]位置
     */
    public int getSkillTextureX() { return textureX; }

    /***
     * 获取技能图标左上角的Y轴[V轴线]位置
     * @return 技能图标左上角Y轴[V轴]位置
     */
    public int getSkillTextureY() { return textureY; }
}
