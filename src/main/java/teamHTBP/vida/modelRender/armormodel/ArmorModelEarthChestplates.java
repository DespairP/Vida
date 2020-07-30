package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthChestplates extends ArmorModelElementChestplates {

    public ArmorModelEarthChestplates(){

        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part22 = new ModelRenderer(this, 0, 43);
        this.part22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part22.addBox(-4.5F, -1.5F, -2.5F, 1.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part23 = new ModelRenderer(this, 0, 53);
        this.part23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part23.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.part19 = new ModelRenderer(this, 0, 32);
        this.part19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part19.addBox(-3.5F, -2.5F, -2.5F, 4.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part20 = new ModelRenderer(this, 0, 43);
        this.part20.mirror = true;
        this.part20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part20.addBox(3.5F, -1.5F, -2.5F, 1.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part27 = new ModelRenderer(this, 18, 53);
        this.part27.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part27.addBox(-4.0F, 0.0F, 2.0F, 8.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.part18 = new ModelRenderer(this, 0, 32);
        this.part18.mirror = true;
        this.part18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part18.addBox(-0.5F, -2.5F, -2.5F, 4.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h.addChild(this.part22);
        this.field_78115_e.addChild(this.part23);
        this.field_178723_h.addChild(this.part19);
        this.field_178724_i.addChild(this.part20);
        this.field_78115_e.addChild(this.part27);
        this.field_178724_i.addChild(this.part18);
    }
}
