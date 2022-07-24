package teamHTBP.vida.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.blockentity.TileEntityPurfiedCauldron;
import teamHTBP.vida.client.renderer.blockentity.base.VidaBaseBlockEntityRenderer;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;

public class PurfiedCauldronRenderer extends VidaBaseBlockEntityRenderer<TileEntityPurfiedCauldron> {
    private float floating = 0; //物品悬浮增量
    private float rPlus = 0; //r变色增量
    private float gPlus = 0; //b变色增量
    private float bPlus = 0; //g变色增量

    public PurfiedCauldronRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }


    private static int getFluidColor(Level world, BlockPos pos, Fluid fluid) {
        if (fluid.isSame(Fluids.WATER)) {
            return BiomeColors.getAverageWaterColor(world, pos);
        }

        return fluid.getAttributes().getColor();
    }

    @Override
    public void render(TileEntityPurfiedCauldron tileEntityIn, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int combinedLightIn, int combinedOverlayIn) {
        Minecraft mc = Minecraft.getInstance();

        if (tileEntityIn.isWater) {
            poseStack.pushPose();
            //获取水的贴图
            Fluid fluid = Fluids.WATER.getSource();
            ResourceLocation resourceLocation;
            int color;
            if (fluid.getAttributes().getStillTexture() != null) {
                resourceLocation = fluid.getAttributes().getStillTexture();
                color = getFluidColor(tileEntityIn.getLevel(), tileEntityIn.getBlockPos(), fluid);
            } else if (fluid.getAttributes().getFlowingTexture() != null) {  // In case that Still Texture don't exist
                resourceLocation = fluid.getAttributes().getFlowingTexture();
                color = getFluidColor(tileEntityIn.getLevel(), tileEntityIn.getBlockPos(), fluid);
            } else { // In case that no texture exist
                resourceLocation = Fluids.WATER.getAttributes().getStillTexture();
                color = getFluidColor(tileEntityIn.getLevel(), tileEntityIn.getBlockPos(), Fluids.WATER);
            }
            TextureAtlasSprite textureAtlasSprite = mc.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(resourceLocation);
            //获取水贴图在精灵图中的最大/小u,v位置
            float uMin = textureAtlasSprite.getU0();
            float uMax = textureAtlasSprite.getU1();
            float vMin = textureAtlasSprite.getV0();
            float vMax = textureAtlasSprite.getV1();
            //环境光，默认为最高光亮
            int light = 15728880;
            //设置基础argb
            float a = 0.95F;
            float r = (color >> 16 & 0xFF) / 255.0F;
            float g = (color >> 8 & 0xFF) / 255.0F;
            float b = (color & 0xFF) / 255.0F;
            //进行变色
            if (changeColor(tileEntityIn, r * 255, g * 255, b * 255)) {
                r = rPlus / 255.0F;
                g = gPlus / 255.0f;
                b = bPlus / 255.0f;
            } else {
                rPlus = 63;
                gPlus = 118;
                bPlus = 228;
            }
            //开始绘制水贴图
            Matrix4f matrix4f = poseStack.last().pose();
            //首先获取水位高度
            float waterlevel = tileEntityIn.container / 110814.2f;
            poseStack.translate(0, 0.3F + waterlevel, 0);
            //设置贴图绘制方式
            VertexConsumer buffer = source.getBuffer(RenderType.translucent());
            //绘制
            buffer.vertex(matrix4f, 0.1F, 0.1F, 0.1F).color(r, g, b, a).uv(uMin, vMin).overlayCoords(0, 0).uv2(light).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0.1F, 0.1F, 0.9F).color(r, g, b, a).uv(uMin, vMax).overlayCoords(0, 0).uv2(light).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0.9F, 0.1F, 0.9F).color(r, g, b, a).uv(uMax, vMax).overlayCoords(0, 0).uv2(light).normal(0, 1, 0).endVertex();
            buffer.vertex(matrix4f, 0.9F, 0.1F, 0.1F).color(r, g, b, a).uv(uMax, vMin).overlayCoords(0, 0).uv2(light).normal(0, 1, 0).endVertex();
            //结束绘制
            mc.getProfiler().pop();
            poseStack.popPose();
        }

        if (!tileEntityIn.meltItem.isEmpty()) {
            poseStack.pushPose();
            double floatingLevel = 0.1 * Math.sin(floating);
            poseStack.translate(0.5f, 1.3f + floatingLevel, 0.55f);
            poseStack.scale(0.6f, 0.6f, 0.6f);

            BlockEntityRenderDispatcher dispatcher = this.renderer;
            Quaternion quaternion = dispatcher.camera.rotation();
            float f3 = Mth.lerp(partialTicks, 0, 0);
            quaternion.mul(Vector3f.XP.rotation(f3));

            poseStack.mulPose(quaternion);
            ItemRenderer itemRenderer = mc.getItemRenderer();

            BakedModel ibakedmodel = itemRenderer.getModel(tileEntityIn.meltItem, tileEntityIn.getLevel(), null, 0);
            itemRenderer.render(tileEntityIn.meltItem, ItemTransforms.TransformType.FIXED, true, poseStack, source, combinedLightIn, combinedOverlayIn, ibakedmodel);

            if (floating >= 2 * Math.PI) {
                floating = 0;
            } else {
                floating += 0.01;
            }
            poseStack.popPose();
        }

        if (tileEntityIn.element == EnumElements.NONE) {
            rPlus = 0;
            gPlus = 0;
            bPlus = 0;
        }
    }

    private boolean changeColor(TileEntityPurfiedCauldron tileEntityIn, float r, float g, float b) {
        IElement element = tileEntityIn.element;
        if (!(element instanceof EnumElements)) {
            return false;
        }
        int container = Math.min(tileEntityIn.container, 5000);
        float level = container / 5000.0f;
        switch ((EnumElements) element) {
            case GOLD:
                rPlus = r + (255 - r) * level;
                gPlus = g + (255 - g) * level;
                bPlus = b + (137 - b) * level;
                return true;
            case WOOD:
                rPlus = r + (0 - r) * level;
                gPlus = g + (214 - g) * level;
                bPlus = b + (24 - b) * level;
                return true;
            case AQUA:
                rPlus = r + (0 - r) * level;
                gPlus = g + (237 - g) * level;
                bPlus = b + (255 - b) * level;
                return true;
            case FIRE:
                rPlus = r + (255 - r) * level;
                gPlus = g + (0 - g) * level;
                bPlus = b + (8 - b) * level;
                return true;
            case EARTH:
                rPlus = r + (153 - r) * level;
                gPlus = g + (143 - g) * level;
                bPlus = b + (79 - b) * level;
                return true;
            default:
                return false;
        }
    }

}
