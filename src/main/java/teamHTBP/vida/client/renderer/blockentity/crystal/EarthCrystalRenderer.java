package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.common.blockentity.crystal.EarthCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class EarthCrystalRenderer extends BaseElementCrystalRenderer<EarthCrystalBlockEntity> {
    public EarthCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.EARTH);
    }
}