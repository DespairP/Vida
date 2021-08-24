package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import teamHTBP.vida.TileEntity.TileEntityInjectTable;
import teamHTBP.vida.client.ClientTickHandler;

public class TileEntityRenderInjectTable extends TileEntityRenderer<TileEntityInjectTable> {
    double sinWave() {
        return (ClientTickHandler.tick() * 0.1) % (Math.PI * 2);
    }


    public TileEntityRenderInjectTable(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityInjectTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        TextureAtlasSprite atlasTexture = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(RenderLoader.injectTable_top);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getCutout());
        Matrix4f matrixStack = matrixStackIn.getLast().getMatrix();


        matrixStackIn.translate(0.5F, 0.65, 0.5F);
        matrixStackIn.scale(0.4f, 0.4f, 0.4f);
        matrixStackIn.rotate(new Quaternion(0, ClientTickHandler.tick() % 360, 0, true));
        matrixStackIn.translate(-0.5f, -0.5f, -0.5f);
        float Minu = atlasTexture.getMinU();
        float Maxu = atlasTexture.getMaxU();
        float MinV = atlasTexture.getMinV();
        float MaxV = atlasTexture.getMaxV();

        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();


        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.pop();


        matrixStackIn.push();
        matrixStack = matrixStackIn.getLast().getMatrix();
        matrixStackIn.translate(0.5F, 0.70, 0.5F);
        matrixStackIn.scale(0.4f, 0.15f, 0.4f);
        matrixStackIn.rotate(new Quaternion(0, ClientTickHandler.tick() % 360, 0, true));
        matrixStackIn.translate(-0.5f, 0, -0.5f);

        atlasTexture = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(RenderLoader.injectTable_side);
        Minu = atlasTexture.getMinU();
        Maxu = atlasTexture.getMaxU();
        MinV = atlasTexture.getMinV();
        MaxV = atlasTexture.getMaxV();

        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 0, 0, 0).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 0, 1, 0).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();

        builder.pos(matrixStack, 1, 0, 1).color(1, 1, 1, 1.0f).tex(Minu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 0, 0).color(1, 1, 1, 1.0f).tex(Maxu, MaxV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 0).color(1, 1, 1, 1.0f).tex(Maxu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();
        builder.pos(matrixStack, 1, 1, 1).color(1, 1, 1, 1.0f).tex(Minu, MinV).lightmap(150, 240).normal(1, 0, 0).endVertex();


        matrixStackIn.pop();

        if (tileEntityIn.hasSwordItem() && tileEntityIn.getSwordStack().getItem() instanceof SwordItem) {
            matrixStackIn.push();
            double floating = 0.12 * Math.sin(sinWave());
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.rotate(new Quaternion(0, 0, 180 - 45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.getSwordStack(), tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.getSwordStack(), ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        } else if (tileEntityIn.hasSwordItem()) {
            matrixStackIn.push();
            double floating = 0.12 * Math.sin(sinWave());
            matrixStackIn.translate(0.5f, 1.8f + floating, 0.5f);
            matrixStackIn.rotate(new Quaternion(0, 0, -45, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.getSwordStack(), tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.getSwordStack(), ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, 240, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        }

    }
}
