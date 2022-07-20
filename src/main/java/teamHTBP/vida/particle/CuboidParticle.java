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

public class CuboidParticle extends SpriteTexturedParticle {
    private final float parAlpha = 0;
    private float spinSpeed = 1;
    private float rotation = 0;
    private float extraYLength = 0;

    public CuboidParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, float r, float g, float b, float scale, int age) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        Random rand = new Random();
        xd = speedX;
        if (rand.nextFloat() > 0.95D)
            yd = speedY + rand.nextFloat() / 50.0f;
        else
            yd = speedY;
        zd = speedZ;
        this.rCol = r / 255.0f;
        this.gCol = g / 255.0f;
        this.bCol = b / 255.0f;
        this.lifetime = age;
        this.spinSpeed = rand.nextInt(3);
        this.quadSize = scale;
        this.extraYLength = rand.nextInt(4) + 3;
    }

    public CuboidParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        Random rand = new Random();
        xd = speedX;
        if (rand.nextFloat() > 0.95D)
            yd = speedY + rand.nextFloat() / 50.0f;
        else
            yd = speedY;
        zd = speedZ;
        this.rCol = 255.0f / 255.0f;
        this.gCol = 255.0f / 255.0f;
        this.bCol = 255.0f / 255.0f;
        this.lifetime = 50;
        this.spinSpeed = rand.nextInt(5);
        this.quadSize = 0.2f;
        this.extraYLength = rand.nextInt(5) + 3;
    }

    @Override
    public void render(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {

        Vector3d vec3d = renderInfo.getPosition();
        float f = (float) (MathHelper.lerp(partialTicks, this.xo, this.x) - vec3d.x());
        float f1 = (float) (MathHelper.lerp(partialTicks, this.yo, this.y) - vec3d.y());
        float f2 = (float) (MathHelper.lerp(partialTicks, this.zo, this.z) - vec3d.z());


        Quaternion quaternion = new Quaternion(0, rotation, 0, true);


        Vector3f[] avector3f = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 2.0F), new Vector3f(-1.0F, -1.0F, 2.0F),
                new Vector3f(-1.0F, -1.0F, 2.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 2.0F), new Vector3f(1.0F, 1.0F + extraYLength, 2.0F), new Vector3f(1.0F, -1.0F, 2.0F),
                new Vector3f(1.0F, -1.0F, 2.0F), new Vector3f(1.0F, 1.0F + extraYLength, 2.0F), new Vector3f(1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F + extraYLength, 2.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(1.0F, 1.0F + extraYLength, 0.0F), new Vector3f(1.0F, 1.0F + extraYLength, 2.0F),
                new Vector3f(-1.0F, -1.0F, 1.0F), new Vector3f(-1.0F, -1.0F, -1.0F), new Vector3f(1.0F, -1.0F, -1.0F), new Vector3f(1.0F, -1.0F, 1.0F)

        };

        Vector3f[] vec = new Vector3f[]{
                //后面
                new Vector3f(-1.0F, -1.0F, -1.0F), new Vector3f(-1.0F, 1.0F + extraYLength, -1.0F), new Vector3f(1.0F, 1.0F + extraYLength, -1.0F), new Vector3f(1.0F, -1.0F, -1.0F),
                //前面
                new Vector3f(-1.0F, -1.0F, 1.0F), new Vector3f(-1.0F, 1.0F + extraYLength, 1.0F), new Vector3f(1.0F, 1.0F + extraYLength, 1.0F), new Vector3f(1.0F, -1.0F, 1.0F)

        };
        float f4 = 0.06F;

        for (Vector3f vector3f : vec) {
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            vector3f.add(f, f1, f2);
        }

        float f7 = this.getU0();
        float f8 = this.getU1();
        float f5 = this.getV0();
        float f6 = this.getV1();
        int j = this.getLightColor(partialTicks);
        //下面
        buffer.vertex(vec[4].x(), vec[4].y(), vec[4].z()).uv(f8, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[0].x(), vec[0].y(), vec[0].z()).uv(f8, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[3].x(), vec[3].y(), vec[3].z()).uv(f7, f5).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[7].x(), vec[7].y(), vec[7].z()).uv(f7, f6).color(this.rCol * 0.5f, this.gCol * 0.5f, this.bCol * 0.5f, this.alpha).uv2(j).endVertex();

        //背面
        buffer.vertex(vec[0].x(), vec[0].y(), vec[0].z()).uv(f8, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[1].x(), vec[1].y(), vec[1].z()).uv(f8, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[2].x(), vec[2].y(), vec[2].z()).uv(f7, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[3].x(), vec[3].y(), vec[3].z()).uv(f7, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();

        //左侧
        buffer.vertex(vec[5].x(), vec[5].y(), vec[5].z()).uv(f8, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[1].x(), vec[1].y(), vec[1].z()).uv(f8, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[0].x(), vec[0].y(), vec[0].z()).uv(f7, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[4].x(), vec[4].y(), vec[4].z()).uv(f7, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();

        //正面
        buffer.vertex(vec[5].x(), vec[5].y(), vec[5].z()).uv(f8, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[4].x(), vec[4].y(), vec[4].z()).uv(f8, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[7].x(), vec[7].y(), vec[7].z()).uv(f7, f5).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[6].x(), vec[6].y(), vec[6].z()).uv(f7, f6).color(this.rCol * 0.8f, this.gCol * 0.8f, this.bCol * 0.8f, this.alpha).uv2(j).endVertex();

        //右侧
        buffer.vertex(vec[2].x(), vec[2].y(), vec[2].z()).uv(f8, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[6].x(), vec[6].y(), vec[6].z()).uv(f8, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[7].x(), vec[7].y(), vec[7].z()).uv(f7, f5).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[3].x(), vec[3].y(), vec[3].z()).uv(f7, f6).color(this.rCol * 0.6f, this.gCol * 0.6f, this.bCol * 0.6f, this.alpha).uv2(j).endVertex();

        //上面
        buffer.vertex(vec[1].x(), vec[1].y(), vec[1].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[5].x(), vec[5].y(), vec[5].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[6].x(), vec[6].y(), vec[6].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(vec[2].x(), vec[2].y(), vec[2].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.y += this.yd;
            this.x += this.xd;
            this.z += this.zd;
            this.rotation += 1 + spinSpeed;
            this.rotation %= 360;
            if (this.lifetime - 20 < this.age) this.alpha -= 0.05f;
        }
    }
}
