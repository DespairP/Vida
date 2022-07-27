package teamHTBP.vida.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SwordItem;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.blockentity.InjectTableBlockEntity;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.blockentity.InjectTableModel;
import teamHTBP.vida.client.event.listener.ClientTickHandler;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;

public class InjectTableRenderer extends VidaBaseBlockEntityRenderer<InjectTableBlockEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Vida.MOD_ID, "textures/tileentity/injecttable.png");
    public static InjectTableModel MODEL;

    public InjectTableRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    float tick(float partTicks) {
        return ClientTickHandler.tick() + partTicks;
    }

    double sinWave(float partTicks) {
        return (tick(partTicks) * 0.1) % (Math.PI * 2);
    }

    InjectTableModel model() {
        return MODEL == null ? MODEL = LayerRegistryHandler.create(InjectTableModel.class) : MODEL;
    }

    @Override
    public void render(InjectTableBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        VertexConsumer iVertexBuilder = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

        matrixStackIn.pushPose();
        matrixStackIn.scale(1, -1, 1);
        matrixStackIn.translate(0.5, 0, 0.5);

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(tick(partialTicks)));
        int light = LightTexture.pack(10, 10);
        model().rotateCube.render(matrixStackIn, iVertexBuilder, light, combinedOverlayIn);
        matrixStackIn.popPose();
        model().renderToBuffer(matrixStackIn, iVertexBuilder, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);

        matrixStackIn.popPose();

        if (tileEntityIn.hasSwordItem() && tileEntityIn.getSwordStack().getItem() instanceof SwordItem) {
            matrixStackIn.pushPose();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.mulPose(new Quaternion(0, 0, 180 - 45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.getSwordStack(), tileEntityIn.getLevel(), null, 0);
            itemRenderer.render(tileEntityIn.getSwordStack(), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.popPose();
        } else if (tileEntityIn.hasSwordItem()) {
            matrixStackIn.pushPose();
            double floating = 0.12 * Math.sin(sinWave(partialTicks));
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.mulPose(new Quaternion(0, 0, -45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.getSwordStack(), tileEntityIn.getLevel(), null, 0);
            itemRenderer.render(tileEntityIn.getSwordStack(), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.popPose();
        }

    }
}
