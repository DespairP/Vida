package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodLeggings extends ArmorModelElementLeggings {

    private final ModelRenderer bone11;
    private final ModelRenderer cube_r1;
    private final ModelRenderer bone9;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;

    public ArmorModelWoodLeggings(){
        super(128,128);

        body_low = new ModelRenderer(this);
        body_low.setRotationPoint(0.0F, 24.0F, 0.0F);
        body_low.setTextureOffset(0, 58).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.2F, false);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -11.75F, 1.5F);
        body_low.addChild(bone11);
        setRotationAngle(bone11, 0.1745F, 0.0F, 0.0F);
        bone11.setTextureOffset(0, 67).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-5.0F, 1.75F, 0.0F);
        bone11.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 70).addBox(1.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 70).addBox(6.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, true);

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        body_low.addChild(bone9);
        bone9.setTextureOffset(46, 64).addBox(-4.5F, -13.5F, 1.95F, 9.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(4.25F, -12.75F, 2.5F);
        bone9.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(24, 58).addBox(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        cube_r2.setTextureOffset(24, 58).addBox(-9.0F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(4.0F, -11.75F, -2.4F);
        bone9.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.1745F);
        cube_r3.setTextureOffset(38, 63).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-4.0F, -11.75F, -2.4F);
        bone9.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.1745F);
        cube_r4.setTextureOffset(38, 63).addBox(-0.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
