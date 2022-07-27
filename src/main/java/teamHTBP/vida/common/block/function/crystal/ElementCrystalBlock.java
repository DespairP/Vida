package teamHTBP.vida.common.block.function.crystal;

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
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.crystal.IElementCrystal;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.api.core.element.IElementHolder;

public abstract class ElementCrystalBlock<T extends BlockEntity & IElementCrystal> extends VidaBaseEntityBlock<T> implements IElementHolder {
    public final VoxelShape SHAPE = Block.box(6, 6, 6, 12, 12, 12);
    public IElement element;

    public ElementCrystalBlock(RegistryObject<BlockEntityType<T>> object, IElement element) {
        super(Properties.of(Material.GLASS).strength(1.0F).noOcclusion().sound(SoundType.GLASS).lightLevel((state) -> 15).noOcclusion(), object);

        this.element = element;
    }

    @Override
    public IElement getElement() {
        return element;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
