package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;

public class CopiedBipedModel extends HumanoidModel {
    public ModelPart bipedHead;
    public ModelPart bipedHeadwear;
    public ModelPart bipedBody;
    public ModelPart bipedRightArm;
    public ModelPart bipedLeftArm;
    public ModelPart bipedRightLeg;
    public ModelPart bipedLeftLeg;
    
    public CopiedBipedModel(float modelSize) {
        super(1);
        texWidth = 64;
        texHeight  = 64;
        this.bipedHead = new ModelPart(this);
        this.bipedHead.setPos(0.0F, 0.0F, 0.0F);
        this.bipedHead.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 1.0f, false);
        this.bipedHeadwear = new ModelPart(this);
        this.bipedHeadwear.setPos(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 1.5f, false);
        this.bipedBody = new ModelPart(this);
        this.bipedBody.setPos(0.0F, 0.0F, 0.0F);
        this.bipedBody.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 1.0f, false);
        this.bipedRightArm = new ModelPart(this);
        this.bipedRightArm.setPos(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 1.0f, false);
        this.bipedLeftArm = new ModelPart(this);
        this.bipedLeftArm.setPos(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.texOffs(0, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 1.0f, true);
        this.bipedRightLeg = new ModelPart(this);
        this.bipedRightLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 1.0f, false);
        this.bipedLeftLeg = new ModelPart(this);
        this.bipedLeftLeg.setPos(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 1.0f, true);
    }
}
