package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.TileEntity.TileEntityElementCrystal;
import teamHTBP.vida.TileEntity.TileEntityGoldElementCrystal;
import teamHTBP.vida.Vida;


public class TileEntityRenderGoldCrystal extends TileEntityRenderer<TileEntityGoldElementCrystal> {
    private int element = 0;
    public ResourceLocation ELEMENT_CRYSTAL;
    public TileEntityRenderGoldCrystal(TileEntityRendererDispatcher rendererDispatcherIn, int element) {
        super(rendererDispatcherIn);
        this.element = element;
        switch (element){
            case 1:
                ELEMENT_CRYSTAL = RenderLoader.goldCrystalLocation;break;
            case 2:
                ELEMENT_CRYSTAL = RenderLoader.woodCrystalLocation;break;
            case 3:
                ELEMENT_CRYSTAL = RenderLoader.aquaCrystalLocation;break;
            case 4:
                ELEMENT_CRYSTAL = RenderLoader.fireCrystalLocation;break;
            case 5:
                ELEMENT_CRYSTAL = RenderLoader.earthCrystalLocation;break;
            default:
                ELEMENT_CRYSTAL = new ResourceLocation(Vida.modId,"model/earthelementcrystal");
        }
    }

    @Override
    public void render(TileEntityGoldElementCrystal tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(ELEMENT_CRYSTAL);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getCutout());
        Matrix4f matrixStack = matrixStackIn.getLast().getMatrix();
        // System.out.println(element);
        // sinWave = 0;
        float sinWave = tileEntityIn.sinWave;
        double floatWave = Math.sin(sinWave);
        long time = System.currentTimeMillis();
        float angle = (time / 50) % 360;


        float colorX = (float) (0.7  - 0.3 * floatWave);
        matrixStackIn.translate(0.6f, 0.6f + 0.03 * floatWave, 0.6f);
        matrixStackIn.scale(0.4f, 0.4f, 0.4f);
        matrixStackIn.rotate(new Quaternion(0, angle, 0, true));
        matrixStackIn.translate(-0.5, -0.5f, -0.5);
        float Minu = atlasTexture.getMinU();
        float Maxu = atlasTexture.getMaxU();
        float MinV = atlasTexture.getMinV();
        float MaxV =atlasTexture.getMaxV();

        float length = 0.25f;

        //System.out.println(Minu - Maxu);
        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();


        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();


        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX,1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX,1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX,1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX,1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 1, 1, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MaxV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(colorX, colorX, colorX, 1.0f).tex(Maxu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(colorX, colorX, colorX, 1.0f).tex(Minu, MinV).lightmap(240, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.pop();
    }
}
