package teamHTBP.vida.common.particle.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author DustW
 */
public abstract class AbstractParticle extends Particle {
    protected float quadSize = 1;
    protected boolean moveless;
    protected Consumer<AbstractParticle> onUpdate;
    protected int lightColor = -1;
    protected boolean cull = true;
    private Level realLevel;

    protected AbstractParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.realLevel = level;
    }

    protected AbstractParticle(ClientLevel level, double x, double y, double z, double sX, double sY, double sZ) {
        super(level, x, y, z, sX, sY, sZ);
        this.realLevel = level;
    }

    public Level getLevel() {
        return realLevel == null ? super.level : realLevel;
    }

    public void setLevel(Level level) {
        this.realLevel = level;
    }

    public void setMoveless(boolean moveless) {
        this.moveless = moveless;
    }

    public void setFullLight() {
        setLight(0xf000f0);
    }

    public void setOnUpdate(Consumer<AbstractParticle> onUpdate) {
        this.onUpdate = onUpdate;
    }

    public boolean isMoveless() {
        return moveless;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime && lifetime > 0) {
            this.remove();
        } else if (onUpdate == null) {
            update();
        } else {
            onUpdate.accept(this);
        }
    }

    protected void update() {
        if (!moveless) {
            this.yd -= 0.04D * this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.speedUpWhenYMotionIsBlocked && this.y == this.yo) {
                this.xd *= 1.1D;
                this.zd *= 1.1D;
            }
            this.xd *= this.friction;
            this.yd *= this.friction;
            this.zd *= this.friction;
            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }
        }
    }

    public void render(@Nonnull VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        Vec3 vec3 = pRenderInfo.getPosition();
        float f = (float) (Mth.lerp(pPartialTicks, this.xo, this.x) - vec3.x());
        float f1 = (float) (Mth.lerp(pPartialTicks, this.yo, this.y) - vec3.y());
        float f2 = (float) (Mth.lerp(pPartialTicks, this.zo, this.z) - vec3.z());
        Quaternion quaternion;
        if (this.roll == 0.0F) {
            quaternion = pRenderInfo.rotation();
        } else {
            quaternion = new Quaternion(pRenderInfo.rotation());
            float f3 = Mth.lerp(pPartialTicks, this.oRoll, this.roll);
            quaternion.mul(Vector3f.ZP.rotation(f3));
        }

        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f4 = this.getQuadSize(pPartialTicks);

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            vector3f.add(f, f1, f2);
        }

        float f7 = this.getU0(pPartialTicks);
        float f8 = this.getU1(pPartialTicks);
        float f5 = this.getV0(pPartialTicks);
        float f6 = this.getV1(pPartialTicks);
        int j = this.getLightColor(pPartialTicks);
        pBuffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
    }

    public float getQuadSize(float pPartialTicks) {
        return this.quadSize;
    }

    @Nonnull
    public AbstractParticle scale(float pScale) {
        this.quadSize = pScale;
        super.scale(pScale);
        return this;
    }

    public void setAlpha(float pAlpha) {
        this.alpha = pAlpha;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGravity() {
        return gravity;
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        if (lightColor >= 0) return lightColor;
        return super.getLightColor(pPartialTick);
    }

    public void setLight(int light) {
        this.lightColor = light;
    }

    public void setColor(int color) {
        this.setColor(FastColor.ARGB32.red(color), FastColor.ARGB32.green(color), FastColor.ARGB32.blue(color));
    }

    @Override
    public boolean shouldCull() {
        return cull;
    }

    protected abstract float getU0(float pPartialTicks);

    protected abstract float getU1(float pPartialTicks);

    protected abstract float getV0(float pPartialTicks);

    protected abstract float getV1(float pPartialTicks);

    public int getAge() {
        return age;
    }

    public void setCull(boolean cull) {
        this.cull = cull;
    }

    public void setImmortal() {
        setLifetime(-1);
    }

    public void addParticle() {
        Minecraft.getInstance().particleEngine.add(this);
    }
}
