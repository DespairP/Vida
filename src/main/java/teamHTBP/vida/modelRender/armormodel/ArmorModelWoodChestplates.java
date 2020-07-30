package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodChestplates extends ArmorModelElementChestplates {
    public ArmorModelWoodChestplates(){
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part17 = new ModelRenderer(this, 0, 40);
        this.part17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part17.addBox(-6.5F, 0.5F, -0.9F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part17, 0.4886921905584123F, 0.3490658503988659F, 0.0F);
        this.part18 = new ModelRenderer(this, 0, 40);
        this.part18.mirror = true;
        this.part18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part18.addBox(4.7F, 2.3F, 2.9F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part18, 0.4886921905584123F, 0.3490658503988659F, 0.0F);
        this.part19 = new ModelRenderer(this, 0, 40);
        this.part19.mirror = true;
        this.part19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part19.addBox(4.5F, 0.3F, -0.9F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part19, 0.4886921905584123F, -0.3490658503988659F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part20 = new ModelRenderer(this, 0, 40);
        this.part20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part20.addBox(-6.6F, 2.4F, 2.6F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part20, 0.4886921905584123F, -0.3490658503988659F, 0.0F);
        this.part22 = new ModelRenderer(this, 0, 46);
        this.part22.setRotationPoint(5.0F, -2.0F, 0.0F);
        this.part22.addBox(-8.5F, 6.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.part15 = new ModelRenderer(this, 0, 32);
        this.part15.mirror = true;
        this.part15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part15.addBox(5.0F, 0.5F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part15, 0.4886921905584123F, 0.0F, 0.0F);
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part16 = new ModelRenderer(this, 0, 32);
        this.part16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part16.addBox(-7.0F, 0.5F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part16, 0.4886921905584123F, 0.0F, 0.0F);
        this.part23 = new ModelRenderer(this, 0, 46);
        this.part23.mirror = true;
        this.part23.setRotationPoint(-5.0F, -2.0F, 0.0F);
        this.part23.addBox(3.5F, 6.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.field_78115_e.addChild(this.part17);
        this.field_78115_e.addChild(this.part18);
        this.field_78115_e.addChild(this.part19);
        this.field_78115_e.addChild(this.part20);
        this.field_178723_h.addChild(this.part22);
        this.field_78115_e.addChild(this.part15);
        this.field_78115_e.addChild(this.part16);
        this.field_178724_i.addChild(this.part23);
    }
}
