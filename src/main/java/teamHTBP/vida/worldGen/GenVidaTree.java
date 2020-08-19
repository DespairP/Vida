package teamHTBP.vida.worldGen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.*;
import teamHTBP.vida.Block.BlockLoader;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class GenVidaTree extends AbstractTreeFeature<TreeFeatureConfig> {
    public GenVidaTree() {
        super(TreeFeatureConfig::func_227338_a_);

    }

    protected boolean hasRoom(IWorldGenerationReader world, BlockPos pos, int height, BaseTreeFeatureConfig config) {
        return this.isSpaceAt(world, pos, height) && this.validSoil(world, pos, config.getSapling());
    }
    //生成原木
    @Override
    protected boolean func_227216_a_(IWorldGenerationReader p_227216_1_, Random p_227216_2_, BlockPos p_227216_3_, Set<BlockPos> p_227216_4_, MutableBoundingBox p_227216_5_, BaseTreeFeatureConfig p_227216_6_) {
        if (!isAirOrLeaves(p_227216_1_, p_227216_3_) && !isTallPlants(p_227216_1_, p_227216_3_) && !isWater(p_227216_1_, p_227216_3_)) {
            return false;
        } else {
            this.func_227217_a_(p_227216_1_, p_227216_3_, p_227216_6_.trunkProvider.getBlockState(p_227216_2_, p_227216_3_), p_227216_5_);
            p_227216_4_.add(p_227216_3_.toImmutable());
            return true;
        }
    }

    //生成树叶
    protected boolean func_227219_b_(IWorldGenerationReader p_227219_1_, Random p_227219_2_, BlockPos p_227219_3_, Set<BlockPos> p_227219_4_, MutableBoundingBox p_227219_5_, BaseTreeFeatureConfig p_227219_6_) {
        if (!isAirOrLeaves(p_227219_1_, p_227219_3_) && !isTallPlants(p_227219_1_, p_227219_3_) && !isWater(p_227219_1_, p_227219_3_)) {
            return false;
        } else {
            this.func_227217_a_(p_227219_1_, p_227219_3_, p_227219_6_.leavesProvider.getBlockState(p_227219_2_, p_227219_3_), p_227219_5_);
            p_227219_4_.add(p_227219_3_.toImmutable());
            return true;
        }
    }

    /* ---   生成树干的方法   ---*/


    /*生成一圈*/
    public void generateRound(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> Set, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn,int round){
        for(int i=0;i<round;i++){
            for(int j=0;j<round;j++){
                this.func_227216_a_(generationReader, rand, positionIn.add(i, 0, j), Set, boundingBoxIn, configIn);
            }
        }

    }

    /*生成一圈
    * 角不生成
    * 高度随机
    * */
    public void generateRound(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> Set, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn,int round,boolean generateCorner,int maxheight){
        for(int i=0;i<round;i++){
            for(int j=0;j<round;j++){
                //角不生成
                if((i==0 && j==0) || (i == round - 1 && j== round - 1) || (i == 0 && j == round - 1) || (i== round - 1 && j == 0)) continue;

                int randheight = rand.nextInt(maxheight) + 1;
                for(int height = 0;height < randheight; height++){
                    this.func_227216_a_(generationReader, rand, positionIn.add(i, height, j), Set, boundingBoxIn, configIn);
                }
            }
        }

    }

    /*
    * 更加稀少的生成方法
    *
    *
    * */
    public void generateRound(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> Set, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn,int round,boolean generateCorner,boolean lux,int maxheight,int maxLog){
        int generate = 0;
        for(int i=0;i<round;i++){
            for(int j=0;j<round;j++){
                if((i==0 && j==0) || (i == round - 1 && j== round - 1) || (i == 0 && j == round - 1) || (i== round - 1 && j == 0)) continue;
                //里面不填充
                if( (i< round-1 && j < round -1) && (i > 0 && j > 0)) continue;
                if(generate > maxLog) continue;
                if(rand.nextBoolean()) continue;
                int randheight = rand.nextInt(maxheight) + 1;
                for(int height = 0;height < randheight; height++){
                    if(rand.nextFloat() <= 0.45f){
                    this.func_227216_a_(generationReader, rand, positionIn.add(i, height, j), Set, boundingBoxIn, configIn);
                    generate ++;
                    }
                }
            }
        }

    }

    /*--- 生成树叶的方法 ---*/
    /*生成一圈树叶*/
    public void generateRoundLeaves(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> Set, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn,int round){
        for(int i=0;i<round;i++){
            for(int j=0;j<round;j++){
                this.func_227219_b_(generationReader, rand, positionIn.add(i, 0, j), Set, boundingBoxIn, configIn);
            }
        }

    }

    protected void func_227257_b_(IWorldGenerationReader p_227257_1_, Random p_227257_2_, BlockPos p_227257_3_, int p_227257_4_, Set<BlockPos> p_227257_5_, MutableBoundingBox p_227257_6_, BaseTreeFeatureConfig p_227257_7_) {
        int i = p_227257_4_ * p_227257_4_;

        for(int j = -p_227257_4_; j <= p_227257_4_; ++j) {
            for(int k = -p_227257_4_; k <= p_227257_4_; ++k) {
                if (j * j + k * k <= i) {
                    this.func_227219_b_(p_227257_1_, p_227257_2_, p_227257_3_.add(j, 0, k), p_227257_5_, p_227257_6_, p_227257_7_);
                }
            }
        }

    }



    /**----  ----**/
    /**
     * returns whether or not there is space for a tree to grow at a certain position
     */
    private boolean isSpaceAt(IWorldGenerationBaseReader worldIn, BlockPos leavesPos, int height) {
        boolean flag = true;
        if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= worldIn.getMaxHeight()) {
            for(int i = 0; i <= 1 + height; ++i) {
                int j = 2;
                if (i == 0) {
                    j = 1;
                } else if (i >= 1 + height - 2) {
                    j = 2;
                }

                for(int k = -j; k <= j && flag; ++k) {
                    for(int l = -j; l <= j && flag; ++l) {
                        if (leavesPos.getY() + i < 0 || leavesPos.getY() + i >= worldIn.getMaxHeight() || !canBeReplacedByLogs(worldIn, leavesPos.add(k, i, l))) {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        } else {
            return false;
        }
    }

    private boolean validSoil(IWorldGenerationReader worldIn, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
        BlockPos blockpos = pos.down();
        if (isSoil(worldIn, blockpos, sapling) && pos.getY() >= 2) {
            setDirtAt(worldIn, blockpos, pos);
            setDirtAt(worldIn, blockpos.east(), pos);
            setDirtAt(worldIn, blockpos.south(), pos);
            setDirtAt(worldIn, blockpos.south().east(), pos);
            return true;
        } else {
            return false;
        }
    }


    //生成树结构
    @Override
    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> Set, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn) {
        int height = rand.nextInt(3) + 11;
        int baseheight = 4;
        if(!this.hasRoom(generationReader,positionIn,10,configIn))
        {
            return false;
        }else{
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            //System.out.println("Vida Tree has generated:");
            //System.out.println(positionIn.getX() + "  " + positionIn.getY() + "  " + positionIn.getZ());
            for(int i = 0; i < height ; ++i) {
                //24
                //13
                //生成最底部树干，大小4*4
                if(i <= baseheight){
                     this.generateRound(generationReader, rand, positionIn.up(i), Set, p_225557_5_, boundingBoxIn, configIn, 4);

                }else if(i <= baseheight + 3){
                    //生成底部上边的树干，大小2*2
                    this.generateRound(generationReader, rand, positionIn.add(1, i, 1), Set, p_225557_5_, boundingBoxIn, configIn, 2);
                    //同样，添加点缀
                    if( i == baseheight + 3 ){
                        this.generateRound(generationReader, rand, positionIn.add(1, i, 0), Set, p_225557_5_, boundingBoxIn, configIn, 1);
                    }
                }else if(i <= height){
                    //生成树干，大小2*2，有偏移
                    //添加点缀
                    if(i <= baseheight + 4) {
                        this.generateRound(generationReader, rand, positionIn.add(1, i, 2), Set, p_225557_5_, boundingBoxIn, configIn, 1);
                        this.generateRound(generationReader, rand, positionIn.add(2, i, 1), Set, p_225557_5_, boundingBoxIn, configIn, 1);
                    }else if(i <= baseheight + 5 && rand.nextBoolean()){
                        this.generateRound(generationReader, rand, positionIn, Set, p_225557_5_, boundingBoxIn, configIn, 1);
                    }
                    //生成树干
                    this.generateRound(generationReader, rand, positionIn.up(i), Set, p_225557_5_, boundingBoxIn, configIn, 2);

                }
            }
            //主树干剩下的装饰
            //底部树干的树皮
            this.generateRound(generationReader, rand, positionIn.add(-1, 0, -1), Set, p_225557_5_, boundingBoxIn, configIn, 6,false,3);
            this.generateRound(generationReader, rand, positionIn.add(-2, 0, -2), Set, p_225557_5_, boundingBoxIn, configIn, 8,false,true,2,rand.nextInt(9) + 9);
            //底部树干上面一层的树皮
            this.generateRound(generationReader, rand, positionIn.up(baseheight + 1), Set, p_225557_5_, boundingBoxIn, configIn, 4,false,3);


            //生成最顶上的树枝
            blockpos$mutable.setPos(positionIn).move(-1, height - 1, 0);
            //点缀方块
            this.func_227216_a_(generationReader, rand, positionIn.add(-1, height - 1, 0), p_225557_5_, boundingBoxIn, configIn);
            this.func_227216_a_(generationReader, rand, positionIn.add(-1, height - 2, 0), p_225557_5_, boundingBoxIn, configIn);
            this.func_227216_a_(generationReader, rand, positionIn.add(-1, height - 1 , 1), p_225557_5_, boundingBoxIn, configIn);
            //最顶树干方块
            this.func_227216_a_(generationReader, rand, positionIn.add(1,     height  , 0), p_225557_5_, boundingBoxIn, configIn);
            //树干后右侧方块
            this.func_227216_a_(generationReader, rand, positionIn.add(0,     height - 1   , 2), p_225557_5_, boundingBoxIn, configIn);
            this.func_227216_a_(generationReader, rand, positionIn.add(-1,    height - 1   , 3), p_225557_5_, boundingBoxIn, configIn);
            for(int i = 0 ; i <= 4; i++){
                   switch (i){
                       case 1:
                           for(int j = 0; j<4 ; j++) {

                               if (rand.nextBoolean())
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(-1, 1, -1), p_225557_5_, boundingBoxIn, configIn);
                               else
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(-1, 1, 0), p_225557_5_, boundingBoxIn, configIn);
                           }
                           this.func_227216_a_(generationReader, rand, blockpos$mutable.move(0, -1, 2), p_225557_5_, boundingBoxIn, configIn);
                           this.func_227216_a_(generationReader, rand, blockpos$mutable.move(-1, 1, 1), p_225557_5_, boundingBoxIn, configIn);
                           break;
                       case 2:
                           blockpos$mutable.setPos(positionIn).move(0, height - 1, 2);
                           for(int j = 0; j < 3 ; j++) {
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(-1, 1, 1), p_225557_5_, boundingBoxIn, configIn);

                           }
                           break;
                       case 3:
                           blockpos$mutable.setPos(positionIn).move(1, height - 1, 0);
                           for(int j = 0; j<4 ; j++) {
                               if (rand.nextFloat() > 0.3f)
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(1, 1, -1), p_225557_5_, boundingBoxIn, configIn);
                               else
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(1, 1, 0), p_225557_5_, boundingBoxIn, configIn);
                           }
                           break;
                       case 4:
                           blockpos$mutable.setPos(positionIn).move(0, height - 1, 2);
                           for(int j = 0; j<4 ; j++) {
                               if (rand.nextFloat() > 0.4f)
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(1, 1, 1), p_225557_5_, boundingBoxIn, configIn);
                               else
                                   this.func_227216_a_(generationReader, rand, blockpos$mutable.move(0, 1, 0), p_225557_5_, boundingBoxIn, configIn);
                           }
                   }
            }

            //生成树叶
            this.generateRoundLeaves(generationReader, rand, positionIn.add(-2,height + 2,-1), Set, p_225557_5_, boundingBoxIn, configIn,6);

            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 6,-2),3, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 5,-2),6, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 4,-2),8, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 3,-2),5, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 2,-2),2, Set, boundingBoxIn, configIn);


            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 6,4),2, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 5,4),5, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 4,4),7, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 3,4),4, Set, boundingBoxIn, configIn);
            this.func_227257_b_(generationReader,rand,positionIn.add(2,height + 2,4),3, Set, boundingBoxIn, configIn);

            this.func_227257_b_(generationReader,rand,positionIn.add(1,height + 6,0),4, Set, boundingBoxIn, configIn);
            return true;
        }
    }
}
