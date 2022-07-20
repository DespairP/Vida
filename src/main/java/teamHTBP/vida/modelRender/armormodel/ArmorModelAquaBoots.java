package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.player.Player;

public class ArmorModelAquaBoots extends AbstractModelElementBoots<Player> {
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;


    public ArmorModelAquaBoots() {
        super(128, 128);

        leg_left = new ModelPart(this);
        leg_left.setPos(2.0F, 12.0F, 0.0F);
        leg_left.texOffs(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        cube_r1 = new ModelPart(this);
        cube_r1.setPos(0.5F, 10.0F, -2.0F);
        leg_left.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
        cube_r1.texOffs(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        leg_right = new ModelPart(this);
        leg_right.setPos(-2.0F, 12.0F, 0.0F);
        leg_right.texOffs(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);

        cube_r2 = new ModelPart(this);
        cube_r2.setPos(0.5F, 10.0F, -2.0F);
        leg_right.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0873F, 0.0F, 0.0F);
        cube_r2.texOffs(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
        cube_r2.texOffs(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
        cube_r2.texOffs(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
