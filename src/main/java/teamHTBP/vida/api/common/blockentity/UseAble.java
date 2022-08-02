package teamHTBP.vida.api.common.blockentity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

/**
 * @author DustW
 */
public interface UseAble {
    InteractionResult use(Player player, InteractionHand hand, BlockHitResult hit);
}
