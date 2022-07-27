package teamHTBP.vida.client.modelRender.tileEntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import teamHTBP.vida.common.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.core.element.IElement;

public class TileEntityRenderPurfiedCauldron extends TileEntityRenderer<TileEntityPurfiedCauldron> {
    private float floating = 0; //物品悬浮增量
    private float rPlus = 0; //r变色增量
    private float gPlus = 0; //b变色增量
    private float bPlus = 0; //g变色增量

    public TileEntityRenderPurfiedCauldron(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private static int getFluidColor(World world, BlockPos pos, Fluid fluid) {
        if (fluid.isEquivalentTo(Fluids.WATER)) {
            return BiomeColors.getWaterColor(world, pos);
        }

        return fluid.getAttributes().getColor();
    }

    @Override
    public void render(TileEntityPurfiedCauldron tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.isWater) {
            matrixStackIn.push();
            //获取水的贴图
            Fluid fluid = Fluids.WATER.getStillFluid();
            BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
            ResourceLocation resourceLocation;
            int color;
            if (fluid.getAttributes().getStillTexture() != null) {
                resourceLocation = fluid.getAttributes().getStillTexture();
                color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), fluid);
            } else if (fluid.getAttributes().getFlowingTexture() != null) {  // In case that Still Texture don't exist
                resourceLocation = fluid.getAttributes().getFlowingTexture();
                color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), fluid);
            } else { // In case that no texture exist
                resourceLocation = Fluids.WATER.getAttributes().getStillTexture();
                color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), Fluids.WATER);
            }
            TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(resourceLocation);
            //获取水贴图在精灵图中的最大/小u,v位置
            float uMin = textureAtlasSprite.getMinU();
            float uMax = textureAtlasSprite.getMaxU();
            float vMin = textureAtlasSprite.getMinV();
            float vMax = textureAtlasSprite.getMaxV();
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
            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
            //首先获取水位高度
            float waterlevel = tileEntityIn.container / 110814.2f;
            matrixStackIn.translate(0, 0.3F + waterlevel, 0);
            //设置贴图绘制方式
            IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getTranslucent());
            //绘制
            buffer.pos(matrix4f, 0.1F, 0.1F, 0.1F).color(r, g, b, a).tex(uMin, vMin).overlay(0, 0).lightmap(light).normal(0, 1, 0).endVertex();
            buffer.pos(matrix4f, 0.1F, 0.1F, 0.9F).color(r, g, b, a).tex(uMin, vMax).overlay(0, 0).lightmap(light).normal(0, 1, 0).endVertex();
            buffer.pos(matrix4f, 0.9F, 0.1F, 0.9F).color(r, g, b, a).tex(uMax, vMax).overlay(0, 0).lightmap(light).normal(0, 1, 0).endVertex();
            buffer.pos(matrix4f, 0.9F, 0.1F, 0.1F).color(r, g, b, a).tex(uMax, vMin).overlay(0, 0).lightmap(light).normal(0, 1, 0).endVertex();
            //结束绘制
            Minecraft.getInstance().getProfiler().endSection();
            matrixStackIn.pop();
        }

        if (!tileEntityIn.meltItem.isEmpty()) {
            matrixStackIn.push();
            double floatingLevel = 0.1 * Math.sin(floating);
            matrixStackIn.translate(0.5f, 1.3f + floatingLevel, 0.55f);
            matrixStackIn.scale(0.6f, 0.6f, 0.6f);

            TileEntityRendererDispatcher dispatcher = this.renderDispatcher;
            Quaternion quaternion = dispatcher.renderInfo.getRotation();
            float f3 = MathHelper.lerp(partialTicks, 0, 0);
            quaternion.multiply(Vector3f.XP.rotation(f3));

            matrixStackIn.rotate(quaternion);
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.meltItem, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.meltItem, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);

            if (floating >= 2 * Math.PI) {
                floating = 0;
            } else {
                floating += 0.01;
            }
            matrixStackIn.pop();
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
        int container = tileEntityIn.container < 5000 ? tileEntityIn.container : 5000;
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
