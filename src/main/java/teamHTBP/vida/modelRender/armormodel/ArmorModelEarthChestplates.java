package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelEarthChestplates extends AbstractModelElementChestplates {

    public ArmorModelEarthChestplates() {
        super(128, 128);

        arm_right = new ModelRenderer(this);
        arm_right.setPos(-4.0F, 1.0F, 0.0F);
        arm_right.texOffs(26, 36).addBox(-4.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, 0.0F, true);
        arm_right.texOffs(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.texOffs(0, 37).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);
        arm_right.texOffs(16, 38).addBox(-4.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);

        arm_left = new ModelRenderer(this);
        arm_left.setPos(4.0F, 1.0F, 0.0F);
        arm_left.texOffs(26, 36).addBox(1.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, 0.0F, false);
        arm_left.texOffs(0, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(0, 37).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);
        arm_left.texOffs(16, 38).addBox(3.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, 0.1F, false);
        body.texOffs(24, 18).addBox(-4.0F, 0.0F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
        body.texOffs(62, 19).addBox(-3.0F, 0.0F, 1.25F, 6.0F, 8.0F, 2.0F, 0.0F, false);
        body.texOffs(44, 18).addBox(-3.5F, 5.0F, -3.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);
    }
}
