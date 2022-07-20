package teamHTBP.vida.modelRender.tilemodel;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public abstract class TileEntityModel extends Model {

    public TileEntityModel() {
        super(RenderType::entityCutoutNoCull);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
