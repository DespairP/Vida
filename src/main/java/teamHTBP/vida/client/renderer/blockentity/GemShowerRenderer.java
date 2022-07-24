package teamHTBP.vida.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.blockentity.TileEntityGemShower;
import teamHTBP.vida.client.renderer.RenderRegistry;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;
import teamHTBP.vida.item.VidaItemRegistry;

public class GemShowerRenderer extends VidaBaseBlockEntityRenderer<TileEntityGemShower> {
    float r = 1, g = 1, b = 1, a = 1;
    double height = 0.0f;

    public GemShowerRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public void render(TileEntityGemShower tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntityIn.gemItem.isEmpty()) {
            TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(this.getGemResource(tileEntityIn.gemItem));
            double floatHeight = Math.sin(height) * 0.1;
            this.height += 0.01;

            if (height >= Math.PI * 2) {
                this.height = 0.0f;
            }

            float uMin = textureAtlasSprite.getU0();
            float uMax = textureAtlasSprite.getU1();
            float vMin = textureAtlasSprite.getV0();
            float vMax = textureAtlasSprite.getV1();

            // 渲染中间的宝石块
            VertexConsumer buffer = bufferIn.getBuffer(RenderType.cutout());

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
            textureAtlasSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(this.getlogoResource(tileEntityIn.gemItem));
            uMin = textureAtlasSprite.getU0();
            uMax = textureAtlasSprite.getU1();
            vMin = textureAtlasSprite.getV0();
            vMax = textureAtlasSprite.getV1();
            //draw logo
            matrixStackIn.pushPose();
            VertexConsumer bufferLogo = bufferIn.getBuffer(RenderType.cutout());
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
        if (putGemItem == VidaItemRegistry.ELEMENTGEM_FIRE.get()) {
            return RenderRegistry.firegemLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_GOLD.get()) {
            return RenderRegistry.goldgemLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_WOOD.get()) {
            return RenderRegistry.woodgemLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_AQUA.get()) {
            return RenderRegistry.aquagemLocation;
        } else {
            return RenderRegistry.earthgemLocation;
        }
    }

    public ResourceLocation getlogoResource(ItemStack itemStack) {
        Item putGemItem = itemStack.getItem();
        if (putGemItem == VidaItemRegistry.ELEMENTGEM_FIRE.get()) {
            return RenderRegistry.firelogoLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_GOLD.get()) {
            return RenderRegistry.goldlogoLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_WOOD.get()) {
            return RenderRegistry.woodlogoLocation;
        } else if (putGemItem == VidaItemRegistry.ELEMENTGEM_AQUA.get()) {
            return RenderRegistry.aqualogoLocation;
        } else {
            return RenderRegistry.earthlogoLocation;
        }
    }
}
