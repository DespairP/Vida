package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import teamHTBP.vida.Block.function.BlockPrismTable;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import java.util.Random;

public class TileEntityRenderPrismTable extends TileEntityRenderer<TileEntityPrismTable> {
    Random random = new Random();
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public TileEntityRenderPrismTable(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityPrismTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        //System.out.println(tileEntityIn.isFire);
        if(tileEntityIn.isGem){
            if(tileEntityIn.getSlot().getStackInSlot(0) != ItemStack.EMPTY && !tileEntityIn.getSlot().getStackInSlot(0).isEmpty()) {
                ItemStack stack  = tileEntityIn.getSlot().getStackInSlot(0);
                matrixStackIn.push();
                matrixStackIn.translate(0.5, 0.6, 0.5);
                if(tileEntityIn.getBlockState().get(FACING) != null) {
                    switch (tileEntityIn.getBlockState().get(FACING)) {
                        case SOUTH:
                            matrixStackIn.rotate(new Quaternion(90, 0, 0, true));
                            break;
                        case NORTH:
                            matrixStackIn.rotate(new Quaternion(90, 0, 180, true));
                            break;
                        case EAST:
                            matrixStackIn.rotate(new Quaternion(90, 0, 270, true));
                            break;
                        case WEST:
                            matrixStackIn.rotate(new Quaternion(90, 180, 270, true));
                            break;
                    }
                }else{
                    matrixStackIn.rotate(new Quaternion(90, 0, 0, true));
                }
                matrixStackIn.scale(0.5f, 0.5f, 0.5f);
                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
                itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                matrixStackIn.pop();
            }
        }
    }
}
