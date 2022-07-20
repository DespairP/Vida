package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ArmorModelGoldHelmet<T extends Entity> extends AbstractModelElementHelmet {
    private final ModelRenderer bone;


    public ArmorModelGoldHelmet() {
        super(1.0f, 0, 128, 128);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, -5.5F, -4.25F);
        head.addChild(bone);
        setRotationAngle(bone, 0.0436F, 0.0F, 0.0F);
        bone.texOffs(72, 11).addBox(-1.0F, 1.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        bone.texOffs(84, 11).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        bone.texOffs(106, 12).addBox(2.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        bone.texOffs(106, 12).addBox(-3.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        bone.texOffs(78, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        bone.texOffs(90, 13).addBox(-4.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        bone.texOffs(98, 13).addBox(1.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        bone.texOffs(64, 14).addBox(1.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
        bone.texOffs(64, 14).addBox(-4.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
