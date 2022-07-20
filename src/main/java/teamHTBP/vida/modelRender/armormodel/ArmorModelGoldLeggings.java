package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelGoldLeggings extends AbstractModelElementLeggings {
    private final ModelPart cube_1;
    private final ModelPart cube_2;


    public ArmorModelGoldLeggings() {
        super(128, 128);

        body_low = new ModelPart(this);
        body_low.setPos(0.0F, 12.0F, 0.0F);
        body_low.texOffs(0, 51).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.2F, false);
        body_low.texOffs(0, 57).addBox(-4.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);
        body_low.texOffs(0, 57).addBox(3.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);

        cube_1 = new ModelPart(this);
        cube_1.setPos(0.0F, 0.0F, 3.75F);
        body_low.addChild(cube_1);
        cube_1.texOffs(18, 59).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);

        cube_2 = new ModelPart(this);
        cube_2.setPos(0.0F, -0.5F, -0.75F);
        body_low.addChild(cube_2);
        cube_2.texOffs(8, 62).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
    }


}
