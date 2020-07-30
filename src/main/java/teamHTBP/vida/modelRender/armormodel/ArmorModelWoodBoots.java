package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodBoots extends ArmorModelElementBoots {
    public ArmorModelWoodBoots(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_178721_j = new ModelRenderer(this, 0, 16);
        this.field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part25 = new ModelRenderer(this, 0, 55);
        this.part25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part25.addBox(-1.0F, 7.0F, -3.0F, 2.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_178722_k = new ModelRenderer(this, 0, 16);
        this.field_178722_k.mirror = true;
        this.field_178722_k.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part24 = new ModelRenderer(this, 0, 55);
        this.part24.mirror = true;
        this.part24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part24.addBox(-1.0F, 7.0F, -3.0F, 2.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_178721_j.addChild(this.part25);
        this.field_178722_k.addChild(this.part24);
    }
}
