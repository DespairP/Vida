package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthHelmet extends ArmorModelElementHelmet {

    public ArmorModelEarthHelmet(){
        super(1.0F,0,64,64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part17 = new ModelRenderer(this, 40, 0);
        this.part17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part17.addBox(-2.5F, -9.0F, -3.6F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part17, 0.17453292519943295F, 0.0F, 0.0F);
        this.part21 = new ModelRenderer(this, 40, 0);
        this.part21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part21.addBox(-1.0F, -10.0F, -3.6F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part21, 0.20943951023931953F, 0.0F, 0.0F);
        this.part15 = new ModelRenderer(this, 24, 0);
        this.part15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part15.addBox(-4.0F, -9.0F, -4.2F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.part16 = new ModelRenderer(this, 40, 0);
        this.part16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part16.addBox(0.5F, -9.0F, -3.6F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part16, 0.17453292519943295F, 0.0F, 0.0F);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.field_78116_c.addChild(this.part17);
        this.field_78116_c.addChild(this.part21);
        this.field_78116_c.addChild(this.part15);
        this.field_78116_c.addChild(this.part16);
    }
}
