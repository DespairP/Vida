package teamHTBP.vida.client.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.DirectionProperty;
import teamHTBP.vida.common.TileEntity.TileEntityPrismTable;

import java.util.Random;

public class TileEntityRenderPrismTable extends TileEntityRenderer<TileEntityPrismTable> {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    Random random = new Random();

    public TileEntityRenderPrismTable(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityPrismTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        //System.out.println(tileEntityIn.isFire);
        if (tileEntityIn.isGem) {

        }
    }
}
