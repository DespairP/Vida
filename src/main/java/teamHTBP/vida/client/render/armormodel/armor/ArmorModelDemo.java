package teamHTBP.vida.client.render.armormodel.armor;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ArmorModelDemo extends AbstractModelElementArmor<Player>{
    private ModelPart head_2_r1;

    public ArmorModelDemo(ModelPart root, EquipmentSlot part) {
        super(root, part);
    }

    @Override
    public void initModel(ModelPart root) {

    }
}
