package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthHelmet extends AbstractModelElementHelmet {


    public ArmorModelEarthHelmet() {
        super(128, 128);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);
        head.texOffs(64, 12).addBox(-1.5F, -9.5F, -4.35F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(72, 12).addBox(1.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(72, 12).addBox(-2.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }
}
