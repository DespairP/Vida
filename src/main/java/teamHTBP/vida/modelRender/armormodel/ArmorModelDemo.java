package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorModelDemo extends AbstractModelElementArmor<PlayerEntity>{
    private ModelRenderer head_2_r1;

    public ArmorModelDemo(EquipmentSlotType part) {
        super(128, 128, part);
    }


    @Override
    public void initModel() {
        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(96, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);
        head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        head_2_r1 = new ModelRenderer(this);
        head_2_r1.setRotationPoint(5.0F, -6.0F, -3.0F);
        head.addChild(head_2_r1);
        setRotationAngle(head_2_r1, -0.1739F, 0.0151F, 0.0859F);
        head_2_r1.setTextureOffset(0, 29).addBox(-0.75F, -5.75F, 0.25F, 0.0F, 5.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(104, 112).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
        body.setTextureOffset(15, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.5F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        leftArm.setTextureOffset(112, 112).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
        leftArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, true);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        rightLeg.setTextureOffset(112, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        rightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
        rightLeg.setTextureOffset(0, 54).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        leftLeg.setTextureOffset(112, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
        leftLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, true);
        leftLeg.setTextureOffset(0, 54).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, true);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        rightArm.setTextureOffset(112, 112).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        rightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        belt = new ModelRenderer(this);
        belt.setRotationPoint(0.0F, 0.0F, 0.0F);
        belt.setTextureOffset(64, 24).addBox(-4.0F, 11.0F, -2.0F, 8.0F, 5.0F, 4.0F, 0.5F, false);
    }
}
