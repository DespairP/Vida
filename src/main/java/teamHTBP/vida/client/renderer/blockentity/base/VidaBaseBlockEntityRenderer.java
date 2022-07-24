package teamHTBP.vida.client.renderer.blockentity.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * @author DustW
 */
public abstract class VidaBaseBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    protected final BlockEntityRendererProvider.Context context;
    protected final BlockEntityRenderDispatcher renderer;

    public VidaBaseBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        renderer = Minecraft.getInstance().getBlockEntityRenderDispatcher();
    }
}
