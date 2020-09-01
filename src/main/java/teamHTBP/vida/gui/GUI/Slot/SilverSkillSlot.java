package teamHTBP.vida.gui.GUI.Slot;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Capability.Skill;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.GUI.ContainerScreenInjectTable;

public class SilverSkillSlot extends AbstractSkillSlot {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/inject_gui.png");
    private int index = 0;
    private float alpha = 0;
    private float hoveredLight = 0.0f;
    private ResourceLocation skillLocation;
    private int skillTextureX = 0;
    private int skillTextureY = 0;
    private float focusAlpha = 0;
    private ContainerScreenInjectTable containerScreen;
    public SilverSkillSlot(int xIn, int yIn, int widthIn, int heightIn, String msg,int index) {
        super(xIn, yIn, widthIn, heightIn, msg);
        this.index = index;
    }

    public SilverSkillSlot(int xIn, int yIn, int widthIn, int heightIn, String msg, int index, Skill skill, ContainerScreenInjectTable containerScreen) {
        super(xIn, yIn, 36, 36, msg);
        this.index = index;
        this.skillLocation =  skill.getLocation();
        this.skillTextureX = skill.getSkillTextureX();
        this.skillTextureY = skill.getSkillTextureY();
        this.visible = true;
        this.containerScreen = containerScreen;
    }

    @Override
    public void onPress() {
        if(containerScreen.buttonClickIndex != index){
            containerScreen.buttonClickIndex = index;
            this.setMouseFouse(true);
            containerScreen.selectProgressLength = 1;
            for(int i = 0;i<containerScreen.buttons.size();i++){
                AbstractSkillSlot button = containerScreen.buttons.get(i);
                if(i == index) continue;
                else button.setMouseFouse(false);
            }
        }else if(containerScreen.buttonClickIndex == index){
            //如果是该按钮，失去焦点
            containerScreen.buttonClickIndex = -1;
            this.setMouseFouse(false);
        }

    }

    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(Gui);
        //System.out.println(isHovered);
        //初始加载的时候加载
        if(alpha < 0.4f) alpha+=0.02f;
        //如果框被悬浮
        if(isHovered && hoveredLight < 0.2f) hoveredLight += 0.02f;
        else if(!isHovered && hoveredLight > 0.0f) hoveredLight -= 0.05f;
        //选中
        if(this.getMouseFocus() && focusAlpha < 0.4f) focusAlpha += 0.02f;
        else if(!this.getMouseFocus() && focusAlpha > 0 ) focusAlpha -= 0.02f;
        changeOverlapAlpha();
        renderSkillFrame();
        renderSkill();
    }

    public void renderSkillFrame(){
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        if(this.getMouseFocus() && this.focusAlpha < 0.5f) this.focusAlpha += 0.01;
        else if(this.focusAlpha > 0 && !this.getMouseFocus()) this.focusAlpha -= 0.01f;
        if(!this.isComplete){
            if(!this.getMouseFocus()){
                RenderSystem.color4f(0.7F + (hoveredLight), 0.7F + (hoveredLight), 0.7F + (hoveredLight), this.alpha + this.hoveredLight + this.focusAlpha - overlapAlpha);
                this.blit(this.x, this.y, 0,38,191, 38,38, 512, 512);}
            else{
                RenderSystem.color4f(1, 1, 1, this.alpha + this.hoveredLight + this.focusAlpha - overlapAlpha);
                this.blit(this.x, this.y, 0,38,260, 38,38, 512, 512);}
        }else{
            if(!this.getMouseFocus()){
                RenderSystem.color4f(1, 1,1,1.0f - overlapAlpha);
                this.blit(this.x, this.y, 0,38,191, 38,38, 512, 512);}
            else{
                RenderSystem.color4f(1, 1,1, 1 - overlapAlpha);
                this.blit(this.x, this.y, 0,38,260, 38,38, 512, 512);}
        }
        RenderSystem.popMatrix();
    }

    public void renderSkill(){
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        if(!this.isComplete){
            if(!this.getMouseFocus()){
                RenderSystem.color4f(0.6F + hoveredLight , 0.6F  + hoveredLight, 0.6F  + hoveredLight, this.alpha + this.hoveredLight + this.focusAlpha - overlapAlpha);
            }else{
                RenderSystem.color4f(1, 1, 1, this.alpha + this.hoveredLight + this.focusAlpha - overlapAlpha);
        }}else{
            if(!this.getMouseFocus()){
                RenderSystem.color4f(1 , 1, 1, 1 - overlapAlpha);
            }else{
                RenderSystem.color4f(1, 1, 1, 1 - overlapAlpha);}
        }
        if(skillLocation!=null){
            minecraft.getTextureManager().bindTexture(skillLocation);
            if(!this.isLock) {
                RenderSystem.scalef(0.7f, 0.7f, 0.7f);
                this.blit((int) (this.x * 1.43) + 12, (int) (this.y * 1.43) + 11, 0, skillTextureX, skillTextureY, 32, 32, 256, 256);
            }else {
                minecraft.getTextureManager().bindTexture(Gui);
                this.blit( this.x + 13,   this.y + 9, 0, 0, 307, 12,18, 512, 512);}
        }
        RenderSystem.popMatrix();
    }
}
