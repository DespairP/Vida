package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodHelmet extends  ArmorModelElementHelmet {
    public ModelRenderer part21;

    public ArmorModelWoodHelmet(){
        super(1.0f,0,128,128);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(64, 12).addBox(-1.5F, -9.5F, -4.35F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(72, 12).addBox(1.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(72, 12).addBox(-2.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }
}
