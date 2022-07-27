package teamHTBP.vida.client.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelGoldLeggings extends AbstractModelElementLeggings {
    private final ModelRenderer cube_1;
    private final ModelRenderer cube_2;


    public ArmorModelGoldLeggings() {
        super(128, 128);

        body_low = new ModelRenderer(this);
        body_low.setRotationPoint(0.0F, 12.0F, 0.0F);
        body_low.setTextureOffset(0, 51).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.2F, false);
        body_low.setTextureOffset(0, 57).addBox(-4.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);
        body_low.setTextureOffset(0, 57).addBox(3.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, 0.0F, false);

        cube_1 = new ModelRenderer(this);
        cube_1.setRotationPoint(0.0F, 0.0F, 3.75F);
        body_low.addChild(cube_1);
        cube_1.setTextureOffset(18, 59).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);

        cube_2 = new ModelRenderer(this);
        cube_2.setRotationPoint(0.0F, -0.5F, -0.75F);
        body_low.addChild(cube_2);
        cube_2.setTextureOffset(8, 62).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
    }


}
