package teamHTBP.vida.modelRender.armormodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelBelt extends HumanoidModel<Player> {
    private final ModelPart body; //模型主要部分
    private final ModelPart bone; //模型次要部分


    public ArmorModelBelt() {
        super(1.0f, 0, 32, 32);
        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(0, 0).addBox(-4.5F, 9.9F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, false);
        body.texOffs(0, 7).addBox(-0.5F, 9.4F, -2.75F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        body.texOffs(4, 7).addBox(0.5F, 9.4F, -2.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        body.texOffs(4, 9).addBox(0.5F, 11.4F, -2.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        bone = new ModelPart(this);
        bone.setPos(5.25F, 11.4F, 0.0F);
        body.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.1309F);
        bone.texOffs(10, 7).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
        bone.texOffs(0, 11).addBox(0.05F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        this.body.visible = true;
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
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
