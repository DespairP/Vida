package teamHTBP.vida.common.entity;

import lombok.Getter;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import teamHTBP.vida.api.common.entity.AttackManagerEntity;
import teamHTBP.vida.common.entity.manager.AttackManager;
import teamHTBP.vida.common.entity.manager.FiniteStateManager;

/**
 * @author DustW
 */
public abstract class AbstractBeliever extends VidaPathfinderMob implements IAnimatable, IAnimationTickable, AttackManagerEntity {
    protected static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(AbstractBeliever.class, EntityDataSerializers.BOOLEAN);

    private static final String STANDBY_STATE = "STANDBY";
    private static final String WALK_STATE = "WALK";
    private static final String ATTACK_STATE = "ATTACK";

    @Getter
    protected final AttackManager attackManager;
    private final AnimationFactory factory = new AnimationFactory(this);
    protected final FiniteStateManager<AbstractBeliever> stateManager;

    protected AbstractBeliever(EntityType<? extends PathfinderMob> type, Level level, AttackManager attackManager) {
        super(type, level);

        stateManager = new FiniteStateManager<>(this);
        stateManager.addState(mob -> !moving() && !has(GOAL_FLAG_JUMP) && !isAttacking(), STANDBY_STATE);
        stateManager.addState(mob -> moving() && !isAttacking(), WALK_STATE);
        stateManager.addState(mob -> !moving() && isAttacking(), ATTACK_STATE);

        this.attackManager = attackManager;
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

        // target
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        entityData.define(ATTACKING, false);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {
            entityData.set(ATTACKING, attackManager.isStarted());

            attackManagerTick();

            setFlagLock(isAttacking());
        }

        stateManager.refresh();
    }

    protected void attackManagerTick() {
        attackManager.tick();

        if (attackManager.isAttacking() && getTarget() != null) {
            attackTarget(getTarget());
        }
    }

    void setFlagLock(boolean isLock) {
        if (isLock) {
            for (Goal.Flag value : Goal.Flag.values()) {
                goalSelector.disableControlFlag(value);
            }
        }
        else {
            for (Goal.Flag value : Goal.Flag.values()) {
                goalSelector.enableControlFlag(value);
            }
        }
    }

    @Override
    public void swing(InteractionHand pHand) {
        super.swing(pHand);
        startAttackAnim = true;
    }

    boolean isAttacking() {
        return entityData.get(ATTACKING);
    }

    boolean startAttackAnim;

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "standby_controller",
                0, this::standbyPredicate));

        data.addAnimationController(new AnimationController<>(this, "walk_controller",
                0, this::walkPredicate));

        data.addAnimationController(new AnimationController<>(this, "attack_controller",
                0, this::attackPredicate));
    }

    private final String standbyAnimName = "animation.%s.standby".formatted(getModelName());
    private final String walkAnimName = "animation.%s.walk".formatted(getModelName());
    private final String attackAnimName = "animation.%s.attack".formatted(getModelName());

    private <T extends IAnimatable> PlayState standbyPredicate(AnimationEvent<T> event) {
        if (stateManager.isActive(STANDBY_STATE)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation(standbyAnimName, true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private <T extends IAnimatable> PlayState walkPredicate(AnimationEvent<T> event) {
        if (stateManager.isActive(WALK_STATE)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation(walkAnimName, true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private <T extends IAnimatable> PlayState attackPredicate(AnimationEvent<T> event) {
        if (stateManager.isActive(ATTACK_STATE)) {
            AnimationController<?> controller = event.getController();

            if (startAttackAnim) {
                startAttackAnim = false;
                // 清除对话缓存，使得 should loop 为 false 时也可以再次播放动画
                controller.markNeedsReload();

                controller.setAnimation(new AnimationBuilder().addAnimation(attackAnimName, false));
            }

            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    protected abstract String getModelName();
    protected abstract void attackTarget(LivingEntity target);
}
