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
