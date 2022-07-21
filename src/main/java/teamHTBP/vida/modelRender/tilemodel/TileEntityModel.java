package teamHTBP.vida.modelRender.tilemodel;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import teamHTBP.vida.modelRender.AutoRegModel;

@AutoRegModel
public abstract class TileEntityModel extends Model {

    public TileEntityModel(ModelPart part) {
        super(RenderType::entityCutoutNoCull);
    }
}
