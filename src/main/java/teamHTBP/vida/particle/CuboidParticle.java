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
        motionX = speedX;
        if (rand.nextFloat() > 0.95D)
            motionY = speedY + rand.nextFloat() / 50.0f;
        else
            motionY = speedY;
        motionZ = speedZ;
        this.particleRed = r / 255.0f;
        this.particleGreen = g / 255.0f;
        this.particleBlue = b / 255.0f;
        this.maxAge = age;
        this.spinSpeed = rand.nextInt(3);
        this.particleScale = scale;
        this.extraYLength = rand.nextInt(4) + 3;
    }

    public CuboidParticle(ClientWorld World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(World, posX, posY, posZ, speedX, speedY, speedZ);
        Random rand = new Random();
        motionX = speedX;
        if (rand.nextFloat() > 0.95D)
            motionY = speedY + rand.nextFloat() / 50.0f;
        else
            motionY = speedY;
        motionZ = speedZ;
        this.particleRed = 255.0f / 255.0f;
        this.particleGreen = 255.0f / 255.0f;
        this.particleBlue = 255.0f / 255.0f;
        this.maxAge = 50;
        this.spinSpeed = rand.nextInt(5);
        this.particleScale = 0.2f;
        this.extraYLength = rand.nextInt(5) + 3;
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {

        Vector3d vec3d = renderInfo.getProjectedView();
        float f = (float) (MathHelper.lerp(partialTicks, this.prevPosX, this.posX) - vec3d.getX());
        float f1 = (float) (MathHelper.lerp(partialTicks, this.prevPosY, this.posY) - vec3d.getY());
        float f2 = (float) (MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());


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

        float f7 = this.getMinU();
        float f8 = this.getMaxU();
        float f5 = this.getMinV();
        float f6 = this.getMaxV();
        int j = this.getBrightnessForRender(partialTicks);
        //下面
        buffer.pos(vec[4].getX(), vec[4].getY(), vec[4].getZ()).tex(f8, f6).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[0].getX(), vec[0].getY(), vec[0].getZ()).tex(f8, f5).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[3].getX(), vec[3].getY(), vec[3].getZ()).tex(f7, f5).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[7].getX(), vec[7].getY(), vec[7].getZ()).tex(f7, f6).color(this.particleRed * 0.5f, this.particleGreen * 0.5f, this.particleBlue * 0.5f, this.particleAlpha).lightmap(j).endVertex();

        //背面
        buffer.pos(vec[0].getX(), vec[0].getY(), vec[0].getZ()).tex(f8, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[1].getX(), vec[1].getY(), vec[1].getZ()).tex(f8, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[2].getX(), vec[2].getY(), vec[2].getZ()).tex(f7, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[3].getX(), vec[3].getY(), vec[3].getZ()).tex(f7, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //左侧
        buffer.pos(vec[5].getX(), vec[5].getY(), vec[5].getZ()).tex(f8, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[1].getX(), vec[1].getY(), vec[1].getZ()).tex(f8, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[0].getX(), vec[0].getY(), vec[0].getZ()).tex(f7, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[4].getX(), vec[4].getY(), vec[4].getZ()).tex(f7, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //正面
        buffer.pos(vec[5].getX(), vec[5].getY(), vec[5].getZ()).tex(f8, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[4].getX(), vec[4].getY(), vec[4].getZ()).tex(f8, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[7].getX(), vec[7].getY(), vec[7].getZ()).tex(f7, f5).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[6].getX(), vec[6].getY(), vec[6].getZ()).tex(f7, f6).color(this.particleRed * 0.8f, this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //右侧
        buffer.pos(vec[2].getX(), vec[2].getY(), vec[2].getZ()).tex(f8, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[6].getX(), vec[6].getY(), vec[6].getZ()).tex(f8, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[7].getX(), vec[7].getY(), vec[7].getZ()).tex(f7, f5).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[3].getX(), vec[3].getY(), vec[3].getZ()).tex(f7, f6).color(this.particleRed * 0.6f, this.particleGreen * 0.6f, this.particleBlue * 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //上面
        buffer.pos(vec[1].getX(), vec[1].getY(), vec[1].getZ()).tex(f8, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[5].getX(), vec[5].getY(), vec[5].getZ()).tex(f8, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[6].getX(), vec[6].getY(), vec[6].getZ()).tex(f7, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos(vec[2].getX(), vec[2].getY(), vec[2].getZ()).tex(f7, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.posY += this.motionY;
            this.posX += this.motionX;
            this.posZ += this.motionZ;
            this.rotation += 1 + spinSpeed;
            this.rotation %= 360;
            if (this.maxAge - 20 < this.age) this.particleAlpha -= 0.05f;
        }
    }
}
