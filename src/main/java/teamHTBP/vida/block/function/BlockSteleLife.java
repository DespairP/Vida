package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.helper.blockHelper.BlockMutiDoubleWithY;

import java.util.Random;

public class BlockSteleLife extends BlockMutiDoubleWithY {

    public BlockSteleLife() {
        super(Block.Properties.of(Material.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).noOcclusion());
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
    }


}
