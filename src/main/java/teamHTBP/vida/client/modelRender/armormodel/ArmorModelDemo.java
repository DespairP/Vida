package teamHTBP.vida.client.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorModelDemo extends AbstractModelElementArmor<PlayerEntity>{
    private ModelRenderer head_2_r1;

    public ArmorModelDemo(EquipmentSlotType part) {
        super(128, 128, part);
    }


    @Override
    public void initModel() {}
}
