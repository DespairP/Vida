package teamHTBP.vida.common.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.PurifiedCauldronBlockEntity;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.common.particle.option.CubeParticleOptions;

import java.util.Random;

public class PurifiedCauldronBlock extends VidaBaseEntityBlock<PurifiedCauldronBlockEntity> {
    private static final VoxelShape SHAPE;

    static {
        VoxelShape aPillar = Block.box(0.25, 2, 0.25, 2.25, 12, 2.25);//↖
        VoxelShape aBase = Block.box(0, 0, 0, 3, 3, 3);//↖基底
        VoxelShape upBase = Block.box(2, 1.75, 1, 14, 10.75, 2);
        VoxelShape bPillar = Block.box(13.75, 2, 0.25, 15.75, 12, 2.25);//↗
        VoxelShape bBase = Block.box(13, 0, 0, 16, 3, 3);//↗基底
        VoxelShape rightBase = Block.box(14, 1.75, 2, 15, 10.75, 14);
        VoxelShape cPillar = Block.box(13.75, 2, 13.75, 15.75, 12, 15.75);//↘
        VoxelShape cBase = Block.box(13, 0, 13, 16, 3, 16);//↘基底
        VoxelShape downBase = Block.box(2, 1.75, 14, 14, 10.75, 15);
        VoxelShape dPillar = Block.box(0.25, 2, 13.75, 2.25, 12, 15.75);//↙
        VoxelShape dBase = Block.box(0, 0, 13, 3, 3, 16);//↙基底
        VoxelShape leftBase = Block.box(1, 1.75, 2, 2, 10.75, 14);
        VoxelShape base = Block.box(1, 0.75, 1, 15, 1.75, 15);
        SHAPE = Shapes.or(aPillar, aBase, upBase, bPillar, bBase, rightBase, cPillar, cBase, downBase, dPillar, dBase, leftBase, base);
    }

    public PurifiedCauldronBlock() {
        super(Block.Properties.of(Material.METAL)
                .randomTicks()
                .noOcclusion()
                .sound(SoundType.STONE)
                .strength(4.0f, 4.0f),
                VidaBlockEntityLoader.TileEntityPurfiedCauldron);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (handIn == InteractionHand.MAIN_HAND) {
            PurifiedCauldronBlockEntity entity = (PurifiedCauldronBlockEntity) worldIn.getBlockEntity(pos);
            //如果是创造模式不消耗水桶
            //如果是就消耗水桶
            if (player.getInventory().getSelected().getItem() == Items.WATER_BUCKET && !entity.isWater) {
                if (player.isCreative()) {
                    entity.isWater = true;
                    worldIn.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                } else {
                    player.getInventory().getSelected().setCount(0);
                    player.getInventory().add(new ItemStack(
                            Items.BUCKET, 1));
                    entity.isWater = true;
                    worldIn.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    worldIn.sendBlockUpdated(pos, state, state, Block.UPDATE_NEIGHBORS);
                    return InteractionResult.SUCCESS;
                }
            }

        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.getBlockState(pos.below()).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.below()).getBlock() == Blocks.LAVA && !worldIn.isClientSide) {
            PurifiedCauldronBlockEntity entity = (PurifiedCauldronBlockEntity) worldIn.getBlockEntity(pos);
            entity.isFire = true;
            worldIn.sendBlockUpdated(pos, state, state, Block.UPDATE_NEIGHBORS);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        PurifiedCauldronBlockEntity entity = (PurifiedCauldronBlockEntity) worldIn.getBlockEntity(pos);
        if (entity != null) {
            //生成粒子
            if (entity.element != null && !entity.meltItem.isEmpty() && rand.nextDouble() >= 0.65) {
                double speedX = rand.nextBoolean() ? 0 + rand.nextFloat() / 1000.0F : 0 - rand.nextFloat() / 1000.0F;
                double speedY = -0.025 + rand.nextFloat() / 1000f;
                double speedZ = rand.nextBoolean() ? 0 + rand.nextFloat() / 1000.0f : 0 - rand.nextFloat() / 1000.0f;
                double posX = rand.nextBoolean() ? pos.getX() + 0.5f + rand.nextFloat() / 4 : pos.getX() + 0.5f - rand.nextFloat() / 4;
                double posY = rand.nextBoolean() ? pos.getY() + 1.2F + rand.nextFloat() / 4 : pos.getY() + 1.2F - rand.nextFloat() / 4;
                double posZ = rand.nextBoolean() ? pos.getZ() + 0.5f + rand.nextFloat() / 4 : pos.getZ() + 0.5f - rand.nextFloat() / 4;
                float r = 1;
                float g = 1;
                float b = 1;
                switch ((EnumElements) entity.element) {
                    case GOLD:
                        r = 255;
                        g = 255;
                        b = 200;
                        break;
                    case WOOD:
                        r = 73;
                        g = 175;
                        b = 92;
                        break;
                    case AQUA:
                        r = 73;
                        g = 203;
                        b = 255;
                        break;
                    case FIRE:
                        r = 255;
                        g = 30;
                        b = 43;
                        break;
                    case EARTH:
                        r = 186;
                        g = 184;
                        b = 111;
                        break;
                }
                worldIn.addParticle(new CubeParticleOptions(r, g, b, 0.02f), posX, posY, posZ, speedX, speedY, speedZ);

            }
        }

    }


    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClientSide) {
            if (entityIn instanceof ItemEntity) {
                PurifiedCauldronBlockEntity entity = (PurifiedCauldronBlockEntity) worldIn.getBlockEntity(pos);
                ItemStack itemStack = ((ItemEntity) entityIn).getItem();
                if (entity.setMeltItem(itemStack)) {
                    entityIn.discard();
                    worldIn.sendBlockUpdated(pos, state, state, Block.UPDATE_NEIGHBORS);
                    entity.setMeltSpeed();
                }
            }
        }
        super.entityInside(state, worldIn, pos, entityIn);
    }

}
