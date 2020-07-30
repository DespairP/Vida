package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelGoldChestplates extends ArmorModelElementChestplates {

    public ArmorModelGoldChestplates(){
        super();
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part16 = new ModelRenderer(this, 0, 32);
        this.part16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part16.addBox(-4.0F, -1.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part18 = new ModelRenderer(this, 0, 41);
        this.part18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part18.addBox(-3.5F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part15 = new ModelRenderer(this, 0, 32);
        this.part15.mirror = true;
        this.part15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part15.addBox(-1.0F, -1.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part24 = new ModelRenderer(this, 16, 41);
        this.part24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part24.addBox(-4.0F, 1.0F, -3.0F, 8.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.part17 = new ModelRenderer(this, 0, 41);
        this.part17.mirror = true;
        this.part17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part17.addBox(-0.5F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h.addChild(this.part16);
        this.field_178723_h.addChild(this.part18);
        this.field_178724_i.addChild(this.part15);
        this.field_78115_e.addChild(this.part24);
        this.field_178724_i.addChild(this.part17);
    }
}
