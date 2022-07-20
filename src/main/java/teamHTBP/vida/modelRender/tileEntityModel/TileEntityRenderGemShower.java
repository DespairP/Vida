package teamHTBP.vida.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import teamHTBP.vida.TileEntity.TileEntityGemShower;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.modelRender.RenderLoader;

public class TileEntityRenderGemShower extends TileEntityRenderer<TileEntityGemShower> {
    float r = 1, g = 1, b = 1, a = 1;
    double height = 0.0f;

    public TileEntityRenderGemShower(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityGemShower tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntityIn.gemItem.isEmpty()) {
            TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(this.getGemResource(tileEntityIn.gemItem));
            double floatHeight = Math.sin(height) * 0.1;
            this.height += 0.01;
            if (height >= Math.PI * 2) this.height = 0.0f;

            float uMin = textureAtlasSprite.getU0();
            float uMax = textureAtlasSprite.getU1();
            float vMin = textureAtlasSprite.getV0();
            float vMax = textureAtlasSprite.getV1();

            // 渲染中间的宝石块
            IVertexBuilder buffer = bufferIn.getBuffer(RenderType.cutout());

            matrixStackIn.pushPose();
            matrixStackIn.translate(.5F, 1.06F, .5F);
            matrixStackIn.scale(0.1f, 0.1f, 0.1f);
            matrixStackIn.mulPose(this.renderer.camera.rotation());
            matrixStackIn.translate(-.5F, -0.9F, -.5F);
            Matrix4f matrix4f = matrixStackIn.last().pose();

            //int light = this.getBrightnessForRender(tileEntityIn, partialTicks);
            buffer.vertex(matrix4f, 0, 1, 1).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 1, 0).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 1, 1).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 1, 0).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();

            buffer.vertex(matrix4f, 0, 0, 0).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(1, 1, 1).endVertex();
            buffer.vertex(matrix4f, 1, 0, 0).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(1, 1, 1).endVertex();
            buffer.vertex(matrix4f, 1, 0, 1).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(1, 1, 1).endVertex();
            buffer.vertex(matrix4f, 0, 0, 1).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(1, 1, 1).endVertex();

            buffer.vertex(matrix4f, 0, 1, 0).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 1, 0).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 0, 0).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 0, 0).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();

            buffer.vertex(matrix4f, 0, 1, 1).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 1, 0).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 0, 0).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 0, 1).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();

            buffer.vertex(matrix4f, 0, 1, 1).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 1, 1).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 0, 1).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(1, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0, 0, 1).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(1, 1, 0).endVertex();


            buffer.vertex(matrix4f, 1, 1, 1).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 1, 0).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 0, 0).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 1, 0, 1).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();


            matrixStackIn.popPose();

            // 渲染logo
            textureAtlasSprite = Minecraft.getInstance().getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(this.getlogoResource(tileEntityIn.gemItem));
            uMin = textureAtlasSprite.getU0();
            uMax = textureAtlasSprite.getU1();
            vMin = textureAtlasSprite.getV0();
            vMax = textureAtlasSprite.getV1();
            //draw logo
            matrixStackIn.pushPose();
            IVertexBuilder bufferLogo = bufferIn.getBuffer(RenderType.cutout());
            matrixStackIn.translate(0.5f, 2.0F + floatHeight, 0.5f);
            matrixStackIn.mulPose(this.renderer.camera.rotation());
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            matrixStackIn.translate(-0.5f, -0.5, -0.5f);

            Matrix4f matrix4flogo = matrixStackIn.last().pose();
            bufferLogo.vertex(matrix4flogo, 0, 1, 0.5F).color(r, g, b, a).uv(uMin, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();
            bufferLogo.vertex(matrix4flogo, 1, 1, 0.5F).color(r, g, b, a).uv(uMin, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            bufferLogo.vertex(matrix4flogo, 1, 0, 0.5F).color(r, g, b, a).uv(uMax, vMax).uv2(120, 240).normal(0, 1, 0).endVertex();
            bufferLogo.vertex(matrix4flogo, 0, 0, 0.5F).color(r, g, b, a).uv(uMax, vMin).uv2(120, 240).normal(0, 1, 0).endVertex();

            matrixStackIn.popPose();

        }
    }

    public ResourceLocation getGemResource(ItemStack itemStack) {
        Item putGemItem = itemStack.getItem();
        if (putGemItem == ItemLoader.ELEMENTGEM_FIRE.get()) {
            return RenderLoader.firegemLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_GOLD.get()) {
            return RenderLoader.goldgemLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_WOOD.get()) {
            return RenderLoader.woodgemLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_AQUA.get()) {
            return RenderLoader.aquagemLocation;
        } else {
            return RenderLoader.earthgemLocation;
        }
    }

    public ResourceLocation getlogoResource(ItemStack itemStack) {
        Item putGemItem = itemStack.getItem();
        if (putGemItem == ItemLoader.ELEMENTGEM_FIRE.get()) {
            return RenderLoader.firelogoLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_GOLD.get()) {
            return RenderLoader.goldlogoLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_WOOD.get()) {
            return RenderLoader.woodlogoLocation;
        } else if (putGemItem == ItemLoader.ELEMENTGEM_AQUA.get()) {
            return RenderLoader.aqualogoLocation;
        } else {
            return RenderLoader.earthlogoLocation;
        }
    }

    protected int getBrightnessForRender(TileEntityGemShower tileEntityGemShower, float partialTick) {
        BlockPos blockpos = new BlockPos(tileEntityGemShower.getBlockPos().getX(), tileEntityGemShower.getBlockPos().getY(), tileEntityGemShower.getBlockPos().getZ());
        return tileEntityGemShower.getLevel().hasChunkAt(blockpos) ? WorldRenderer.getLightColor(tileEntityGemShower.getLevel(), blockpos) : 0;
    }
}
