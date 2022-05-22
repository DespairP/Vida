package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorModelDemo extends AbstractModelElementArmor<PlayerEntity>{

    public ArmorModelDemo(EquipmentSlotType part) {
        super(64, 64, part);
    }


    @Override
    public void initModel() {

    }
}
