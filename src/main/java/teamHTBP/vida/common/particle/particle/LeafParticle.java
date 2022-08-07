package teamHTBP.vida.common.particle.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

/**
 * 树叶粒子类
 * 继承SpriteTexturedParticle
 *
 * @Version 0.0.1
 **/
public class LeafParticle extends TextureParticle {
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

    /*以透明渲染方式进行*/
    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    /**每一帧会运行此方法，主要是设置一些粒子的可变参数*/
    @Override
    protected void update() {
        y += yd;
        x = centerX + Mth.sin((float) (this.xd + this.zd + this.y * 5));
        z = centerZ + Mth.cos((float) (this.xd + this.zd + this.y * 5));
    }
}
