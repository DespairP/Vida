package teamHTBP.vida.common.entity;

import lombok.Getter;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import teamHTBP.vida.common.entity.goal.AttackManagerAttackGoal;
import teamHTBP.vida.common.entity.manager.AttackManager;
import teamHTBP.vida.common.entity.manager.FiniteStateManager;

/**
 * @author DustW
 */
public class AncientBeliever extends VidaPathfinderMob implements IAnimatable, IAnimationTickable, AttackManagerEntity {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(AncientBeliever.class, EntityDataSerializers.BOOLEAN);

    private static final String STANDBY_STATE = "STANDBY";
    private static final String WALK_STATE = "WALK";
    private static final String ATTACK_STATE = "ATTACK";

    @Getter
    private final AttackManager attackManager = new AttackManager(this, 20, (int) (.5 * 20));
    private final AnimationFactory factory = new AnimationFactory(this);
    private final FiniteStateManager<AncientBeliever> stateManager;

    public AncientBeliever(EntityType<AncientBeliever> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        stateManager = new FiniteStateManager<>(this);
        stateManager.addState(mob -> !moving() && !has(GOAL_FLAG_JUMP) && !isAttacking(), STANDBY_STATE);
        stateManager.addState(mob -> moving() && !isAttacking(), WALK_STATE);
        stateManager.addState(mob -> !moving() && isAttacking(), ATTACK_STATE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 3);
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
        this.goalSelector.addGoal(2, new AttackManagerAttackGoal(this));
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
            attackManager.tick();

            entityData.set(ATTACKING, attackManager.isStarted());

            if (attackManager.isAttacking() && getTarget() != null) {
                doHurtTarget(getTarget());
            }

            setFlagLock(isAttacking());
        }

        stateManager.refresh();
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

    boolean isAttacking() {
        return entityData.get(ATTACKING);
    }

    boolean startAttackAnim;

    @Override
    public void swing(InteractionHand pHand) {
        super.swing(pHand);
        startAttackAnim = true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "standby_controller",
                0, this::standbyPredicate));

        data.addAnimationController(new AnimationController<>(this, "walk_controller",
                0, this::walkPredicate));

        data.addAnimationController(new AnimationController<>(this, "attack_controller",
                0, this::attackPredicate));
    }

    private static final String STANDBY_ANIM = "animation.ancient_believer.standby";
    private static final String WALK_ANIM = "animation.ancient_believer.walk";
    private static final String ATTACK_ANIM = "animation.ancient_believer.attack";

    private <T extends IAnimatable> PlayState standbyPredicate(AnimationEvent<T> event) {
        if (stateManager.isActive(STANDBY_STATE)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation(STANDBY_ANIM, true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private <T extends IAnimatable> PlayState walkPredicate(AnimationEvent<T> event) {
        if (stateManager.isActive(WALK_STATE)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation(WALK_ANIM, true));
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

                controller.setAnimation(new AnimationBuilder().addAnimation(ATTACK_ANIM, false));
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
}
