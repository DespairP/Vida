package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaChestplates extends ArmorModelElementChestplates {
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer cube_r1;
    private final ModelRenderer bone6;
    private final ModelRenderer bone10;
    private final ModelRenderer bone3;
    private final ModelRenderer bone11;

    public ArmorModelAquaChestplates(){
        super(128,128);
        arm_left = new ModelRenderer(this);
        arm_left.setRotationPoint(4.0F, 1.0F, 0.0F);
        arm_left.setTextureOffset(24, 41).addBox(0.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.2F, false);
        arm_left.setTextureOffset(40, 41).addBox(0.0F, 8.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, false);
        arm_left.setTextureOffset(24, 36).addBox(-0.25F, -3.0F, -2.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(1.25F, -3.5F, -2.75F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(2.5F, -2.25F, -2.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(2.75F, -2.75F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(2.5F, -2.5F, 1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(1.25F, -2.25F, 0.75F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(-0.75F, -2.5F, 0.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        arm_left.setTextureOffset(24, 36).addBox(-0.5F, -2.75F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        arm_right = new ModelRenderer(this);
        arm_right.setRotationPoint(-4.0F, 1.0F, 0.0F);
        arm_right.setTextureOffset(0, 41).addBox(-4.0F, 6.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_right.setTextureOffset(0, 32).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        arm_right.addChild(bone4);
        bone4.setTextureOffset(16, 35).addBox(-4.5F, -3.0F, 0.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-3.5F, -3.5F, 1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-4.75F, -4.0F, -0.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-4.75F, 6.0F, -0.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-4.25F, 4.5F, 0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-5.0F, 5.0F, -1.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-4.75F, 5.5F, -2.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bone4.setTextureOffset(16, 35).addBox(-3.75F, -3.25F, -2.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, 0.2F, false);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-4.5F, -0.5F, -2.25F);
        body.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.6109F);
        bone5.setTextureOffset(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, -0.25F);
        bone5.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 1.3963F, 0.0F);
        cube_r1.setTextureOffset(0, 30).addBox(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-5.0F, 1.0F, -2.25F);
        body.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, 0.2618F);
        bone6.setTextureOffset(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-5.25F, 2.5F, -2.25F);
        body.addChild(bone10);
        bone10.setTextureOffset(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-5.0F, 4.0F, -2.25F);
        body.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.2618F);
        bone3.setTextureOffset(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 5.0F, 1.75F);
        body.addChild(bone11);
        setRotationAngle(bone11, -0.0873F, 0.0F, 0.0F);
        bone11.setTextureOffset(32, 33).addBox(-4.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        bone11.setTextureOffset(32, 33).addBox(2.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        bone11.setTextureOffset(32, 33).addBox(-1.25F, -6.0905F, -0.987F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
