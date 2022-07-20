package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ArmorModelDemo extends AbstractModelElementArmor<Player>{
    private ModelRenderer head_2_r1;

    public ArmorModelDemo(EquipmentSlot part) {
        super(128, 128, part);
    }


    @Override
    public void initModel() {}
}
