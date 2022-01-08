package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.skillSystem.ISkill;
import teamHTBP.vida.capability.skillSystem.SkillHelper;
import teamHTBP.vida.capability.skillSystem.SkillSurface;
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.gui.GUI.Slot.AbstractSkillSlot;
import teamHTBP.vida.gui.GUI.Slot.NormalSkillSlot;
import teamHTBP.vida.item.staff.IElementTools;
import teamHTBP.vida.item.staff.ItemElementPickaxe;
import teamHTBP.vida.item.staff.ItemElementSword;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ContainerScreenInjectTable extends ContainerScreen<ContainerInjectTable> {
    /**
     * 最大界面刷新时间
     */
    public final int MAX_REFRESH_TIME = 100;
    /**
     * 屏幕x的中间
     */
    public int backgroundXsize = 0;
    /**
     * y的中间
     */
    public int backgroundYsize = 0;
    /**
     * 所有的技能按钮都在这里
     */
    public LinkedList<AbstractSkillSlot> skillSlots = new LinkedList<AbstractSkillSlot>();
    /**
     * 上一次选中的技能在界面中的index
     */
    public int buttonClickIndex = -1;
    /**
     * 上一次选中的技能在界面中的ID
     */
    public String buttonClickSkillName = "";
    public int selectSkillProgressLength = 0;
    /**
     * 刷新ticks
     */
    public int refreshTick = 0;
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/inject_gui.png");
    ResourceLocation background = new ResourceLocation(Vida.modId, "textures/gui/spacestars2.png");
    /**
     * 显示的工具框
     */
    ItemStack stack = ItemStack.EMPTY;
    /**
     * 该工具的所有可用技能储存在这个hashmap中
     */
    LinkedHashMap<String, SkillSurface> skills;
    /**
     * 鼠标位移的x
     */
    private int offsetX = 0;
    /**
     * 鼠标位移的y
     */
    private int offsetY = 0;
    /**
     * 整体透明度
     */
    private float alpha = 0;
    /**
     * 工具经验进度条进度
     */
    private int progressLevel = 0;
    /**
     * 宝石亮度deg值
     */
    private float gemLightnessDegree = 0.0F;
    /**
     * 能量条透明度
     */
    private float selectSkillProgressAlpha = 0.0f;
    /**
     * 能量条上方的技能名的透明度
     */
    private int selectSkillNameAlpha = 0;


    public ContainerScreenInjectTable(ContainerInjectTable screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.stack = this.getContainer().stack;
        this.backgroundXsize = this.width / 2;
        this.backgroundYsize = this.height / 2;
    }

    /**
     * 初始化界面，打开时加载
     */
    @Override
    protected void init() {
        initScreenSize(); //设置屏幕的长宽，并阻止NEI或者JEI的物品列表生成
        super.init(); //父类初始化设置屏幕的中间点
        initSkillAndSkillSlot(); //加入技能按钮
    }

    /**
     * 初始化屏幕
     */
    private void initScreenSize() {
        this.xSize = this.width; //获取屏幕宽度
        this.ySize = this.height; //获取屏幕
        this.backgroundXsize = this.width / 2;
        this.backgroundYsize = this.height / 2;
    }

    /**
     * 初始化所有技能
     */
    private void initSkillAndSkillSlot() {
        this.skills = SkillHelper.getAllSkillSurfaceFromItemStack(this.stack); //获取所有技能
        AtomicInteger index = new AtomicInteger(0);
        skillSlots.clear();
        //添加所有的技能到技能格中
        skills.forEach((name, skill) -> {
            AbstractSkillSlot slot = new NormalSkillSlot(backgroundXsize + offsetX - 21 + skill.getSkillX(),
                    backgroundYsize - 24 + offsetY + skill.getSkillY(), "", skill, index.getAndIncrement());
            //给予技能格监听器，在技能格被点击时焦点会聚集到该技能格上，其他技能格焦点消失。如果再次点击技能格技能格焦点也随之消失
            slot.listener = (id, in, skillSlot) -> {
                if (buttonClickIndex == -1 || buttonClickIndex != in) {
                    buttonClickIndex = in;
                    buttonClickSkillName = name;
                } else {
                    buttonClickIndex = -1;
                    buttonClickSkillName = "";
                }
                this.skillSlots.forEach(skillSlots -> {
                    skillSlots.setMouseFouse(false);
                    if (buttonClickIndex != -1) skillSlot.setMouseFouse(true);
                    selectSkillProgressLength = 0;
                });
            };
            //判定技能格是否为上锁状态
            slot.setLock(isSkillLocked(skill));
            skillSlots.add(slot);
            addButton(slot);
        });
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.stack = this.getContainer().injectTable.getSwordStack();
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindTexture(Gui);
        alpha += 0.1f;
        if (alpha >= 1.0f) alpha = 1.0f;
        refreshTick = (refreshTick + 1) % MAX_REFRESH_TIME;
        CompoundNBT nbt = stack.getOrCreateTag();
        drawForegGround(matrixStack); //绘制背景
        drawSkillIntroducution(matrixStack); //绘制技能介绍
        drawSkill(); //调整技能位置
        drawLevel(matrixStack, nbt, this.stack); //绘制工具等级
        drawSkillEnergy(matrixStack);
        refreshSkill(nbt, false);
        drawSword(matrixStack);   //绘制工具
    }

    /**
     * 绘制工具和工具框
     */
    private void drawSword(MatrixStack matrixStack) {
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.color4f(1, 1, 1, alpha);
        //渲染物品
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack, this.backgroundXsize - 18 + 10 + this.offsetX, this.backgroundYsize - 18 + 11 + this.offsetY);
        //渲染物品的外框
        this.minecraft.getTextureManager().bindTexture(Gui);
        blit(matrixStack, this.backgroundXsize - 21 + offsetX, this.backgroundYsize - 24 + offsetY, 0, 91, 56, 42, 48, 512, 512);
        RenderSystem.popMatrix();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float ticks) {
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY); //渲染原生的tooltip
        skillSlots.forEach((slot) -> {
            if (slot.isHovered()) this.renderSkillToolTip(matrixStack, mouseX, mouseY, slot.getSkillSurface());
        }); //当悬浮技能框上时会显示技能名字
    }


    /**
     * 画左上角的等级框
     **/
    public void drawLevel(MatrixStack matrixStack, CompoundNBT nbt, ItemStack stack) {
        double toolExpPercent = getToolExpPercent(nbt, stack);
        increaseProgressLevel(toolExpPercent, 52);
        RenderSystem.pushMatrix();
        RenderSystem.color4f(1, 1, 1, alpha);
        //渲染外框
        blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 0, 228, 68, 13, 512, 512);
        //渲染进度条
        blit(matrixStack, this.guiLeft + 14 + backgroundXsize / 20, this.guiTop + 9 + 10, 0, 70, 238, progressLevel, 2, 512, 512);
        //渲染左侧元素所属区域
        renderGem(matrixStack, stack);
        RenderSystem.popMatrix();
    }

    /**
     * 获得经验百分比
     */
    private double getToolExpPercent(CompoundNBT nbt, ItemStack stack) {
        int level = nbt.getInt("level");
        int exp = 0;
        double percent = 1;
        Item itemTool = stack.getItem();
        if (itemTool instanceof ItemElementPickaxe) {
            exp = nbt.getInt("pickaxeEXP");
            percent = exp / (level * 500.0f);
        } else if (itemTool instanceof ItemElementSword) {
            exp = nbt.getInt("swordEXP");
            percent = exp / (level * 200.0f + level * 13.0f);
        }
        return percent;
    }

    /**
     * 按帧数增长经验条
     */
    private void increaseProgressLevel(double percent, int maxSize) {
        int maxLength = (int) (percent * maxSize);
        if (progressLevel < maxLength) progressLevel++;
        else if (progressLevel >= maxLength) progressLevel = maxLength;
        else progressLevel = 0;
    }

    /**
     * 元素渲染
     */
    private void renderGem(MatrixStack matrixStack, ItemStack itemStack) {
        Item itemTool = stack.getItem();
        RenderSystem.color4f(1, (float) 1, 1, 0.6f + (float) gemLightnessRenew());
        if (itemTool instanceof IElementTools) {
            EnumElements elementType = ((IElementTools) itemTool.getItem()).getElementType();
            switch (elementType) {
                case GOLD:
                    blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 39, 241, 13, 13, 512, 512);
                    break;
                case WOOD:
                    blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 26, 241, 13, 13, 512, 512);
                    break;
                case AQUA:
                    blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 13, 241, 13, 13, 512, 512);
                    break;
                case FIRE:
                    blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 0, 241, 13, 13, 512, 512);
                    break;
                case EARTH:
                    blit(matrixStack, this.guiLeft + backgroundXsize / 20, this.guiTop + 10, 0, 52, 241, 13, 13, 512, 512);
                    break;
            }
        }
    }

    /**
     * 元素闪动并返回所处值，0.0~0.4
     */
    public double gemLightnessRenew() {
        if (gemLightnessDegree < 360)
            gemLightnessDegree += 0.5f;
        else
            gemLightnessDegree = 0;
        return Math.sin(gemLightnessDegree * 0.03f) * 0.4f;
    }


    /**
     * 渲染技能按钮
     **/
    public void drawSkill() {
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.color4f(1, 1, 1, alpha);
        skillSlots.forEach((slot) -> {
            slot.updateSkillPosition(backgroundXsize, backgroundYsize, offsetX, offsetY);
        });
        RenderSystem.popMatrix();
    }


    public void drawSwordFrameDeco() {
        this.minecraft.getTextureManager().bindTexture(Gui);
        RenderSystem.popMatrix();
    }


    /**
     * 画底边的技能能量条
     **/
    public void drawSkillEnergy(MatrixStack matrixStack) {
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.color4f(1, 1, 1, selectSkillProgressAlpha);
        if (buttonClickIndex != -1) {
            SkillSurface surface = skills.get(buttonClickSkillName);
            double energyLength = (surface.skillCurrentExp * 107.0) / surface.getRequiredExp(surface.skillCurrentLevel); //计算能量最大长度
            if (selectSkillProgressLength < energyLength) selectSkillProgressLength += 2; //增长进度条
            if (selectSkillProgressAlpha < 1.0f) selectSkillProgressAlpha += 0.1f; //增加透明度
            if (selectSkillNameAlpha < 10) selectSkillNameAlpha += 1; //检查是否透明度溢出
            if (selectSkillProgressLength > 106) selectSkillProgressLength = 106; //检查进度条是否溢出
            RenderSystem.pushMatrix();
            MatrixStack matrixstack = new MatrixStack();
            IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
            //渲染文字
            Minecraft.getInstance().fontRenderer.renderString(surface.getSkillTranlateName(), (float) (backgroundXsize - this.font.getStringWidth(surface.getSkillTranlateName()) / 2), this.guiTop + backgroundYsize * 2 - 35, Integer.parseInt("008CFF", 16), false, matrixstack.getLast().getMatrix(),
                    irendertypebuffer$impl, false, 5, 15728890 - selectSkillNameAlpha);
            irendertypebuffer$impl.finish();
            RenderSystem.popMatrix();
        } else {
            //不再有焦点是减少透明度
            if (selectSkillProgressAlpha > 0.0f) selectSkillProgressAlpha -= 0.1f;
            selectSkillNameAlpha = 0;
            selectSkillProgressLength = 0;
        }
        this.minecraft.getTextureManager().bindTexture(Gui);
        //绘制原框
        blit(matrixStack, backgroundXsize - 55, this.guiTop + backgroundYsize * 2 - 25, 0, 175, 150, 111, 7, 512, 512);
        //绘制低点位
        blit(matrixStack, backgroundXsize - 54, this.guiTop + backgroundYsize * 2 - 23, 0, 176, 159, 1, 3, 512, 512);
        //绘制高点位
        blit(matrixStack, backgroundXsize - 52 + selectSkillProgressLength, this.guiTop + backgroundYsize * 2 - 23, 0, 176, 159, 1, 3, 512, 512);
        //绘制条
        blit(matrixStack, backgroundXsize - 53, this.guiTop + backgroundYsize * 2 - 24, 0, 177, 158, selectSkillProgressLength + 1, 5, 512, 512);
        RenderSystem.popMatrix();
    }

    public void drawSkillIntroducution(MatrixStack matrixStack) {
        drawBoard(matrixStack);
        if (this.buttonClickIndex > -1) {
            RenderSystem.pushMatrix();
            AbstractSkillSlot selectedSlot = skillSlots.get(buttonClickIndex);
            SkillSurface surface = selectedSlot.getSkillSurface();
            //绘制文字
            drawCenteredString(matrixStack, this.font, surface.getSkillTranlateName(), this.guiLeft + this.xSize - 54 - 1 * this.backgroundXsize / 50, this.guiTop + 1 * this.backgroundYsize / 3, Integer.parseInt(selectedSlot.isLock() ? "FF3C00" : "45E800", 16));
            //绘制介绍
            if (selectedSlot.isLock()) {
                drawCenteredString(matrixStack, this.font, "???", this.guiLeft + this.xSize - 54 - 1 * this.backgroundXsize / 50, this.guiTop + 3 * this.backgroundYsize / 7, Integer.parseInt("C0C0C0", 16));
                //绘制需要的前置技能
                drawCenteredString(matrixStack, this.font, I18n.format("skill.vida.requiredSkill.anno"), this.guiLeft + this.xSize - 54 - 1 * this.backgroundXsize / 50, this.guiTop + 6 * this.backgroundYsize / 7, Integer.parseInt("FF6A00", 16));
                AtomicInteger integer = new AtomicInteger(0);
                //获取所有的前置技能
                surface.dependentSkill.forEach((id) -> {
                    //从现有的skill中获取然后显示
                    ISkill skill = this.skills.get(id);
                    if (skill != null)
                        drawCenteredString(matrixStack, this.font, "·" + skill.getSkillTranlateName(), this.guiLeft + this.xSize - 54 - 1 * this.backgroundXsize / 50, this.guiTop + 10 + 10 * integer.getAndIncrement() + 6 * this.backgroundYsize / 7, Integer.parseInt("FF615F", 16));
                });
            } else {
                //解锁后绘制解锁后的介绍文字
                //todo 谜之数字98
                this.font.drawString(matrixStack, surface.getSkillTranlateDesc(), this.guiLeft + this.xSize - 104 - 1 * this.backgroundXsize / 50, this.guiTop + 3 * this.backgroundYsize / 7, 0x90E8E8);
            }
            RenderSystem.popMatrix();
        }
        this.minecraft.getTextureManager().bindTexture(Gui);
    }

    public void drawBoard(MatrixStack matrixStack) {
        //绘制右边信息板
        RenderSystem.pushMatrix();
        //这里使用的是下面技能进度条的透明度，因为两者是同步的
        RenderSystem.color4f(1, 1, 1, selectSkillProgressAlpha * 0.5f);
        blit(matrixStack, this.guiLeft + this.xSize - 114 - 1 * this.backgroundXsize / 100, this.guiTop + 1 * this.backgroundYsize / 4, 0, 173, 5, 114, 142, 512, 512);
        RenderSystem.popMatrix();
    }

    /**
     * 渲染技能的提示悬浮框
     **/
    // todo 这块可能得改改
    public void renderSkillToolTip(MatrixStack matrixStack, int mouseX, int mouseY, SkillSurface skill) {
        if (skill == null) return;
        ArrayList<IReorderingProcessor> list = new ArrayList<>();
        list.add(IReorderingProcessor.fromString(skill.getSkillTranlateName(), Style.EMPTY));
        this.renderTooltip(matrixStack, list, mouseX, mouseY);
    }

    public boolean buttonOverLap(AbstractSkillSlot slot) {
        int midx = slot.x + 19;
        int midy = slot.y + 19;
        int minleft = (this.backgroundXsize * 2 - 130) - 19;
        int maxleft = (this.backgroundXsize * 2 - 130) + 19 + 114;
        int mintop = this.backgroundYsize / 3 - 19;
        int maxtop = this.backgroundYsize / 3 + 142 + 19;
        //System.out.println(mintop + " " +maxleft);
        if (buttonClickIndex != -1) {
            return (midx > minleft && midx < maxleft) && (midy > mintop && midy < maxtop);
        }
        return false;
    }

    /**
     * 渲染背景
     */
    public void drawForegGround(MatrixStack matrixStack) {
        this.minecraft.getTextureManager().bindTexture(background);
        RenderSystem.pushMatrix();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.color4f(1, 1, 1, 0.4f);
        RenderSystem.scalef(6.0f, 6.0f, 6.0f);
        RenderSystem.translated(-0, -0, -0);
        blit(matrixStack, 0 + offsetX / 10, 0 + offsetY / 10, 0, 0, 0, 64, 64, 64, 64);
        RenderSystem.popMatrix();
        this.minecraft.getTextureManager().bindTexture(Gui);
    }

    /**
     * 当鼠标拖拽屏幕时会自动调用这个方法
     */
    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        this.offsetX += (int) p_mouseDragged_6_;
        this.offsetY += (int) p_mouseDragged_8_;
        if (this.offsetX >= 200) this.offsetX = 200;
        if (this.offsetX <= -200) this.offsetX = -200;
        if (this.offsetY >= 200) this.offsetY = 200;
        if (this.offsetY <= -119) this.offsetY = -119;
        return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
    }

    /**
     * 判定技能是否是上锁状态
     */
    public boolean isSkillLocked(SkillSurface surface) {
        if (surface.dependentSkill == null || surface.dependentSkill.size() == 0) return false;
        else if (surface.dependentSkill.size() > 0) {
            SkillSurface dependSkill = skills.get(surface.dependentSkill.get(0));
            if (dependSkill == null) return false;
            else
                return dependSkill.skillCurrentLevel != dependSkill.maxSkillLevel || dependSkill.skillCurrentExp != dependSkill.getRequiredExp(dependSkill.maxSkillLevel);
        }
        return false;
    }

    /***/
    public void refreshSkill(CompoundNBT nbt, boolean fullRefresh) {
        if (refreshTick == MAX_REFRESH_TIME - 1) {
            if (!fullRefresh) {
                skills.forEach((id, skill) -> {
                    SkillHelper.refreshSkillSurfaceExpAndLevel(skill, nbt);
                });
            } else {
                //fullRefresh,TODO
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {

    }
}