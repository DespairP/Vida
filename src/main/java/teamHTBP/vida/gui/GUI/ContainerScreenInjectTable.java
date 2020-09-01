package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.Capability.ElementHelper;
import teamHTBP.vida.Capability.Skill;
import teamHTBP.vida.Capability.SkillHelper;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.GUI.Slot.AbstractSkillSlot;
import teamHTBP.vida.gui.GUI.Slot.GoldSkillSlot;
import teamHTBP.vida.gui.GUI.Slot.NormalSkillSlot;
import teamHTBP.vida.gui.GUI.Slot.SilverSkillSlot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContainerScreenInjectTable extends ContainerScreen<ContainerInjectTable> {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/inject_gui.png");
    ResourceLocation background = new ResourceLocation(Vida.modId, "textures/gui/inject_back.png");

    public LinkedList<AbstractSkillSlot> buttons = new LinkedList<AbstractSkillSlot>();
    ItemStack stack = ItemStack.EMPTY;
    //上一次选中的技能
    public int buttonClickIndex = -1;
    private int element = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    private float alpha = 0;
    private int progressLevel = 0;
    private float selectProgressAlpha = 0.0f;
    public int selectProgressLength = 0;
    public int backgroundXsize = 0;
    public int backgroundYsize = 0;
    public float introduceAlpha = 0;
    public double framedeco[] = {0,Math.PI/2,Math.PI * 2 - Math.PI / 2,Math.PI};
    public int flexX = 0;
    public int flexY = 0;
    ArrayList<Skill> skills = new ArrayList<Skill>();
    public ContainerScreenInjectTable(ContainerInjectTable screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.stack = this.getContainer().stack;
        this.element = this.getContainer().element;
        this.backgroundXsize = this.width / 2;
        this.backgroundYsize = this.height / 2;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.stack = this.getContainer().injectTable.getSwordStack();
        this.renderBackground();
        this.minecraft.getTextureManager().bindTexture(Gui);

        alpha += 0.1f;
        if(alpha >= 1.0f) alpha = 1.0f;
        progressLevel += 1;
        CompoundNBT nbt = stack.getOrCreateTag();
        renderIntroduceSkill();
        drawSkill(nbt);
        drawLevel(nbt);
        drawSkillEnergy(nbt);
        //drawForegGround();
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        this.offsetX += (int)p_mouseDragged_6_;
        this.offsetY += (int)p_mouseDragged_8_;
        if(this.offsetX >= 200) this.offsetX = 200;
        if(this.offsetX <= -200) this.offsetX = -200;
        if(this.offsetY >= 200) this.offsetY = 200;
        if(this.offsetY <= -119) this.offsetY = -119;
        return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
    }

    @Override
    public void render(int mouseX, int mouseY, float ticks) {
        super.render(mouseX, mouseY, ticks);
        renderHoveredToolTip(mouseX, mouseY);
        for(int i=0;i<buttons.size();i++){
            AbstractButton button = buttons.get(i);
            if(button.isHovered()){
                Skill skill = SkillHelper.getSwordSkill(stack,i);
                if(skill == null) skill = skills.get(i);
                renderSkillToolTip(mouseX, mouseY,skill);
            }
        }
    }

    @Override
    protected void init() {
        this.xSize = this.width ;
        this.ySize = this.height ;
        this.backgroundXsize = this.width / 2;
        this.backgroundYsize = this.height / 2;
        if(stack != ItemStack.EMPTY){
            skills.clear();
            buttons.clear();
            for(int i = 0;i<SkillHelper.getMaxElementSwordSkill(stack);i++){
                Skill skill =  SkillHelper.getSwordSkill(stack, i);
                AbstractSkillSlot slot = null;
                switch (skill.getSkillLevel())
                {
                    case 1:
                        slot = new NormalSkillSlot(this.guiLeft + skill.getSkillX(),this.guiTop + skill.getSkillY(),0,0,"",i,skill,this);
                        this.addButton(slot);
                        skills.add(skill);
                        buttons.add(slot);
                    break;
                    case 2:
                         slot = new SilverSkillSlot(this.guiLeft + skill.getSkillX(),this.guiTop + skill.getSkillY(),0,0,"",i,skill,this);
                         this.addButton(slot);
                         skills.add(skill);
                         buttons.add(slot);
                         slot = slot;
                    break;
                    case 3:
                        slot = new GoldSkillSlot(this.guiLeft + skill.getSkillX(),this.guiTop + skill.getSkillY(),0,0,"",i,skill,this);
                        this.addButton(slot);
                        skills.add(skill);
                        buttons.add(slot);
                        slot = slot;
                    break;
                }
                CompoundNBT nbt = stack.getOrCreateTag();
                //如果有附属的话
                if(skill.dependSkillIndex != -1 && slot != null){
                    Skill skillDepend = SkillHelper.getSwordSkill(stack, skill.dependSkillIndex);
                    if(skillDepend != null){
                        //如果前置技能能量满了这个就是false
                       boolean isSkillEnergyFull = (nbt.getInt(skillDepend.getSkillName() + "EXP") <= skill.getSkillExp());
                       slot.setLock(isSkillEnergyFull);
                    }
                }
                if(skill.getSkillExp() <= nbt.getInt(skill.getSkillName() + "EXP"))
                    slot.setComplete(true);
            }
        }
        //drawForegGround();
        super.init();
    }

    /**画左上角的等级框**/
    public void drawLevel(CompoundNBT nbt){
        int level = nbt.getInt("level");
        int exp = nbt.getInt("pickaxeEXP");
        //System.out.println(level * 500);
        int max = (int) (exp * 52.0f/ ((level-1) * 500.0f));
        if(exp == 0) {
            exp = nbt.getInt("swordEXP");
            max = (int) (exp * 52.0f/ (level  * 200 + level * 13));
        }
        if(progressLevel >= max) this.progressLevel = max;
        this.minecraft.getTextureManager().bindTexture(Gui);
        RenderSystem.pushMatrix();
        RenderSystem.color4f(1,1,1, 1);
        blit(this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 0,228,68,13,512,512);
        blit(this.guiLeft + 14 + backgroundXsize / 20, this.guiTop + 9 + 10, 0, 0,258 ,progressLevel,2,512,512);
        RenderSystem.popMatrix();
    }



    /**画中间的技能按钮**/
    public void drawSkill(CompoundNBT nbt){
        int left = this.backgroundXsize;
        int top = this.backgroundYsize;
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.color4f(1,1,1,  alpha);
        //RenderSystem.scalef(0.8f, 0.8f, 0.8f);
        blit(left - 17 + offsetX, top - 18 + offsetY, 0, 94,62,36,36,512,512);
        for(int i = 0;i<buttons.size();i++){
            Skill skill =  SkillHelper.getSwordSkill(stack, i);
            AbstractSkillSlot button = buttons.get(i);
            if(button != null && skill != null){
                button.x = left - 18 + skill.getSkillX() + offsetX;
                button.y = top - 18 + skill.getSkillY() + offsetY;
            }else{
                skill = skills.get(i);
                button.x = left - 18 + skill.getSkillX() + offsetX;
                button.y = top - 18 + skill.getSkillY() + offsetY;
            }
            if(skill.dependSkillIndex != -1 && skills.get(skill.dependSkillIndex)!=null){
                Skill dependSkill = skills.get(skill.dependSkillIndex);
                //System.out.println(dependSkill.getSkillExp() + " " + nbt.getInt(dependSkill.getSkillName()+ "EXP"));
                if(button instanceof AbstractSkillSlot){
                    ((AbstractSkillSlot)button).setLock(!(nbt.getInt(dependSkill.getSkillName()+ "EXP") >= dependSkill.getSkillExp()));
                }
            }
            if(skill.getSkillExp() <= nbt.getInt(skill.getSkillName() + "EXP")){
                if(button instanceof AbstractSkillSlot){
                    ((AbstractSkillSlot)button).setComplete(true);
                } }
            button.setOverlap(buttonOverLap(button));
        }
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack, left - 18 + 10 + this.offsetX  , top - 18 + 11 + this.offsetY);
        RenderSystem.popMatrix();
        drawSwordFrameDeco();
    }


    public void drawSwordFrameDeco(){
        int left = this.backgroundXsize;
        int top = this.backgroundYsize;
        this.minecraft.getTextureManager().bindTexture(Gui);
        for(int i = 0; i < framedeco.length;i++) {
            RenderSystem.pushMatrix();
            framedeco[i] = ((framedeco[i] + 0.02) % (Math.PI * 2));
            float light = (float)(0.75 * Math.sin(framedeco[i]));
            RenderSystem.color4f(1 + light, 1 + light, 1 + light, 1 );
            if( i == 0)
                blit(left + 1 - 17 + offsetX,top - 18 + offsetY, 0, 5, 63, 9, 9, 512,512);
            if( i == 1)
                blit(left - 1 + 11 + offsetX,top - 18 + offsetY, 0, 30,63, 9, 9, 512,512);
            if( i == 2)
                 blit(left + 1 - 17 + offsetX,top + 26 - 18 + offsetY, 0, 5, 88, 9, 9, 512,512);
            if( i == 3)
                blit(left - 1 + 11 + offsetX,top + 26 - 18 + offsetY, 0, 30, 88, 9, 9, 512,512);
            RenderSystem.popMatrix();
        }
    }
    /**画底边的技能能量条**/
    public void drawSkillEnergy(CompoundNBT nbt){
        int left = this.backgroundXsize;
        int top = this.backgroundYsize;
        if(buttonClickIndex >= 0){
            RenderSystem.pushMatrix();
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            String selectedSkillName = "";
            Skill skill;
            RenderSystem.color4f(1, 1, 1, selectProgressAlpha);
            if(this.selectProgressAlpha < 1.0f) this.selectProgressAlpha += 0.05f;
            try{
                if(skills.get(buttonClickIndex) != null) {
                    selectedSkillName = skills.get(buttonClickIndex).getSkillName();
                    skill = skills.get(buttonClickIndex);
                }
                else{
                    selectedSkillName = SkillHelper.getSwordSkill(stack, buttonClickIndex).getSkillName();
                    skill = SkillHelper.getSwordSkill(stack, buttonClickIndex);
                }
                int SkillExp = nbt.getInt(selectedSkillName + "EXP");
                //System.out.println(SkillExp);
                int maxSkillLength = (int)((SkillExp * 107.0f) / (skill.getSkillExp() * 1.0f));
                if(selectProgressLength < maxSkillLength) selectProgressLength += 1;
                if(selectProgressLength >= 107) this.selectProgressLength = 107;
                blit(left - 18 - 40                            , (int)(top * 1.65) + 10, 0, 175, 150, 111,7,512,512);
                blit(left - 18 - 40 + 2                        , (int)(top * 1.65) + 10 , 0, 177, 157, selectProgressLength,7,512,512);
                blit(left - 18 - 40 + 1                        , (int)(top * 1.65) + 12, 0, 176, 159, 1,3,512,512);
                blit(left - 18 - 40 + selectProgressLength + 2 , (int)(top * 1.65) + 12, 0, 176 + selectProgressLength + 1, 159, 1,3,512,512);

                RenderSystem.pushMatrix();
                //System.out.println(height);
                RenderSystem.scalef(1.5f, 1.5f, 1.5f);
                FontRenderer renderer = minecraft.fontRenderer;
                if(!buttons.get(buttonClickIndex).isLock())
                    drawCenteredString(renderer, skill.getSkillTranlateName(),left * 2/3, top + 10, 34000);
                else
                    drawCenteredString(renderer, skill.getSkillTranlateName(),left * 2/3, top + 10, Integer.parseInt("FF006E", 16));
                RenderSystem.popMatrix();
            }catch (Exception ex){RenderSystem.popMatrix(); return;}
            RenderSystem.popMatrix();
        }
    }

    public void renderIntroduceSkill(){
        if(this.buttonClickIndex > -1){
            int left = this.backgroundXsize;
            int top = this.backgroundYsize;
            AbstractSkillSlot slot = buttons.get(buttonClickIndex);
            if(slot.isOverlap()){ this.introduceAlpha = this.introduceAlpha < 1.0f?this.introduceAlpha + 0.05f:1.0f;
            }else{ this.introduceAlpha = this.introduceAlpha > 0.0f?this.introduceAlpha - 0.05f:0.0f; }
            RenderSystem.pushMatrix();
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            RenderSystem.color4f(1, 1, 1, (this.selectProgressAlpha - 0.3f < 0?0:this.selectProgressAlpha - 0.3f));
            blit(left * 2 - 130 ,top/3,0, 173,5,114,142,512,512);
            //System.out.println(left * 2 - 130 +" "+top/3);
            RenderSystem.popMatrix();
            Skill skill = skills.get(buttonClickIndex);
            if(skill != null){
                this.drawCenteredString(minecraft.fontRenderer,skills.get(buttonClickIndex).getSkillTranlateName(),left * 2 + 57 - 130,top/3 + 6,Integer.parseInt("FFFFFF",16));
                this.drawString(minecraft.fontRenderer,skills.get(buttonClickIndex).getSkillTranlateDesc(),left * 2 + 13 - 130,top/3 + 25,Integer.parseInt("FFFFFF",16));
                if(skill.dependSkillIndex!= -1 && slot !=null && slot.isLock() && skills.get(skill.dependSkillIndex)!=null){
                    this.drawCenteredString(minecraft.fontRenderer, I18n.format("desc.need.skill"),left * 2 + 57 - 130,top  + 35,Integer.parseInt("B70027",16));
                    this.drawCenteredString(minecraft.fontRenderer, skills.get(skill.dependSkillIndex).getSkillTranlateName(),left * 2 + 57 - 130,top + 45,Integer.parseInt("B70027",16));
                }else if(skill.dependSkillIndex!= -1 && slot != null && !slot.isLock() && skills.get(skill.dependSkillIndex)!=null){
                    this.drawCenteredString(minecraft.fontRenderer, I18n.format("desc.need.skill"),left * 2 + 57 - 130,top  + 35,Integer.parseInt("AAFFF0",16));
                    this.drawCenteredString(minecraft.fontRenderer, skills.get(skill.dependSkillIndex).getSkillTranlateName(),left * 2 + 57 - 130,top + 45,Integer.parseInt("AAFFF0",16));
                }
            }else if(SkillHelper.getSwordSkill(stack, buttonClickIndex) != null){

            }
            this.minecraft.getTextureManager().bindTexture(Gui);
        }
    }

    /**渲染技能的悬浮框**/
    public void renderSkillToolTip(int mouseX,int mouseY,Skill skill){
        if(skill == null) return;
        ArrayList<String> list = new ArrayList<String>();
        list.add(skill.getSkillTranlateName());
        this.renderTooltip(list,mouseX,mouseY);
    }

    public boolean buttonOverLap(AbstractSkillSlot slot){
        int midx = slot.x + 19;
        int midy = slot.y + 19;
        int minleft = (this.backgroundXsize * 2 - 130) - 19;
        int maxleft = (this.backgroundXsize * 2 - 130) + 19 + 114;
        int mintop = this.backgroundYsize/3 - 19;
        int maxtop = this.backgroundYsize/3 + 142 + 19;
        //System.out.println(mintop + " " +maxleft);
        if(buttonClickIndex != -1){
            if((midx >  minleft && midx < maxleft) && (midy > mintop && midy < maxtop)){return true;}
        }
        return false;
    }

    public void drawForegGround(){
        this.minecraft.getTextureManager().bindTexture(background);
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        //RenderSystem.scalef(2.0f, 2.0f, 2.0f);
        RenderSystem.translated(-125, -125, -125);
        blit(0 + offsetX/2, 0 + offsetY/2, 0, 0, 0, 512, 512, 512, 512);
        RenderSystem.popMatrix();
        this.minecraft.getTextureManager().bindTexture(Gui);
    }
}