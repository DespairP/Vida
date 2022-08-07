package teamHTBP.vida.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import teamHTBP.vida.common.entity.goal.AttackManagerMeleeAttackGoal;
import teamHTBP.vida.common.entity.manager.AttackManager;

/**
 * @author DustW
 */
public class AncientBeliever extends AbstractBeliever {

    public AncientBeliever(EntityType<AncientBeliever> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, new AttackManager(20, (int) (.5 * 20)));
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
        this.goalSelector.addGoal(2, new AttackManagerMeleeAttackGoal(this));
    }

    @Override
    protected String getModelName() {
        return "ancient_believer";
    }

    @Override
    protected void attackTarget(LivingEntity target) {
        if (getTarget() != null)
            doHurtTarget(getTarget());
    }
}
