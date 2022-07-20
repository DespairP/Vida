package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.List;

import static net.minecraft.inventory.EquipmentSlotType.HEAD;

public abstract class AbstractModelElementArmor<T extends LivingEntity> extends BipedModel<T> {
    public final EquipmentSlotType renderPart;
    public ModelRenderer head;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer body;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer belt;

    public AbstractModelElementArmor(int textureWidthIn, int textureHeightIn, EquipmentSlotType part) {
        this(1.0f, 0, textureWidthIn, textureHeightIn,part);
    }

    public AbstractModelElementArmor(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn,EquipmentSlotType part) {
        super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
        this.head     = new ModelRenderer(this, 0, 0);
        this.body     = new ModelRenderer(this,0,0);
        this.leftArm  = new ModelRenderer(this,0,0);
        this.rightArm = new ModelRenderer(this,0,0);
        this.leftLeg  = new ModelRenderer(this,0,0);
        this.rightLeg = new ModelRenderer(this,0,0);
        this.belt = new ModelRenderer(this,0,0);
        this.renderPart = part;
        initModel();
    }


    @Override
    protected Iterable<ModelRenderer> headParts() {
        return renderPart == HEAD ? ImmutableList.of(head) : ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        List<ModelRenderer> renderParts;
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
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        copyModelRotation();
        setAllVisible(true);
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public abstract void initModel();

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
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
