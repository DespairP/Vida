package teamHTBP.vida.common.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import teamHTBP.vida.core.element.ElementManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DustW
 */
public class AncientBeliever extends CreatureEntity implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    /**是否正在移动*/
    private static final DataParameter<Boolean> IS_MOVING = EntityDataManager.createKey(AncientBeliever.class, DataSerializers.BOOLEAN);
    /**是否被锁定*/
    private static final DataParameter<Boolean> IS_TARGET = EntityDataManager.createKey(AncientBeliever.class, DataSerializers.BOOLEAN);
    /**是否正在跳跃*/
    private static final DataParameter<Boolean> IS_JUMPING = EntityDataManager.createKey(AncientBeliever.class, DataSerializers.BOOLEAN);
    /**是否正在四处观望*/
    private static final DataParameter<Boolean> IS_LOOKING = EntityDataManager.createKey(AncientBeliever.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean>[] PARAMETERS = new DataParameter[]{
            IS_MOVING, IS_LOOKING, IS_JUMPING, IS_TARGET
    };


    public AncientBeliever(EntityType<AncientBeliever> pEntityType, World world) {
        super(pEntityType, world);
    }

    /**加入生物AI*/
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this,0.6F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    /**定义属性*/
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return AttributeModifierMap.createMutableAttribute()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE,10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2F)
                .createMutableAttribute(ForgeMod.ENTITY_GRAVITY.get(), 1D)
                .createMutableAttribute(Attributes.ARMOR,1.0D);
    }

    /**加入动画控制器*/
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicateStand));
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicateMoving));
    }

    /**注册生物变量*/
    @Override
    public void registerData() {
        super.registerData();
        for(DataParameter<Boolean> parameter:PARAMETERS){
            this.dataManager.register(parameter,false);
        }
    }

    private <T extends IAnimatable> PlayState predicateStand(AnimationEvent<T> event) {
        if(!isEntityMoving()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.ancient_believer.standby", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private <T extends IAnimatable> PlayState predicateMoving(AnimationEvent<T> event) {
        if(isEntityMoving()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.ancient_believer.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    /**获取tick*/
    @Override
    public int tickTimer() {
        return ticksExisted;
    }

    /**tick*/
    @Override
    public void tick() {
        super.tick();
        if(world.isRemote){
            return;
        }
        //同步
        final Goal.Flag[] values = Goal.Flag.values();
        for (int i = 0; i < values.length; i++) {
            this.dataManager.set(PARAMETERS[i], hasGoalFlag(values[i]));
        }
    }

    private boolean hasGoalFlag(Goal.Flag flag) {
        return goalSelector.getRunningGoals().anyMatch(
                goal -> goal.isRunning() && goal.getMutexFlags().contains(flag)
        );
    }

    /**生物是否在移动*/
    private boolean isEntityMoving(){
        return (this.dataManager.get(IS_MOVING) || this.dataManager.get(IS_JUMPING)) && !positionOffset().equals(new Vector3d(0,0,0));
    }

    /**nbt读取*/
    @Override
    public void readAdditional(CompoundNBT compound) {
        this.dataManager.set(IS_MOVING, compound.getBoolean("moving"));
        this.dataManager.set(IS_LOOKING,compound.getBoolean("looking"));
        this.dataManager.set(IS_JUMPING,compound.getBoolean("jumping"));
        this.dataManager.set(IS_TARGET,compound.getBoolean("targeting"));

    }

    /**nbt写入*/
    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putBoolean("moving", this.dataManager.get(IS_MOVING));
        compound.putBoolean("looking", this.dataManager.get(IS_LOOKING));
        compound.putBoolean("jumping", this.dataManager.get(IS_JUMPING));
        compound.putBoolean("targeting", this.dataManager.get(IS_TARGET));
    }


}
