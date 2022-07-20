package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelGoldChestplates extends AbstractModelElementChestplates {

    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;

    public ArmorModelGoldChestplates() {
        super(128, 128);
        arm_right = new ModelRenderer(this);
        arm_right.setPos(-4.0F, 1.0F, 0.0F);
        arm_right.texOffs(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.texOffs(0, 37).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.texOffs(48, 34).addBox(-4.0F, 4.0F, -2.5F, 1.0F, 7.0F, 5.0F, 0.0F, true);
        arm_right.texOffs(38, 37).addBox(-1.0F, -4.15F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, true);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-3.0F, 2.75F, -0.5F);
        arm_right.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.48F);
        cube_r1.texOffs(16, 38).addBox(-1.5F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-3.0F, 2.0F, 0.0F);
        arm_right.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -0.0873F);
        cube_r2.texOffs(26, 37).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, true);

        arm_left = new ModelRenderer(this);
        arm_left.setPos(4.0F, 1.0F, 0.0F);
        arm_left.texOffs(0, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(0, 37).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(48, 34).addBox(3.0F, 4.0F, -2.5F, 1.0F, 7.0F, 5.0F, 0.0F, false);
        arm_left.texOffs(38, 37).addBox(-1.0F, -4.15F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(3.0F, 2.75F, -0.5F);
        arm_left.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.48F);
        cube_r3.texOffs(16, 38).addBox(-1.5F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(3.0F, 2.0F, 0.0F);
        arm_left.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.0873F);
        cube_r4.texOffs(26, 37).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, 0.2F, false);
        body.texOffs(42, 19).addBox(-3.0F, 3.5F, -2.5F, 6.0F, 4.0F, 1.0F, 0.0F, false);
        body.texOffs(42, 19).addBox(-3.0F, 3.5F, 1.5F, 6.0F, 4.0F, 1.0F, 0.0F, false);
        body.texOffs(24, 19).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 4.0F, 1.0F, 0.0F, false);
        body.texOffs(24, 19).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 4.0F, 1.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
