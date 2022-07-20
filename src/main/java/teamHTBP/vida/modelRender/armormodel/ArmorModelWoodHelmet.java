package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.geom.ModelPart;

public class ArmorModelWoodHelmet extends AbstractModelElementHelmet {
    private final ModelPart bone;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart bone4;
    private final ModelPart cube_r4;
    private final ModelPart cube_r5;
    private final ModelPart cube_r6;
    private final ModelPart bone2;
    private final ModelPart cube_r7;
    private final ModelPart cube_r8;
    private final ModelPart cube_r9;
    private final ModelPart bone5;
    private final ModelPart cube_r10;
    private final ModelPart cube_r11;
    private final ModelPart cube_r12;
    public ModelPart part21;


    public ArmorModelWoodHelmet() {
        super(1.0f, 0, 128, 128);

        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelPart(this);
        bone.setPos(3.75F, -1.5F, -4.0F);
        head.addChild(bone);
        setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);


        cube_r1 = new ModelPart(this);
        cube_r1.setPos(1.75F, 0.5F, -0.25F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.1745F, 0.0F);
        cube_r1.texOffs(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPos(0.8F, 0.0F, 2.75F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.4363F, 0.0F, 0.0F);
        cube_r2.texOffs(80, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        cube_r3 = new ModelPart(this);
        cube_r3.setPos(0.75F, 0.5F, -0.25F);
        bone.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
        cube_r3.texOffs(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        bone4 = new ModelPart(this);
        bone4.setPos(-3.75F, -1.5F, -4.0F);
        head.addChild(bone4);
        setRotationAngle(bone4, -0.1745F, 0.0F, 0.0F);


        cube_r4 = new ModelPart(this);
        cube_r4.setPos(-1.75F, 0.5F, -0.25F);
        bone4.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.1745F, 0.0F);
        cube_r4.texOffs(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r5 = new ModelPart(this);
        cube_r5.setPos(-0.8F, 0.0F, 2.75F);
        bone4.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.4363F, 0.0F, 0.0F);
        cube_r5.texOffs(80, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        cube_r6 = new ModelPart(this);
        cube_r6.setPos(-0.75F, 0.5F, -0.25F);
        bone4.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.1745F, 0.0F, 0.0F);
        cube_r6.texOffs(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        bone2 = new ModelPart(this);
        bone2.setPos(3.75F, -7.0F, -4.0F);
        head.addChild(bone2);
        setRotationAngle(bone2, 0.1309F, 0.1745F, 0.0F);


        cube_r7 = new ModelPart(this);
        cube_r7.setPos(1.75F, 0.5F, -0.25F);
        bone2.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, -0.1745F, 0.0F);
        cube_r7.texOffs(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelPart(this);
        cube_r8.setPos(0.8F, 0.0F, 2.75F);
        bone2.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0873F, -0.4363F, 0.0F);
        cube_r8.texOffs(80, 3).addBox(-0.8086F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        cube_r9 = new ModelPart(this);
        cube_r9.setPos(0.75F, 0.5F, -0.25F);
        bone2.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.1745F, 0.0F, 0.0F);
        cube_r9.texOffs(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        bone5 = new ModelPart(this);
        bone5.setPos(-3.75F, -7.0F, -4.0F);
        head.addChild(bone5);
        setRotationAngle(bone5, 0.1309F, -0.1745F, 0.0F);


        cube_r10 = new ModelPart(this);
        cube_r10.setPos(-1.75F, 0.5F, -0.25F);
        bone5.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.1745F, 0.0F);
        cube_r10.texOffs(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r11 = new ModelPart(this);
        cube_r11.setPos(-0.8F, 0.0F, 2.75F);
        bone5.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0873F, 0.4363F, 0.0F);
        cube_r11.texOffs(80, 3).addBox(-0.1914F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        cube_r12 = new ModelPart(this);
        cube_r12.setPos(-0.75F, 0.5F, -0.25F);
        bone5.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.1745F, 0.0F, 0.0F);
        cube_r12.texOffs(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
