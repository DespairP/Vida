package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaBoots extends ArmorModelElementBoots {

    public  ArmorModelAquaBoots(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_178721_j = new ModelRenderer(this, 0, 16);
        this.field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178722_k = new ModelRenderer(this, 0, 16);
        this.field_178722_k.mirror = true;
        this.field_178722_k.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);

    }
}
