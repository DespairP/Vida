package teamHTBP.vida.modelRender.tileEntityModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * @author DustW
 */
public abstract class ModBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    BlockEntityRendererProvider.Context context;
    BlockEntityRenderDispatcher renderer;

    public ModBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        renderer = Minecraft.getInstance().getBlockEntityRenderDispatcher();
    }
}
