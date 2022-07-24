package teamHTBP.vida.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.skill.ISkill;
import teamHTBP.vida.capability.skill.SkillHelper;
import teamHTBP.vida.capability.skill.SkillSurface;
import teamHTBP.vida.client.gui.screen.base.VidaBaseScreen;
import teamHTBP.vida.menu.InjectTableMenu;
import teamHTBP.vida.menu.slot.AbstractSkillSlot;
import teamHTBP.vida.menu.slot.NormalSkillSlot;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.item.staff.IElementTools;
import teamHTBP.vida.item.staff.ItemElementPickaxe;
import teamHTBP.vida.item.staff.ItemElementSword;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class InjectTableScreen extends VidaBaseScreen<InjectTableMenu> {
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
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/inject_gui.png");
    ResourceLocation background = new ResourceLocation(Vida.MOD_ID, "textures/gui/spacestars2.png");
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


    public InjectTableScreen(InjectTableMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.stack = this.getMenu().stack;
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
        this.imageWidth = this.width; //获取屏幕宽度
        this.imageHeight = this.height; //获取屏幕
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
            addWidget(slot);
        });
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.stack = this.getMenu().injectTable.getSwordStack();
        this.renderBackground(matrixStack);
        RenderSystem.setShaderTexture(0, Gui);
        alpha += 0.1f;
        if (alpha >= 1.0f) alpha = 1.0f;
        refreshTick = (refreshTick + 1) % MAX_REFRESH_TIME;
        CompoundTag nbt = stack.getOrCreateTag();
        drawForegGround(matrixStack); //绘制背景
        drawSkillIntroducution(matrixStack); //绘制技能介绍
        drawSkill(matrixStack, mouseX,mouseY); //调整技能位置
        drawLevel(matrixStack, nbt, this.stack); //绘制工具等级
        drawSkillEnergy(matrixStack);
        refreshSkill(nbt, false);
        drawSword(matrixStack);   //绘制工具
    }

    /**
     * 绘制工具和工具框
     */
    private void drawSword(PoseStack matrixStack) {
        matrixStack.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        //渲染物品
        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, this.backgroundXsize - 18 + 10 + this.offsetX, this.backgroundYsize - 18 + 11 + this.offsetY);
        //渲染物品的外框
        RenderSystem.setShaderTexture(0, Gui);
        blit(matrixStack, this.backgroundXsize - 21 + offsetX, this.backgroundYsize - 24 + offsetY, 0, 91, 56, 42, 48, 512, 512);
        matrixStack.popPose();
    }

    /**渲染tooltip*/
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float ticks) {
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY); //渲染原生的tooltip
        skillSlots.forEach((slot) -> {
            if (slot.isHovered()) this.renderSkillToolTip(matrixStack, mouseX, mouseY, slot.getSkillSurface());
        }); //当悬浮技能框上时会显示技能名字
    }


    /**
     * 画左上角的等级框
     **/
    public void drawLevel(PoseStack matrixStack, CompoundTag nbt, ItemStack stack) {
        double toolExpPercent = getToolExpPercent(nbt, stack);
        increaseProgressLevel(toolExpPercent, 52);
        matrixStack.pushPose();
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        //渲染外框
        blit(matrixStack, this.leftPos + backgroundXsize / 20, this.topPos + 10, 0, 0, 228, 68, 13, 512, 512);
        //渲染进度条
        blit(matrixStack, this.leftPos + 14 + backgroundXsize / 20, this.topPos + 9 + 10, 0, 70, 238, progressLevel, 2, 512, 512);
        //渲染左侧元素所属区域
        renderGem(matrixStack, stack);
        matrixStack.popPose();
    }

    /**
     * 获得经验百分比
     */
    private double getToolExpPercent(CompoundTag nbt, ItemStack stack) {
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
    private void renderGem(PoseStack matrixStack, ItemStack itemStack) {
        Item itemTool = stack.getItem();
        RenderSystem.setShaderColor(1, (float) 1, 1, 0.6f + (float) gemLightnessRenew());
        if (itemTool instanceof IElementTools elementTool) {
            if (elementTool.getElement() instanceof EnumElements element) {
                int u = switch (element) {
                    case VOID, FIRE, NONE -> 0;
                    case GOLD -> 39;
                    case WOOD -> 26;
                    case AQUA -> 13;
                    case EARTH -> 52;
                };
                blit(matrixStack, this.leftPos + backgroundXsize / 20, this.topPos + 10, 0,
                        52, 241,
                        13, 13,
                        512, 512);
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
    public void drawSkill(PoseStack ps, int mouseX,int mouseY) {
        ps.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        skillSlots.forEach((slot) -> {
            slot.updateSkillSlotInfo(backgroundXsize, backgroundYsize, offsetX, offsetY, mouseX, mouseY);
        });
        ps.popPose();
    }


    public void drawSwordFrameDeco(PoseStack os) {
        RenderSystem.setShaderTexture(0, Gui);
    }


    /**
     * 画底边的技能能量条
     **/
    public void drawSkillEnergy(PoseStack matrixStack) {
        matrixStack.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, selectSkillProgressAlpha);
        if (buttonClickIndex != -1) {
            SkillSurface surface = skills.get(buttonClickSkillName);
            double energyLength = (surface.skillCurrentExp * 107.0) / surface.getRequiredExp(surface.skillCurrentLevel); //计算能量最大长度
            if (selectSkillProgressLength < energyLength) selectSkillProgressLength += 2; //增长进度条
            if (selectSkillProgressAlpha < 1.0f) selectSkillProgressAlpha += 0.1f; //增加透明度
            if (selectSkillNameAlpha < 10) selectSkillNameAlpha += 1; //检查是否透明度溢出
            if (selectSkillProgressLength > 106) selectSkillProgressLength = 106; //检查进度条是否溢出
            matrixStack.pushPose();
            PoseStack matrixstack = new PoseStack();
            MultiBufferSource.BufferSource irendertypebuffer$impl = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            //渲染文字
            Minecraft.getInstance().font.drawInBatch(surface.getSkillTranslateName(), (float) (backgroundXsize - this.font.width(surface.getSkillTranslateName()) / 2), this.topPos + backgroundYsize * 2 - 35, Integer.parseInt("008CFF", 16), false, matrixstack.last().pose(),
                    irendertypebuffer$impl, false, 5, 15728890 - selectSkillNameAlpha);
            irendertypebuffer$impl.endBatch();
            matrixstack.popPose();
        } else {
            //不再有焦点是减少透明度
            if (selectSkillProgressAlpha > 0.0f) selectSkillProgressAlpha -= 0.1f;
            selectSkillNameAlpha = 0;
            selectSkillProgressLength = 0;
        }
        RenderSystem.setShaderTexture(0, Gui);
        //绘制原框
        blit(matrixStack, backgroundXsize - 55, this.topPos + backgroundYsize * 2 - 25, 0, 175, 150, 111, 7, 512, 512);
        //绘制低点位
        blit(matrixStack, backgroundXsize - 54, this.topPos + backgroundYsize * 2 - 23, 0, 176, 159, 1, 3, 512, 512);
        //绘制高点位
        blit(matrixStack, backgroundXsize - 52 + selectSkillProgressLength, this.topPos + backgroundYsize * 2 - 23, 0, 176, 159, 1, 3, 512, 512);
        //绘制条
        blit(matrixStack, backgroundXsize - 53, this.topPos + backgroundYsize * 2 - 24, 0, 177, 158, selectSkillProgressLength + 1, 5, 512, 512);
        matrixStack.popPose();
    }

    public void drawSkillIntroducution(PoseStack matrixStack) {
        drawBoard(matrixStack);
        if (this.buttonClickIndex > -1) {
            matrixStack.pushPose();
            AbstractSkillSlot selectedSlot = skillSlots.get(buttonClickIndex);
            SkillSurface surface = selectedSlot.getSkillSurface();
            //绘制文字
            drawCenteredString(matrixStack, this.font, surface.getSkillTranslateName(), this.leftPos + this.imageWidth - 54 - 1 * this.backgroundXsize / 50, this.topPos + 1 * this.backgroundYsize / 3, Integer.parseInt(selectedSlot.isLock() ? "FF3C00" : "45E800", 16));
            //绘制介绍
            if (selectedSlot.isLock()) {
                drawCenteredString(matrixStack, this.font, "???", this.leftPos + this.imageWidth - 54 - 1 * this.backgroundXsize / 50, this.topPos + 3 * this.backgroundYsize / 7, Integer.parseInt("C0C0C0", 16));
                //绘制需要的前置技能
                drawCenteredString(matrixStack, this.font, new TranslatableComponent("skill.vida.requiredSkill.anno"), this.leftPos + this.imageWidth - 54 - 1 * this.backgroundXsize / 50, this.topPos + 6 * this.backgroundYsize / 7, Integer.parseInt("FF6A00", 16));
                AtomicInteger integer = new AtomicInteger(0);
                //获取所有的前置技能
                surface.dependentSkill.forEach((id) -> {
                    //从现有的skill中获取然后显示
                    ISkill skill = this.skills.get(id);
                    if (skill != null)
                        drawCenteredString(matrixStack, this.font, "·" + skill.getSkillTranslateName(), this.leftPos + this.imageWidth - 54 - 1 * this.backgroundXsize / 50, this.topPos + 10 + 10 * integer.getAndIncrement() + 6 * this.backgroundYsize / 7, Integer.parseInt("FF615F", 16));
                });
            } else {
                //解锁后绘制解锁后的介绍文字
                //todo 谜之数字98
                this.font.draw(matrixStack, surface.getSkillTranlateDesc(), this.leftPos + this.imageWidth - 104 - 1 * this.backgroundXsize / 50, this.topPos + 3 * this.backgroundYsize / 7, 0x90E8E8);
            }
            matrixStack.popPose();
        }
        RenderSystem.setShaderTexture(0, Gui);
    }

    public void drawBoard(PoseStack matrixStack) {
        //绘制右边信息板
        matrixStack.pushPose();
        //这里使用的是下面技能进度条的透明度，因为两者是同步的
        RenderSystem.setShaderColor(1, 1, 1, selectSkillProgressAlpha * 0.5f);
        blit(matrixStack, this.leftPos + this.imageWidth - 114 - 1 * this.backgroundXsize / 100, this.topPos + 1 * this.backgroundYsize / 4, 0, 173, 5, 114, 142, 512, 512);
        matrixStack.popPose();
    }

    /**
     * 渲染技能的提示悬浮框
     **/
    // todo 这块可能得改改
    public void renderSkillToolTip(PoseStack matrixStack, int mouseX, int mouseY, SkillSurface skill) {
        if (skill == null) return;
        ArrayList<FormattedCharSequence> list = new ArrayList<>();
        list.add(FormattedCharSequence.forward(skill.getSkillTranslateName(), Style.EMPTY));
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
    public void drawForegGround(PoseStack matrixStack) {
        RenderSystem.setShaderTexture(0, background);
        matrixStack.pushPose();
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 0.4f);
        matrixStack.scale(6.0f, 6.0f, 6.0f);
        matrixStack.translate(-0, -0, -0);
        blit(matrixStack, 0 + offsetX / 10, 0 + offsetY / 10, 0, 0, 0, 64, 64, 64, 64);
        matrixStack.popPose();
        RenderSystem.setShaderTexture(0, Gui);
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
    public void refreshSkill(CompoundTag nbt, boolean fullRefresh) {
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
    protected void renderLabels(PoseStack p_230451_1_, int p_230451_2_, int p_230451_3_) {

    }


}