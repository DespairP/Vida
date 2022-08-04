package teamHTBP.vida.common.entity.manager;

import lombok.Data;
import net.minecraft.world.entity.PathfinderMob;

/**
 * @author DustW
 */
@Data
public class AttackManager {
    PathfinderMob attacker;
    int maxTick;
    int attackTick;

    public AttackManager(PathfinderMob attacker, int maxTick, int attackTick) {
        this.attacker = attacker;
        this.maxTick = maxTick;
        this.attackTick = attackTick;
    }

    boolean isAttacking;
    int tempTick;
    boolean started;

    public void start() {
        started = true;
    }

    public void tick() {
        if (started) {
            isAttacking = false;
            tempTick = Math.min(tempTick + 1, maxTick);

            if (tempTick == attackTick) {
                attack(attacker);
                isAttacking = true;
            } else if (tempTick >= maxTick) {
                stop();
            }
        }
    }

    public void stop() {
        tempTick = 0;
        started = false;
    }

    protected void attack(PathfinderMob attacker) {

    }
}
