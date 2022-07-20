package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import teamHTBP.vida.TileEntity.TileEntityOreReationMachine;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockOreReactionMachine extends Block {
    public BlockOreReactionMachine() {
        super(Properties.of(Material.STONE).noOcclusion().strength(4.0f, 4.0f));
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntityOreReationMachine tileEntityOreReationMachine = (TileEntityOreReationMachine) worldIn.getBlockEntity(pos);
        if (tileEntityOreReationMachine != null) {
            for (int i = 0; i < 4; i++)
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getSmeltSlotInv().getItem(i)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getFuelInv().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getCompleteSlot().getItem(0)));

        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityOreReationMachine();
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            TileEntityOreReationMachine tileEntityOreReationMachine = (TileEntityOreReationMachine) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityOreReationMachine, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityOreReationMachine.getBlockPos());
            });
        }
        return ActionResultType.SUCCESS;
    }
}
