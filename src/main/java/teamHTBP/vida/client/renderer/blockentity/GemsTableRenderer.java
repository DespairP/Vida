package teamHTBP.vida.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;
import teamHTBP.vida.common.blockentity.GemsTableBlockEntity;
import teamHTBP.vida.helper.box.InteractBox;
import teamHTBP.vida.helper.box.BoxRender;

import java.util.List;

/**
 * @author DustW
 */
public class GemsTableRenderer extends VidaBaseBlockEntityRenderer<GemsTableBlockEntity> {
    public GemsTableRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(GemsTableBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        List<InteractBox> behaviours = pBlockEntity.getBehaviours();

        for (int i = 0; i < behaviours.size(); i++) {
            BoxRender.renderWithItem(behaviours.get(i), pPoseStack, pBlockEntity.getHandler().getStackInSlot(i));
        }
    }
}
