package teamHTBP.vida.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import teamHTBP.vida.common.entity.projectile.BarrageShooter;

/**
 * @author DustW
 */
public class BarrageCrystalItem extends Item {
    public BarrageCrystalItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            BarrageShooter shooter = new BarrageShooter(pPlayer, pLevel);
            Vec3 scale = pPlayer.getLookAngle().normalize().scale(2);
            shooter.setPos(pPlayer.position().add(scale));
            shooter.shoot(scale.x, scale.y, scale.z, 2F, 0);
            pLevel.addFreshEntity(shooter);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
