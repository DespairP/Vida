package teamHTBP.vida.entity.entityRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.entityModel.EntityModelFaintLight;
import teamHTBP.vida.modelRender.RenderLoader;

public class EntityRenderFaintLight extends EntityRenderer<EntityFaintLight> {
    private final EntityModel<EntityFaintLight> model;


    protected EntityRenderFaintLight(EntityRendererManager renderManager) {
        super(renderManager);
        model = new EntityModelFaintLight();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityFaintLight entity) {
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
    public void render(EntityFaintLight entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        Quaternion quaternion = this.getRenderManager().info.getRotation();
        quaternion.multiply(Vector3f.XP.rotation(0));
        matrixStackIn.translate(0.6, 0.6, 0);
        //matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        matrixStackIn.rotate(quaternion);
        matrixStackIn.translate(-0.5f, -0.5F, -0.5f);

        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(this.getEntityTexture(entityIn));


        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getCutout());

        //model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, a / 2);

        float MaxU = atlasTexture.getMaxU();
        float MinU = atlasTexture.getMinU();
        float MaxV = atlasTexture.getMaxV();
        float MinV = atlasTexture.getMinV();

        Matrix4f matrixStack = matrixStackIn.getLast().getMatrix();

        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 0.7f).tex(MinU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 0.7f).tex(MaxU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 0.7f).tex(MaxU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 0.7f).tex(MinU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(MinU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 1.0f).tex(MaxU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(MaxU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(MinU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.pop();

    }

    protected int getBrightnessForRender(EntityFaintLight entityIn, float partialTick) {
        BlockPos blockpos = new BlockPos(entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ);
        return entityIn.world.isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(entityIn.world, blockpos) : 0;
    }
}
