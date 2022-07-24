package teamHTBP.vida.client.model.blockentity;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public abstract class VidaBlockEntityModel extends Model {

    public VidaBlockEntityModel(ModelPart part) {
        super(RenderType::entityCutoutNoCull);
    }
}
