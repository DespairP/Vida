package teamHTBP.vida.common.entity.projectile;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.common.entity.VidaEntityLoader;
import teamHTBP.vida.helper.math.Vector3;
import teamHTBP.vida.helper.math.curve.Bezier3Curve;

import java.util.Comparator;
import java.util.List;

/**
 * @author DustW
 */
@EqualsAndHashCode(callSuper = true)
public class ElementBallProjectile extends Projectile {

    final ControlPoints movePoints = new ControlPoints();

    @Nullable
    @Setter
    Class<? extends LivingEntity> targetClass;

    public ElementBallProjectile(EntityType<ElementBallProjectile> type, Level level) {
        super(type, level);
        setNoGravity(true);
    }

    public ElementBallProjectile(double x, double y, double z, Level level) {
        this(VidaEntityLoader.ELEMENT_BALL.get(), level);
        setPos(x, y, z);
    }

    public ElementBallProjectile(LivingEntity shooter, Level level) {
        this(shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), level);
        setOwner(shooter);
    }

    @Override
    protected void defineSynchedData() {
        // no data need sync
    }

    public Class<? extends LivingEntity> getTargetClass() {
        return targetClass == null ? LivingEntity.class : targetClass;
    }

    void startTick() {
        if (movePoints.started() && movePoints.startedTick > 1) {
            setDeltaMovement(Vec3.ZERO);
        }
        else {
            Vec3 vec3 = this.getDeltaMovement();
            double d2 = this.getX() + vec3.x;
            double d0 = this.getY() + vec3.y;
            double d1 = this.getZ() + vec3.z;
            this.setPos(d2, d0, d1);
        }
    }

    private LivingEntity target;

    @Override
    public void tick() {
        startTick();

        super.tick();

        if (!level.isClientSide) {
            if (target == null) {
                LivingEntity temp = findTarget();

                if (temp != null) {
                    setTarget(temp);
                }
            } else {
                if (!target.isAlive()) {
                    discard();
                }

                movePoints.setTargetPos(new Vector3(target.position()));

                setPos(movePoints.getPos());

                if (movePoints.needEnd()) {
                    movePoints.end();
                    hit();
                }
            }

            movePoints.tick();
        } else {
            addParticle();
        }
    }

    void addParticle() {
        Vec3 position = position();
        level.addParticle(ParticleTypes.SMOKE, position.x, position.y, position.z, 0, 0, 0.001);
    }

    @Nullable
    protected LivingEntity findTarget() {
        List<? extends LivingEntity> list = level.getEntitiesOfClass(getTargetClass(),
                AABB.ofSize(position(), 80, 80, 80), entity -> {
                    Vec3 directionVec = directionVec();
                    Vec3 position = entity.position();

                    return !entity.equals(getOwner()) && !entity.isSpectator() && entity.isAlive() &&
                            position().vectorTo(position).dot(directionVec) > 0;
                });

        if (!list.isEmpty()) {
            if (list.size() > 1)
                list.sort(Comparator.comparingDouble(o -> o.distanceToSqr(position())));

            return list.get(0);
        }

        return null;
    }

    Vec3 directionVec() {
        return new Vec3(xOld, yOld, zOld).vectorTo(position());
    }

    public void setTarget(@NotNull LivingEntity target) {
        this.target = target;
        movePoints.start(position(), directionVec().normalize().scale(3).add(position()), target.position(), 80);
    }

    protected void hit() {
        if (!level.isClientSide) {
            target.invulnerableTime = 0;
            target.hurt(DamageSource.thrown(this, getOwner()), getDamageFromEntity(target));
            discard();
        }
    }

    protected float getDamageFromEntity(Entity entity) {
        return 1;
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    static class ControlPoints {
        static final float ROT = (float) Math.toRadians(30);
        static final float ROT_COS = Mth.cos(ROT);

        Vector3 reachedPos;
        Vector3 inertiaVec;
        Vector3 tempPos;
        @Setter
        Vector3 targetPos;

        boolean started;
        int startedTick;
        int maxTick;

        public void start(Vec3 reachedPos, Vec3 inertiaVec, Vec3 targetPos, int maxTick) {
            this.reachedPos = new Vector3(reachedPos);
            this.inertiaVec = new Vector3(inertiaVec);
            this.targetPos = new Vector3(targetPos);
            this.maxTick = maxTick;

            tempPos = this.targetPos;

            started = true;
        }

        public void tick() {
            if (started()) {
                startedTick++;
            }
        }

        public boolean needEnd() {
            return startedTick >= maxTick;
        }

        public void end() {
            started = false;
            startedTick = 0;

            reachedPos = null;
            inertiaVec = null;
            tempPos = null;
            targetPos = null;
        }

        public boolean started() {
            return started;
        }

        public Vec3 getPos() {
            if (started())
                return Bezier3Curve.bezier3(reachedPos, inertiaVec, tempPos, targetPos, (float) startedTick / maxTick).vec3();
            return Vec3.ZERO;
        }
    }
}
