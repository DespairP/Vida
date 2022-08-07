package teamHTBP.vida.common.entity.manager;

import lombok.Data;

/**
 * @author DustW
 */
@Data
public class AttackManager {
    int maxTick;
    int attackTick;

    public AttackManager(int maxTick, int attackTick) {
        this.maxTick = maxTick;
        this.attackTick = attackTick;
    }

    int tempTick;
    boolean started;

    public void start() {
        started = true;
    }

    public void tick() {
        if (started) {
            tempTick = Math.min(tempTick + 1, maxTick);

            if (tempTick >= maxTick) {
                stop();
            }
        }
    }

    public boolean isAttacking() {
        return tempTick == attackTick;
    }

    public void stop() {
        tempTick = 0;
        started = false;
    }

    public boolean canAttack() {
        return true;
    }
}
