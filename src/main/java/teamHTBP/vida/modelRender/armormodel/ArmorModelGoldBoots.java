package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelGoldBoots extends ArmorModelElementBoots {

    public ArmorModelGoldBoots(){
        this.textureWidth = 64;
        this.textureHeight = 64;

        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_right.setTextureOffset(0, 67).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
        leg_right.setTextureOffset(0, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
        leg_right.setTextureOffset(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, true);

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_left.setTextureOffset(0, 67).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
        leg_left.setTextureOffset(0, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
        leg_left.setTextureOffset(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
    }

}
