package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthLeggings extends ArmorModelElementLeggings {
    public  ArmorModelEarthLeggings(){

        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part26 = new ModelRenderer(this, 36, 33);
        this.part26.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part26.addBox(-4.5F, 11.0F, -2.0F, 9.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 11.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part25 = new ModelRenderer(this, 18, 32);
        this.part25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part25.addBox(-5.0F, 10.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part24 = new ModelRenderer(this, 18, 32);
        this.part24.mirror = true;
        this.part24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part24.addBox(1.0F, 10.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e.addChild(this.part26);
        this.field_78115_e.addChild(this.part25);
        this.field_78115_e.addChild(this.part24);
    }
}
