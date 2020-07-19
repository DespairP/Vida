package teamHTBP.vida.Entity.entityRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import teamHTBP.vida.Entity.EntityFaintLight;
import teamHTBP.vida.Entity.entityModel.EntityModelFaintLight;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.Vida;

public class EntityRenderFaintLight extends EntityRenderer<EntityFaintLight> {
    private EntityModel<EntityFaintLight> model;


    protected EntityRenderFaintLight(EntityRendererManager renderManager) {
        super(renderManager);
        model = new EntityModelFaintLight();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityFaintLight entity) {
        switch(entity.getFaintLightType()){
            case 1:
                return new ResourceLocation(Vida.modId, "textures/model/faintlight/gold/fire_"+entity.meta +".png");
            case 2:
                return new ResourceLocation(Vida.modId, "textures/model/faintlight/wood/fire_"+entity.meta +".png");
            case 3:
                return new ResourceLocation(Vida.modId, "textures/model/faintlight/aqua/fire_"+entity.meta +".png");
            case 4:
                return new ResourceLocation(Vida.modId, "textures/model/faintlight/fire/fire_"+entity.meta +".png");
            case 5:
                return new ResourceLocation(Vida.modId, "textures/model/faintlight/earth/fire_"+entity.meta +".png");
        }
        return new ResourceLocation(Vida.modId, "textures/model/faintlight/gold/fire_"+entity.meta +".png");
    }




    public void render(EntityFaintLight entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        Quaternion quaternion = this.getRenderManager().info.getRotation();
        quaternion.multiply(Vector3f.XP.rotation(0));
        matrixStackIn.translate(0.6, 0.6, 0);
        //matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        matrixStackIn.rotate(quaternion);
        float r = 1;
        float g = 1;
        float b = 1;
        float a = 1;

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getText(this.getEntityTexture(entityIn)));

                model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, r, g, b, a / 2);

        matrixStackIn.pop();

    }

    protected int getBrightnessForRender(EntityFaintLight entityIn,float partialTick) {
        BlockPos blockpos = new BlockPos(entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ);
        return entityIn.world.isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(entityIn.world, blockpos) : 0;
    }
}
