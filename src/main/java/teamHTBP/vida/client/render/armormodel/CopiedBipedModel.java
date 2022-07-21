package teamHTBP.vida.client.render.armormodel;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class CopiedBipedModel extends HumanoidModel {
    public ModelPart bipedHead;
    public ModelPart bipedHeadwear;
    public ModelPart bipedBody;
    public ModelPart bipedRightArm;
    public ModelPart bipedLeftArm;
    public ModelPart bipedRightLeg;
    public ModelPart bipedLeftLeg;

    public CopiedBipedModel(ModelPart pRoot) {
        super(pRoot);

        bipedHead = pRoot.getChild("bipedHead");
        bipedHeadwear = pRoot.getChild("bipedHeadwear");
        bipedBody = pRoot.getChild("bipedBody");
        bipedRightArm = pRoot.getChild("bipedRightArm");
        bipedLeftArm = pRoot.getChild("bipedLeftArm");
        bipedRightLeg = pRoot.getChild("bipedRightLeg");
        bipedLeftLeg = pRoot.getChild("bipedLeftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerH(partDefinition -> {
            var bipedHead = partDefinition.addOrReplaceChild("bipedHead",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var bipedHeadwear = partDefinition.addOrReplaceChild("bipedHeadwear",
                    CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.5F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var bipedBody = partDefinition.addOrReplaceChild("bipedBody",
                    CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var bipedRightArm = partDefinition.addOrReplaceChild("bipedRightArm",
                    CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(-5.0F, 2.0F, 0.0F));

            var bipedLeftArm = partDefinition.addOrReplaceChild("bipedLeftArm",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(5.0F, 2.0F, 0.0F));

            var bipedRightLeg = partDefinition.addOrReplaceChild("bipedRightLeg",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(-1.9F, 12.0F, 0.0F));

            var bipedLeftLeg = partDefinition.addOrReplaceChild("bipedLeftLeg",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
                    ,PartPose.offset(1.9F, 12.0F, 0.0F));


        });
    }
}
