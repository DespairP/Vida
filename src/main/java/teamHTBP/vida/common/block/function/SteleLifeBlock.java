package teamHTBP.vida.common.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import teamHTBP.vida.common.block.MutiDoubleWithYBlock;

import java.util.Random;

public class SteleLifeBlock extends MutiDoubleWithYBlock {

    public SteleLifeBlock() {
        super(Block.Properties.of(Material.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).noOcclusion());
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
    }


}
