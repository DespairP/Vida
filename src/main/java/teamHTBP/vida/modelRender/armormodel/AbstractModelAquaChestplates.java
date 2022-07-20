package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class AbstractModelAquaChestplates extends AbstractModelElementChestplates {
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart cube_r1;
    private final ModelPart bone6;
    private final ModelPart bone11;

    public AbstractModelAquaChestplates() {
        super(128, 128);

        arm_left = new ModelPart(this);
        arm_left.setPos(4.0F, 1.0F, 0.0F);
        arm_left.texOffs(24, 41).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(40, 41).addBox(-1.0F, 7.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(24, 36).addBox(-1.25F, -4.0F, -2.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(0.25F, -4.5F, -2.75F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(1.5F, -3.25F, -2.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(1.75F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(1.5F, -3.5F, 1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(0.25F, -3.25F, 0.75F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(-1.75F, -3.5F, 0.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.texOffs(24, 36).addBox(-1.5F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        arm_right = new ModelPart(this);
        arm_right.setPos(-4.0F, 1.0F, 0.0F);
        arm_right.texOffs(0, 41).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_right.texOffs(0, 32).addBox(-3.0F, -0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        bone4 = new ModelPart(this);
        bone4.setPos(0.0F, 0.0F, 0.0F);
        arm_right.addChild(bone4);
        bone4.texOffs(16, 35).addBox(-3.5F, -4.0F, 0.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-2.5F, -4.5F, 1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-3.75F, -5.0F, -0.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-3.75F, 5.0F, -0.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-3.25F, 3.5F, 0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-4.0F, 4.0F, -1.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-3.75F, 4.5F, -2.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.texOffs(16, 35).addBox(-2.75F, -4.25F, -2.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, 0.2F, false);

        bone5 = new ModelPart(this);
        bone5.setPos(-4.5F, -0.5F, -2.25F);
        body.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.6109F);
        bone5.texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelPart(this);
        cube_r1.setPos(0.0F, 0.0F, -0.25F);
        bone5.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 1.3963F, 0.0F);
        cube_r1.texOffs(0, 30).addBox(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone6 = new ModelPart(this);
        bone6.setPos(-5.0F, 1.0F, -2.25F);
        body.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, 0.5672F);
        bone6.texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        bone6.texOffs(0, 28).addBox(0.4403F, 0.9759F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone11 = new ModelPart(this);
        bone11.setPos(0.0F, 5.0F, 1.75F);
        body.addChild(bone11);
        setRotationAngle(bone11, -0.0873F, 0.0F, 0.0F);
        bone11.texOffs(32, 33).addBox(-4.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        bone11.texOffs(32, 33).addBox(2.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        bone11.texOffs(32, 33).addBox(-1.25F, -6.0905F, -0.987F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
