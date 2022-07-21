package teamHTBP.vida.modelRender.armormodel.armor;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import teamHTBP.vida.utils.ModelHelper;

public class ArmorModelSeasonApprentice extends AbstractModelElementArmor<Player> {

    private ModelPart head_2_r1;

    public ArmorModelSeasonApprentice(ModelPart root, EquipmentSlot part) {
        super(root, part);
    }

    @Override
    public void initModel(ModelPart root) {
        head_2_r1 = root.getChild("head_2_r1");
    }

    // TODO
    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(96, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F))
                            .texOffs(32, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var head_2_r1 = head.addOrReplaceChild("head_2_r1",
                    CubeListBuilder.create().texOffs(0, 61).addBox(-0.75F, -5.75F, 0.25F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(5.0F, -6.0F, -3.0F, -0.1739F, 0.0151F, 0.0859F));

            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(104, 112).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(16, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                            .texOffs(15, 64).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var leftArm = partDefinition.addOrReplaceChild("leftArm",
                    CubeListBuilder.create().texOffs(112, 112).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(40, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                    ,PartPose.offset(5.0F, 2.0F, 0.0F));

            var rightLeg = partDefinition.addOrReplaceChild("rightLeg",
                    CubeListBuilder.create().texOffs(112, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                            .texOffs(0, 86).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F))
                    ,PartPose.offset(-1.9F, 12.0F, 0.0F));

            var leftLeg = partDefinition.addOrReplaceChild("leftLeg",
                    CubeListBuilder.create().texOffs(112, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                            .texOffs(0, 86).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F))
                    ,PartPose.offset(1.9F, 12.0F, 0.0F));

            var rightArm = partDefinition.addOrReplaceChild("rightArm",
                    CubeListBuilder.create().texOffs(112, 112).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                            .texOffs(40, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                    ,PartPose.offset(-5.0F, 2.0F, 0.0F));

            var belt = partDefinition.addOrReplaceChild("belt",
                    CubeListBuilder.create().texOffs(64, 56).addBox(-4.0F, 11.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.5F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

        }, 128, 128);
    }
}
