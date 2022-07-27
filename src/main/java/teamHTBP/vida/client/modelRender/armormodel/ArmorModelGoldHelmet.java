package teamHTBP.vida.client.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ArmorModelGoldHelmet<T extends Entity> extends AbstractModelElementHelmet {
    private final ModelRenderer bone;


    public ArmorModelGoldHelmet() {
        super(1.0f, 0, 128, 128);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -5.5F, -4.25F);
        head.addChild(bone);
        setRotationAngle(bone, 0.0436F, 0.0F, 0.0F);
        bone.setTextureOffset(72, 11).addBox(-1.0F, 1.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(84, 11).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(106, 12).addBox(2.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(106, 12).addBox(-3.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(78, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(90, 13).addBox(-4.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(98, 13).addBox(1.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(64, 14).addBox(1.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);
        bone.setTextureOffset(64, 14).addBox(-4.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
