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

    public float[] modelScale = new float[] { 1.07F, 1.07F, 1.07F };


    public ArmorModelFireHelmet() {
        super(1.0f, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.part15 = new ModelRenderer(this, 24, 0);
        this.part15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part15.addBox(-4.0F, -9.0F, -4.25F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.field_78116_c.addChild(this.part15);
    }


}
