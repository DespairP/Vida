package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
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
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import javax.annotation.Nullable;

public class BlockOreReactionMachine extends Block {
    public BlockOreReactionMachine() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(4.0f, 4.0f));
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityOreReationMachine();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            TileEntityOreReationMachine tileEntityOreReationMachine = (TileEntityOreReationMachine) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityOreReationMachine, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityOreReationMachine.getPos());
            });
        }
        return ActionResultType.SUCCESS;
    }
}
