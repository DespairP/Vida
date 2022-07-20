package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.blockentity.TileEntityElementCoreAltar;

import java.util.Random;

public class TileEntityRenderElementCoreAltar extends BlockEntityRenderer<TileEntityElementCoreAltar> {
    int[] randomRotationY = {0, 0, 0, 0};
    int[] randomRotationZ = {0, 0, 0, 0};
    Random rand = new Random();

    public TileEntityRenderElementCoreAltar(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);


    }

    @Override
    public void render(TileEntityElementCoreAltar tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if (tileEntityIn.coreItem != ItemStack.EMPTY || !tileEntityIn.coreItem.isEmpty()) {
            matrixStackIn.pushPose();
            float degree = tileEntityIn.moveup * 90.0f / 0.8f;
            matrixStackIn.translate(0.5f, 0.7f + tileEntityIn.moveup + tileEntityIn.floating, 0.5f);
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            if (tileEntityIn.moveup < 0.8f)
                matrixStackIn.mulPose(new Quaternion(90 - degree, 0, 0, true));
            else
                matrixStackIn.mulPose(this.renderer.camera.rotation());
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.coreItem, tileEntityIn.getLevel(), null);
            itemRenderer.render(tileEntityIn.coreItem, ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            matrixStackIn.popPose();
        }
        for (int i = 0; i < 4; i++) {
            if (!tileEntityIn.getStack(i).isEmpty()) {
                if (randomRotationY[i] == 0) this.randomRotationY[i] = rand.nextInt();
                if (randomRotationZ[i] == 0) this.randomRotationZ[i] = rand.nextInt();
                matrixStackIn.pushPose();
                switch (i) {
                    case 0:
                        matrixStackIn.translate(0.2, 0.7 + tileEntityIn.moveup / 4.0 - tileEntityIn.floating / 2, 0.5);
                        break;
                    case 1:
                        matrixStackIn.translate(0.5, 0.7 + tileEntityIn.moveup / 4.0 - tileEntityIn.floating / 2, 0.2);
                        break;
                    case 2:
                        matrixStackIn.translate(0.5, 0.7 + tileEntityIn.moveup / 4.0 - tileEntityIn.floating / 2, 0.8);
                        break;
                    case 3:
                        matrixStackIn.translate(0.8, 0.7 + tileEntityIn.moveup / 4.0 - tileEntityIn.floating / 2, 0.5);
                        break;
                }
                matrixStackIn.mulPose(new Quaternion(90, 0, this.randomRotationZ[i], true));
                matrixStackIn.scale(0.3f, 0.3f, 0.3f);
                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.getStack(i), tileEntityIn.getLevel(), null);
                itemRenderer.render(tileEntityIn.getStack(i), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                matrixStackIn.popPose();
            }
        }

    }
}
