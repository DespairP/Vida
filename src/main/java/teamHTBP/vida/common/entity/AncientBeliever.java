package teamHTBP.vida.common.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

/**
 * @author DustW
 */
public class AncientBeliever extends PathfinderMob implements IAnimatable, IAnimationTickable {

    private static final EntityDataAccessor<Boolean> GOAL_FLAG_MOVE =
            SynchedEntityData.defineId(AncientBeliever.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GOAL_FLAG_JUMP =
            SynchedEntityData.defineId(AncientBeliever.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GOAL_FLAG_TARGET =
            SynchedEntityData.defineId(AncientBeliever.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GOAL_FLAG_LOOK =
            SynchedEntityData.defineId(AncientBeliever.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean>[] FLAGS = new EntityDataAccessor[] {
            GOAL_FLAG_MOVE, GOAL_FLAG_LOOK, GOAL_FLAG_JUMP, GOAL_FLAG_TARGET
    };

    private final AnimationFactory factory = new AnimationFactory(this);

    public AncientBeliever(EntityType<AncientBeliever> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        // nothing
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        // jump
        this.goalSelector.addGoal(0, new FloatGoal(this));
        // look
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        // move look
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "standby_controller",
                0, this::standbyPredicate));

        data.addAnimationController(new AnimationController<>(this, "walk_controller",
                0, this::walkPredicate));
    }

    boolean has(Goal.Flag flag) {
        var goals = goalSelector.getAvailableGoals();
        return goals.stream().anyMatch(g ->
                g.isRunning() && g.getFlags().contains(flag));
    }

    boolean has(EntityDataAccessor<Boolean> flag) {
        return entityData.get(flag);
    }

    boolean moving() {
        return has(GOAL_FLAG_MOVE) && !position().equals(new Vec3(xOld, yOld, zOld));
    }

    private <T extends IAnimatable> PlayState standbyPredicate(AnimationEvent<T> event) {
        if (moving() || has(GOAL_FLAG_JUMP)) {
            return PlayState.STOP;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.ancient_believer.standby", true));
            return PlayState.CONTINUE;
        }
    }

    private <T extends IAnimatable> PlayState walkPredicate(AnimationEvent<T> event) {
        if (moving()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.ancient_believer.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            return PlayState.STOP;
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
}
