package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelFireHelmet<T extends Entity> extends ArmorModelElementHelmet{
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone9;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer bone10;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer bone11;

    public ArmorModelFireHelmet() {
        super(1.0f, 0, 128, 128);
        this.textureWidth = 128;
        this.textureHeight = 128;
        field_78116_c = new ModelRenderer(this);
        field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_78116_c.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_78116_c.addChild(bone7);


        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-2.0F, -6.0F, 2.0F);
        bone7.addChild(bone8);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-0.5F, -0.15F, -6.5F);
        bone8.addChild(cube_r1);
        setRotateAngle(cube_r1, 0.2618F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-0.5F, -0.85F, -6.5F);
        bone8.addChild(cube_r2);
        setRotateAngle(cube_r2, -0.2618F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(0, 3).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(3.0F, -6.0F, 2.0F);
        bone7.addChild(bone9);


        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-0.5F, -0.15F, -6.5F);
        bone9.addChild(cube_r3);
        setRotateAngle(cube_r3, 0.2618F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-0.5F, -0.85F, -6.5F);
        bone9.addChild(cube_r4);
        setRotateAngle(cube_r4, -0.2618F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(0, 3).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -5.5F, 2.0F);
        bone7.addChild(bone10);


        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-0.5F, -0.15F, -6.5F);
        bone10.addChild(cube_r5);
        setRotateAngle(cube_r5, 0.2618F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(24, 0).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-0.5F, -0.85F, -6.5F);
        bone10.addChild(cube_r6);
        setRotateAngle(cube_r6, -0.2618F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(24, 3).addBox(-0.5F, -3.5F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-4.0F, -1.5F, 0.0F);
        field_78116_c.addChild(bone11);
        setRotateAngle(bone11, 0.0873F, 0.0F, 0.0F);
        bone11.setTextureOffset(32, 32).addBox(-0.5044F, -0.5F, -4.4999F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        bone11.setTextureOffset(32, 32).addBox(7.4956F, -0.5F, -4.4999F, 1.0F, 1.0F, 9.0F, 0.0F, true);
        bone11.setTextureOffset(43, 39).addBox(0.4956F, -0.5F, -4.4999F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        bone11.setTextureOffset(43, 39).addBox(5.4956F, -0.5F, -4.4999F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        bone11.setTextureOffset(49, 39).addBox(0.4956F, -0.5F, 3.5001F, 7.0F, 1.0F, 1.0F, 0.0F, false);}


}
