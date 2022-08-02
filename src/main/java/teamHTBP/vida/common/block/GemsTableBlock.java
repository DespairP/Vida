package teamHTBP.vida.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.api.common.blockentity.UseAble;
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.GemsTableBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 */
@SuppressWarnings("deprecation")
public class GemsTableBlock extends VidaBaseEntityBlock<GemsTableBlockEntity> {

    public GemsTableBlock() {
        super(Properties.of(Material.STONE), VidaBlockEntityLoader.GEMS_TABLE);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hit) {

        if (level.getBlockEntity(pos) instanceof UseAble useAble) {
            return useAble.use(player, hand, hit);
        }

        return InteractionResult.PASS;
    }
}
