package teamHTBP.vida.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.blockentity.OreReationMachineBlockEntity;
import teamHTBP.vida.client.screen.base.VidaBaseScreen;
import teamHTBP.vida.common.menu.OreReactionMachineMenu;

public class OreReactionMachineScreen extends VidaBaseScreen<OreReactionMachineMenu> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/orereaction_gui.png");
    OreReationMachineBlockEntity oreReationMachineBlockEntity;

    public OreReactionMachineScreen(OreReactionMachineMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.oreReationMachineBlockEntity = this.getMenu().machine;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, Gui);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2 - 30;
        blit(matrixStack, i, j, 0, 0, 176, 211, 256, 256);
        //渲染箭头
        if (oreReationMachineBlockEntity.isCooking()) {
            int progress = (int) (oreReationMachineBlockEntity.array.get(1) * 18.0f / (oreReationMachineBlockEntity.MAX_COOKTIME * 1.0f));
            blit(matrixStack, i + 79, j + 64, 0, 232, 0, 8, progress, 256, 256);
        }
        //System.out.println(oreReationMachineBlockEntity.isBurning());
        if (oreReationMachineBlockEntity.isBurning()) {
            int progress = (int) (oreReationMachineBlockEntity.array.get(0) * 72.0f / (oreReationMachineBlockEntity.MAX_BURNTIME * 1.0f));
            blit(matrixStack, i + 52, j + 37 + 72 - progress, 0, 248, 0 + 72 - progress, 8, progress, 256, 256);
        }
        if (oreReationMachineBlockEntity.getGoldEnergy() > 0) {
            int progress = (int) (oreReationMachineBlockEntity.getGoldEnergy() * 72.0f / (oreReationMachineBlockEntity.MAX_GOLDENERGY * 1.0f));
            blit(matrixStack, i + 32, j + 37 + 72 - progress, 0, 240, 0 + 72 - progress, 8, progress, 256, 256);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }
}
