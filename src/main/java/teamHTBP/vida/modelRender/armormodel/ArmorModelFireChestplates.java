package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireChestplates extends ArmorModelElementChestplates{

    public ArmorModelFireChestplates(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part18 = new ModelRenderer(this, 0, 40);
        this.part18.mirror = true;
        this.part18.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.part18.addBox(6.5F, -1.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part18, 0.0F, 0.0F, -0.08726646259971647F);
        this.part20 = new ModelRenderer(this, 0, 48);
        this.part20.mirror = true;
        this.part20.setRotationPoint(5.0F, -2.0F, 0.0F);
        this.part20.addBox(-2.5F, 6.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part20, 0.0F, 0.0F, 0.05235987755982988F);
        this.part21 = new ModelRenderer(this, 0, 48);
        this.part21.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.part21.addBox(0.4F, 6.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part21, 0.0F, 0.0F, -0.05235987755982988F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 11.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part19 = new ModelRenderer(this, 0, 40);
        this.part19.setRotationPoint(5.0F, -2.0F, 0.0F);
        this.part19.addBox(-8.5F, -1.0F, -1.5F, 2.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part19, 0.0F, 0.0F, 0.08726646259971647F);
        this.field_178724_i.addChild(this.part18);
        this.field_178724_i.addChild(this.part20);
        this.field_178723_h.addChild(this.part21);
        this.field_178723_h.addChild(this.part19);

    }
}
