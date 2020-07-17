package teamHTBP.vida.Entity.entityRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import teamHTBP.vida.Entity.EntityFaintLight;
import teamHTBP.vida.Entity.entityModel.EntityModelFaintLight;
import teamHTBP.vida.Vida;

public class EntityRenderFaintLight extends EntityRenderer<EntityFaintLight> {
    private EntityModel<EntityFaintLight> model;
    private int prevCounter = 0;
    private int currentCounter  = 0;
    private int meta = 0;


    protected EntityRenderFaintLight(EntityRendererManager renderManager) {
        super(renderManager);
        model = new EntityModelFaintLight();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityFaintLight entity) {
        return new ResourceLocation(Vida.modId, "textures/model/fire_"+ meta  +".png");
    }


    public ResourceLocation getEntityTexture(EntityFaintLight entity,int meta) {
        return new ResourceLocation(Vida.modId, "textures/model/fire_"+ meta  +".png");
    }

    public void render(EntityFaintLight entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();

        Quaternion quaternion = this.renderManager.info.getRotation();
        currentCounter += 1;
        int counter = 0;
           if(currentCounter<=20) {
                   counter =(int)( currentCounter + (currentCounter - prevCounter) * partialTicks);
               }
           else {
                   currentCounter=0;
               }
        prevCounter =currentCounter;
           if(counter == 20){
               this.meta ++;
               if(this.meta > 29) this.meta = 0;
           }
        float f3 = MathHelper.lerp(partialTicks, 0, 0);
        quaternion.multiply(Vector3f.ZN.rotation(f3));
        RenderHelper.disableStandardItemLighting();
        matrixStackIn.rotate(quaternion);
        int j = this.getBrightnessForRender(entityIn,partialTicks);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getText(this.getEntityTexture(entityIn,meta)));
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
        matrixStackIn.pop();

    }

    protected int getBrightnessForRender(EntityFaintLight entityIn,float partialTick) {
        BlockPos blockpos = new BlockPos(entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ);
        return entityIn.world.isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(entityIn.world, blockpos) : 0;
    }
}
