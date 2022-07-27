package teamHTBP.vida.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.common.blockentity.CollectorBlockEntity;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;

public class CollectorRenderer extends VidaBaseBlockEntityRenderer<CollectorBlockEntity> {

    public CollectorRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CollectorBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.coreItem != ItemStack.EMPTY && !tileEntityIn.coreItem.isEmpty()) {
            ItemStack stack = tileEntityIn.coreItem;
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 1.3, 0.5);
            matrixStackIn.scale(0.4f, 0.4f, 0.4f);
            matrixStackIn.mulPose(this.renderer.camera.rotation());
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            // matrixStack.pushPose();
            // RenderSystem.setShaderColor(1, 1.0f, 1, 0.8f);
            BakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntityIn.getLevel(), null, 0);
            itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            // matrixStack.popPose();
            matrixStackIn.popPose();
        }
    }
}
