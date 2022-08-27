package teamHTBP.vida.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.client.screen.Renders;
import teamHTBP.vida.client.shader.Shaders;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class PlayerRenderListener {
    static void vertices(Matrix4f m4, BufferBuilder builder, float sX, float eX, float sY, float eY) {
        builder.vertex(m4, sX, eY, 0).endVertex();
        builder.vertex(m4, eX, eY, 0).endVertex();
        builder.vertex(m4, eX, sY, 0).endVertex();
        builder.vertex(m4, sX, sY, 0).endVertex();

        builder.vertex(m4, sX, eY, 0).endVertex();
        builder.vertex(m4, sX, sY, 0).endVertex();
        builder.vertex(m4, eX, sY, 0).endVertex();
        builder.vertex(m4, eX, eY, 0).endVertex();
    }

    static int renderShaderToBuffer(float partialTick) {
        RenderTarget temp = Renders.temp();
        temp.bindWrite(false);

        ShaderInstance shader = Shaders.glowEdge;
        RenderSystem.setShader(() -> shader);

        float len = temp.height;
        Shaders.setUniforms(shader, new Shaders.Point2f(len, len), new Shaders.Point2f(len, 20), partialTick);

        BufferBuilder builder = Tesselator.getInstance().getBuilder();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        Matrix4f m4 = new Matrix4f();
        m4.setIdentity();

        Matrix4f backup = RenderSystem.getProjectionMatrix();
        RenderSystem.setProjectionMatrix(m4);

        vertices(m4, builder, 0, len, 0, len);

        builder.end();
        BufferUploader._endInternal(builder);
        RenderSystem.setProjectionMatrix(backup);

        return temp.getColorTextureId();
    }

    @SubscribeEvent
    public static void onEvent(RenderPlayerEvent.Post event) {
        PoseStack poseStack = event.getPoseStack();
        float partialTick = event.getPartialTick();
        Player player = event.getPlayer();

        Minecraft mc = Minecraft.getInstance();
        RenderTarget main = mc.getMainRenderTarget();

        int colorTextureId = renderShaderToBuffer(partialTick);

        main.bindWrite(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, colorTextureId);

        poseStack.pushPose();
        Matrix4f m4 = poseStack.last().pose();
        poseStack.mulPose(Vector3f.YN.rotationDegrees(player.getYRot()));
        poseStack.scale(1.5f, 0, 1.5f);
        poseStack.translate(-.5, 0, -.5);

        BufferBuilder builder = Tesselator.getInstance().getBuilder();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        builder.vertex(m4, 0, 0, 1).uv(0, 0).endVertex();
        builder.vertex(m4, 1, 0, 1).uv(1, 0).endVertex();
        builder.vertex(m4, 1, 0, 0).uv(1, 1).endVertex();
        builder.vertex(m4, 0, 0, 0).uv(0, 1).endVertex();

        builder.vertex(m4, 0, 0, 1).uv(0, 1).endVertex();
        builder.vertex(m4, 0, 0, 0).uv(0, 0).endVertex();
        builder.vertex(m4, 1, 0, 0).uv(1, 0).endVertex();
        builder.vertex(m4, 1, 0, 1).uv(1, 1).endVertex();

        builder.end();
        BufferUploader.end(builder);

        poseStack.popPose();

        RenderSystem.disableBlend();
    }
}
