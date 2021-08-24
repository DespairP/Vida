package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaBoots extends ArmorModelElementBoots {
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;


    public ArmorModelAquaBoots() {
        super(128, 128);

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_left.setTextureOffset(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.setTextureOffset(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.5F, 10.0F, -2.0F);
        leg_left.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_right.setTextureOffset(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.setTextureOffset(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.5F, 10.0F, -2.0F);
        leg_right.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0873F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
        cube_r2.setTextureOffset(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
        cube_r2.setTextureOffset(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
