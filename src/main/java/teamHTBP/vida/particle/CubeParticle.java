package teamHTBP.vida.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Random;

/**
 * 尝试一下3D粒子
 *
 * @Version 0.0.1
 **/
public class CubeParticle extends SpriteTexturedParticle {

    private int rotationType = 1;

    protected CubeParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        this.canCollide = true;
        motionX = speedX;
        motionY = speedY;
        motionZ = speedZ;
        particleScale = 0.1f;
        this.maxAge = 100;

    }

    protected CubeParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, float r, float g, float b, float scale) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        this.canCollide = true;
        motionX = speedX;
        motionY = speedY;
        motionZ = speedZ;
        particleScale = scale;
        this.maxAge = 100;
        this.particleRed = r / 255.0f;
        this.particleGreen = g / 255.0f;
        this.particleBlue = b / 255.0f;
        this.rotationType = new Random().nextInt(10) + 1;
        if (this.particleScale == 0.03f) {
            this.particleAlpha = 0.7f;
        }
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {

        Vector3d vec3d = renderInfo.getProjectedView();
        float f = (float) (MathHelper.lerp(partialTicks, this.prevPosX, this.posX) - vec3d.getX());
        float f1 = (float) (MathHelper.lerp(partialTicks, this.prevPosY, this.posY) - vec3d.getY());
        float f2 = (float) (MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());


        Quaternion quaternion = new Quaternion(renderInfo.getRotation());
        switch (rotationType) {
            case 1:
                quaternion = new Quaternion(this.particleAngle, this.particleAngle, 0, true);
                break;
            case 2:
                quaternion = new Quaternion(this.particleAngle, this.particleAngle, this.particleAngle, true);
                break;
            case 3:
                quaternion = new Quaternion(this.particleAngle, 0, this.particleAngle, true);
                break;
            case 4:
                quaternion = new Quaternion(0, this.particleAngle, this.particleAngle, true);
                break;
            case 5:
                quaternion = new Quaternion(0, this.particleAngle, 0, true);
                break;
            case 6:
                quaternion = new Quaternion(0, this.particleAngle, this.particleAngle, true);
                break;
            case 7:
                quaternion = new Quaternion(0, -this.particleAngle, -this.particleAngle, true);
                break;
            case 8:
                quaternion = new Quaternion(-this.particleAngle, -this.particleAngle, this.particleAngle, true);
                break;
            case 9:
                quaternion = new Quaternion(-this.particleAngle, -this.particleAngle, -this.particleAngle, true);
                break;
            case 10:
                quaternion = new Quaternion(0, this.particleAngle, -this.particleAngle, true);
                break;
        }

        Vector3f[] avector3f = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 2.0F), new Vector3f(-1.0F, -1.0F, 2.0F),
                new Vector3f(-1.0F, -1.0F, 2.0F), new Vector3f(-1.0F, 1.0F, 2.0F), new Vector3f(1.0F, 1.0F, 2.0F), new Vector3f(1.0F, -1.0F, 2.0F),
                new Vector3f(1.0F, -1.0F, 2.0F), new Vector3f(1.0F, 1.0F, 2.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 2.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 2.0F),
                new Vector3f(-1.0F, -1.0F, 2.0F), new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 2.0F)

        };
        float f4 = this.getScale(partialTicks);

        for (int i = 0; i < 24; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            vector3f.add(f, f1, f2);
        }

        float f7 = this.getMinU();
        float f8 = this.getMaxU();
        float f5 = this.getMinV();
        float f6 = this.getMaxV();
        int j = this.getBrightnessForRender(partialTicks);
        //下面
        buffer.pos(avector3f[20].getX(), avector3f[20].getY(), avector3f[20].getZ()).tex(f8, f6).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[21].getX(), avector3f[21].getY(), avector3f[21].getZ()).tex(f8, f5).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[22].getX(), avector3f[22].getY(), avector3f[22].getZ()).tex(f7, f5).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[23].getX(), avector3f[23].getY(), avector3f[23].getZ()).tex(f7, f6).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();

        //背面
        buffer.pos(avector3f[0].getX(), avector3f[0].getY(), avector3f[0].getZ()).tex(f8, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[1].getX(), avector3f[1].getY(), avector3f[1].getZ()).tex(f8, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[2].getX(), avector3f[2].getY(), avector3f[2].getZ()).tex(f7, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[3].getX(), avector3f[3].getY(), avector3f[3].getZ()).tex(f7, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //左侧
        buffer.pos(avector3f[4].getX(), avector3f[4].getY(), avector3f[4].getZ()).tex(f8, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[5].getX(), avector3f[5].getY(), avector3f[5].getZ()).tex(f8, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[6].getX(), avector3f[6].getY(), avector3f[6].getZ()).tex(f7, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[7].getX(), avector3f[7].getY(), avector3f[7].getZ()).tex(f7, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //正面
        buffer.pos(avector3f[8].getX(), avector3f[8].getY(), avector3f[8].getZ()).tex(f8, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[9].getX(), avector3f[9].getY(), avector3f[9].getZ()).tex(f8, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[10].getX(), avector3f[10].getY(), avector3f[10].getZ()).tex(f7, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[11].getX(), avector3f[11].getY(), avector3f[11].getZ()).tex(f7, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //右侧
        buffer.pos(avector3f[12].getX(), avector3f[12].getY(), avector3f[12].getZ()).tex(f8, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[13].getX(), avector3f[13].getY(), avector3f[13].getZ()).tex(f8, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[14].getX(), avector3f[14].getY(), avector3f[14].getZ()).tex(f7, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[15].getX(), avector3f[15].getY(), avector3f[15].getZ()).tex(f7, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //上面
        buffer.pos(avector3f[16].getX(), avector3f[16].getY(), avector3f[16].getZ()).tex(f8, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[17].getX(), avector3f[17].getY(), avector3f[17].getZ()).tex(f8, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[18].getX(), avector3f[18].getY(), avector3f[18].getZ()).tex(f7, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(avector3f[19].getX(), avector3f[19].getY(), avector3f[19].getZ()).tex(f7, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.particleScale -= 0.0001f;
        if (!this.onGround)
            this.particleAngle += 2f;
        if (this.particleAngle >= 360)
            this.particleAngle = 0;
        if (this.onGround) {
            this.particleScale -= 0.0005f;
            this.particleAlpha -= 0.1f;
        }
        if (this.particleAlpha <= 0)
            this.particleAlpha = 0;
        if (this.particleScale <= 0)
            this.particleScale = 0;

    }


}
