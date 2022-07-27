package teamHTBP.vida.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

import static teamHTBP.vida.helper.mathHelper.Vector3dHelper.copy;
public class TrailParticle extends SpriteTexturedParticle {
    /**轨道线*/
    private final LinkedList<Vector3d> tails;
    /**记录频次*/
    private final int frequency;
    /**最大线长度*/
    private final int maxTail;

    public TrailParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.tails = new LinkedList<>();
        this.frequency = 1;
        this.maxAge = 100;
        maxTail = 15;
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        Vector3d[] verts = new Vector3d[tails.size() * 2];
        double x = (MathHelper.lerp(partialTicks, this.prevPosX, this.posX));
        double y = (MathHelper.lerp(partialTicks, this.prevPosY, this.posY));
        double z = (MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ));
        Vector3d lastTail = new Vector3d(x, y, z);
        Vector3d cameraPos = renderInfo.getProjectedView();
        int size = tails.size() - 1;
        for (int i = size; i >= 0; i--) {
            Vector3d tail = copy(tails.get(i));
            renderTail(verts, size - i, cameraPos, lastTail, tail, partialTicks);
            lastTail = tail;
        }
        for (int i = 0; i < verts.length; i++) {
            Vector3d vector3d = verts[i];
            verts[i] = vector3d.add(x, y, z);
        }
        for (int i = 0; i < (verts.length / 2) - 1; i++) {
            Vector3d currentU = verts[i * 2];
            Vector3d currentD = verts[i * 2 + 1];
            Vector3d nextU = verts[(i + 1) * 2];
            Vector3d nextD = verts[(i + 1) * 2 + 1];

            float minU = this.getMinU();
            float maxU = this.getMaxU();
            float minV = this.getMinV();
            float maxV = this.getMaxV();

            int j = this.getBrightnessForRender(partialTicks);

            buffer.pos(currentD.x, currentD.y, currentD.z).tex(maxU, minV).color(1,1,1,1).lightmap(j).endVertex();
            buffer.pos(currentU.x, currentU.y, currentU.z).tex(maxU, maxV).color(1,1,1,1).lightmap(j).endVertex();
            buffer.pos(nextD.x, nextD.y, nextD.z).tex(minU, minV).color(1,1,1,1).lightmap(j).endVertex();

            buffer.pos(nextD.x, nextD.y, nextD.z).tex(minU, minV).color(1,1,1,1).lightmap(j).endVertex();
            buffer.pos(currentU.x, currentU.y, currentU.z).tex(maxU, maxV).color(1,1,1,1).lightmap(j).endVertex();
            buffer.pos(nextU.x, nextU.y, nextU.z).tex(minU, maxV).color(1,1,1,1).lightmap(j).endVertex();
        }

    }





    @Override
    public IParticleRenderType getRenderType() {
        return new IParticleRenderType() {
            @Override
            public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
                RenderSystem.depthMask(true);
                textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                RenderSystem.alphaFunc(516, 0.003921569F);
                bufferBuilder.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            }

            @Override
            public void finishRender(Tessellator tesselator) {
                tesselator.draw();
            }
        };
    }

    @Override
    public void tick() {
        //记录轨道
        if (age % frequency == 0) {
            tails.add(getTail());
            //移除多余的轨道
            while (tails.size() > maxTail) {
                tails.remove(0);
            }
        }
        //expire
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            //如果没有expire,继续移动
            this.motionY -= 0.04D * (double)this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)0.98F;
            this.motionY *= (double)0.98F;
            this.motionZ *= (double)0.98F;
            if (this.onGround) {
                this.motionX *= (double)0.7F;
                this.motionZ *= (double)0.7F;
            }

        }
    }

    /**获得上一条轨道线*/
    public Vector3d getTail(){
        return new Vector3d(this.prevPosX, this.prevPosY, this.prevPosZ);
    }

    /**计算轨道逻辑*/
    public void renderTail(Vector3d[] verts, int i, Vector3d cameraPos, Vector3d current, Vector3d nextTail, float partialTicks) {
        float size = 0.15f;
        Vector3d direction = copy(nextTail).subtract(current);
        Vector3d toTail = copy(current).subtract(cameraPos);
        Vector3d normal = copy(toTail).crossProduct(direction).normalize();
        verts[i * 2] = copy(current).add(copy(normal).scale(size)).subtract(cameraPos);
        verts[i * 2 + 1] = copy(current).add(copy(normal).scale(-size)).subtract(cameraPos);
    }

}
