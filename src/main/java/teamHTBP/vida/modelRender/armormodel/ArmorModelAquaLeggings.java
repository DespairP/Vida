package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaLeggings extends ArmorModelElementLeggings {

    public ArmorModelAquaLeggings(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part19 = new ModelRenderer(this, 9, 32);
        this.part19.setRotationPoint(-4.5F, 9.0F, -2.5F);
        this.part19.addBox(0.0F, 0.0F, 0.0F, 9.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part21 = new ModelRenderer(this, 0, 49);
        this.part21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part21.addBox(-3.7F, 12.0F, 1.8F, 4.0F, 11.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part21, 0.06981317007977318F, -0.08726646259971647F, 0.0F);
        this.part20 = new ModelRenderer(this, 0, 49);
        this.part20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part20.addBox(-0.3F, 12.0F, 1.8F, 4.0F, 11.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part20, 0.06981317007977318F, 0.08726646259971647F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 11.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e.addChild(this.part19);
        this.field_78115_e.addChild(this.part21);
        this.field_78115_e.addChild(this.part20);
    }
}
