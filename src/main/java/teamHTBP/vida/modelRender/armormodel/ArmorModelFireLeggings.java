package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireLeggings extends  ArmorModelElementLeggings {
    private final ModelRenderer bone13;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;


    public ArmorModelFireLeggings(){
        super(128,128);
        body_low = new ModelRenderer(this);
        body_low.setRotationPoint(0.0F, 24.0F, 0.0F);
        body_low.setTextureOffset(0, 45).addBox(-4.0F, -15.0F, -2.0F, 8.0F, 3.0F, 4.0F, 0.2F, false);

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-3.0F, -12.0F, -2.0F);
        body_low.addChild(bone13);
        bone13.setTextureOffset(24, 47).addBox(1.5F, -2.5F, 3.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-1.0F, -0.75F, 4.0F);
        bone13.addChild(cube_r1);
        setRotateAngle(cube_r1, 0.0873F, 0.0F, 0.0873F);
        cube_r1.setTextureOffset(20, 45).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(7.0F, -0.75F, 4.0F);
        bone13.addChild(cube_r2);
        setRotateAngle(cube_r2, 0.0873F, 0.0F, -0.0873F);
        cube_r2.setTextureOffset(20, 45).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(7.0F, -0.75F, 0.0F);
        bone13.addChild(cube_r3);
        setRotateAngle(cube_r3, -0.0873F, 0.0F, -0.0873F);
        cube_r3.setTextureOffset(20, 45).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-1.0F, -0.75F, 0.0F);
        bone13.addChild(cube_r4);
        setRotateAngle(cube_r4, -0.0873F, 0.0F, 0.0873F);
        cube_r4.setTextureOffset(20, 45).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }
}
