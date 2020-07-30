package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelGoldLeggings extends ArmorModelElementLeggings{

    public ArmorModelGoldLeggings(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part23 = new ModelRenderer(this, 30, 32);
        this.part23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part23.addBox(-4.5F, 11.0F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part20 = new ModelRenderer(this, 0, 51);
        this.part20.mirror = true;
        this.part20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part20.addBox(4.0F, 10.0F, -1.5F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.part21 = new ModelRenderer(this, 0, 51);
        this.part21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part21.addBox(-5.0F, 10.0F, -1.5F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.part22 = new ModelRenderer(this, 20, 32);
        this.part22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part22.addBox(-2.0F, 10.0F, -3.0F, 4.0F, 7.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 11.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part25 = new ModelRenderer(this, 20, 32);
        this.part25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part25.addBox(-2.0F, 10.0F, 2.0F, 4.0F, 7.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e.addChild(this.part23);
        this.field_78115_e.addChild(this.part20);
        this.field_78115_e.addChild(this.part21);
        this.field_78115_e.addChild(this.part22);
        this.field_78115_e.addChild(this.part25);
    }



}
