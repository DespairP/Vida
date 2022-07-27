package teamHTBP.vida.client.modelRender.entityRender;

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
import teamHTBP.vida.common.entity.EntityFaintLight;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.client.modelRender.RenderLoader;
import teamHTBP.vida.client.modelRender.entityModel.EntityModelFaintLight;

public class EntityRenderFaintLight extends EntityRenderer<EntityFaintLight> {
    private final EntityModel<EntityFaintLight> model;


    public EntityRenderFaintLight(EntityRendererManager renderManager) {
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
        Quaternion quaternion = getRenderManager().info.getRotation();
        //quaternion.multiply(Vector3f.XP.rotation(0));
        matrixStackIn.translate(0.5, 0.5, 0);
        //matrixStackIn.rotate(quaternion);
        //matrixStackIn.translate(-0.5f, -0.5F, -0.5f);


        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(this.getEntityTexture(entityIn));


        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getTranslucent());

        //model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, a / 2);


        float MaxU = atlasTexture.getMaxU();
        float MinU = atlasTexture.getMinU();
        float MaxV = atlasTexture.getMaxV();
        float MinV = atlasTexture.getMinV();

        Matrix4f matrixStack = matrixStackIn.getLast().getMatrix();
        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f4 = 0.3f;

        for(int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            //vector3f.add(f, f1, f2);
        }

        //正反面渲染
        builder.pos(matrixStack, avector3f[0].getX(), avector3f[0].getY(), avector3f[0].getZ()).color(1, 1, 1, 0.7f).tex(MinU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[1].getX(), avector3f[1].getY(), avector3f[1].getZ()).color(1, 1, 1, 0.7f).tex(MaxU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[2].getX(), avector3f[2].getY(), avector3f[2].getZ()).color(1, 1, 1, 0.7f).tex(MaxU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[3].getX(), avector3f[3].getY(), avector3f[3].getZ()).color(1, 1, 1, 0.7f).tex(MinU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, avector3f[3].getX(), avector3f[3].getY(), avector3f[3].getZ()).color(1, 1, 1, 0.7f).tex(MinU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[2].getX(), avector3f[2].getY(), avector3f[2].getZ()).color(1, 1, 1, 0.7f).tex(MaxU, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[1].getX(), avector3f[1].getY(), avector3f[1].getZ()).color(1, 1, 1, 0.7f).tex(MaxU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, avector3f[0].getX(), avector3f[0].getY(), avector3f[0].getZ()).color(1, 1, 1, 0.7f).tex(MinU, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.pop();

    }

    protected int getBrightnessForRender(EntityFaintLight entityIn, float partialTick) {
        BlockPos blockpos = new BlockPos(entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ);
        return entityIn.world.isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(entityIn.world, blockpos) : 0;
    }
}
