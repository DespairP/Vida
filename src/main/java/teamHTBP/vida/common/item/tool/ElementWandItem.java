package teamHTBP.vida.common.item.tool;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.common.entity.projectile.ElementBallProjectile;

import javax.annotation.ParametersAreNonnullByDefault;

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
        if (!level.isClientSide) {
            ElementBallProjectile ball = new ElementBallProjectile(player, level);
            Vec3 look = player.getLookAngle();
            ball.shoot(look.x, look.y, look.z, 1, 0);
            level.addFreshEntity(ball);
        }

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
