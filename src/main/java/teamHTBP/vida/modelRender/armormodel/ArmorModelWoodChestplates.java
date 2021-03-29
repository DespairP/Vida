package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodChestplates extends ArmorModelElementChestplates {
    private final ModelRenderer bone6;
    private final ModelRenderer bone3;
    private final ModelRenderer bone10;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone7;
    private final ModelRenderer cube_r3;
    private final ModelRenderer bone8;
    private final ModelRenderer cube_r4;

    public ArmorModelWoodChestplates(){
        super(128,128);

        arm_left = new ModelRenderer(this);
        arm_left.setRotationPoint(4.0F, 1.0F, 0.0F);
        arm_left.setTextureOffset(0, 28).addBox(0.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
        arm_left.setTextureOffset(0, 38).addBox(0.0F, 5.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
        arm_left.setTextureOffset(0, 48).addBox(3.5F, 4.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, true);

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        arm_left.addChild(bone6);
        bone6.setTextureOffset(16, 28).addBox(2.75F, -1.5F, -2.5F, 1.0F, 5.0F, 5.0F, 0.0F, true);
        bone6.setTextureOffset(16, 28).addBox(4.0F, -1.75F, -2.25F, 1.0F, 5.0F, 5.0F, 0.0F, true);
        bone6.setTextureOffset(16, 28).addBox(1.25F, -1.75F, -2.25F, 1.0F, 5.0F, 5.0F, 0.0F, true);

        arm_right = new ModelRenderer(this);
        arm_right.setRotationPoint(-4.0F, 1.0F, 0.0F);
        arm_right.setTextureOffset(0, 28).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        arm_right.setTextureOffset(0, 38).addBox(-4.0F, 5.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        arm_right.setTextureOffset(0, 48).addBox(-4.5F, 4.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        arm_right.addChild(bone3);
        bone3.setTextureOffset(16, 28).addBox(-3.75F, -1.5F, -2.5F, 1.0F, 5.0F, 5.0F, 0.0F, false);
        bone3.setTextureOffset(16, 28).addBox(-5.0F, -1.75F, -2.25F, 1.0F, 5.0F, 5.0F, 0.0F, false);
        bone3.setTextureOffset(16, 28).addBox(-2.25F, -1.75F, -2.25F, 1.0F, 5.0F, 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, 0.0F, false);

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-3.0F, 12.0F, 0.0F);
        body.addChild(bone10);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(5.0F, -10.75F, -2.75F);
        bone10.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(32, 19).addBox(-0.5F, -0.75F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(32, 19).addBox(-4.5F, -0.75F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(3.0F, -10.75F, -2.75F);
        bone10.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(24, 19).addBox(-1.5F, -0.75F, 0.0F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 1.75F, 2.5F);
        body.addChild(bone7);
        setRotationAngle(bone7, 0.0873F, 0.0F, 0.0F);
        bone7.setTextureOffset(56, 20).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        bone7.setTextureOffset(36, 19).addBox(-4.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        bone7.setTextureOffset(36, 19).addBox(1.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-4.4F, 0.1F, -0.5F);
        bone7.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(44, 20).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        cube_r3.setTextureOffset(44, 20).addBox(8.35F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.25F, 2.5F);
        body.addChild(bone8);
        setRotationAngle(bone8, 0.0436F, 0.0F, 0.0F);
        bone8.setTextureOffset(56, 20).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, 0.0F, false);
        bone8.setTextureOffset(36, 19).addBox(-4.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        bone8.setTextureOffset(36, 19).addBox(1.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-4.4F, 0.1F, -0.5F);
        bone8.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.1745F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(44, 20).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        cube_r4.setTextureOffset(44, 20).addBox(8.35F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
