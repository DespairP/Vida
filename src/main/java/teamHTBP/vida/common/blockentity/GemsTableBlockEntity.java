package teamHTBP.vida.common.blockentity;

import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.api.common.blockentity.UseAble;
import teamHTBP.vida.common.blockentity.base.VidaBaseBlockEntity;
import teamHTBP.vida.helper.box.BoxTransform;
import teamHTBP.vida.helper.box.InteractBox;
import teamHTBP.vida.helper.item.ItemHandlerHelper;
import teamHTBP.vida.helper.math.VecHelper;
import teamHTBP.vida.helper.math.angle.AngleHolder;

import java.util.List;

/**
 * @author DustW
 */
public class GemsTableBlockEntity extends VidaBaseBlockEntity implements UseAble {

    @Getter
    ItemStackHandler handler = new ItemStackHandler(9);
    @Getter
    List<InteractBox> behaviours;

    public GemsTableBlockEntity(BlockPos pos, BlockState state) {
        super(VidaBlockEntityLoader.GEMS_TABLE.get(), pos, state);

        behaviours = List.of(
                new InteractBox(Direction.UP, getBlockPos(),
                        new BoxTransform(.2F,
                                VecHelper.voxelSpace(5, 2, 5),
                                new AngleHolder.DirectionAngle(Direction.UP)
                        )),
                new InteractBox(Direction.UP, getBlockPos(),
                        new BoxTransform(.2F,
                                VecHelper.voxelSpace(10, 2, 10),
                                new AngleHolder.DirectionAngle(Direction.UP)
                        )),
                new InteractBox(Direction.UP, getBlockPos(),
                        new BoxTransform(.2F,
                                VecHelper.voxelSpace(5, 2, 10),
                                new AngleHolder.DirectionAngle(Direction.UP)
                        )),
                new InteractBox(Direction.UP, getBlockPos(),
                        new BoxTransform(.2F,
                                VecHelper.voxelSpace(10, 2, 5),
                                new AngleHolder.DirectionAngle(Direction.UP)
                        ))
        );
    }

    @Override
    public InteractionResult use(Player player, InteractionHand hand, BlockHitResult hit) {
        assert level != null;

        for (int i = 0; i < behaviours.size(); i++) {
            if (behaviours.get(i).testHit(player, level.isClientSide)) {
                ItemStack stack = handler.extractItem(i, handler.getSlotLimit(i), false);
                handler.insertItem(i, player.getItemInHand(hand), false);
                player.setItemInHand(hand, stack);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void tick() {
        // nothing to do
    }

    @Override
    public List<ItemStack> getDrops() {
        return ItemHandlerHelper.getItems(handler);
    }

    public static final String HANDLER_KEY = "handler";

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);

        handler.deserializeNBT(tag.getCompound(HANDLER_KEY));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        tag.put(HANDLER_KEY, handler.serializeNBT());
    }
}
