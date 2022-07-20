package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.TileEntity.TileEntityCollector;

public class TileEntityRenderCollector extends TileEntityRenderer<TileEntityCollector> {
    public TileEntityRenderCollector(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityCollector tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.coreItem != ItemStack.EMPTY && !tileEntityIn.coreItem.isEmpty()) {
            ItemStack stack = tileEntityIn.coreItem;
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 1.3, 0.5);
            matrixStackIn.scale(0.4f, 0.4f, 0.4f);
            matrixStackIn.mulPose(this.renderer.camera.rotation());
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            // RenderSystem.pushMatrix();
            // RenderSystem.color4f(1, 1.0f, 1, 0.8f);
            IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntityIn.getLevel(), null);
            itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            // RenderSystem.popMatrix();
            matrixStackIn.popPose();
        }
    }
}
