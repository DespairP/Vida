package teamHTBP.vida.client.render.armormodel.armor;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

import static net.minecraft.world.entity.EquipmentSlot.HEAD;

public abstract class AbstractModelElementArmor<T extends LivingEntity> extends HumanoidModel<T> {
    public final EquipmentSlot renderPart;
    public ModelPart head;
    public ModelPart leftArm;
    public ModelPart rightArm;
    public ModelPart body;
    public ModelPart leftLeg;
    public ModelPart rightLeg;
    public ModelPart belt;

    public AbstractModelElementArmor(ModelPart root, EquipmentSlot part) {
        super(root);

        head = root.getChild("head");
        leftArm = root.getChild("leftArm");
        rightArm = root.getChild("rightArm");
        body = root.getChild("body");
        leftLeg = root.getChild("leftLeg");
        rightLeg = root.getChild("rightLeg");
        belt = root.getChild("belt");

        this.renderPart = part;
        initModel(root);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return renderPart == HEAD ? ImmutableList.of(head) : ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        List<ModelPart> renderParts;
        switch (renderPart){
            case CHEST:
                return ImmutableList.of(leftArm, rightArm, body, leftArm, rightArm, body);
            case FEET:
                return ImmutableList.of(leftLeg, rightLeg, leftLeg, rightLeg);
            case LEGS:
                return ImmutableList.of(leftLeg, rightLeg, belt);
            case HEAD:
            default:
                return ImmutableList.of();
        }
    }

    @Override
    public void setAllVisible(boolean visible) {
        this.head.visible = visible;
        this.body.visible = visible;
        this.leftArm.visible = visible;
        this.rightArm.visible = visible;
        this.leftLeg.visible = visible;
        this.rightLeg.visible = visible;
        this.belt.visible = visible;
        super.setAllVisible(visible);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        copyModelRotation();
        setAllVisible(true);
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public abstract void initModel(ModelPart root);

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public void copyModelRotation(){
        this.head.copyFrom(head);
        this.body.copyFrom(body);
        this.leftArm.copyFrom(leftArm);
        this.rightArm.copyFrom(rightArm);
        this.leftLeg.copyFrom(leftLeg);
        this.rightLeg.copyFrom(rightLeg);
    }
}
