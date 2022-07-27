package teamHTBP.vida.common.menu.slot;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.capability.skill.SkillSurface;

public class NormalSkillSlot extends AbstractSkillSlot {
    /**
     * 该技能框所包含的技能
     */
    private final SkillSurface skillSurface;
    /**
     * <b>Unused<b/>--技能的缩放值
     */
    private final float scale = 0;
    /**
     * 基本技能框的Gui
     */
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/inject_gui.png");
    /**
     * 该技能框的基础alpha值，最大为0.4f
     */
    private float alpha = 0;
    /**
     * 该技能框的悬浮alpha值，当被悬浮时，最大为0.2f
     */
    private float hoveredLight = 0.0f;
    /**
     * 该技能框取得焦点的alpha值，当取得焦点时，最大为0.6f
     */
    private float focusAlpha = 0;
    /**
     * 技能框在整个界面中所处的下标
     */
    private int index = 0;
    /**
     * 材质偏移值U，用于偏移框（铜/银/金）
     */
    private int offsetU = 0;


    public NormalSkillSlot(int xIn, int yIn, String msg, SkillSurface skill, int index) {
        super(xIn, yIn, 42, 48, msg);
        this.skillSurface = skill;
        this.visible = true;
        this.index = index;
    }

    @Override
    public void onPress() {
        if (listener != null) listener.onClick(skillSurface.getSkillName(), index, this);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        //System.out.println(isHovered);
        //初始加载的时候加载
        if (alpha < 0.4f) alpha += 0.02f;
        //如果框被悬浮
        if (isHovered && hoveredLight < 0.2f) hoveredLight += 0.02f;
        else if (!isHovered && hoveredLight > 0.0f) hoveredLight -= 0.05f;
        //选中
        if (this.getMouseFocus() && focusAlpha < 0.6f) focusAlpha += 0.05f;
        else if (!this.getMouseFocus() && focusAlpha > 0) focusAlpha -= 0.05f;
        changeOverlapAlpha();
        renderSkillFrame(matrixStack);
        drawProgress(matrixStack);
        if (!isLock)
            renderSkill(matrixStack);
        else
            renderLock(matrixStack);
    }

    /**
     * 渲染技能框
     */
    public void renderSkillFrame(PoseStack matrixStack) {
        matrixStack.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, alpha + hoveredLight + focusAlpha);
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, Gui);
        switch (skillSurface.getSkillType()) {
            case NORMAL:
                break;
            case PlATIUM:
                offsetU = 42 * 3;
                break;
            case BRONZE:
                offsetU = 42 * 2;
                break;
            case GOLD:
            case SILVER:
                offsetU = 42 * 1;
                break;
        }
        if (!mouseFocus) blit(matrixStack, this.x, this.y, 0, 0 + offsetU, 254, 42, 48, 512, 512);
        else blit(matrixStack, this.x, this.y, 0, 0 + offsetU, 302, 42, 48, 512, 512);
        matrixStack.popPose();
    }

    /**
     * 渲染材质
     */
    public void renderSkill(PoseStack matrixStack) {
        matrixStack.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, alpha + hoveredLight + focusAlpha);
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, skillSurface.getLocation());
        matrixStack.scale(0.8f, 0.8f, 0.8f);
        blit(matrixStack, (int) ((this.x + 6) * 1.25) + 4, (int) ((this.y + 9) * 1.25) + 3, 0, skillSurface.getSkillTextureU(), skillSurface.getSkillTextureV(), 32, 32, 256, 256);
        matrixStack.popPose();
    }

    /**
     * 更新技能框信息
     * 包括：整个UI中的位置，是否有被悬浮
     *
     *
     * @param InX     guiLeft
     * @param InY     guiRight
     * @param offsetX 偏移的X值
     * @param offsetY 偏移的Y值
     * @param mouseX  鼠标现在的X
     * @param mouseY  鼠标现在的Y
     */
    @Override
    public void updateSkillSlotInfo(int InX, int InY, int offsetX, int offsetY, int mouseX, int mouseY) {
        this.x = skillSurface.getSkillX() - 21 + InX + offsetX;
        this.y = skillSurface.getSkillY() - 24 + InY + offsetY;
        this.isHovered = isHovered(mouseX, mouseY);
    }

    @Override
    public SkillSurface getSkillSurface() {
        return skillSurface;
    }

    /**
     * 渲染技能进度条
     */
    public void drawProgress(PoseStack matrixStack) {
        matrixStack.pushPose();
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, Gui);
        double progress = skillSurface.skillCurrentExp * 1.0 / skillSurface.getRequiredExp(skillSurface.skillCurrentLevel) * 100.0;
        if (progress > 100) progress = 100;
        if (progress < 50)
            blit(matrixStack, this.x + 21, this.y + 4, 0, 21, 352, 17, (int) (40 * progress / 50), 512, 512);
        else {
            blit(matrixStack, this.x + 21, this.y + 4, 0, 21, 352, 17, 40, 512, 512);
            blit(matrixStack, this.x, this.y + 44 - (int) (40 * (progress - 50) / 50), 0, 0, 392 - (int) (40 * (progress - 50) / 50), 21, (int) (40 * (progress - 50) / 50), 512, 512);
        }
        matrixStack.popPose();
    }

    /**
     * 渲染技能锁
     */
    public void renderLock(PoseStack matrixStack) {
        matrixStack.pushPose();
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, Gui);
        blit(matrixStack, this.x + 13, this.y + 12, 0, 0, 170, 15, 23, 512, 512);
        matrixStack.popPose();
    }

    public boolean isHovered(int mouseX, int mouseY){
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }


}
