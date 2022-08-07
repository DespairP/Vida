package teamHTBP.vida.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import teamHTBP.vida.common.entity.goal.AttackManagerRangedAttackGoal;
import teamHTBP.vida.common.entity.manager.AttackManager;
import teamHTBP.vida.common.entity.projectile.ElementBallProjectile;

/**
 * @author DustW
 */
public class SilentBeliever extends AbstractBeliever implements RangedAttackMob {

    protected SilentBeliever(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level, new AttackManager((int) (2.08 * 20), (int) (1.68 * 20)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 3);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2,
                new AttackManagerRangedAttackGoal<>(this, 1, 40, 15));
    }

    @Override
    protected String getModelName() {
        return "silent_believer";
    }

    @Override
    protected void attackTarget(LivingEntity target) {
        ElementBallProjectile entity = new ElementBallProjectile(this, level);
        Vec3 vec = getLookAngle();
        entity.setPos(vec.normalize().scale(2).add(getEyePosition()));
        entity.shoot(vec.x, vec.y, vec.z, 1, 0);
        if (getTarget() != null)
            entity.setTarget(getTarget());
        else
            entity.setTargetClass(Player.class);
        level.addFreshEntity(entity);
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        attackTarget(pTarget);
    }
}
