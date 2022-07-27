package teamHTBP.vida.client.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import teamHTBP.vida.common.TileEntity.TileEntityInjectTable;
import teamHTBP.vida.Vida;
import teamHTBP.vida.event.client.ClientTickHandler;
import teamHTBP.vida.client.modelRender.tilemodel.InjectTableModel;

public class TileEntityRenderInjectTable extends TileEntityRenderer<TileEntityInjectTable> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Vida.MOD_ID, "textures/tileentity/injecttable.png");
    public static final InjectTableModel MODEL = new InjectTableModel();

    float tick(float partTicks) {
        return ClientTickHandler.tick() + partTicks;
    }

    double sinWave(float partTicks) {
        return (tick(partTicks) * 0.1) % (Math.PI * 2);
    }


    public TileEntityRenderInjectTable(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityInjectTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        IVertexBuilder iVertexBuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(TEXTURE));

        matrixStackIn.push();
        matrixStackIn.scale(1, -1, 1);
        matrixStackIn.translate(0.5, 0, 0.5);

        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(tick(partialTicks)));
        int light = LightTexture.packLight(10, 10);
        MODEL.rotateCube.render(matrixStackIn, iVertexBuilder, light, combinedOverlayIn);
        matrixStackIn.pop();
        MODEL.render(matrixStackIn, iVertexBuilder, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);

        matrixStackIn.pop();

        if (tileEntityIn.hasSwordItem() && tileEntityIn.getSwordStack().getItem() instanceof SwordItem) {
            matrixStackIn.push();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.rotate(new Quaternion(0, 0, 180 - 45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.getSwordStack(), tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.getSwordStack(), ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        } else if (tileEntityIn.hasSwordItem()) {
            matrixStackIn.push();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.rotate(new Quaternion(0, 0, -45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.getSwordStack(), tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.getSwordStack(), ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        }

    }
}
