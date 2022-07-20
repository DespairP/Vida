package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelWoodLeggings extends AbstractModelElementLeggings {

    private final ModelPart bone11;
    private final ModelPart cube_r1;
    private final ModelPart bone9;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;

    public ArmorModelWoodLeggings() {
        super(128, 128);

        body_low = new ModelPart(this);
        body_low.setPos(0.0F, 24.0F, 0.0F);
        body_low.texOffs(0, 58).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.2F, false);

        bone11 = new ModelPart(this);
        bone11.setPos(0.0F, -11.75F, 1.5F);
        body_low.addChild(bone11);
        setRotationAngle(bone11, 0.1745F, 0.0F, 0.0F);
        bone11.texOffs(0, 67).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelPart(this);
        cube_r1.setPos(-5.0F, 1.75F, 0.0F);
        bone11.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
        cube_r1.texOffs(0, 70).addBox(1.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(0, 70).addBox(6.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, true);

        bone9 = new ModelPart(this);
        bone9.setPos(0.0F, 0.0F, 0.0F);
        body_low.addChild(bone9);
        bone9.texOffs(46, 64).addBox(-4.5F, -13.5F, 1.95F, 9.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPos(4.25F, -12.75F, 2.5F);
        bone9.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
        cube_r2.texOffs(24, 58).addBox(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        cube_r2.texOffs(24, 58).addBox(-9.0F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

        cube_r3 = new ModelPart(this);
        cube_r3.setPos(4.0F, -11.75F, -2.4F);
        bone9.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.1745F);
        cube_r3.texOffs(38, 63).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelPart(this);
        cube_r4.setPos(-4.0F, -11.75F, -2.4F);
        bone9.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.1745F);
        cube_r4.texOffs(38, 63).addBox(-0.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
