package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireChestplates extends AbstractModelElementChestplates {


    public ArmorModelFireChestplates() {
        super(128, 128);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(48, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        arm_left = new ModelRenderer(this);
        arm_left.setPos(5.0F, 2.0F, 0.0F);
        arm_left.texOffs(48, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        arm_right = new ModelRenderer(this);
        arm_right.setPos(-5.0F, 2.0F, 0.0F);
        arm_right.texOffs(48, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    }
}
