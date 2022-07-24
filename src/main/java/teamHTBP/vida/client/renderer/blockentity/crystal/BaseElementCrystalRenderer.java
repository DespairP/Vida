package teamHTBP.vida.client.renderer.blockentity.crystal;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import teamHTBP.vida.blockentity.crystal.BaseCrystalBlockEntity;
import teamHTBP.vida.client.renderer.RenderRegistry;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;

import java.util.Map;

public class BaseElementCrystalRenderer<T extends BaseCrystalBlockEntity> extends VidaBaseBlockEntityRenderer<T> {
    public ResourceLocation ELEMENT_CRYSTAL;

    static final Map<IElement, ResourceLocation> MODEL_MAP = new ImmutableMap.Builder<IElement, ResourceLocation>()
            .put(EnumElements.GOLD, RenderRegistry.goldCrystalLocation)
            .put(EnumElements.AQUA, RenderRegistry.aquaCrystalLocation)
            .put(EnumElements.EARTH, RenderRegistry.earthCrystalLocation)
            .put(EnumElements.FIRE, RenderRegistry.fireCrystalLocation)
            .put(EnumElements.WOOD, RenderRegistry.woodCrystalLocation)
            .build();

    public BaseElementCrystalRenderer(BlockEntityRendererProvider.Context context, IElement element) {
        super(context);
        ELEMENT_CRYSTAL = MODEL_MAP.getOrDefault(element, RenderRegistry.goldCrystalLocation);
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(ELEMENT_CRYSTAL);
        VertexConsumer builder = bufferIn.getBuffer(RenderType.cutout());
        Matrix4f matrixStack = matrixStackIn.last().pose();
        // System.out.println(element);
        // sinWave = 0;
        float sinWave = tileEntityIn.sinWave;
        double floatWave = Math.sin(sinWave);
        long time = System.currentTimeMillis();
        float angle = (time / 50) % 360;


        float colorX = (float) (0.7 - 0.3 * floatWave);
        matrixStackIn.translate(0.6f, 0.6f + 0.03 * floatWave, 0.6f);
        matrixStackIn.scale(0.4f, 0.4f, 0.4f);
        matrixStackIn.mulPose(new Quaternion(0, angle, 0, true));
        matrixStackIn.translate(-0.5, -0.5f, -0.5);
        float Minu = atlasTexture.getU0();
        float Maxu = atlasTexture.getU1() - (atlasTexture.getU1() - atlasTexture.getU0()) / 4.0f;
        float MinV = atlasTexture.getV0();
        float MaxV = atlasTexture.getV1() - (atlasTexture.getV1() - atlasTexture.getV0()) / 4.0f;

        float length = 0.25f;

        //System.out.println(Minu - Maxu);
        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();


        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();


        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();

        builder.vertex(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MaxV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).uv(Maxu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();
        builder.vertex(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).uv(Minu, MinV).uv2(240, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.popPose();
    }
}
