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

/**
 * 树叶粒子类
 * 继承SpriteTexturedParticle
 *
 * @Version 0.0.1
 **/
public class LeafParticle extends TextureSheetParticle {
    //掉到地面上不会马上消失,所以设置一个延迟时间
    private final int offsetTime = 0;
    //最大延迟时间为20
    private final int MAX_OFFSETTIME = 20;

    private boolean collidedY;

    /**X轴圆心*/
    private double centerX;
    /**Y轴圆心*/
    private double centerZ;

    public LeafParticle(ClientLevel worldIn, double posXIn, double posYIn, double posZIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, posXIn, posYIn, posZIn, xSpeedIn, ySpeedIn, zSpeedIn);
        //存在时间1000帧
        lifetime = 20;
        //粒子大小x0.5
        quadSize = 0.5F;

        // 设置速度
        xd = xSpeedIn;
        yd = ySpeedIn;
        zd = zSpeedIn;

        //是否有碰撞体积
        this.hasPhysics = false;

        //设置透明度，不透明为1
        this.setAlpha(0.9f);

        //设置圆心位置
        this.centerX = posXIn;
        this.centerZ = posZIn;
    }


    /*
     * 渲染粒子
     * 复制自TexturedParticle
     * */
    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
        Vec3 vec3d = renderInfo.getPosition();
        float f = (float) (Mth.lerp(partialTicks, this.xo, this.x) - vec3d.x());
        float f1 = (float) (Mth.lerp(partialTicks, this.yo, this.y) - vec3d.y());
        float f2 = (float) (Mth.lerp(partialTicks, this.zo, this.z) - vec3d.z());


        Quaternion quaternion;
        if (this.roll == 0.0F) {
            quaternion = renderInfo.rotation();
        } else {
            quaternion = new Quaternion(renderInfo.rotation());
            float f3 = Mth.lerp(partialTicks, this.oRoll, this.roll);
            quaternion.mul(Vector3f.ZP.rotation(f3));
        }

        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f4 = this.getQuadSize(partialTicks);

        for (int i = 0; i < 4; ++i) {
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


        buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
    }

    /*以透明渲染方式进行*/
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    /**每一帧会运行此方法，主要是设置一些粒子的可变参数*/
    @Override
    public void tick() {
        //System.out.println(motionX + "" + motionY + "" + motionZ);
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            y += yd;
            x = centerX + Mth.sin((float) (this.xd + this.zd + this.y * 5));
            z = centerZ + Mth.cos((float) (this.xd + this.zd + this.y * 5));

        }
    }

}
