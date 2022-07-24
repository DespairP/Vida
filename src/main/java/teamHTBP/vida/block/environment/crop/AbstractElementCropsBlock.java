package teamHTBP.vida.block.environment.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;
import teamHTBP.vida.block.base.IElementBlock;
import teamHTBP.vida.element.Allelopathy;
import teamHTBP.vida.element.ElementHelper;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;

import java.util.Random;

public abstract class AbstractElementCropsBlock extends CropBlock implements IElementBlock {
    private final static IntegerProperty AGE = BlockStateProperties.AGE_5;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    private final IElement element;
    private int maxStage = 5;

    public AbstractElementCropsBlock(int stage, IElement element) {
        super(Properties.of(Material.PLANT).noCollission().sound(SoundType.CROP)
                .noOcclusion().strength(0.5f, 0).randomTicks());
        this.maxStage = stage;
        this.element = element;
    }

    protected static float getGrowthChance(Block blockIn, BlockGetter worldIn, BlockPos pos) {
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

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    /***
     * 获取植物的AGE PROPERTY
     * @return Property
     */
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    /***
     * 是否能在现有的土地上生长
     * @return 是否能生长
     * */
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock() == Blocks.FARMLAND;
    }

    /***
     * 获取作物能生长的最大值
     * @return 生长的最大值
     */
    public int getMaxStage() {
        return maxStage;
    }

    /***
     * 获取植物的年龄
     * @param state 植物的state
     * @return 植物的年龄
     */
    @Override
    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    /***
     * 注册State
     * */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    /***
     * 设置植物的年龄
     * @param age 设置的年龄
     * @return 修改后的state
     */
    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    /***
     * 是否已经到达最大的生长年龄
     * @param state 植物的state
     * @return 是否已经达到最大生长状态
     */
    @Override
    public boolean isMaxAge(BlockState state) {
        return state.getValue(this.getAgeProperty()) >= this.getMaxStage();
    }

    /*是否能生长*/
    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    /*是否能使用骨粉，默认不能使用*/
    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    /*骨粉生长逻辑*/
    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + 1;
        int j = this.maxStage;
        if (i > j) {
            i = j;
        }
    }

    /*当玩家碰撞的时候，破坏方块*/
    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof Ravager && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true, entityIn);
        }
        super.entityInside(state, worldIn, pos, entityIn);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.canSurvive(state, worldIn, pos);
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }

        if (worldIn.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            //元素不为相克时才能生长
            if (i < this.getMaxStage() &&
                    ElementHelper.getRelationShip(element, ElementHelper.getBiomeElement(worldIn.getBiome(pos).value())) != Allelopathy.Conflict) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlock(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }

    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getBaseSeedId());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return getSeedsItem();
    }

    protected ItemLike getSeedsItem() {return Items.WHEAT_SEEDS;};

    @Override
    public IElement getElement() {
        return this.element;
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.CROP;
    }


    /**生长逻辑接口*/
    public interface GrowLogic{
        /**生长逻辑*/
        public boolean grow(BlockState state,
                            ServerLevel worldIn,
                            BlockPos pos,
                            Random rand,
                            EnumElements cropElement,
                            int currentAge,
                            int maxAge);
    }
}
