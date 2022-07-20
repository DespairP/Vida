package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.Vida;
import teamHTBP.vida.event.client.HUDElementItemHandler;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;
import teamHTBP.vida.utils.math.IntRange;

@OnlyIn(Dist.CLIENT)
public class ElementLevelToolsHUD extends GuiComponent {
    private ItemStack itemStack = ItemStack.EMPTY;
    private boolean isIncrease = true;
    private IntRange alpha;
    private IntRange progress;
    //
    private int width;
    private int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");

    private final static Logger LOGGER = LogManager.getLogger();
    public ElementLevelToolsHUD(ItemStack stack) {
        this.itemStack = stack;
        this.alpha = new IntRange(0,100,0);
        this.progress = new IntRange(0,17,0);
        this.minecraft = Minecraft.getInstance();
    }

    public void render(PoseStack matrixStack, float partialTicks){
        int alphaNum = isIncrease ? alpha.increase(2) : alpha.decrease(2);
        if((alphaNum == 0 && !isIncrease) || itemStack == null || !(itemStack.getItem() instanceof IElementLevelTools)){
            LOGGER.debug("ElementLevelToolsHUD is being pop,isIncrease:{},itemStack:{}",isIncrease,itemStack);
            HUDElementItemHandler.popRender(this);
            return;
        }
        // 重新获取屏幕的 Width 和 Height
        renew();
        // 获取渲染进度的高度
        IElementLevelTools item = ((IElementLevelTools) itemStack.getItem());
        int level = item.getCurrentLevel(itemStack);
        double exp = item.getCurrentLevelXP(itemStack);
        int progressHeight = this.progress.set((int) (exp * 16.0f / (level * 500.0f)));
        // 定位渲染位置
        int renderX = 3;
        int renderY = (int) (this.height * 5.5F / 6.0F);
        // --- start render ---
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alphaNum / 100.0f);
        matrixStack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, HUD);
        // 根据物品元素进行渲染
        EnumElements element = item.getItemElement();
        switch (element) {
            case GOLD:
                blit(matrixStack, renderX, renderY, 0, 0, 0, 16, 16, 35, 119);
                break;
            case WOOD:
                blit(matrixStack, renderX, renderY, 0, 48, 0, 16, 16, 35, 119);
                break;
            case AQUA:
                blit(matrixStack, renderX, renderY, 0, 32, 0, 16, 16, 35, 119);
                break;
            case FIRE:
                blit(matrixStack, renderX, renderY, 0, 16, 0, 16, 16, 35, 119);
                break;
            case EARTH:
                blit(matrixStack, renderX, renderY, 0, 64, 0, 16, 16, 35, 119);
                break;
        }
        // draw progress
        switch (element) {
            case GOLD:
                blit(matrixStack, renderX, renderY + 16 - progressHeight, 0, 0, 32 - progressHeight, 16, progressHeight, 35, 119);
                break;
            case WOOD:
                blit(matrixStack, renderX, renderY + 16 - progressHeight, 0, 48, 32 - progressHeight, 16, progressHeight, 35, 119);
                break;
            case AQUA:
                blit(matrixStack, renderX, renderY + 16 - progressHeight, 0, 32, 32 - progressHeight, 16, progressHeight, 35, 119);
                break;
            case FIRE:
                blit(matrixStack, renderX, renderY + 16 - progressHeight, 0, 16, 32 - progressHeight, 16, progressHeight, 35, 119);
                break;
            case EARTH:
                blit(matrixStack, renderX, renderY + 16 - progressHeight, 0, 64, 32 - progressHeight, 16, progressHeight, 35, 119);
                break;
        }
        matrixStack.popPose();
        matrixStack.pushPose();
        // 显示工具等级
        drawCenteredString(matrixStack, minecraft.font, level + "", renderX + 16, renderY + 10, RGBAColor.getColorCodeFromRGBA(126,186,137,255 * alphaNum / 100));
        matrixStack.popPose();
    }

    public void notifyStopRender(){
        isIncrease = false;
    }

    public boolean isSameItemStack(ItemStack stack){
        return stack.sameItemStackIgnoreDurability(this.itemStack);
    }

    public void renew(){
        this.width = minecraft.getWindow().getGuiScaledWidth();
        this.height = minecraft.getWindow().getGuiScaledHeight();
    }

    public void renewItemStack(ItemStack stack){
        if(this.isSameItemStack(stack)) {
            this.itemStack = stack;
        }
    }
}
