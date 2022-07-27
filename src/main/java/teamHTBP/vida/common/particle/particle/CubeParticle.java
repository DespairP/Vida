package teamHTBP.vida.common.particle.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

/**
 * 尝试一下3D粒子
 *
 * @Version 0.0.1
 **/
public class CubeParticle extends TextureSheetParticle {

    private int rotationType = 1;

    protected CubeParticle(ClientLevel World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        this.hasPhysics = true;
        xd = speedX;
        yd = speedY;
        zd = speedZ;
        quadSize = 0.1f;
        this.lifetime = 100;

    }

    public CubeParticle(ClientLevel World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, float r, float g, float b, float scale) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        this.hasPhysics = true;
        xd = speedX;
        yd = speedY;
        zd = speedZ;
        quadSize = scale;
        this.lifetime = 100;
        this.rCol = r / 255.0f;
        this.gCol = g / 255.0f;
        this.bCol = b / 255.0f;
        this.rotationType = new Random().nextInt(10) + 1;
        if (this.quadSize == 0.03f) {
            this.alpha = 0.7f;
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {

        Vec3 vec3d = renderInfo.getPosition();
        float f = (float) (Mth.lerp(partialTicks, this.xo, this.x) - vec3d.x());
        float f1 = (float) (Mth.lerp(partialTicks, this.yo, this.y) - vec3d.y());
        float f2 = (float) (Mth.lerp(partialTicks, this.zo, this.z) - vec3d.z());


        Quaternion quaternion = new Quaternion(renderInfo.rotation());
        switch (rotationType) {
            case 1:
                quaternion = new Quaternion(this.roll, this.roll, 0, true);
                break;
            case 2:
                quaternion = new Quaternion(this.roll, this.roll, this.roll, true);
                break;
            case 3:
                quaternion = new Quaternion(this.roll, 0, this.roll, true);
                break;
            case 4:
                quaternion = new Quaternion(0, this.roll, this.roll, true);
                break;
            case 5:
                quaternion = new Quaternion(0, this.roll, 0, true);
                break;
            case 6:
                quaternion = new Quaternion(0, this.roll, this.roll, true);
                break;
            case 7:
                quaternion = new Quaternion(0, -this.roll, -this.roll, true);
                break;
            case 8:
                quaternion = new Quaternion(-this.roll, -this.roll, this.roll, true);
                break;
            case 9:
                quaternion = new Quaternion(-this.roll, -this.roll, -this.roll, true);
                break;
            case 10:
                quaternion = new Quaternion(0, this.roll, -this.roll, true);
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
        float f4 = this.getQuadSize(partialTicks);

        for (int i = 0; i < 24; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            vector3f.add(f, f1, f2);
        }

        float f7 = this.getU0();
        float f8 = this.getU1();
        float f5 = this.getV0();
        float f6 = this.getV1();
        int j = this.getLightColor(partialTicks);
        //下面(正反面都要渲染)
        buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f8, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[8].x(), avector3f[8].y(), avector3f[8].z()).uv(f8, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[11].x(), avector3f[11].y(), avector3f[11].z()).uv(f7, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f7, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();

        buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f8, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[11].x(), avector3f[11].y(), avector3f[11].z()).uv(f8, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[8].x(), avector3f[8].y(), avector3f[8].z()).uv(f7, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f7, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        //背面
        buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f8, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f8, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f7, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f7, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();

        //左侧
        buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f8, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f8, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[8].x(), avector3f[8].y(), avector3f[8].z()).uv(f7, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[9].x(), avector3f[9].y(), avector3f[9].z()).uv(f7, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();

        //正面
        buffer.vertex(avector3f[11].x(), avector3f[11].y(), avector3f[11].z()).uv(f8, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[10].x(), avector3f[10].y(), avector3f[10].z()).uv(f8, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[9].x(), avector3f[9].y(), avector3f[9].z()).uv(f7, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[8].x(), avector3f[8].y(), avector3f[8].z()).uv(f7, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();

        //右侧
        buffer.vertex(avector3f[10].x(), avector3f[10].y(), avector3f[10].z()).uv(f8, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[11].x(), avector3f[11].y(), avector3f[11].z()).uv(f8, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f7, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f7, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();

        //上面(正反面都要渲染)
        buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[10].x(), avector3f[10].y(), avector3f[10].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[9].x(), avector3f[9].y(), avector3f[9].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();

        buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[9].x(), avector3f[9].y(), avector3f[9].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[10].x(), avector3f[10].y(), avector3f[10].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.quadSize -= 0.0001f;
        if (!this.onGround) this.roll += 2f;
        if (this.roll >= 360) this.roll = 0;
        if (this.onGround) {
            this.quadSize -= 0.0005f;
            this.alpha -= 0.1f;
        }
        if (this.alpha <= 0)
            this.alpha = 0;
        if (this.quadSize <= 0)
            this.quadSize = 0;
    }


}
