package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaHelmet extends ArmorModelElementHelmet{
    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer bone3;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;


    public ArmorModelAquaHelmet() {
        super(1.0f, 0, 128, 128);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-4.5F, -6.0F, -5.0F);
        head.addChild(bone);
        setRotationAngle(bone, -1.3526F, -0.1309F, 0.0F);
        bone.setTextureOffset(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.2182F);
        cube_r1.setTextureOffset(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 1.7017F);
        cube_r2.setTextureOffset(17, 36).addBox(-1.0F, -1.8F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-4.5F, -4.0F, -5.0F);
        head.addChild(bone2);
        setRotationAngle(bone2, -1.6144F, -0.1309F, 0.0F);
        bone2.setTextureOffset(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0873F, 0.0F, 0.2182F);
        cube_r3.setTextureOffset(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 1.7453F);
        cube_r4.setTextureOffset(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-4.5F, -2.0F, -5.0F);
        head.addChild(bone3);
        setRotationAngle(bone3, -1.8326F, -0.1309F, 0.0F);
        bone3.setTextureOffset(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone3.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0873F, 0.0F, 0.2182F);
        cube_r5.setTextureOffset(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(1.0F, -0.25F, 0.0F);
        bone3.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 1.7453F);
        cube_r6.setTextureOffset(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        super.setRotateAngle(modelRenderer,x,y,z);
    }
}
