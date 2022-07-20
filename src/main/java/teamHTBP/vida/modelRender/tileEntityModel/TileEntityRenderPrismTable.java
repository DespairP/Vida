package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.math.vector.Quaternion;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import java.util.Random;

public class TileEntityRenderPrismTable extends TileEntityRenderer<TileEntityPrismTable> {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    Random random = new Random();

    public TileEntityRenderPrismTable(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityPrismTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        //System.out.println(tileEntityIn.isFire);
        if (tileEntityIn.isGem) {
            if (tileEntityIn.getSlot().getItem(0) != ItemStack.EMPTY && !tileEntityIn.getSlot().getItem(0).isEmpty()) {
                ItemStack stack = tileEntityIn.getSlot().getItem(0);
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5, 0.6, 0.5);
                if (tileEntityIn.getBlockState().getValue(FACING) != null) {
                    switch (tileEntityIn.getBlockState().getValue(FACING)) {
                        case SOUTH:
                            matrixStackIn.mulPose(new Quaternion(90, 0, 0, true));
                            break;
                        case NORTH:
                            matrixStackIn.mulPose(new Quaternion(90, 0, 180, true));
                            break;
                        case EAST:
                            matrixStackIn.mulPose(new Quaternion(90, 0, 270, true));
                            break;
                        case WEST:
                            matrixStackIn.mulPose(new Quaternion(90, 180, 270, true));
                            break;
                    }
                } else {
                    matrixStackIn.mulPose(new Quaternion(90, 0, 0, true));
                }
                matrixStackIn.scale(0.5f, 0.5f, 0.5f);
                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntityIn.getLevel(), null);
                itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                matrixStackIn.popPose();
            }
        }
    }
}
