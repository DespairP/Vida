package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.common.blockentity.crystal.AquaCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class AquaCrystalRenderer extends BaseElementCrystalRenderer<AquaCrystalBlockEntity> {
    public AquaCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.AQUA);
    }
}