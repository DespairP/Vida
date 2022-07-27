package teamHTBP.vida.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;

public class WispParticle extends SpriteTexturedParticle {

    public WispParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, float r, float g, float b, float scale, int age) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        RenderSystem.pushMatrix();

        RenderSystem.translatef((float) posX, (float) posY + 0.1f, (float) posZ);

        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);
        RenderSystem.disableLighting();


        float alpha = 1f;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);



        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        int i = age % maxAge / 2;
        float minU = (i / 5 * 16) / 80f;
        float minV = (i % 4 * 16) / 80f;
        float maxU = (i / 5 * 16 + 16) / 80f;
        float maxV = (i % 4 * 16 + 16) / 80f;

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex( maxU,  maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex( minU,  maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex( minU,  minV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex(maxU,  minV).normal(0.0F, 1.0F, 0.0F).endVertex();

        tessellator.draw();

        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();
        RenderSystem.disableRescaleNormal();
        RenderSystem.enableLighting();
        RenderSystem.popMatrix();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
