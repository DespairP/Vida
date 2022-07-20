package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.item.SwordItem;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityInjectTable;
import teamHTBP.vida.event.client.ClientTickHandler;
import teamHTBP.vida.modelRender.tilemodel.InjectTableModel;

public class TileEntityRenderInjectTable extends BlockEntityRenderer<TileEntityInjectTable> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Vida.MOD_ID, "textures/tileentity/injecttable.png");
    public static final InjectTableModel MODEL = new InjectTableModel();

    float tick(float partTicks) {
        return ClientTickHandler.tick() + partTicks;
    }

    double sinWave(float partTicks) {
        return (tick(partTicks) * 0.1) % (Math.PI * 2);
    }


    public TileEntityRenderInjectTable(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityInjectTable tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        VertexConsumer iVertexBuilder = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

        matrixStackIn.pushPose();
        matrixStackIn.scale(1, -1, 1);
        matrixStackIn.translate(0.5, 0, 0.5);

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(tick(partialTicks)));
        int light = LightTexture.pack(10, 10);
        MODEL.rotateCube.render(matrixStackIn, iVertexBuilder, light, combinedOverlayIn);
        matrixStackIn.popPose();
        MODEL.renderToBuffer(matrixStackIn, iVertexBuilder, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);

        matrixStackIn.popPose();

        if (tileEntityIn.hasSwordItem() && tileEntityIn.getSwordStack().getItem() instanceof SwordItem) {
            matrixStackIn.pushPose();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.mulPose(new Quaternion(0, 0, 180 - 45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.getSwordStack(), tileEntityIn.getLevel(), null);
            itemRenderer.render(tileEntityIn.getSwordStack(), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.popPose();
        } else if (tileEntityIn.hasSwordItem()) {
            matrixStackIn.pushPose();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.mulPose(new Quaternion(0, 0, -45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.getSwordStack(), tileEntityIn.getLevel(), null);
            itemRenderer.render(tileEntityIn.getSwordStack(), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.popPose();
        }

    }
}
