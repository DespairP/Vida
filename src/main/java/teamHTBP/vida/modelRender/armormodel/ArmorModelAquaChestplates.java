package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaChestplates extends ArmorModelElementChestplates {

    public ArmorModelAquaChestplates(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part18 = new ModelRenderer(this, 0, 32);
        this.part18.setRotationPoint(5.0F, -2.0F, 0.0F);
        this.part18.addBox(-7.0F, 5.0F, 4.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part18, -0.296705972839036F, 0.0F, 0.0F);
        this.part17_1 = new ModelRenderer(this, 0, 32);
        this.part17_1.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.part17_1.addBox(5.0F, 5.0F, 4.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part17_1, -0.296705972839036F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part17 = new ModelRenderer(this, 0, 39);
        this.part17.setRotationPoint(5.0F, -2.0F, 0.0F);
        this.part17.addBox(-7.5F, 4.0F, 0.7F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part17, -0.10471975511965977F, 0.0F, 0.0F);
        this.part16 = new ModelRenderer(this, 0, 39);
        this.part16.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.part16.addBox(4.5F, 4.0F, 0.7F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part16, -0.10471975511965977F, 0.0F, 0.0F);
        this.field_178723_h.addChild(this.part18);
        this.field_178724_i.addChild(this.part17_1);
        this.field_178723_h.addChild(this.part17);
        this.field_178724_i.addChild(this.part16);
    }
}
