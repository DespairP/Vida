package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireBoots extends ArmorModelElementBoots {
    private final ModelRenderer bone2;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone14;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;


    public ArmorModelFireBoots(){
        super(128,128);
        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_right.setTextureOffset(0, 60).addBox(-2.0F, 6.75F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.setTextureOffset(0, 52).addBox(-2.0F, 4.25F, -2.0F, 4.0F, 1.0F, 4.0F, 0.2F, true);
        leg_right.setTextureOffset(0, 57).addBox(-1.5F, 3.75F, -2.5F, 3.0F, 2.0F, 1.0F, 0.0F, true);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg_right.addChild(bone2);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 8.5F, -2.0F);
        bone2.addChild(cube_r1);
        setRotateAngle(cube_r1, 0.2618F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(24, 64).addBox(-1.0F, -2.75F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-1.0F, 11.6F, -2.0F);
        bone2.addChild(cube_r2);
        setRotateAngle(cube_r2, -0.0873F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(16, 65).addBox(-0.5F, -1.75F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, true);

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_left.setTextureOffset(0, 60).addBox(-2.0F, 6.75F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.setTextureOffset(0, 52).addBox(-2.0F, 4.25F, -2.0F, 4.0F, 1.0F, 4.0F, 0.2F, false);
        leg_left.setTextureOffset(0, 57).addBox(-1.5F, 3.75F, -2.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg_left.addChild(bone14);


        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, 8.5F, -2.0F);
        bone14.addChild(cube_r3);
        setRotateAngle(cube_r3, 0.2618F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(24, 64).addBox(-1.0F, -2.75F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-1.0F, 11.6F, -2.0F);
        bone14.addChild(cube_r4);
        setRotateAngle(cube_r4, -0.0873F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(16, 65).addBox(-0.5F, -1.75F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);}
}
