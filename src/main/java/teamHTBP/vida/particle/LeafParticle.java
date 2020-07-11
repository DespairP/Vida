package teamHTBP.vida.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ReuseableStream;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

import java.util.stream.Stream;

/**
 * 树叶粒子类
 * 继承SpriteTexturedParticle
 * @Version 0.0.1
 * **/
public class LeafParticle extends SpriteTexturedParticle  {
      //掉到地面上不会马上消失,所以设置一个延迟时间
      private int offsetTime = 0;
      //最大延迟时间为20
      private final int MAX_OFFSETTIME = 20;

      private boolean collidedY;


    protected LeafParticle(World worldIn, double posXIn, double posYIn, double posZIn,double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, posXIn, posYIn, posZIn, xSpeedIn,  ySpeedIn,  zSpeedIn);
        //存在时间100帧
        maxAge = 100;
        //粒子大小x0.5
        particleScale = 0.5F;
        motionX = xSpeedIn;
        //Y轴下落速度为0.2
        motionY = -0.2f;
        motionZ = zSpeedIn;
        //是否有碰撞体积
        this.canCollide = true;
        //设置透明度，不透明为1
        this.setAlphaF(1.0f);


    }


    /*
    * 渲染粒子
    * 复制自TexturedParticle
    * */
    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        Vec3d vec3d = renderInfo.getProjectedView();
        float f = (float)(MathHelper.lerp((double)partialTicks, this.prevPosX, this.posX) - vec3d.getX());
        float f1 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosY, this.posY) - vec3d.getY());
        float f2 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());


        Quaternion quaternion;
        if (this.particleAngle == 0.0F) {
            quaternion = renderInfo.getRotation();
        } else {
            quaternion = new Quaternion(renderInfo.getRotation());
            float f3 = MathHelper.lerp(partialTicks, this.prevParticleAngle, this.particleAngle);
            quaternion.multiply(Vector3f.ZP.rotation(f3));
        }

        Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
        vector3f1.transform(quaternion);
        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f4 = this.getScale(partialTicks);

        for(int i = 0; i < 4; ++i) {
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


        buffer.pos((double)avector3f[0].getX(), (double)avector3f[0].getY(), (double)avector3f[0].getZ()).tex(f8, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[1].getX(), (double)avector3f[1].getY(), (double)avector3f[1].getZ()).tex(f8, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[2].getX(), (double)avector3f[2].getY(), (double)avector3f[2].getZ()).tex(f7, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
        buffer.pos((double)avector3f[3].getX(), (double)avector3f[3].getY(), (double)avector3f[3].getZ()).tex(f7, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
    }

    /*以透明渲染方式进行*/
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    /*每一帧会运行此方法，主要是设置一些粒子的可变参数*/
    @Override
    public void tick() {
        super.tick();
        //如果在地上的话，且还没过最大延迟时间，延迟时间++
        if(this.onGround && offsetTime < MAX_OFFSETTIME){
              offsetTime ++;
        }else if(this.onGround){
            //到达最大延迟时间，开始减小透明度
            this.particleAlpha -= 0.1F;
            //透明度达到负值粒子又会出现，所以加个检测机制
            if(this.particleAlpha <= 0)
                this.particleScale = 0;
        }
    }


    /*从TexturedParticle复制的方法*/
    @Override
    public void move(double x, double y, double z) {
        if (!this.collidedY) {
            double d0 = x;
            double d1 = y;
            double origZ = z;
            if (this.canCollide && (x != 0.0D || y != 0.0D || z != 0.0D)) {
                Vec3d vec3d = Entity.collideBoundingBoxHeuristically((Entity) null, new Vec3d(x, y, z), this.getBoundingBox(), this.world, ISelectionContext.dummy(), new ReuseableStream<>(Stream.empty()));
                x = vec3d.x;
                y = vec3d.y;
                z = vec3d.z;
            }

            if (x != 0.0D || y != 0.0D || z != 0.0D) {
                this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
                this.resetPositionToBB();
            }

            if (Math.abs(d1) >= (double) 1.0E-0F && Math.abs(y) < (double) 1.0E-0F) {
                this.collidedY = true;
            }

            this.onGround = d1 != y && d1 < 0.0D;
            if (d0 != x) {
                this.motionX = 0.0D;
            }

            if (origZ != z) {
                this.motionZ = 0.0D;
            }

        }
    }
}
