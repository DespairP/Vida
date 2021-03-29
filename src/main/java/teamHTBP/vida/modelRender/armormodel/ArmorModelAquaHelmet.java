package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelAquaHelmet extends ArmorModelElementHelmet{
    private final ModelRenderer bone7;
    private final ModelRenderer cube_r1;
    private final ModelRenderer bone9;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone8;
    private final ModelRenderer cube_r3;


    public ArmorModelAquaHelmet() {
        super(1.0f, 0, 128, 128);
        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-5.0F, -5.75F, -3.0F);
        head.addChild(bone7);
        setRotationAngle(bone7, 0.1309F, -0.1309F, 0.0F);
        bone7.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-0.1F, 0.0F, 0.5F);
        bone7.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.5236F, 0.0F);
        cube_r1.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-3.0F, -8.75F, -3.0F);
        head.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.7854F);
        bone9.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-0.1F, 0.0F, 0.5F);
        bone9.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.5236F, 0.0F);
        cube_r2.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-5.0F, -2.5F, -3.0F);
        head.addChild(bone8);
        setRotationAngle(bone8, -0.0436F, -0.1309F, -0.2182F);
        bone8.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-0.1F, 0.0F, 0.5F);
        bone8.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, -0.5236F, 0.0F);
        cube_r3.setTextureOffset(64, 10).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        super.setRotateAngle(modelRenderer,x,y,z);
    }
}
