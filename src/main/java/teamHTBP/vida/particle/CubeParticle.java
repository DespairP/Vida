package teamHTBP.vida.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 尝试一下3D粒子
 * @Version 0.0.1
 * **/
public class CubeParticle extends SpriteTexturedParticle {
    protected CubeParticle(World World, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(World,posX,posY,posZ,speedX,speedY,speedZ);
        this.canCollide = true;
        motionX = speedX;
        motionY = speedY;
        motionZ = speedZ;
        particleScale = 0.1f;
        this.maxAge = 100;

    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {

        Vec3d vec3d = renderInfo.getProjectedView();
        float f = (float)(MathHelper.lerp((double)partialTicks, this.prevPosX, this.posX) - vec3d.getX());
        float f1 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosY, this.posY) - vec3d.getY());
        float f2 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());

        Quaternion quaternion;
            quaternion = new Quaternion(this.particleAngle,this.particleAngle,0,true);



        Vector3f[] avector3f = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 2.0F), new Vector3f(-1.0F, -1.0F, 2.0F),
                new Vector3f(-1.0F, -1.0F, 2.0F), new Vector3f(-1.0F, 1.0F, 2.0F),  new Vector3f(1.0F,   1.0F, 2.0F), new Vector3f( 1.0F,-1.0F, 2.0F),
                new Vector3f( 1.0F, -1.0F, 2.0F), new Vector3f( 1.0F, 1.0F, 2.0F),  new Vector3f(1.0F,   1.0F, 0.0F), new Vector3f( 1.0F,-1.0F, 0.0F),
                new Vector3f( -1.0F, 1.0F, 2.0F), new Vector3f( -1.0F, 1.0F, 0.0F),  new Vector3f(1.0F,   1.0F, 0.0F), new Vector3f( 1.0F,1.0F, 2.0F),
                new Vector3f( -1.0F, -1.0F, 2.0F), new Vector3f( -1.0F, -1.0F, 0.0F),  new Vector3f(1.0F,   -1.0F, 0.0F), new Vector3f( 1.0F,-1.0F, 2.0F)

        };
        float f4 = this.getScale(partialTicks);

        for(int i = 0; i < 24; ++i) {
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
        buffer.pos((double)avector3f[20].getX(), (double)avector3f[20].getY(), (double)avector3f[20].getZ()).tex(f8, f6).color(this.particleRed* 0.5f, this.particleGreen* 0.5f, this.particleBlue* 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[21].getX(), (double)avector3f[21].getY(), (double)avector3f[21].getZ()).tex(f8, f5).color(this.particleRed* 0.5f, this.particleGreen* 0.5f, this.particleBlue* 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[22].getX(), (double)avector3f[22].getY(), (double)avector3f[22].getZ()).tex(f7, f5).color(this.particleRed* 0.5f, this.particleGreen* 0.5f, this.particleBlue* 0.5f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[23].getX(), (double)avector3f[23].getY(), (double)avector3f[23].getZ()).tex(f7, f6).color(this.particleRed* 0.5f, this.particleGreen* 0.5f, this.particleBlue* 0.5f, this.particleAlpha).lightmap(j).endVertex();

        //背面
        buffer.pos((double)avector3f[0].getX(), (double)avector3f[0].getY(), (double)avector3f[0].getZ()).tex(f8, f6).color(this.particleRed * 0.8f , this.particleGreen * 0.8f, this.particleBlue * 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[1].getX(), (double)avector3f[1].getY(), (double)avector3f[1].getZ()).tex(f8, f5).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[2].getX(), (double)avector3f[2].getY(), (double)avector3f[2].getZ()).tex(f7, f5).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[3].getX(), (double)avector3f[3].getY(), (double)avector3f[3].getZ()).tex(f7, f6).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //左侧
        buffer.pos((double)avector3f[4].getX(), (double)avector3f[4].getY(), (double)avector3f[4].getZ()).tex(f8, f6).color(this.particleRed * 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[5].getX(), (double)avector3f[5].getY(), (double)avector3f[5].getZ()).tex(f8, f5).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[6].getX(), (double)avector3f[6].getY(), (double)avector3f[6].getZ()).tex(f7, f5).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[7].getX(), (double)avector3f[7].getY(), (double)avector3f[7].getZ()).tex(f7, f6).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //正面
        buffer.pos((double)avector3f[8].getX(), (double)avector3f[8].getY(), (double)avector3f[8].getZ()).tex(f8, f6).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[9].getX(), (double)avector3f[9].getY(), (double)avector3f[9].getZ()).tex(f8, f5).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[10].getX(), (double)avector3f[10].getY(), (double)avector3f[10].getZ()).tex(f7, f5).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[11].getX(), (double)avector3f[11].getY(), (double)avector3f[11].getZ()).tex(f7, f6).color(this.particleRed* 0.8f, this.particleGreen* 0.8f, this.particleBlue* 0.8f, this.particleAlpha).lightmap(j).endVertex();

        //右侧
        buffer.pos((double)avector3f[12].getX(), (double)avector3f[12].getY(), (double)avector3f[12].getZ()).tex(f8, f6).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[13].getX(), (double)avector3f[13].getY(), (double)avector3f[13].getZ()).tex(f8, f5).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[14].getX(), (double)avector3f[14].getY(), (double)avector3f[14].getZ()).tex(f7, f5).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[15].getX(), (double)avector3f[15].getY(), (double)avector3f[15].getZ()).tex(f7, f6).color(this.particleRed* 0.6f, this.particleGreen* 0.6f, this.particleBlue* 0.6f, this.particleAlpha).lightmap(j).endVertex();

        //上面
        buffer.pos((double)avector3f[16].getX(), (double)avector3f[16].getY(), (double)avector3f[16].getZ()).tex(f8, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[17].getX(), (double)avector3f[17].getY(), (double)avector3f[17].getZ()).tex(f8, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[18].getX(), (double)avector3f[18].getY(), (double)avector3f[18].getZ()).tex(f7, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[19].getX(), (double)avector3f[19].getY(), (double)avector3f[19].getZ()).tex(f7, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.particleScale -= 0.0001f;
        if(!this.onGround)
        this.particleAngle += 1f;
        if(this.particleAngle >= 360)
            this.particleAngle = 0;
        if(this.onGround){
            this.particleScale -= 0.0005f;
            this.particleAlpha -= 0.1f;
        }
        if(this.particleAlpha <= 0)
            this.particleAlpha = 0;
        if(this.particleScale <= 0)
            this.particleScale = 0;

    }
}
