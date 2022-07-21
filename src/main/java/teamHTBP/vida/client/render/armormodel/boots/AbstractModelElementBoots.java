package teamHTBP.vida.client.render.armormodel.boots;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractModelElementBoots<T extends LivingEntity> extends HumanoidModel<T> {

    public ModelPart leg_right;
    public ModelPart leg_left;

    public AbstractModelElementBoots(ModelPart pRoot) {
        super(pRoot);

        leg_right = pRoot.getChild("leg_right");
        leg_left = pRoot.getChild("leg_left");
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        leg_left.copyFrom(leftLeg);
        leg_right.copyFrom(rightLeg);
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.leg_right, this.leg_left, this.hat);
    }

    @Override
    public void setAllVisible(boolean visible) {
        this.head.visible = false;
        this.hat.visible = false;
        this.body.visible = false;
        this.rightArm.visible = false;
        this.leftArm.visible = false;
        this.leg_right.visible = true;
        this.leg_left.visible = true;
    }
}
