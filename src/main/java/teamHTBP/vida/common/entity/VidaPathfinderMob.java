package teamHTBP.vida.common.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * @author DustW
 */
public class VidaPathfinderMob extends PathfinderMob {

    protected static final EntityDataAccessor<Boolean> GOAL_FLAG_MOVE =
            SynchedEntityData.defineId(VidaPathfinderMob.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> GOAL_FLAG_JUMP =
            SynchedEntityData.defineId(VidaPathfinderMob.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> GOAL_FLAG_TARGET =
            SynchedEntityData.defineId(VidaPathfinderMob.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> GOAL_FLAG_LOOK =
            SynchedEntityData.defineId(VidaPathfinderMob.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean>[] FLAGS = new EntityDataAccessor[] {
            GOAL_FLAG_MOVE, GOAL_FLAG_LOOK, GOAL_FLAG_JUMP, GOAL_FLAG_TARGET
    };

    protected VidaPathfinderMob(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        for (var flag : FLAGS) {
            this.entityData.define(flag, false);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {
            Goal.Flag[] values = Goal.Flag.values();

            for (int i = 0; i < values.length; i++) {
                entityData.set(FLAGS[i], has(values[i]));
            }
        }
    }

    protected boolean has(Goal.Flag flag) {
        var goals = goalSelector.getAvailableGoals();
        return goals.stream().anyMatch(g ->
                g.isRunning() && g.getFlags().contains(flag));
    }

    protected boolean has(EntityDataAccessor<Boolean> flag) {
        return entityData.get(flag);
    }

    protected boolean moving() {
        return has(GOAL_FLAG_MOVE) && !position().equals(new Vec3(xOld, yOld, zOld));
    }
}
