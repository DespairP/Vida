package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.blockentity.crystal.WoodElementCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class WoodCrystalRenderer extends BaseElementCrystalRenderer<WoodElementCrystalBlockEntity> {
    public WoodCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.WOOD);
    }
}
