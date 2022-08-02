package teamHTBP.vida.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;

/**
 * @author DustW
 */
public class VidaRenderTypes extends RenderStateShard {
    public VidaRenderTypes(String pName, Runnable pSetupState, Runnable pClearState) {
        super(pName, pSetupState, pClearState);
    }

    private static final ResourceLocation BLANK = new ResourceLocation(Vida.MOD_ID, "textures/special/blank.png");

    private static final RenderType OUTLINE_SOLID =
            RenderType.create(createLayerName("outline_solid"), DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS,
                    256, false,
                    false, RenderType.CompositeState.builder()
                            .setShaderState(RENDERTYPE_ENTITY_SOLID_SHADER)
                            .setCullState(CULL)
                            .setTextureState(new RenderStateShard.TextureStateShard(BLANK, false, false))
                            .setLightmapState(LIGHTMAP)
                            .setOverlayState(OVERLAY)
                            .createCompositeState(false));

    public static RenderType getOutlineSolid() {
        return OUTLINE_SOLID;
    }

    private static String createLayerName(String name) {
        return Vida.MOD_ID + ":" + name;
    }
}
