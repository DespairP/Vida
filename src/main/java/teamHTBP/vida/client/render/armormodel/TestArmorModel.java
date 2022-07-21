package teamHTBP.vida.client.render.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

/**
 * PlayerModel - Either Mojang or a mod author (Taken From Memory)
 */
@OnlyIn(Dist.CLIENT)
@AutoRegModel
public class TestArmorModel<T extends Entity> extends HumanoidModel<ArmorStand> {
    private final boolean isChildHeadScaled = false;
    private final float childHeadOffsetY = 5.0f;
    private final float childHeadOffsetZ = 5.0f;
    public ModelPart head;
    public ModelPart a4;
    public ModelPart a2;
    public ModelPart a3;

    public TestArmorModel(ModelPart pRoot) {
        super(pRoot);

        head = pRoot.getChild("head");
        a4 = head.getChild("a4");
        a2 = head.getChild("a2");
        a3 = head.getChild("a3");
    }


    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        //this.head.copyModelAngles(this.bipedHead);
        return ImmutableList.of(this.head);
    }


    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerH(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

            var a3 = head.addOrReplaceChild("a3",
                    CubeListBuilder.create(),PartPose.offset(0.0F, 0.0F, 0.0F));

            var a2 = head.addOrReplaceChild("a2",
                    CubeListBuilder.create(),PartPose.offset(0.0F, 0.0F, 0.0F));

            var a4 = head.addOrReplaceChild("a4",
                    CubeListBuilder.create(),PartPose.offset(0.0F, 0.0F, 0.0F));


        });
    }

}
