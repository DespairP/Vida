package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthBoots extends ArmorModelElementBoots {
    public ArmorModelEarthBoots(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part29 = new ModelRenderer(this, 7, 43);
        this.part29.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part29.addBox(-2.0F, 8.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_178722_k = new ModelRenderer(this, 0, 16);
        this.field_178722_k.mirror = true;
        this.field_178722_k.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178721_j = new ModelRenderer(this, 0, 16);
        this.field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part28 = new ModelRenderer(this, 7, 43);
        this.part28.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part28.addBox(-2.0F, 8.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_178721_j.addChild(this.part29);
        this.field_178722_k.addChild(this.part28);
    }
}
