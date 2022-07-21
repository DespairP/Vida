package teamHTBP.vida.client.render.tilemodel;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public abstract class TileEntityModel extends Model {

    public TileEntityModel(ModelPart part) {
        super(RenderType::entityCutoutNoCull);
    }
}
