package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelGoldBoots extends AbstractModelElementBoots {

    public ArmorModelGoldBoots() {
        leg_right = new ModelPart(this);

        leg_right.setPos(-2.0F, 12.0F, 0.0F);
        leg_right.texOffs(0, 67).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(0, 76).addBox(-2.0F, 6.9F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, true);

        leg_left = new ModelPart(this);
        leg_left.setPos(2.0F, 12.0F, 0.0F);
        leg_left.texOffs(0, 67).addBox(-2.0F, -0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(0, 76).addBox(-2.0F, 6.9F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
    }

}
