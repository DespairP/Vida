package teamHTBP.vida.common.block.environment.crop;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import teamHTBP.vida.core.element.Allelopathy;
import teamHTBP.vida.core.element.ElementHelper;

import java.util.List;

import static net.minecraftforge.common.ForgeHooks.onCropsGrowPre;
import static teamHTBP.vida.common.block.environment.crop.AbstractElementCropsBlock.GrowLogic;

/**
 * 统一管理生长逻辑
 *
 */
public class GrowthLogics {
    private final static IntegerProperty AGE = BlockStateProperties.AGE_5;
    private final static List<Allelopathy> ILLEGAL_ALLELOPATHY_LIST = ImmutableList.of(Allelopathy.UnDefined,Allelopathy.Conflict);

    /**元素相生相克生长方式，光源在>9以上*/
    public static final GrowLogic MUTUAL_WITH_LIGHT = (state,worldIn,pos,rand,cropElement,age,maxAge) -> {
        // 如果加载超界或者光源不满足的情况下，停止生长
        if (!worldIn.isAreaLoaded(pos, 1) || worldIn.getRawBrightness(pos, 0) < 9) return false;
        // 如果不符合相生定律,不生长
        final Allelopathy allelopathy = ElementHelper.getRelationShip(cropElement, ElementHelper.getBiomeElement(worldIn.getBiome(pos).value()));
        if(ILLEGAL_ALLELOPATHY_LIST.contains(allelopathy)) return false;
        final float f = getGrowthChance(state.getBlock(), worldIn, pos);
        return age < maxAge && onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0);
    };

    /**以夜晚生长方式，光源在<9以上*/
    public static final GrowLogic NIGHT_LIGHT = (state,worldIn,pos,rand,cropElement,age,maxAge) -> {
        if (!worldIn.isAreaLoaded(pos, 1)) return false;
        if (!worldIn.isNight() || worldIn.getRawBrightness(pos,0) > 9) return false;
        return true;
    };




    /**获取当前植物年龄*/
    public static int getAge(BlockState state){
        return state.getValue(AGE);
    }

    /**获取生长概率*/
    public static float getGrowthChance(Block blockIn, BlockGetter worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.offset(i, 0, j), Direction.UP, (net.minecraftforge.common.IPlantable) blockIn)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, blockpos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }
}
