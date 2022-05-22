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

/**
 * PlayerModel - Either Mojang or a mod author (Taken From Memory)
 */
@OnlyIn(Dist.CLIENT)
public class TestArmorModel<T extends Entity> extends BipedModel<ArmorStandEntity> {
    private final boolean isChildHeadScaled = false;
    private final float childHeadOffsetY = 5.0f;
    private final float childHeadOffsetZ = 5.0f;
    public ModelRenderer field_78116_c;
    public ModelRenderer a4;
    public ModelRenderer a2;
    public ModelRenderer a3;


    public TestArmorModel() {
        super(1.0f, 0, 64, 64);


        this.textureWidth = 64;
        this.textureHeight = 64;
        this.a3 = new ModelRenderer(this, 0, 49);
        this.a3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.a3.addBox(-1.0F, -7.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(a3, 0.42987458011881735F, 0.0F, 1.0555751236166873F);
        this.a2 = new ModelRenderer(this, 0, 56);
        this.a2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.a2.addBox(-1.0F, -5.8F, 5.0F, 2.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(a2, 1.4465288361160007F, 0.0F, 0.0F);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.a4 = new ModelRenderer(this, 0, 49);
        this.a4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.a4.addBox(-1.0F, -7.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(a4, 0.42987458011881735F, 0.0F, -1.0555751236166873F);
        this.field_78116_c.addChild(this.a3);
        this.field_78116_c.addChild(this.a2);
        this.field_78116_c.addChild(this.a4);


    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        //this.field_78116_c.copyModelAngles(this.bipedHead);
        return ImmutableList.of(this.field_78116_c);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


}
