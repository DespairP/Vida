package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireChestplates extends ArmorModelElementChestplates{


    public ArmorModelFireChestplates() {
        super(128, 128);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(48, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        arm_left = new ModelRenderer(this);
        arm_left.setRotationPoint(5.0F, 2.0F, 0.0F);
        arm_left.setTextureOffset(48, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        arm_right = new ModelRenderer(this);
        arm_right.setRotationPoint(-5.0F, 2.0F, 0.0F);
        arm_right.setTextureOffset(48, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    }
}
