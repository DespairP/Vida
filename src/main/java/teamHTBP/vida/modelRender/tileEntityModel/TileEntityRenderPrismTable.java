package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import teamHTBP.vida.blockentity.TileEntityPrismTable;

import java.util.Random;

public class TileEntityRenderPrismTable extends ModBlockEntityRenderer<TileEntityPrismTable> {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    Random random = new Random();

    public TileEntityRenderPrismTable(BlockEntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public void render(TileEntityPrismTable tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
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
                BakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntityIn.getLevel(), null, 0);
                itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                matrixStackIn.popPose();
            }
        }
    }
}
