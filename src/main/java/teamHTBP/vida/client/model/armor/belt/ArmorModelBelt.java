package teamHTBP.vida.client.model.armor.belt;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.helper.client.ModelHelper;

@OnlyIn(Dist.CLIENT)
@AutoRegModel
public class ArmorModelBelt extends HumanoidModel<Player> {
    private final ModelPart body; //模型主要部分
    private final ModelPart bone; //模型次要部分

    public ArmorModelBelt(ModelPart pRoot) {
        super(pRoot);

        body = pRoot.getChild("body");
        bone = pRoot.getChild("bone");

        this.body.visible = true;
    }


    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 9.9F, -2.5F, 9.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 7).addBox(-0.5F, 9.4F, -2.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(4, 7).addBox(0.5F, 9.4F, -2.75F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(4, 9).addBox(0.5F, 11.4F, -2.75F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone = body.addOrReplaceChild("bone",
                    CubeListBuilder.create().texOffs(10, 7).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 11).addBox(0.05F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(5.25F, 11.4F, 0.0F, 0.0F, 0.0F, -0.1309F));


        }, 32, 32);
    }

    @Override
    public void setAllVisible(boolean visible) {
        this.head.visible = false;
        this.hat.visible = false;
        this.body.visible = true;
        this.rightArm.visible = false;
        this.leftArm.visible = false;
        this.rightLeg.visible = false;
        this.leftLeg.visible = false;
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
