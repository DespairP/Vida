package teamHTBP.vida.modelRender.armormodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelFireHelmet<T extends Entity> extends AbstractModelElementHelmet {

    public ArmorModelFireHelmet() {
        super(128, 128);

        head = new ModelRenderer(this);
        //head.setRotationPoint(0.0F, 0.0F, 1.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
    }


    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public Iterable<ModelRenderer> headParts() {
        return super.headParts();
    }
}
