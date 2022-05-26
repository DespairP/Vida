package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;

import java.util.List;

/**
 * 渲染元素瓶HUD
 * 注意：由于是每个renderTick都new一次，所以不需要考虑alpha增减
 */
@OnlyIn(Dist.CLIENT)
public class BottleHUD extends AbstractGui {
    /**总屏幕大小*/
    private final int width;
    private final int height;
    /**client Minecraft*/
    private final Minecraft minecraft;
    /**资源路径*/
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/bottles_hud.png");
    /**玩家元素瓶装备*/
    private final ItemStack armorStack;
    /**透明度*/
    private float alpha;

    public BottleHUD(ItemStack stack, int alpha) {
        minecraft = Minecraft.getInstance();
        width = minecraft.getMainWindow().getScaledWidth();
        height = minecraft.getMainWindow().getScaledHeight();
        this.armorStack = stack;
        this.alpha = alpha / 100.0f;
    }

    public void render(MatrixStack matrixStack) {
        try {
            // 获取装备中的三个瓶子
            CompoundNBT nbt = armorStack.getOrCreateTag();
            final ItemStack stack1 = ItemStack.read(nbt.getCompound("bottle1"));
            final ItemStack stack2 = ItemStack.read(nbt.getCompound("bottle2"));
            final ItemStack stack3 = ItemStack.read(nbt.getCompound("bottle3"));

            // 开始渲染
            RenderSystem.pushMatrix();
            RenderSystem.color4f(alpha, alpha, alpha, alpha);
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            this.minecraft.getTextureManager().bindTexture(HUD);
            int screenWidth = this.width / 2 - 53;
            int screenHeight = this.height / 2 - 66;

            renderFrames(matrixStack);
            renderBottles(matrixStack, stack1, "bottle1Num",19, 40);
            renderBottles(matrixStack, stack2, "bottle2Num",78,40);
            renderBottles(matrixStack, stack3, "bottle3Num",48, 10);

            renderBottlesText(matrixStack, stack1, "bottle1Num",18, 30);
            renderBottlesText(matrixStack, stack2,"bottle2Num", 76, 30);
            renderBottlesText(matrixStack, stack3, "bottle3Num",45,-1);


            RenderSystem.popMatrix();
            RenderSystem.defaultBlendFunc();
            RenderSystem.defaultAlphaFunc();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }


    /**
     * 渲染框架
     */
    public void renderFrames(MatrixStack matrixStack) {
        int screenWidth = this.width / 2 - 53;
        int screenHeight = this.height / 2 - 66;
        // 渲染中心圆圈
        blit(matrixStack, screenWidth, screenHeight, 0, 0, 0, 101, 67, 128, 128);
        // 渲染锁
        if (armorStack.getItem() instanceof ItemArmorElementLegginsWithBottles){
            int element = ((ItemArmorElementLegginsWithBottles) armorStack.getItem()).element;
            // 只有木元素不渲染锁
            if(element != 2){
                blit(matrixStack, screenWidth + 48, screenHeight + 12, 0, 0, 83, 10, 13, 128, 128);
            }
        }
    }

    /**
     * 渲染物品
     *
     * @param offsetX 游戏内渲染偏移X
     * @param offsetY 游戏内渲染偏移Y
     * @param key 关于bottle的nbt获取Key
     */
    public void renderBottles(MatrixStack matrixStack, ItemStack bottleStack, String key, int offsetX, int offsetY) {
        if (bottleStack == null) return;
        if (armorStack.isEmpty() || bottleStack.isEmpty()) return;

        int screenWidth = this.width / 2 - 53;
        int screenHeight = this.height / 2 - 66;
        // 获取填充量
        CompoundNBT nbt = armorStack.getOrCreateTag();
        int progress = nbt.getInt(key);
        // 计算渲染比例
        int containDraw = (int) (10.0f * progress / 100.0f);
        if (containDraw >= 10) containDraw = 10;
        // 渲染
        blit(matrixStack, screenWidth + offsetX, screenHeight + offsetY, 0, 16, 81, 10, 15, 128, 128);
        blit(matrixStack, screenWidth + offsetX, screenHeight + offsetY + 15 - containDraw, 0, 32, 86 + 10 - containDraw, 10, containDraw, 128, 128);
    }

    /**
     * 渲染文字,包括完成的百分率
     *
     * @param offsetX 游戏内渲染偏移X,主要是百分值
     * @param offsetY 游戏内渲染偏移Y,主要是效果
     * @param key 关于bottle的nbt获取Key
     * */
    public void renderBottlesText(MatrixStack matrixStack, ItemStack bottleStack,String key,int offsetX,int offsetY) {
        // 如果没有物品,就不渲染文字
        if(bottleStack == null || bottleStack.isEmpty()) return;

        // 开始渲染
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        int screenWidth = this.width / 2 - 53;
        int screenHeight = this.height / 2 - 66;
        int progress = armorStack.getOrCreateTag().getInt(key);
        // 获取效果
        List<EffectInstance> list = PotionUtils.getEffectsFromStack(bottleStack);
        ITextComponent itextcomponent = new TranslationTextComponent("");
        for (EffectInstance effectinstance : list) {
            itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
        }
        // 渲染效果
        drawCenteredString(matrixStack, minecraft.fontRenderer, itextcomponent.getString(), screenWidth + offsetX + 8, screenHeight + offsetY, 120010 | (int) alpha * 100);
        // 渲染百分比
        minecraft.fontRenderer.drawString(matrixStack, progress + "%", screenWidth + offsetX, screenHeight + offsetY + 26, 120010 | (int) alpha * 100);
        RenderSystem.popMatrix();
    }
}
