package teamHTBP.vida.client.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;
import teamHTBP.vida.utils.math.IntRange;

public class ElementLevelToolsExpHUD extends AbstractGui {
    private final ResourceLocation VIDA_ICONS = new ResourceLocation(Vida.MOD_ID, "textures/gui/vida_icons.png");
    private IntRange alpha;
    private IntRange expProgress;
    private IntRange toolExpProgress;
    private int width;
    private int height;
    private ItemStack itemStack = ItemStack.EMPTY;
    private final Minecraft minecraft;

    public ElementLevelToolsExpHUD() {
        this.alpha = new IntRange(0,100,0);
        this.expProgress = new IntRange(0,183,0);
        this.toolExpProgress = new IntRange(0,183,0);
        this.minecraft = Minecraft.getInstance();
    }

    public void render(MatrixStack matrixStack,float partialTicks,ItemStack stack){
        if(stack == null || stack.isEmpty()){
            return;
        }
        assert this.minecraft.player != null;
        checkRenew(stack);
        width = this.minecraft.getMainWindow().getScaledWidth();
        height = this.minecraft.getMainWindow().getScaledHeight();

        int xPos = width / 2 - 91;
        //渲染经验
        this.minecraft.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
        RenderSystem.pushMatrix();
        int i = this.minecraft.player.xpBarCap();
        if (i > 0) {
            int expWidth = (int)(this.minecraft.player.experience * 183.0F);
            expProgress = expProgress.get() == expWidth ? expProgress : new IntRange(expProgress.get(), expWidth,0);
            int yPos = height - 32 + 3;
            this.blit(matrixStack, xPos, yPos, 0, 64, 182, 5);
            if (expWidth > 0) {
                this.blit(matrixStack, xPos, yPos, 0, 69, expProgress.increase(2), 2);
            }
        }
        RenderSystem.popMatrix();
        //渲染工具经验
        if(!(itemStack.getItem() instanceof  IElementLevelTools)){
            return;
        }
        IElementLevelTools item = ((IElementLevelTools) itemStack.getItem());
        RGBAColor toolElementColor = item.getItemElement().getElementRGBAColor();
        if(toolElementColor == null) {
            return;
        }
        this.minecraft.getTextureManager().bindTexture(VIDA_ICONS);
        RenderSystem.pushMatrix();
        double toolExp = item.getCurrentLevelXP(itemStack);
        double nextToolExp = item.getNextLevelRequiredXP(itemStack);
        RenderSystem.color4f(toolElementColor.getRed() / 255.0F,toolElementColor.getGreen() / 255.0F , toolElementColor.getBlue() / 255.0F , 1);
        if(toolExp > 0){
            int toolExpWidth = (int)( toolExp / nextToolExp * 183.0F);
            toolExpProgress = toolExpProgress.get() == toolExpWidth ? toolExpProgress : new IntRange(toolExpProgress.get(), toolExpWidth,0);
            int yPos = height - 32 + 6;
            this.blit(matrixStack, xPos, yPos, 0, 8, toolExpProgress.increase(2), 3);
        }
        RenderSystem.popMatrix();
        //渲染经验文字
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        if (this.minecraft.player.experienceLevel > 0) {
            String s = "" + this.minecraft.player.experienceLevel;
            int i1 = (width - this.minecraft.fontRenderer.getStringWidth(s)) / 2 - 20;
            int j1 = height - 31 - 4;
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)(i1 + 1), (float)j1, RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)(i1 - 1), (float)j1, RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)(j1 + 1), RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)(j1 - 1), RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)j1, RGBAColor.getColorCodeFromRGBA(128,255,32, (int)((alpha.increase(1) / 100.0f) * 255.0F)));
        }
        RenderSystem.popMatrix();
        //渲染工具等级
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        if (this.minecraft.player.experienceLevel > 0) {
            String s = "" + item.getCurrentLevel(stack);
            int i1 = (width - this.minecraft.fontRenderer.getStringWidth(s)) / 2 + 20;
            int j1 = height - 31 - 4;
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)(i1 + 1), (float)j1, RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)(i1 - 1), (float)j1, RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)(j1 + 1), RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)(j1 - 1), RGBAColor.getColorCodeFromRGBA(0,0,0,(int)((alpha.increase(1) / 100.0f) * 255.0F)));
            this.minecraft.fontRenderer.drawString(matrixStack, s, (float)i1, (float)j1, RGBAColor.getColorCodeFromRGBA(toolElementColor.getRed(),toolElementColor.getGreen(),toolElementColor.getBlue(), (int)((alpha.increase(1) / 100.0f) * 255.0F)));
        }
        RenderSystem.popMatrix();
    }

    /**
     * 根据ItemStack检查HUD是否要重新渲染
     *
     *
     * */
    public void checkRenew(ItemStack renderItem){
        if(itemStack != null && itemStack.isItemEqualIgnoreDurability(renderItem)){
            this.itemStack = renderItem;
            return;
        }
        reset();
        this.itemStack = renderItem;
    }

    public void reset(){
        this.alpha = new IntRange(0,100,0);
        this.expProgress = new IntRange(0,183,0);
        this.toolExpProgress = new IntRange(0,183,0);
        this.itemStack = ItemStack.EMPTY;
    }

}
