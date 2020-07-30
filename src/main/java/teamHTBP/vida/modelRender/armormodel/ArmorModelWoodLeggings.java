package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodLeggings extends ArmorModelElementLeggings {

    public ArmorModelWoodLeggings(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_78115_e = new ModelRenderer(this, 32, 11);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 11.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part26 = new ModelRenderer(this, 16, 32);
        this.part26.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part26.addBox(-4.5F, 7.0F, -2.5F, 9.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e.addChild(this.part26);
    }
}
