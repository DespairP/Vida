package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireLeggings extends  ArmorModelElementLeggings {

    public ArmorModelFireLeggings(){

        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 10.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.part16 = new ModelRenderer(this, 0, 32);
        this.part16.mirror = true;
        this.part16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part16.addBox(-1.5F, 10.3F, -2.5F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part16, 0.0F, 0.0F, -0.17453292519943295F);
        this.part17 = new ModelRenderer(this, 0, 32);
        this.part17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part17.addBox(-2.5F, 10.3F, -2.5F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(part17, 0.0F, 0.0F, 0.17453292519943295F);
        this.field_78115_e.addChild(this.part16);
        this.field_78115_e.addChild(this.part17);
    }
}
