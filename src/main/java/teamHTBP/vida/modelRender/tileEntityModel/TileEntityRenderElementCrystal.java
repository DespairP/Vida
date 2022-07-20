package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityElementCrystal;
import teamHTBP.vida.modelRender.RenderLoader;

public class TileEntityRenderElementCrystal extends BlockEntityRenderer<TileEntityElementCrystal> {
    public ResourceLocation ELEMENT_CRYSTAL;
    private int element = 0;

    public TileEntityRenderElementCrystal(BlockEntityRenderDispatcher rendererDispatcherIn, int element) {
        super(rendererDispatcherIn);
        this.element = element;
        switch (element) {
            case 1:
                ELEMENT_CRYSTAL = RenderLoader.goldCrystalLocation;
                break;
            case 2:
                ELEMENT_CRYSTAL = RenderLoader.woodCrystalLocation;
                break;
            case 3:
                ELEMENT_CRYSTAL = RenderLoader.aquaCrystalLocation;
                break;
            case 4:
                ELEMENT_CRYSTAL = RenderLoader.fireCrystalLocation;
                break;
            case 5:
                ELEMENT_CRYSTAL = RenderLoader.earthCrystalLocation;
                break;
            default:
                ELEMENT_CRYSTAL = new ResourceLocation(Vida.MOD_ID, "model/earthelementcrystal");
        }
    }

    @Override
    public void render(TileEntityElementCrystal tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();
        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(ELEMENT_CRYSTAL);
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
