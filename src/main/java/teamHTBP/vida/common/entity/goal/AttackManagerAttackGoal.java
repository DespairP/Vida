package teamHTBP.vida.common.entity.goal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import teamHTBP.vida.api.common.entity.AttackManagerEntity;
import teamHTBP.vida.common.entity.manager.AttackManager;

/**
 * @author DustW
 */
public class AttackManagerAttackGoal extends MeleeAttackGoal {
    AttackManager attackManager;

    public <T extends PathfinderMob & AttackManagerEntity> AttackManagerAttackGoal(T pMob) {
        super(pMob, 1, false);
    }

    AttackManager attackManager() {
        if (attackManager == null)
            attackManager = ((AttackManagerEntity) mob).getAttackManager();
        return attackManager;
    }

    @Override
    public void tick() {
        super.tick();

        if (mob.getTarget() == null) {
            attackManager().stop();
        }
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);

        if (pDistToEnemySqr <= d0 && isTimeToAttack() && !attackManager().isStarted()) {
            attackManager().start();
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
        }
        else if (pDistToEnemySqr > d0 && attackManager().isStarted()) {
            attackManager().stop();
        }
    }

    @Override
    protected void resetAttackCooldown() {
        int max = attackManager().getMaxTick();
        ticksUntilNextAttack = this.adjustedTickDelay(max + 20);
    }
}
