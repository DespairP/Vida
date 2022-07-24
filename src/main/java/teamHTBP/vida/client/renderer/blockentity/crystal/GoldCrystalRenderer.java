package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.blockentity.crystal.GoldElementCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;


public class GoldCrystalRenderer extends BaseElementCrystalRenderer<GoldElementCrystalBlockEntity> {
    public GoldCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.GOLD);
    }
}
