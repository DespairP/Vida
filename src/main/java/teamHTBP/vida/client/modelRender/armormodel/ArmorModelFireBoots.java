package teamHTBP.vida.client.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireBoots extends AbstractModelElementBoots {

    public ArmorModelFireBoots() {
        super(128, 128);
        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_right.setTextureOffset(64, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_left.setTextureOffset(64, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }
}
