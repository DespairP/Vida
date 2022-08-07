package teamHTBP.vida.common.item.tool;

import com.mojang.math.Vector3f;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.common.particle.particle.ElementTailParticle;
import teamHTBP.vida.helper.color.ARGBColor;
import teamHTBP.vida.helper.math.Vector3;
import teamHTBP.vida.helper.math.curve.Bezier3Curve;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Comparator;
import java.util.List;

/**
 * @author DustW
 */
public class ElementWandItem extends Item {
    public ElementWandItem(Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // if (!level.isClientSide) {
        //     ElementBallProjectile ball = new ElementBallProjectile(player, level);
        //     Vec3 look = player.getLookAngle();
        //     ball.shoot(look.x, look.y, look.z, 1, 0);
        //     level.addFreshEntity(ball);
        // }

        if (level.isClientSide) {
            spawnParticles(level, player);
        }

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    Direction[] directions = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    /** 十字 */
    protected void spawnParticles(Level level, Player player) {
        for (Direction value : directions) {
            Vec3 first = player.position();
            Vec3 t = first.add(0, 10, 0);
            Vector3f step = value.step();
            step.mul(10);
            Vec3 s = first.add(new Vec3(step));
            Vec3 end = first.add(0, -5, 0);

            new ElementTailParticle((ClientLevel) level, new Bezier3Curve(
                    new Vector3(first),
                    new Vector3(s),
                    new Vector3(t),
                    new Vector3(end)
            ), 200, ARGBColor.DARK_RED).addParticle();
        }
    }

    /** 追踪 */
    protected void spawnParticle(Level level, Player player) {
        LivingEntity target = findTarget(level, player);

        if (target != null) {
            Vec3 p = player.getEyePosition();
            Vec3 a = player.getLookAngle().normalize().scale(3).add(p);
            Vec3 b = target.position();

            new ElementTailParticle((ClientLevel) level, new Bezier3Curve(
                    new Vector3(p),
                    new Vector3(a),
                    new Vector3(b),
                    new Vector3(b)
            ), 80, ARGBColor.CYAN_GREEN).addParticle();
        }
    }

    @Nullable
    protected LivingEntity findTarget(Level level, Player player) {
        List<? extends LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class,
                AABB.ofSize(player.position(), 80, 80, 80), entity -> {
                    Vec3 directionVec = player.getLookAngle();
                    Vec3 position = entity.position();

                    return !entity.equals(player) && !entity.isSpectator() && entity.isAlive() &&
                            player.position().vectorTo(position).dot(directionVec) > 0;
                });

        if (!list.isEmpty()) {
            if (list.size() > 1)
                list.sort(Comparator.comparingDouble(o -> o.distanceToSqr(player.position())));

            return list.get(0);
        }

        return null;
    }
}
