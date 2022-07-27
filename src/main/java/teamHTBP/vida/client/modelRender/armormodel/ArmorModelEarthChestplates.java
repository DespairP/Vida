package teamHTBP.vida.client.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthChestplates extends AbstractModelElementChestplates {

    public ArmorModelEarthChestplates() {
        super(128, 128);

        arm_right = new ModelRenderer(this);
        arm_right.setRotationPoint(-4.0F, 1.0F, 0.0F);
        arm_right.setTextureOffset(26, 36).addBox(-4.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, 0.0F, true);
        arm_right.setTextureOffset(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.setTextureOffset(0, 37).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.setTextureOffset(16, 38).addBox(-4.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);

        arm_left = new ModelRenderer(this);
        arm_left.setRotationPoint(4.0F, 1.0F, 0.0F);
        arm_left.setTextureOffset(26, 36).addBox(1.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, 0.0F, false);
        arm_left.setTextureOffset(0, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.setTextureOffset(0, 37).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.setTextureOffset(16, 38).addBox(3.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, 0.1F, false);
        body.setTextureOffset(24, 18).addBox(-4.0F, 0.0F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(62, 19).addBox(-3.0F, 0.0F, 1.25F, 6.0F, 8.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(44, 18).addBox(-3.5F, 5.0F, -3.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);
    }
}
