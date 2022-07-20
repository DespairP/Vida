package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthBoots extends AbstractModelElementBoots {
    private final ModelRenderer bone;
    private final ModelRenderer bone2;

    public ArmorModelEarthBoots() {
        super(128, 128);

        leg_left = new ModelRenderer(this);
        leg_left.setPos(2.0F, 12.0F, 0.0F);
        leg_left.texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(38, 56).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        leg_left.texOffs(54, 61).addBox(-1.5F, 5.0F, -2.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 12.25F, -1.75F);
        leg_left.addChild(bone);
        setRotationAngle(bone, -0.1309F, 0.0F, 0.0F);
        bone.texOffs(62, 62).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        bone.texOffs(54, 63).addBox(-1.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.texOffs(54, 62).addBox(0.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        leg_right = new ModelRenderer(this);
        leg_right.setPos(-2.0F, 12.0F, 0.0F);
        leg_right.texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(38, 56).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        leg_right.texOffs(54, 61).addBox(-1.5F, 5.0F, -2.5F, 3.0F, 3.0F, 1.0F, 0.0F, true);

        bone2 = new ModelRenderer(this);
        bone2.setPos(0.0F, 12.25F, -1.75F);
        leg_right.addChild(bone2);
        setRotationAngle(bone2, -0.1309F, 0.0F, 0.0F);
        bone2.texOffs(62, 62).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        bone2.texOffs(54, 63).addBox(0.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        bone2.texOffs(54, 62).addBox(-1.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
