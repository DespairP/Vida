package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelWoodBoots extends AbstractModelElementBoots {
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;

    public ArmorModelWoodBoots() {
        super(128, 128);

        leg_right = new ModelPart(this);
        leg_right.setPos(-2.0F, 12.0F, 0.0F);
        leg_right.texOffs(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(16, 77).addBox(-2.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, true);
        leg_right.texOffs(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);

        cube_r1 = new ModelPart(this);
        cube_r1.setPos(0.0F, 12.0F, -2.0F);
        leg_right.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.1309F, 0.0F, 0.0F);
        cube_r1.texOffs(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

        leg_left = new ModelPart(this);
        leg_left.setPos(2.0F, 12.0F, 0.0F);
        leg_left.texOffs(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(16, 77).addBox(1.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, false);
        leg_left.texOffs(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPos(0.0F, 12.0F, -2.0F);
        leg_left.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.1309F, 0.0F, 0.0F);
        cube_r2.texOffs(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
