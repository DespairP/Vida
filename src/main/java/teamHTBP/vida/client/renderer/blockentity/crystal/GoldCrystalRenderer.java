package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.common.blockentity.crystal.GoldElementCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;


public class GoldCrystalRenderer extends BaseElementCrystalRenderer<GoldElementCrystalBlockEntity> {
    public GoldCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.GOLD);
    }
}
