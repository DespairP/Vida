package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireLeggings extends AbstractModelElementLeggings {


    public ArmorModelFireLeggings() {
        super(128, 128);

        body_low = new ModelRenderer(this);
        body_low.setRotationPoint(0.0F, 24.0F, 0.0F);
    }
}
