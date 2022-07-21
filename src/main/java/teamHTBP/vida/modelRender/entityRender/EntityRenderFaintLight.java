package teamHTBP.vida.modelRender.entityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.modelRender.LayerRegistryHandler;
import teamHTBP.vida.modelRender.RenderLoader;
import teamHTBP.vida.modelRender.entityModel.EntityModelFaintLight;

public class EntityRenderFaintLight extends EntityRenderer<EntityFaintLight> {
    private final EntityModel<EntityFaintLight> model;


    public EntityRenderFaintLight(Context renderManager) {
        super(renderManager);
        model = LayerRegistryHandler.create(EntityModelFaintLight.class);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFaintLight entity) {
        if (entity.getFaintLightType() instanceof EnumElements) {
            switch ((EnumElements) entity.getFaintLightType()) {
                case GOLD:
                    return RenderLoader.goldFaintLightLocation;
                case WOOD:
                    return RenderLoader.woodFaintLightLocation;
                case AQUA:
                    return RenderLoader.aquaFaintLightLocation;
                case FIRE:
                    return RenderLoader.fireFaintLightLocation;
                case EARTH:
                    return RenderLoader.earthFaintLightLocation;
            }
        }
        return RenderLoader.earthFaintLightLocation;
    }


    @Override
    public void render(EntityFaintLight entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        //super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        Quaternion quaternion = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
        //quaternion.multiply(Vector3f.XP.rotation(0));
        matrixStackIn.translate(0.5, 0.5, 0);
        //matrixStackIn.rotate(quaternion);
        //matrixStackIn.translate(-0.5f, -0.5F, -0.5f);


        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(this.getTextureLocation(entityIn));


        VertexConsumer builder = bufferIn.getBuffer(RenderType.translucent());

        //model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, a / 2);


        float MaxU = atlasTexture.getU1();
        float MinU = atlasTexture.getU0();
        float MaxV = atlasTexture.getV1();
        float MinV = atlasTexture.getV0();

        Matrix4f matrixStack = matrixStackIn.last().pose();
        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f4 = 0.3f;

        for(int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            //vector3f.add(f, f1, f2);
        }

        //正反面渲染
        builder.vertex(matrixStack, avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).color(1, 1, 1, 0.7f).uv(MinU, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).color(1, 1, 1, 0.7f).uv(MaxU, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).color(1, 1, 1, 0.7f).uv(MaxU, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).color(1, 1, 1, 0.7f).uv(MinU, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).color(1, 1, 1, 0.7f).uv(MinU, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).color(1, 1, 1, 0.7f).uv(MaxU, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).color(1, 1, 1, 0.7f).uv(MaxU, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).color(1, 1, 1, 0.7f).uv(MinU, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.popPose();

    }
}
