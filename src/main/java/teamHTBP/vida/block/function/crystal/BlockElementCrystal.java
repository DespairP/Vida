package teamHTBP.vida.block.function.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.block.base.ModBaseEntityBlock;
import teamHTBP.vida.blockentity.crystal.IElementCrystal;

public abstract class BlockElementCrystal<T extends BlockEntity & IElementCrystal> extends ModBaseEntityBlock<T> {
    public VoxelShape SHAPE;
    public int element = 0;

    public BlockElementCrystal(RegistryObject<BlockEntityType<T>> object, int element) {
        super(Properties.of(Material.GLASS).strength(1.0F).noOcclusion().sound(SoundType.GLASS).lightLevel((state) -> 15).noOcclusion(), object);

        this.element = element;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        SHAPE = Block.box(6, 6, 6, 12, 12, 12);
        return SHAPE;
    }


    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
