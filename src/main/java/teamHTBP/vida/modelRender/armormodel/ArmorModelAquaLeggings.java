package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaLeggings extends AbstractModelElementLeggings {

    public ArmorModelAquaLeggings() {
        super(128, 128);

        body_low = new ModelRenderer(this);
        body_low.setPos(0.0F, 24.0F, 0.0F);
        body_low.texOffs(0, 57).addBox(-4.5F, -14.0F, -0.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        body_low.texOffs(0, 50).addBox(-4.0F, -14.5F, -2.0F, 8.0F, 3.0F, 4.0F, 0.2F, false);
        body_low.texOffs(0, 57).addBox(1.5F, -14.0F, -0.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        body_low.texOffs(0, 63).addBox(1.5F, -11.0F, -0.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
        body_low.texOffs(0, 63).addBox(-4.5F, -11.0F, -0.5F, 3.0F, 10.0F, 3.0F, 0.0F, true);
        body_low.texOffs(0, 77).addBox(-1.5F, -12.0F, -0.75F, 3.0F, 10.0F, 3.0F, 0.0F, false);
    }
}
