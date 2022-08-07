package teamHTBP.vida.common.particle.particle;

import com.lowdragmc.lowdraglib.client.shader.Shaders;
import com.lowdragmc.lowdraglib.client.shader.management.Shader;
import com.lowdragmc.lowdraglib.client.shader.management.ShaderManager;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.ModsInfo;
import teamHTBP.vida.helper.color.ARGBColor;
import teamHTBP.vida.helper.math.Vector3;
import teamHTBP.vida.helper.math.curve.Bezier3Curve;

import javax.annotation.Nonnull;

/**
 * @author DustW
 */
public class ElementTailParticle extends AbstractTailParticle {

    private final Bezier3Curve curve;
    private boolean needHead;
    private ElementHeadParticle headParticle;
    private final ARGBColor color;

    public ElementTailParticle(ClientLevel level, Bezier3Curve curve, int dur, ARGBColor color, boolean needHead) {
        this(level, curve, dur, color);

        this.needHead = needHead;

        if (needHead) {
            headParticle = new ElementHeadParticle(level, curve.pos0.x, curve.pos0.y, curve.pos0.z);
            headParticle.setLifetime(dur);
            headParticle.scale(0.22f);
            headParticle.setColor(color.getR() / 256F, color.getG() / 256F, color.getB() / 256F);
        }
    }

    public ElementTailParticle(ClientLevel level, Bezier3Curve curve, int dur, ARGBColor color) {
        super(level, curve.pos0.x, curve.pos0.y, curve.pos0.z);
        setLifetime(dur + 15);
        setLight(0xf000f0);
        this.curve = curve;
        maxTail = 15;
        width = 0.3f;
        this.lifetime = dur;
        this.color = color;
    }

    Minecraft mc = Minecraft.getInstance();

    @Override
    protected void update() {
        if (needHead)
            headParticle.tick();

        if (getAge() > 0) {
            var t = (mc.getFrameTime() + age) / lifetime;
            Vector3 point = curve.getPoint(t);
            setPos(point.x, point.y, point.z);

            if (needHead)
                headParticle.setPos(point.x, point.y, point.z);
        }

        if (this.lifetime - this.age < 15) {
            tails.remove(0);
        }
    }

    @Override
    public void render(@NotNull VertexConsumer pBuffer, @NotNull Camera camera, float partialTicks) {
        int texture = RenderSystem.getShaderTexture(0);
        Tesselator tesselator = Tesselator.getInstance();
        var builder = tesselator.getBuilder();

        if (needHead) {
            RenderSystem.setShader(GameRenderer::getParticleShader);
            headParticle.getRenderType().begin(builder, null);
            RenderSystem.depthMask(false);
            headParticle.render(builder, camera, partialTicks);
            headParticle.getRenderType().end(tesselator);
            RenderSystem.setShaderTexture(0, texture);
        }

        RenderSystem.setShader(GameRenderer::getParticleShader);
        builder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.PARTICLE);
        super.render(pBuffer, camera, partialTicks);
        tesselator.end();
    }

    @Override
    @Nonnull
    public ParticleRenderType getRenderType() {
        return type;
    }

    static final ResourceLocation KILA_TAIL = new ResourceLocation("ldlib:textures/particle/kila_tail.png");
    static final ResourceLocation TAIL = new ResourceLocation(Vida.MOD_ID, "textures/particle/trail.png");

    static final ResourceLocation TAIL_SHADER = new ResourceLocation(Vida.MOD_ID, "tail");

    protected final ParticleRenderType type = new ParticleRenderType() {
        @Override
        public void begin(@NotNull BufferBuilder bufferBuilder, @NotNull TextureManager textureManager) {
            if (ModsInfo.LDL_LOADED) {
                RenderTarget mainTarget = Minecraft.getInstance().getMainRenderTarget();
                int lastID = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING);

                TextureTarget tempTarget = ShaderManager.getTempTarget();
                ShaderManager manager = ShaderManager.getInstance();

                tempTarget.clear(false);
                RenderTarget target = manager.renderFullImageInFramebuffer(tempTarget,
                        Shaders.load(Shader.ShaderType.FRAGMENT, TAIL_SHADER),
                        uniformCache -> {
                            uniformCache.glUniform4F("color1", color.getR() / 256F, color.getG() / 256F, color.getB() / 256F, 1);
                            uniformCache.glUniform4F("color2", 1, 1, 1, 1);
                        },
                        shaderProgram -> shaderProgram.bindTexture("iChannel0", KILA_TAIL));

                GlStateManager._glBindFramebuffer(36160, lastID);

                if (!manager.hasViewPort()) {
                    GlStateManager._viewport(0, 0, mainTarget.viewWidth, mainTarget.viewHeight);
                }

                setShader();
                RenderSystem.setShaderTexture(0, target.getColorTextureId());
            }
            else {
                setShader();
                RenderSystem.setShaderTexture(0, TAIL);
            }

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(true);
            RenderSystem.enableCull();
        }

        void setShader() {
            RenderSystem.setShader(GameRenderer::getParticleShader);
        }

        @Override
        public void end(@Nonnull Tesselator tesselator) {
            RenderSystem.depthMask(true);
        }
    };

    @Override
    public void addParticle() {
        if (ModsInfo.SHIMMER_LOADED) {
            PostProcessing.BLOOM_UNREAL.postParticle(this);
        }
        else {
            super.addParticle();
        }
    }
}
