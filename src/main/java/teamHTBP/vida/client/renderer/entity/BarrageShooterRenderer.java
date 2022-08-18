package teamHTBP.vida.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.entity.BallModel;
import teamHTBP.vida.common.entity.projectile.BarrageShooter;

/**
 * @author DustW
 */
public class BarrageShooterRenderer extends EntityRenderer<BarrageShooter> {
    BallModel model;

    public BarrageShooterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = LayerRegistryHandler.create(BallModel.class);
    }

    static final ResourceLocation TEXTURE = new ResourceLocation(Vida.MOD_ID, "textures/special/ball.png");

    @Override
    public void render(BarrageShooter entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource source, int packedLight) {
        VertexConsumer buffer = source.getBuffer(RenderType.entityTranslucent(TEXTURE));
        model.renderToBuffer(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    public ResourceLocation getTextureLocation(BarrageShooter pEntity) {
        return TEXTURE;
    }
}
