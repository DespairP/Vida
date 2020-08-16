package teamHTBP.vida.gui.HUD;

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
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.TileEntity.TileEntityCollector;
import teamHTBP.vida.Vida;

import java.util.List;

public class BottleHUD extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.modId, "textures/gui/bottles_hud.png");
    private ItemStack itemStack ;
    float alpha;
    public BottleHUD(ItemStack stack,int alpha){
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        minecraft = Minecraft.getInstance();
        this.itemStack = stack;
        this.alpha = alpha / 100.0f;
    }

    public void render(){
        try {
            ItemStack stack1 = ItemStack.EMPTY;
            ItemStack stack2 = ItemStack.EMPTY;
            ItemStack stack3 = ItemStack.EMPTY;
            if (itemStack == ItemStack.EMPTY) return;
            RenderSystem.pushMatrix();
            RenderSystem.color4f(alpha, alpha, alpha, alpha);
            RenderSystem.enableAlphaTest();
            RenderSystem.enableBlend();
            this.minecraft.getTextureManager().bindTexture(HUD);
            int screenWidth = this.width / 2 - 53;
            int screenHeight = this.height / 2 - 66;
            float offset = alpha * 10.0f;
            blit(screenWidth, screenHeight, 0, 0, 0, 101, 67, 128, 128);

            CompoundNBT nbt = itemStack.getOrCreateTag();
            if (nbt.getCompound("bottle1") != null) {
                ItemStack stack = ItemStack.read(nbt.getCompound("bottle1"));
                if (stack != ItemStack.EMPTY && !stack.isEmpty()) {
                    stack1 = stack;
                    int progress = nbt.getInt("bottle1Num");
                    int containDraw = (int) (10.0f * progress / 100.0f);
                    if (containDraw >= 10) containDraw = 10;
                    blit(screenWidth + 19, screenHeight + 40, 0, 16, 81, 10, 15, 128, 128);
                    blit(screenWidth + 19, screenHeight + 40 + 15 - containDraw, 0, 32, 86 + 10 - containDraw, 10, containDraw, 128, 128);

                }
            }
            if (nbt.getCompound("bottle2") != null) {
                ItemStack stack = ItemStack.read(nbt.getCompound("bottle2"));
                if (stack != ItemStack.EMPTY && !stack.isEmpty()) {
                    stack2 = stack;
                    int progress = nbt.getInt("bottle2Num");
                    int containDraw = (int) (10.0f * progress / 100.0f);
                    if (containDraw >= 10) containDraw = 10;
                    blit(screenWidth + 78, screenHeight + 40, 0, 16, 81, 10, 15, 128, 128);
                    blit(screenWidth + 78, screenHeight + 40 + 15 - containDraw, 0, 32, 86 + 10 - containDraw, 10, containDraw, 128, 128);

                }
            }
            int element = 0;
            if (itemStack.getItem() instanceof ItemArmorElementLegginsWithBottles)
                element = ((ItemArmorElementLegginsWithBottles) itemStack.getItem()).element;
            if (element != 2)
                blit(screenWidth + 48, screenHeight + 12, 0, 0, 83, 10, 13, 128, 128);
            else {
                if (nbt.getCompound("bottle3") != null) {
                    ItemStack stack = ItemStack.read(nbt.getCompound("bottle3"));
                    if (stack != ItemStack.EMPTY && !stack.isEmpty()) {
                        stack3 = stack;
                        int progress = nbt.getInt("bottle3Num");
                        int containDraw = (int) (10.0f * progress / 100.0f);
                        if (containDraw >= 10) containDraw = 10;
                        blit(screenWidth + 48, screenHeight + 10, 0, 16, 81, 10, 15, 128, 128);
                        blit(screenWidth + 48, screenHeight + 10 + 15 - containDraw, 0, 32, 86 + 10 - containDraw, 10, containDraw, 128, 128);

                    }
                }
            }


            if (stack1 != ItemStack.EMPTY && !stack1.isEmpty()) {
                int progress = itemStack.getTag().getInt("bottle1Num");
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack1);
                ITextComponent itextcomponent;
                for (EffectInstance effectinstance : list) {
                    itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
                    drawCenteredString(minecraft.fontRenderer, itextcomponent.getFormattedText(), screenWidth + 24, screenHeight + 30, 120010 | (int) alpha * 100);
                }
                minecraft.fontRenderer.drawString(progress + "%", screenWidth + 18, screenHeight + 56, 120010 | (int) alpha * 100);

                RenderSystem.popMatrix();
            }

            if (stack2 != ItemStack.EMPTY && !stack2.isEmpty()) {
                int progress = itemStack.getTag().getInt("bottle2Num");
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack2);
                ITextComponent itextcomponent;
                for (EffectInstance effectinstance : list) {
                    itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
                    drawCenteredString(minecraft.fontRenderer, itextcomponent.getFormattedText(), screenWidth + 85, screenHeight + 30, 120010 | (int) alpha * 100);
                }
                minecraft.fontRenderer.drawString(progress + "%", screenWidth + 76, screenHeight + 56, 300010 | (int) alpha * 100);
                RenderSystem.popMatrix();
            }

            if (stack3 != ItemStack.EMPTY && !stack3.isEmpty()) {
                int progress = itemStack.getTag().getInt("bottle3Num");
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack3);
                ITextComponent itextcomponent;
                for (EffectInstance effectinstance : list) {
                    itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
                    drawCenteredString(minecraft.fontRenderer, itextcomponent.getFormattedText(), screenWidth + 53, screenHeight - 1, 120010 | (int) alpha * 100);
                }
                minecraft.fontRenderer.drawString(progress + "%", screenWidth + 45, screenHeight + 29, 300010 | (int) alpha * 100);
                RenderSystem.popMatrix();
            }

            RenderSystem.popMatrix();
            RenderSystem.defaultBlendFunc();
            RenderSystem.defaultAlphaFunc();
        }catch (Exception ex){
            return;
        }
    }
}
