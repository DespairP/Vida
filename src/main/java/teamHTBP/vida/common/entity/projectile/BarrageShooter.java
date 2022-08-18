package teamHTBP.vida.common.entity.projectile;

import lombok.EqualsAndHashCode;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import teamHTBP.vida.client.animation.particle.CircleParticleAnimation;
import teamHTBP.vida.common.entity.VidaEntityLoader;

/**
 * @author DustW
 */
@EqualsAndHashCode(callSuper = true)
public class BarrageShooter extends Projectile {
    public BarrageShooter(EntityType<BarrageShooter> type, Level level) {
        super(type, level);
        setNoGravity(true);
    }

    public BarrageShooter(double x, double y, double z, Level level) {
        this(VidaEntityLoader.BARRAGE_SHOOTER.get(), level);
        setPos(x, y, z);
    }

    public BarrageShooter(LivingEntity shooter, Level level) {
        this(shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), level);
        setOwner(shooter);
    }

    @Override
    protected void defineSynchedData() {

    }

    int tick;

    @Override
    public void tick() {
        super.tick();

        if (tick++ > 60) {
            if (level.isClientSide) {
                new CircleParticleAnimation(20, 30, 120).start(this);
                discard();
            } else if (tick++ > 70) {
                discard();
            }
        }
    }
}
