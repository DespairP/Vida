package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodHelmet extends  ArmorModelElementHelmet {

    public ArmorModelWoodHelmet(){
        super(1.0f,0,64,64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.part21 = new ModelRenderer(this, 24, 0);
        this.part21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part21.addBox(-4.0F, -9.0F, -4.2F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.field_78116_c.addChild(this.part21);
    }
}
