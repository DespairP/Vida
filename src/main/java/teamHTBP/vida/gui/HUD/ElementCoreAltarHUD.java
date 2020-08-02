package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.Vida;

public class ElementCoreAltarHUD extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.modId, "textures/gui/altar_hud.png");
    private TileEntityElementCoreAltar tileEntityElementCoreAltar ;
    public ElementCoreAltarHUD(TileEntityElementCoreAltar tileEntityElementCoreAltar){
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityElementCoreAltar = tileEntityElementCoreAltar;
    }

    public void render(){
        if(tileEntityElementCoreAltar==null) return;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(HUD);
        int screenWidth =this.width / 2 - 28;
        int screenHeight = this.height / 2 - 66;
        blit(screenWidth, screenHeight, 0, 0, 0, 56, 56, 80, 80);
        blit(screenWidth + 4 , screenHeight + 80, 0, 0, 75, 48, 4, 80, 80);
        float length = (tileEntityElementCoreAltar.progress / 30000.0f) * 48.0f;
        blit(screenWidth + 4 , screenHeight + 80, 0, 0, 71, (int)length, 4, 80, 80);
        for(int i = 0;i<4;i++){
            ItemStack stack = tileEntityElementCoreAltar.altarItem[i];
            if(stack != ItemStack.EMPTY && !stack.isEmpty()){
                switch (i){
                    case 0:
                         Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack, screenWidth + 1  , screenHeight + 19);
                         break;
                    case 1:
                        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack, screenWidth + 20  , screenHeight );
                        break;
                    case 2:
                        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack, screenWidth + 39  , screenHeight + 20);
                        break;
                    case 3:
                        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack,screenWidth + 20 , screenHeight + 39);
                        break;
                }

            }
        }

        ItemStack stack = tileEntityElementCoreAltar.coreItem;
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(stack,screenWidth + 20 , screenHeight + 20);


    }
}
