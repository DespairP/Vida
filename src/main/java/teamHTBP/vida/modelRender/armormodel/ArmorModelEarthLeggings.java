package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthLeggings extends AbstractModelElementLeggings {

    public ArmorModelEarthLeggings() {
        super(128, 128);

        body_low = new ModelRenderer(this);
        body_low.setPos(0.0F, 24.0F, 0.0F);
        body_low.texOffs(0, 46).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.2F, false);
        body_low.texOffs(14, 52).addBox(-5.0F, -14.0F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, true);
        body_low.texOffs(14, 52).addBox(4.0F, -14.0F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);
        body_low.texOffs(0, 52).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);
        body_low.texOffs(0, 52).addBox(-3.0F, -14.0F, 2.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);
    }
}
