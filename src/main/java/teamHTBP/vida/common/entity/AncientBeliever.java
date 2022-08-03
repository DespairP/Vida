package teamHTBP.vida.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
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

/**
 * @author DustW
 */
public class AncientBeliever extends PathfinderMob implements IAnimatable, IAnimationTickable {
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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "standby_controller",
                5, this::standbyPredicate));

        data.addAnimationController(new AnimationController<>(this, "walk_controller",
                5, this::walkPredicate));
    }

    boolean has(Goal.Flag flag) {
        return goalSelector.getRunningGoals().anyMatch(g -> g.getFlags().contains(flag));
    }

    private <T extends IAnimatable> PlayState standbyPredicate(AnimationEvent<T> event) {
        if (has(Goal.Flag.MOVE) || has(Goal.Flag.JUMP)) {
            return PlayState.STOP;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.ancient_believer.standby", true));
            return PlayState.CONTINUE;
        }
    }

    private <T extends IAnimatable> PlayState walkPredicate(AnimationEvent<T> event) {
        if (has(Goal.Flag.MOVE)) {
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
