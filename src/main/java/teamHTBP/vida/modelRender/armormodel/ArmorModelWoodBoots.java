package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodBoots extends ArmorModelElementBoots {
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;

    public ArmorModelWoodBoots(){
        super(128,128);

        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_right.setTextureOffset(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, true);
        leg_right.setTextureOffset(16, 77).addBox(-2.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, true);
        leg_right.setTextureOffset(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 12.0F, -2.0F);
        leg_right.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.1309F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_left.setTextureOffset(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.2F, false);
        leg_left.setTextureOffset(16, 77).addBox(1.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, false);
        leg_left.setTextureOffset(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 12.0F, -2.0F);
        leg_left.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.1309F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
