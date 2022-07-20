package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelAquaHelmet extends AbstractModelElementHelmet {
    private final ModelPart bone;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart bone2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart bone3;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;


    public ArmorModelAquaHelmet() {
        super(1.0f, 0, 128, 128);

        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelPart(this);
        bone.setPos(-4.5F, -6.0F, -5.0F);
        head.addChild(bone);
        setRotationAngle(bone, -1.3526F, -0.1309F, 0.0F);
        bone.texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r1 = new ModelPart(this);
        cube_r1.setPos(1.0F, -0.25F, 0.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.2182F);
        cube_r1.texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPos(1.0F, -0.25F, 0.0F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 1.7017F);
        cube_r2.texOffs(17, 36).addBox(-1.0F, -1.8F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        bone2 = new ModelPart(this);
        bone2.setPos(-4.5F, -4.0F, -5.0F);
        head.addChild(bone2);
        setRotationAngle(bone2, -1.6144F, -0.1309F, 0.0F);
        bone2.texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelPart(this);
        cube_r3.setPos(1.0F, -0.25F, 0.0F);
        bone2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0873F, 0.0F, 0.2182F);
        cube_r3.texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r4 = new ModelPart(this);
        cube_r4.setPos(1.0F, -0.25F, 0.0F);
        bone2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 1.7453F);
        cube_r4.texOffs(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        bone3 = new ModelPart(this);
        bone3.setPos(-4.5F, -2.0F, -5.0F);
        head.addChild(bone3);
        setRotationAngle(bone3, -1.8326F, -0.1309F, 0.0F);
        bone3.texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r5 = new ModelPart(this);
        cube_r5.setPos(1.0F, -0.25F, 0.0F);
        bone3.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0873F, 0.0F, 0.2182F);
        cube_r5.texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r6 = new ModelPart(this);
        cube_r6.setPos(1.0F, -0.25F, 0.0F);
        bone3.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 1.7453F);
        cube_r6.texOffs(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        super.setRotateAngle(modelRenderer, x, y, z);
    }
}
