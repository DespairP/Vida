package teamHTBP.vida.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.mathHelper.Vector3;

import java.util.LinkedList;

public abstract class AbstractTailParticle extends Particle {
    /**轨道线*/
    protected final LinkedList<Vector3> tails;
    /**记录频次*/
    private final int frequency;
    /**最大线长度*/
    private final int maxTail;

    public AbstractTailParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.tails = new LinkedList<>();
        this.frequency = 1;
        this.maxAge = 100;
        maxTail = 15;
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        Tessellator tesselator = Tessellator.getInstance();
        Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(Vida.MOD_ID, "textures/particle/trail.png"));
        BufferBuilder builder = tesselator.getBuffer();
        builder.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

        Vector3[] verts = new Vector3[tails.size() * 2];
        double x = (MathHelper.lerp(partialTicks, this.prevPosX, this.posX));
        double y = (MathHelper.lerp(partialTicks, this.prevPosY, this.posY));
        double z = (MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ));
        Vector3 lastTail = new Vector3(x, y, z);
        Vector3 cameraPos = new Vector3(renderInfo.getProjectedView());
        int size = tails.size() - 1;
        for (int i = size; i >= 0; i--) {
            Vector3 tail = tails.get(i).copy();
            renderTail(verts, size - i, cameraPos, lastTail, tail, partialTicks);
            lastTail = tail;
        }
        for (int i = 0; i < (verts.length / 2) - 1; i++) {
            Vector3 currentU = verts[i * 2];
            Vector3 currentD = verts[i * 2 + 1];
            Vector3 nextU = verts[(i + 1) * 2];
            Vector3 nextD = verts[(i + 1) * 2 + 1];

            float u0 = this.getMinU(i, partialTicks);
            float u1 = this.getMaxU(i, partialTicks);
            float v0 = this.getMinV(i, partialTicks);
            float v1 = this.getMaxV(i, partialTicks);

            int j = this.getBrightnessForRender(partialTicks);

            buffer.pos(currentD.x, currentD.y, currentD.z).tex(u1, v0).color(this.particleRed, this.particleGreen, this.particleBlue,1).lightmap(j).endVertex();
            buffer.pos(currentU.x, currentU.y, currentU.z).tex(u1, v1).color(this.particleRed,this.particleGreen,this.particleBlue,1).lightmap(j).endVertex();
            buffer.pos(nextD.x, nextD.y, nextD.z).tex(u0, v0).color(this.particleRed,this.particleGreen,this.particleBlue,1).lightmap(j).endVertex();

            buffer.pos(nextD.x, nextD.y, nextD.z).tex(u0, v0).color(this.particleRed,this.particleGreen,this.particleBlue,1).lightmap(j).endVertex();
            buffer.pos(currentU.x, currentU.y, currentU.z).tex(u1, v1).color(this.particleRed,this.particleGreen,this.particleBlue,1).lightmap(15728880).endVertex();
            buffer.pos(nextU.x, nextU.y, nextU.z).tex(u0, v1).color(this.particleRed,this.particleGreen,this.particleBlue,1).lightmap(j).endVertex();
        }

        tesselator.draw();
    }


    @Override
    public IParticleRenderType getRenderType() {
        return new IParticleRenderType() {
            private final ResourceLocation TAIL = new ResourceLocation(Vida.MOD_ID, "textures/particle/trail.png");

            @Override
            public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
                RenderSystem.depthMask(true);
                textureManager.bindTexture(TAIL);
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                RenderSystem.alphaFunc(516, 0.003921569F);

            }

            @Override
            public void finishRender(Tessellator tesselator) {
                RenderSystem.depthMask(true);
            }

        };
    }

    @Override
    public void tick() {
        //记录轨道
        if (age % frequency == 0) {
            tails.add(getTail());
            while (tails.size() > maxTail) {
                tails.remove(0);
            }
        }
        //更新轨迹
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge && maxAge > 0) {
            this.setExpired();
            return;
        }
        this.update();
    }

    /**获得上一条轨道线*/
    public Vector3 getTail(){
        return new Vector3(this.prevPosX, this.prevPosY, this.prevPosZ);
    }

    /**计算轨道逻辑*/
    public void renderTail(Vector3[] verts, int i, Vector3 cameraPos, Vector3 current, Vector3 nextTail, float partialTicks) {
        float size = 0.3f;
        Vector3 direction = nextTail.copy().subtract(current);
        Vector3 toTail = current.copy().subtract(cameraPos);
        Vector3 normal = toTail.copy().crossProduct(direction).normalize();
        verts[i * 2] = current.copy().add(normal.copy().multiply(size)).subtract(cameraPos);
        verts[i * 2 + 1] = current.copy().add(normal.copy().multiply(-size)).subtract(cameraPos);
    }


    protected float getMinU(int tail, float partialTicks) {
        return  1 - (tail + 1 + partialTicks) / (maxTail - 1f);
    }

    protected float getMinV(int tail, float partialTicks) {
        return 0;
    }

    protected float getMaxU(int tail, float partialTicks) {
        return  1 - (tail + partialTicks) / (maxTail - 1f);
    }

    protected float getMaxV(int tail, float partialTicks) {
        return 1;
    }

    /**粒子运动函数*/
    public abstract void update();

    /**是否使用cull渲染*/
    @Override
    public boolean shouldCull() {
        return false;
    }
}
