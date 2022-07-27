package teamHTBP.vida.client.renderer.blockentity.crystal;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import teamHTBP.vida.common.blockentity.crystal.FireElementCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class FireCrystalRenderer extends BaseElementCrystalRenderer<FireElementCrystalBlockEntity> {
    public FireCrystalRenderer(BlockEntityRendererProvider.Context context) {
        super(context, EnumElements.FIRE);
    }
}