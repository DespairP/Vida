package teamHTBP.vida.utils;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.function.Consumer;

/**
 * @author DustW
 */
public class ModelHelper {
    public static LayerDefinition createBodyLayer(Consumer<PartDefinition> definition) {
        return createBodyLayer(definition, 64, 64);
    }

    public static LayerDefinition createBodyLayer(Consumer<PartDefinition> definition, int width, int height) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        definition.accept(partdefinition);

        return LayerDefinition.create(meshdefinition, width, height);
    }
}
