package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import teamHTBP.vida.helper.itemHelper.PlayerInventoryHelper;

import java.util.LinkedList;
import java.util.List;
import static net.minecraft.inventory.EquipmentSlotType.*;

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
    protected Iterable<ModelRenderer> getHeadParts() {
        return renderPart == HEAD ? ImmutableList.of(head) : ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        List<ModelRenderer> renderParts;
        switch (renderPart){
            case CHEST:
                return ImmutableList.of(leftArm, rightArm, body, bipedLeftArm, bipedRightArm, bipedBody);
            case FEET:
                return ImmutableList.of(leftLeg, rightLeg, bipedLeftLeg, bipedRightLeg);
            case LEGS:
                return ImmutableList.of(leftLeg, rightLeg, belt);
            case HEAD:
            default:
                return ImmutableList.of();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.head.showModel = visible;
        this.body.showModel = visible;
        this.leftArm.showModel = visible;
        this.rightArm.showModel = visible;
        this.leftLeg.showModel = visible;
        this.rightLeg.showModel = visible;
        this.belt.showModel = visible;
        super.setVisible(visible);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        copyModelRotation();
        setVisible(true);
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public abstract void initModel();

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void copyModelRotation(){
        this.head.copyModelAngles(bipedHead);
        this.body.copyModelAngles(bipedBody);
        this.leftArm.copyModelAngles(bipedLeftArm);
        this.rightArm.copyModelAngles(bipedRightArm);
        this.leftLeg.copyModelAngles(bipedLeftLeg);
        this.rightLeg.copyModelAngles(bipedRightLeg);
    }
}
